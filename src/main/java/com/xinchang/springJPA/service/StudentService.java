package com.xinchang.springJPA.service;

import com.xinchang.springJPA.dao.StudentDao;
import com.xinchang.springJPA.exception.StudentIDInvalidIdException;
import com.xinchang.springJPA.exception.StudentNameEmptyException;
import com.xinchang.springJPA.model.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentDao dao;

  @Autowired
  public StudentService(StudentDao dao) {
    this.dao = dao;
  }

  public Student saveStudent(Student student) {
    if(student.getName().isEmpty()) throw new StudentNameEmptyException("Student Name is empty");
    return dao.save(student);
  }

  public Student updateStudent(Student student) {
    if(student.getId()==null
        || !dao.existsById(student.getId())) throw new StudentIDInvalidIdException("Student ID is invalid");
    return dao.save(student);
  }

  public List<Student> getAllStudents() {
    return (List<Student>) dao.findAll();
  }

  public Optional<Student> getStudentById(Long id) {
    return dao.findById(id);
  }

}
