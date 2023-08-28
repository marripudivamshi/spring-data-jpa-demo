package com.example.springdatajpademo.dao;

import java.util.List;

import com.example.springdatajpademo.entity.Course;
import com.example.springdatajpademo.entity.Instructor;
import com.example.springdatajpademo.entity.InstructorDetail;
import com.example.springdatajpademo.entity.Student;

public interface InstructorDAO {
	void save(Instructor instructor);
	
	Instructor findInstructorById(int id);
	
	void deleteInstructorById(int id);

	InstructorDetail findInstructorDetailById(int id);

	void deleteInstructorDetailById(int id);

	List<Course> findCoursesInLazyInstructor(int id);

	Instructor findInstructorWithCoursesByIdJoinFetch(int id);

	void updateInstructor(Instructor instructor1);

	Course findCourseById(int id);

	void updateCourse(Course course1);

	void deleteCourseById(int id);

	void saveCourse(Course course);

	Course findCourseWithReviews(int id);

	Course findCourseAndStudentsByCourseId(int theId);

	Student findStudentsAndCoursesByStudentId(int theId);

	void updateStudent(Student student);

	void deleteStudentById(int id);
	
	
}
