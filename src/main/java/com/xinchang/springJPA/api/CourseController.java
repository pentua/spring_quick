package com.xinchang.springJPA.api;


import com.xinchang.springJPA.exception.CourseYearInvalidException;
import com.xinchang.springJPA.model.Course;
import com.xinchang.springJPA.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/course")
public class CourseController {
  private CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public List<Course> getAllC() {
    return courseService.getAllCourses();
  }

  @PostMapping("/add")
  public ResponseEntity<String> addC(@RequestBody Course course) {
    try {
      Course result = courseService.addCourse(course);
      return ResponseEntity.ok("Added course " + result.toString());
    } catch(CourseYearInvalidException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
