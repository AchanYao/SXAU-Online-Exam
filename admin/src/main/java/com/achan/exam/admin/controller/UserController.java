package com.achan.exam.admin.controller;

import com.achan.exam.admin.task.ImportStudentTask;
import com.achan.exam.admin.task.ImportTeacherTask;
import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.common.entity.Student;
import com.achan.exam.common.entity.Teacher;
import com.achan.exam.common.entity.User;
import com.achan.exam.common.entity.UserRole;
import com.achan.exam.common.exception.ExamException;
import com.achan.exam.common.exception.InsertUserException;
import com.achan.exam.common.service.impl.StudentServiceImpl;
import com.achan.exam.common.service.impl.TeacherServiceImpl;
import com.achan.exam.common.service.impl.UserRoleServiceImpl;
import com.achan.exam.common.service.impl.UserServiceImpl;
import com.achan.exam.common.vo.ResultCodeEnum;
import com.achan.exam.common.vo.enumerate.RoleEnum;
import com.achan.exam.util.file.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author Achan
 * @date 2020/1/14
 */
@RestController
@RequestMapping("/api/users")
@Api("用户操作")
@BaseResponse
@Slf4j
@Transactional(rollbackFor = Exception.class)
@PreAuthorize("hasRole('admin')")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private ImportStudentTask importStudentTask;
    @Autowired
    private ImportTeacherTask importTeacherTask;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/students/add")
    @ApiOperation("向指定班级添加学生")
    public Student addStudent(@RequestBody @Validated Student student) {
        User user = new User().setUsername(student.getNumber()).setPassword(passwordEncoder.encode(student.getNumber()));
        boolean result;
        result = userService.save(user);
        result = result && userRoleService.save(new UserRole().setUserId(user.getId()).setRoleId(RoleEnum.STUDENT.getId()));
        result = result && studentService.save(student.setUserId(user.getId()).setClazzId(student.getClazzId()));
        if (!result) {
            throw new ExamException(ResultCodeEnum.DATA_SAVE_ERROR);
        }
        return student;
    }

    @PutMapping("/change-status/{id}")
    public boolean changeStatus(@PathVariable int id) {
        User user = userService.getById(id);
        user.setEnable(user.getEnable() == 1 ? 0 : 1);
        return userService.updateById(user);
    }

    @PostMapping("/teachers/add")
    @ApiOperation("添加教师")
    public Teacher addTeacher(@RequestBody @Validated Teacher teacher) {
        User user = new User().setUsername(teacher.getNumber()).setPassword(passwordEncoder.encode(teacher.getNumber()));
        boolean result = userService.save(user);
        if (!result) {
            throw new InsertUserException();
        }
        result = userRoleService.save(new UserRole().setUserId(user.getId()).setRoleId(RoleEnum.TEACHER.getId()));
        result = result && teacherService.save(teacher.setUserId(user.getId()));
        if (!result) {
            throw new InsertUserException();
        }
        return teacher;
    }

    @DeleteMapping("/students/delete/{id}")
    @ApiOperation("删除指定学生")
    public boolean deleteStudent(@PathVariable int id) {
        Student student = studentService.getById(id);
        return userService.removeById(student.getUserId());
    }

    @DeleteMapping("/teachers/delete/{id}")
    @ApiOperation("删除教师")
    public boolean deleteTeacher(@PathVariable int id) {
        Teacher teacher = teacherService.getById(id);
        return userService.removeById(teacher.getUserId());
    }

    @PutMapping("/students/update")
    @ApiOperation("修改学生信息")
    public boolean updateStudent(@RequestBody Student student) {
        return studentService.updateById(student);
    }

    @PutMapping("/teachers/update")
    @ApiOperation("修改教师信息")
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateById(teacher);
    }

    @PostMapping("/students/import")
    @ApiOperation("学生信息导入")
    public List<Student> importStudent(@RequestPart MultipartFile file) throws IOException {
        File newFile = saveDataFile(file);
        Path filePath = newFile.toPath();
        // try-with-resources
        var data = Files.lines(filePath, Charset.forName("GBK"));
        return importStudentTask.setData(data).importData();
    }

    @PostMapping("/teachers/import")
    @ApiOperation("教师信息导入")
    public List<Teacher> importTeacher(@RequestPart MultipartFile file) throws IOException {
        File newFile = saveDataFile(file);
        Path filePath = newFile.toPath();
        // try-with-resources
        var data = Files.lines(filePath, Charset.forName("GBK"));
        return importTeacherTask.setData(data).importData();
    }

    @GetMapping("/count")
    @ApiOperation("用户数")
    public int userCount() {
        return userService.count();
    }

    private File saveDataFile(MultipartFile file) throws IOException {
        File folder = new File("data/csv/");
        return FileUtil.transferTo(file, folder.getAbsoluteFile());
    }

    private JobDetail buildJobDetail(Stream<String> data) {
        JobDataMap map = new JobDataMap();
        map.put("data", data);

        return JobBuilder.newJob(ImportStudentTask.class).usingJobData(map)
                .withIdentity(UUID.randomUUID().toString(), "import-student").build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "import-student-trigger")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(1)).build();
    }
}
