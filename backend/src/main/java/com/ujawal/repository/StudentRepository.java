package com.ujawal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ujawal.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByRollNumber(String rollNumber);

	List<Student> findByFirstName(String firstName);

	List<Student> findByLastName(String lastName);

	List<Student> findBySchoolClass_ClassId(Long classId);

	List<Student> findByGender(String gender);

	// List<Student> findByStatus(String status);

	List<Student> findByStudentId(Long classId);

	@Query("""
			SELECT s FROM Student s
			WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
			   OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
			   OR LOWER(s.admissionNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))
			   OR LOWER(s.fatherName) LIKE LOWER(CONCAT('%', :keyword, '%'))
			   OR LOWER(s.studentMobile) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
	List<Student> searchStudents(@Param("keyword") String keyword);

	boolean existsByRollNumber(String rollNumber);

	// long countByStatus(String status);
	Optional<Student> findByStudentEmail(String studentEmail);

	boolean existsByStudentEmail(String studentEmail);

	List<Student> findByStatus(String status);

	long countByStatus(String status);
}