package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("from Student")
	List<Student> findAllStudents();
	@Query("select st.firstName,st.lastName from Student st")
	List<Object[]> findAllStudentPartialData();
	@Query("from Student where firstName=:firstName")
	List<Student> findAllStudentFirstName(@Param("firstName") String firstName);
	@Query("from Student where score>:min and score<:max")
	List<Student> findStudentForGivenScore(@Param("min") int min,@Param("max") int max);
	@Modifying
	@Query("delete from Student where firstName=:firstName ")
	void deleteStudentByFirstname(@Param("firstName") String firstName);
	
	//native queries
	@Query(value="select * from student",nativeQuery=true)
	List<Student> findAllStudentNQ();
	@Query(value="select * from student where fname=:firstName",nativeQuery=true)
	List<Student> findByStudentFirstNameNQ(@Param("firstName") String firstName);

}
