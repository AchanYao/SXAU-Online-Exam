package com.achan.exam.admin.task;

import com.achan.exam.common.entity.*;
import com.achan.exam.common.exception.InsertUserException;
import com.achan.exam.common.service.impl.TeacherServiceImpl;
import com.achan.exam.common.service.impl.UserRoleServiceImpl;
import com.achan.exam.common.service.impl.UserServiceImpl;
import com.achan.exam.common.task.ImportDataTask;
import com.achan.exam.common.vo.enumerate.RoleEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Achan
 * @date 2020/1/17
 */
@Data
@Slf4j
@Component
@Accessors(chain = true)
public class ImportTeacherTask extends QuartzJobBean implements ImportDataTask<Teacher> {

    private Stream<String> data;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private TeacherServiceImpl teacherService;

    @Override
    @Transactional(rollbackFor = InsertUserException.class)
    public List<Teacher> importData() {
        Supplier<Stream<String>> streamSupplier = () -> data;
        List<Teacher> teachers = new ArrayList<>();
        streamSupplier.get().skip(1).collect(Collectors.toList()).forEach(s -> {
            String[] line = s.split("\t");
            User user = new User()
                    .setUsername(line[0])
                    .setPassword(passwordEncoder.encode(line[0]))
                    .setActive(1);
            if (!userService.save(user)) {
                throw new InsertUserException();
            }
            user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername()));
            Teacher teacher = new Teacher()
                    .setNumber(line[0])
                    .setName(line[1])
                    .setUserId(user.getId());
            if (!teacherService.save(teacher)) {
                throw new InsertUserException();
            }
            teacher = teacherService.getOne(new QueryWrapper<Teacher>().lambda().eq(Teacher::getUserId, user.getId()));
            UserRole userRole = new UserRole()
                    .setRoleId(RoleEnum.STUDENT.getId())
                    .setUserId(user.getId());
            userRoleService.save(userRole);
            teachers.add(teacher);
        });
        return teachers;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        importData();
    }
}
