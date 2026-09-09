package com.ujawal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujawal.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Optional<Teacher> findByEmployeeId(String employeeId);

	Optional<Teacher> findByEmail(String email);

	List<Teacher> findByFirstName(String firstName);

	List<Teacher> findByLastName(String lastName);

	List<Teacher> findByQualification(String qualification);

	List<Teacher> findBySpecialization(String specialization);

	List<Teacher> findByTeacherType(String teacherType);

	List<Teacher> findByActive(Boolean active);

	List<Teacher> findByCity(String city);

	List<Teacher> findByState(String state);

	List<Teacher> findBySubject_SubjectId(Long subjectId);

	boolean existsByEmployeeId(String employeeId);

	boolean existsByEmail(String email);

	long countByActive(Boolean active);
}