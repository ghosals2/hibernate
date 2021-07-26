package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Student;
import com.example.demo.repository.StudentRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JpqlandnativeQueriesApplicationTests {
	
	@Autowired
	StudentRepository repo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testStudent() {
		Student s1=new Student();
		s1.setFirstName("John");
		s1.setLastName("Ferguson");
		s1.setScore(88);
		repo.save(s1);
		Student s2=new Student();
		s2.setFirstName("Bill");
		s2.setLastName("Gates");
		s2.setScore(75);
		
		repo.save(s2);
	}
	@Test
	public void testfindAllStudent() {
		repo.findAllStudents().forEach(student->System.out.println(student));
	}
	@Test
	public void testfindAllStudentPartial() {
		repo.findAllStudentPartialData().forEach(student->System.out.println(student[0] + " ," + student[1]));
	}
	@Test
	public void testfindAllStudentFirstName() {
		repo.findAllStudentFirstName("John").forEach(student->System.out.println(student));
	}
	@Test
	public void testfindStudentForGivenScores() {
		repo.findStudentForGivenScore(80, 90).forEach(student->System.out.println(student));
	}
	@Test
	@Transactional
	public void testdeleteStudentByFirstName() {
		repo.deleteStudentByFirstname("John");
	}
	@Test
	public void testfindAllStudentNQ() {
		repo.findAllStudentNQ().forEach(student->System.out.println(student));
	}
	@Test
	public void testfindAllStudentFirstNameNQ() {
		repo.findByStudentFirstNameNQ("Bill").forEach(student->System.out.println(student));
	}

}
