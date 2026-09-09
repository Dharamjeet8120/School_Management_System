package com.ujawal.service;

import java.util.List;

import com.ujawal.dto.TeacherDTO;

public interface TeacherService {

	TeacherDTO saveTeacher(TeacherDTO teacherDTO);

	TeacherDTO updateTeacher(Long teacherId, TeacherDTO teacherDTO);

	TeacherDTO getTeacherById(Long teacherId);

	List<TeacherDTO> getAllTeachers();

	void deleteTeacher(Long teacherId);

	TeacherDTO getTeacherByEmployeeId(String employeeId);

	TeacherDTO getTeacherByEmail(String email);

	List<TeacherDTO> getTeachersByFirstName(String firstName);

	List<TeacherDTO> getTeachersByLastName(String lastName);

	List<TeacherDTO> getTeachersByQualification(String qualification);

	List<TeacherDTO> getTeachersBySpecialization(String specialization);

	List<TeacherDTO> getTeachersByTeacherType(String teacherType);

	List<TeacherDTO> getTeachersBySubject(Long subjectId);

	List<TeacherDTO> getTeachersByCity(String city);

	List<TeacherDTO> getTeachersByState(String state);

	List<TeacherDTO> getActiveTeachers();

	List<TeacherDTO> getInactiveTeachers();

	boolean existsByEmployeeId(String employeeId);

	boolean existsByEmail(String email);

	long getTotalTeachers();

	long countActiveTeachers();
}