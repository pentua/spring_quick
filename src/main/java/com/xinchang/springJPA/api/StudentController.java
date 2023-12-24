package com.xinchang.springJPA.api;


import com.xinchang.springJPA.exception.StudentNameEmptyException;
import com.xinchang.springJPA.model.Student;
import com.xinchang.springJPA.service.StudentService;
import jakarta.persistence.GeneratedValue;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/student")
public class StudentController {

  private StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public List<Student> getALlS() {
    return studentService.getAllStudents();
  }

  @PostMapping("/add")
  public ResponseEntity<String> addS(@RequestBody Student student) {
    try {
      Student result = studentService.saveStudent(student);
      return ResponseEntity.ok("Added student " + result.toString());
    } catch (StudentNameEmptyException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
