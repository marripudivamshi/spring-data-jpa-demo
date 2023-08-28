package com.example.springdatajpademo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpademo.entity.Course;

public interface CoursedataJpaRepository extends JpaRepository<Course, Integer>{

}
