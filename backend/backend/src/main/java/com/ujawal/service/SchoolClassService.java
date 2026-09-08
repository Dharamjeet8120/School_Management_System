package com.ujawal.service;

import java.util.List;

import com.ujawal.dto.SchoolClassDTO;

public interface SchoolClassService {

	SchoolClassDTO saveClass(SchoolClassDTO schoolClassDTO);

	SchoolClassDTO updateClass(Long classId, SchoolClassDTO schoolClassDTO);

	SchoolClassDTO getClassById(Long classId);

	List<SchoolClassDTO> getAllClasses();

	void deleteClass(Long classId);

	SchoolClassDTO getClassByNameAndSection(String className, String section);

	List<SchoolClassDTO> getClassesByName(String className);

	List<SchoolClassDTO> getClassesBySection(String section);

	List<SchoolClassDTO> getClassesByAcademicYear(String academicYear);

	List<SchoolClassDTO> getClassesByTeacher(Long teacherId);

	boolean existsByClassNameAndSection(String className, String section);

	long getTotalClasses();

	long countClassesByAcademicYear(String academicYear);
}