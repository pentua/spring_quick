package com.xinchang.springJPA.api;


import com.xinchang.springJPA.exception.CourseIDInvalidException;
import com.xinchang.springJPA.exception.StudentIDInvalidIdException;
import com.xinchang.springJPA.exception.StudentNameEmptyException;
import com.xinchang.springJPA.model.Student;
import com.xinchang.springJPA.service.StudentService;
import jakarta.persistence.GeneratedValue;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping(path = "register/{sID}/{cID}")
  public ResponseEntity<String> registerCourse(@PathVariable("sID") Long studentID,
      @PathVariable("cID") Long courseID) {
    try {
      Student student = studentService.registerCourse(studentID, courseID);
      return ResponseEntity.ok("Registered course " + String.valueOf(courseID) +
          " to student " + String.valueOf(studentID));
    } catch (StudentIDInvalidIdException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (CourseIDInvalidException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
