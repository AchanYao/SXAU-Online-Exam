package com.achan.exam.admin.task;

import com.achan.exam.common.entity.Student;
import com.achan.exam.common.entity.User;
import com.achan.exam.common.entity.UserRole;
import com.achan.exam.common.exception.InsertUserException;
import com.achan.exam.common.service.impl.StudentServiceImpl;
import com.achan.exam.common.service.impl.UserRoleServiceImpl;
import com.achan.exam.common.service.impl.UserServiceImpl;
import com.achan.exam.common.task.ImportDataTask;
import com.achan.exam.common.vo.enumerate.RoleEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
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
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@Component
@Accessors(chain = true)
public class ImportStudentTask extends QuartzJobBean implements ImportDataTask<Student> {

    private Stream<String> data;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Override
    @Transactional(rollbackFor = InsertUserException.class)
    public List<Student> importData() {
        Supplier<Stream<String>> streamSupplier = () -> data;
        List<Student> students = new ArrayList<>();
        streamSupplier.get().skip(1).collect(Collectors.toList()).forEach(s -> {
            String[] line = s.split("\t");
            User user = new User()
                    .setUsername(line[0])
                    .setPassword(passwordEncoder.encode(line[0]))
                    .setEnable(1);
            if (!userService.save(user)) {
                throw new InsertUserException();
            }
            user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername()));
            Student student = new Student()
                    .setNumber(line[0])
                    .setName(line[1])
                    .setClazzId(Integer.parseInt(line[2]))
                    .setUserId(user.getId());
            if (!studentService.save(student)) {
                throw new InsertUserException();
            }
            student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, user.getId()));
            UserRole userRole = new UserRole()
                    .setRoleId(RoleEnum.STUDENT.getId())
                    .setUserId(user.getId());
            userRoleService.save(userRole);
            students.add(student);
        });
        return students;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        importData();
    }
}
