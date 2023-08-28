package com.example.springdatajpademo;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springdatajpademo.dao.CoursedataJpaRepository;
import com.example.springdatajpademo.dao.InstructorDAO;
import com.example.springdatajpademo.entity.Course;
import com.example.springdatajpademo.entity.Instructor;
import com.example.springdatajpademo.entity.InstructorDetail;
import com.example.springdatajpademo.entity.Review;
import com.example.springdatajpademo.entity.Student;

@SpringBootApplication
public class SpringDataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CoursedataJpaRepository coursedataJpaRepository) {
		return runner -> {
			System.out.println("Welcome to hibernate many to many demo");
			
			createCourseAndStudents(coursedataJpaRepository);
			
			//deleteCourse(coursedataJpaRepository);
			
			//findCourse(coursedataJpaRepository);
				
		};
	}
	
	private void findCourse(CoursedataJpaRepository coursedataJpaRepository) {
		int id = 11;
		Optional<Course> course = coursedataJpaRepository.findById(id);
		System.out.println(course);
		System.out.println("Course found successfully");
		
	}

	private void deleteStudent(InstructorDAO instructorDAO) {
		int id = 1;
		instructorDAO.deleteStudentById(id);
		System.out.println("Student deleted successfully");
	}


	private void addCoursesToStudent(InstructorDAO instructorDAO) {
		int theId = 1;
		Student student = instructorDAO.findStudentsAndCoursesByStudentId(theId);
		
		Course course1 = new Course("rrr");
		Course course2 = new Course("bahubali");
		
		student.addCourse(course2);
		student.addCourse(course1);
		
		instructorDAO.updateStudent(student);
		
		System.out.println("adding courses to student done");
		
	}


	private void findStudentsAndCourses(InstructorDAO instructorDAO) {
		int theId = 1;
		Student student = instructorDAO.findStudentsAndCoursesByStudentId(theId);
		
		System.out.println("course found : "+student);
		System.out.println("students found : "+student.getCourses());
		
		System.out.println("finding done");
		
	}


	private void findCourseAndStudents(InstructorDAO instructorDAO) {
		int theId = 10;
		Course course = instructorDAO.findCourseAndStudentsByCourseId(theId);
		
		System.out.println("course found : "+course);
		System.out.println("students found : "+course.getStudents());
		
		System.out.println("finding done");
		
	}


	private void createCourseAndStudents(CoursedataJpaRepository coursedataJpaRepository) {
		Course course1 = new Course("balu abcdefg");
		
		Student student1 = new Student("vamshi", "marripudi", "vamshi.marripudi@fmr.com");
		Student student2 = new Student("krishna", "marripudi", "krishna.marripudi@fmr.com");
		
		course1.addStudent(student1);
		course1.addStudent(student2);
		
		System.out.println("the course is "+course1);
		System.out.println("the students being added to course are "+course1.getStudents());
		
		coursedataJpaRepository.save(course1);
		
		System.out.println("saving course and adding students to it, done");
		
	}
	
	


	private void deleteCourseAlongWithReviews(InstructorDAO instructorDAO) {
		int id =10;
		instructorDAO.deleteCourseById(id);
		System.out.println("course along with reviews deleted successfully");
		
	}

	private void findCourseAlongWithReviews(InstructorDAO instructorDAO) {
		int id =10;
		Course course = instructorDAO.findCourseWithReviews(id);
		System.out.println("found course : " +course);
		System.out.println("found reviews of course" +course.getReviews());
		
	}

	private void createCourseAndReviews(InstructorDAO instructorDAO) {
		Course course1 = new Course("balu abcdefg");
		Review review1 = new Review("good");
		Review review2 = new Review("bad");
		Review review3 = new Review("worst");
		
		course1.addReview(review1);
		course1.addReview(review2);
		course1.addReview(review3);
		
		instructorDAO.saveCourse(course1);
		System.out.println("course and reviews saved successfully");
	}

	private void deleteCourse(CoursedataJpaRepository coursedataJpaRepository) {
		int id = 10;
		coursedataJpaRepository.deleteById(id);
		System.out.println("Course deleted successfully");
		
	}

	private void deleteInstructor(InstructorDAO instructorDAO) {
		int id = 1;
		instructorDAO.deleteInstructorById(id);
		System.out.println("instructor deleted successfully");
	}

	private void updateCourse(InstructorDAO instructorDAO) {
		int id = 10;
		Course course1 = instructorDAO.findCourseById(id);
		
		System.out.println("found Course : " +course1);
		
		course1.setTitle("developer beeps");
		
		instructorDAO.updateCourse(course1);
		System.out.println("updated course successfully");
		
	}
	
	private void updateInstructor(InstructorDAO instructorDAO) {
		int id = 1;
		Instructor instructor1 = instructorDAO.findInstructorById(id);
		
		System.out.println("found instructor : " +instructor1);
		
		instructor1.setLastName("developer");
		
		instructorDAO.updateInstructor(instructor1);
		System.out.println("updated instructor successfully");
		
	}

	

	private void createInstructorWithCourses(InstructorDAO instructorDAO) {
		Instructor instructor1 = new Instructor ("vamshi", "marripudi", "vamshi.marripudi@fmr.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("I love coding", "guitar");
		
		instructor1.setInstructorDetail(instructorDetail1);
		
		Course course1 = new Course("balu abcdefg");
		Course course2 = new Course("billa");
		Course course3 = new Course("darling");
		
		instructor1.add(course1);
		instructor1.add(course2);
		instructor1.add(course3);
		
		instructorDAO.save(instructor1);
		System.out.println("saved instructor along with courses");
		
		
	}
	
	private void findCoursesOfLazyInstructor(InstructorDAO instructorDAO) {
		int id = 1;
		Instructor instructor1 = instructorDAO.findInstructorById(id);
		
		System.out.println("found instructor : " +instructor1);
		
		List<Course> courses = instructorDAO.findCoursesInLazyInstructor(id);
		instructor1.setCourses(courses);
		
		System.out.println("Instructor's courses found : " +instructor1.getCourses());
		
	}
	
	private void findCoursesDirectlyFromLazyInstructor(InstructorDAO instructorDAO) {
		int id = 1;
		Instructor instructor1 = instructorDAO.findInstructorWithCoursesByIdJoinFetch(id);
		
		System.out.println("found instructor : " +instructor1);
		
		System.out.println("Instructor's courses found : " +instructor1.getCourses());
		
	}
	
	
	
	

	private void saveInstructor(InstructorDAO instructorDAO) {
		Instructor instructor1 = new Instructor ("vamshi", "marripudi", "vamshi.marripudi@fmr.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("I love coding", "guitar");
		
		Instructor instructor2 = new Instructor ("abbu", "paturi", "abbu.paturi@fmr.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("I love coding", "bike");
		
		Instructor instructor3 = new Instructor ("padmavathi", "velaga", "padmavathi.velaga@fmr.com");
		InstructorDetail instructorDetail3 = new InstructorDetail("I love coding", "chit chat");
		
		instructor1.setInstructorDetail(instructorDetail1);
		instructor2.setInstructorDetail(instructorDetail2);
		instructor3.setInstructorDetail(instructorDetail3);
		
		instructorDAO.save(instructor1);
		instructorDAO.save(instructor2);
		instructorDAO.save(instructor3);
		System.out.println("instructor & instructorDetail 1, 2, 3 saved successfully");
	}
	
	private void findInstructorById(InstructorDAO instructorDAO) {
		int id = 1;
		Instructor instructor1 = instructorDAO.findInstructorById(id);
		
		System.out.println("found instructor : " +instructor1);
		System.out.println("instructor detail found : " +instructor1.getInstructorDetail());
	}
	
	private void findInstructorDetailById(InstructorDAO instructorDAO) {
		int id =2;
		InstructorDetail instructorDetail = instructorDAO.findInstructorDetailById(id);
		
		System.out.println("Instructor detail found : " +instructorDetail);
	}
	
	private void deleteInstructorDetailById(InstructorDAO instructorDAO) {
		int id = 3;
		instructorDAO.deleteInstructorDetailById(id);
		System.out.println("deleted InstructorDetail By Id done");
		
	}

}
