package com.xinchang.springJPA.service;

import com.xinchang.springJPA.dao.CourseDao;
import com.xinchang.springJPA.exception.CourseYearInvalidException;
import com.xinchang.springJPA.model.Course;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class CourseService {

  private CourseDao dao;

  @Autowired
  public CourseService(CourseDao dao) {
    this.dao = dao;
  }

  public List<Course> getAllCourses() {
    return (List<Course>) dao.findAll();
  }

  public Course addCourse(Course course) {
    int curYear = Calendar.getInstance().get(Calendar.YEAR);
    if(!course.getYear().equals(curYear)) throw
        new CourseYearInvalidException("The course year is invalid");
    //add duplication check here?
    return dao.save(course);
  }

}
