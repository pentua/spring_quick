package com.xinchang.springJPA.dao;

import com.xinchang.springJPA.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, Long> {

}
