package com.example.demo.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	//@Query("SELECT s FROM Student s WHERE s.rollNo = 1")
	@Query(
			  value = "SELECT * FROM student100 s WHERE s.roll_no = 1", 
			  nativeQuery = true)
	Collection<Student> findAllStudents();
	
	List<Student>findAll(Sort s);
	
	Page<Student> findAll(Pageable page);
	
	@Query(
			  value = "SELECT * FROM student100 ORDER BY roll_no", 
			  countQuery = "SELECT count(*) FROM student100", 
			  nativeQuery = true)
			Page<Student> findAllStudentsWithPagination(Pageable pageable);
	
	@Query("SELECT s FROM Student s WHERE s.name = ?1 and s.percent = ?2")
	Student findStudentByNameAndPercent(String name, Float percent);
	
	@Query(
			  value = "SELECT * FROM student100 s WHERE s.name = ?1", 
			  nativeQuery = true)
			Student findStudentByNameNative(String name);
	
	@Query("SELECT s FROM Student s WHERE s.name = :name and s.percent = :percent")
	Student findStudentByNameAndPercentNamed(@Param("name") String name, @Param("percent") Float percent);
	
	@Query(value="SELECT * FROM student100 s WHERE s.name = :name and s.percent = :percent",
		   nativeQuery=true)
	Student findStudentByNameAndPercentNamedNative(@Param("name") String name, @Param("percent") Float percent);
	
	@Query(value = "SELECT s FROM Student s WHERE s.name IN :names")
	List<Student> findStudentByNameList(@Param("names") Collection<String> names);
	
	@Transactional
	@Modifying
	@Query("update Student s set s.percent = :percent where s.name = :name")
	int updateStudentSetPercentForName(@Param("name") String name, @Param("percent") Float percent);
	
	@Transactional
	@Modifying
	@Query(value="update student100 s SET s.percent = :percent WHERE s.name = :name",
			nativeQuery=true)
	int updateStudentSetPercentForNameNative(@Param("name") String name, @Param("percent") Float percent);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO student100(name,percent) VALUES(:name,:percent)",
			nativeQuery=true)
	int insertStudent(@Param("name") String name, @Param("percent") Float percent);
}