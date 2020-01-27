package com.achan.exam.common.service.impl;

import com.achan.exam.common.entity.Teacher;
import com.achan.exam.common.entity.TeacherGroup;
import com.achan.exam.common.entity.TeacherMidGroup;
import com.achan.exam.common.exception.ConnectionRelationException;
import com.achan.exam.common.exception.DataSaveException;
import com.achan.exam.common.mapper.TeacherGroupMapper;
import com.achan.exam.common.mapper.TeacherMapper;
import com.achan.exam.common.mapper.TeacherMidGroupMapper;
import com.achan.exam.common.service.ITeacherGroupService;
import com.achan.exam.common.vo.group.TeacherGroupOverview;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 教师组表 服务实现类
 * </p>
 *
 * @author Achan
 * @since 2020-01-13
 */
@Service
public class TeacherGroupServiceImpl extends ServiceImpl<TeacherGroupMapper, TeacherGroup> implements ITeacherGroupService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherMidGroupMapper teacherMidGroupMapper;

    public TeacherGroupOverview teacherGroupOverview(Integer id) {
        return this.baseMapper.details(id);
    }

    public Page<TeacherGroupOverview> pageOverview(Page<?> page) {
        return this.baseMapper.pageDetails(page);
    }

    public Page<TeacherGroupOverview> pageOverviewWithKeyword(Page<?> page, String keyword) {
        return this.baseMapper.pageDetailsWithKeyword(page, keyword);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveTeacherGroupDetails(TeacherGroup teacherGroup, List<Teacher> teachers) {
        Teacher teacher = teacherMapper.selectById(teacherGroup.getPrincipalId());
        if (teacher == null) {
            throw new ConnectionRelationException();
        }
        if (!this.save(teacherGroup)) {
            throw new DataSaveException();
        }
        teacherGroup = this.getOne(new QueryWrapper<TeacherGroup>()
                .lambda()
                .eq(TeacherGroup::getCourseId, teacherGroup.getCourseId())
                .eq(TeacherGroup::getPrincipalId, teacherGroup.getPrincipalId())
                .eq(TeacherGroup::getNumber, teacherGroup.getNumber())
        );
        List<TeacherMidGroup> groupList = new ArrayList<>(teachers.size());
        final Integer teacherGroupId = teacherGroup.getId();
        teachers.forEach(t -> {
            groupList.add(new TeacherMidGroup().setTeacherGroupId(teacherGroupId).setTeacherId(t.getId()));
        });
        return this.batchSaveMember(groupList);
    }

    private boolean batchSaveMember(Collection<TeacherMidGroup> entityList) {
        String sqlStatement = SqlHelper.table(TeacherMidGroup.class).getSqlStatement(SqlMethod.INSERT_ONE.getMethod());
        int size = entityList.size();
        this.executeBatch((sqlSession) -> {
            int i = 1;

            for(Iterator var6 = entityList.iterator(); var6.hasNext(); ++i) {
                TeacherMidGroup entity = (TeacherMidGroup) var6.next();
                sqlSession.insert(sqlStatement, entity);
                if (i % entityList.size() == 0 || i == size) {
                    sqlSession.flushStatements();
                }
            }
        });
        return true;
    }
}
