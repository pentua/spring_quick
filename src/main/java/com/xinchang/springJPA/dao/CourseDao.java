package com.xinchang.springJPA.dao;

import com.xinchang.springJPA.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course, Long> {

}
