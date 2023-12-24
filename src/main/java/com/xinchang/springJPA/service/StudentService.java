package com.xinchang.springJPA.service;

import com.xinchang.springJPA.dao.CourseDao;
import com.xinchang.springJPA.dao.StudentDao;
import com.xinchang.springJPA.exception.CourseIDInvalidException;
import com.xinchang.springJPA.exception.StudentIDInvalidIdException;
import com.xinchang.springJPA.exception.StudentNameEmptyException;
import com.xinchang.springJPA.model.Course;
import com.xinchang.springJPA.model.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentDao studentDao;
  private CourseDao courseDao;

  @Autowired
  public StudentService(StudentDao studentDao, CourseDao courseDao) {
    this.studentDao = studentDao;
    this.courseDao = courseDao;
  }

  public Student saveStudent(Student student) {
    if(student.getName().isEmpty()) throw new StudentNameEmptyException("Student Name is empty");
    return studentDao.save(student);
  }

  public Student updateStudent(Student student) {
    if(student.getId()==null
        || !studentDao.existsById(student.getId())) throw new StudentIDInvalidIdException("Student ID is invalid");
    return studentDao.save(student);
  }

  public Student registerCourse(Long studentId, Long courseId) {
    if(studentId==null || !studentDao.existsById(studentId)) throw new StudentIDInvalidIdException("Student ID is invalid");
    if(courseId==null || !courseDao.existsById(courseId)) throw new CourseIDInvalidException("Course ID is invalid");
    Student student = getStudentById(studentId).get();
    Course course = courseDao.findById(courseId).get();
    student.setCourse(course);
    return studentDao.save(student);
  }

  public List<Student> getAllStudents() {
    return (List<Student>) studentDao.findAll();
  }

  public Optional<Student> getStudentById(Long id) {
    return studentDao.findById(id);
  }
}
