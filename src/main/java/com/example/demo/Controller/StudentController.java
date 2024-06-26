package com.example.demo.Controller;

import com.example.demo.DTO.Response.CustomBaseResponse;
import com.example.demo.Custom.EmailAlreadyInUseException;
import com.example.demo.Custom.UsernameAlreadyInUseException;
import com.example.demo.DTO.Request.UserRequest;
import com.example.demo.DTO.Response.StudentResponse;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;

    @Autowired
    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents(@RequestParam(required = false) String studentGroup) {
        List<StudentResponse> students = (studentGroup != null) ?
                studentService.getStudentsByStudentGroup(studentGroup) :
                studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/register/student")
    public ResponseEntity<Void> registerNewStudent(@RequestBody StudentEntity student) {
        studentService.addNewStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long studentId) {
        Optional<StudentResponse> studentResponseOptional = studentService.getStudentResponseById(studentId);
        return studentResponseOptional
                .map(studentResponse -> new ResponseEntity<>(studentResponse, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/profile/{studentId}")
    public ResponseEntity<StudentResponse> getUserProfileById(@PathVariable Long studentId) {
        Optional<StudentResponse> studentResponseOptional = studentService.getStudentResponseById(studentId);
        return studentResponseOptional
                .map(studentResponse -> new ResponseEntity<>(studentResponse, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create-account")
    public ResponseEntity<CustomBaseResponse<Void>> createAccount(@RequestBody UserRequest userRequest) {
        try {
            // Attempt to create the user account
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userRequest.getUsername());
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setPassword(userRequest.getPassword());

            UserEntity createdUser = userService.createUser(userEntity);

            // If successful, return a success response
            CustomBaseResponse<Void> response = new CustomBaseResponse<>("00", "Account created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EmailAlreadyInUseException e) {
            // If the email is already in use, return a custom error response
            CustomBaseResponse<Void> response = new CustomBaseResponse<>("409", "Email already in use");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (UsernameAlreadyInUseException e) {
            // If the username is already in use, return a custom error response
            CustomBaseResponse<Void> response = new CustomBaseResponse<>("409", "Username already in use");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            // For any other exceptions, return an internal server error response
            CustomBaseResponse<Void> response = new CustomBaseResponse<>("500", "Internal Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
