package com.ujawal.service;

import java.util.List;

import com.ujawal.dto.StudentDTO;

public interface StudentService {

	StudentDTO saveStudent(StudentDTO studentDTO);

	StudentDTO updateStudent(Long studentId, StudentDTO studentDTO);

	StudentDTO getStudentById(Long studentId);

	List<StudentDTO> getAllStudents();

	void deleteStudent(Long studentId);

	StudentDTO getStudentByEmail(String email);

	StudentDTO getStudentByRollNumber(String rollNumber);

	List<StudentDTO> getStudentsByFirstName(String firstName);

	List<StudentDTO> getStudentsByLastName(String lastName);

	List<StudentDTO> getStudentsByGender(String gender);

	List<StudentDTO> getStudentsByStatus(String status);

	List<StudentDTO> getStudentsByClass(Long classId);

	List<StudentDTO> searchStudents(String keyword);

	boolean existsByEmail(String email);

	boolean existsByRollNumber(String rollNumber);

	long getTotalStudents();

	long countStudentsByStatus(String status);
}