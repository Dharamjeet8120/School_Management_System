package com.ujawal.service;

import java.util.List;

import com.ujawal.dto.SubjectDTO;

public interface SubjectService {

	SubjectDTO saveSubject(SubjectDTO subjectDTO);

	SubjectDTO updateSubject(Long subjectId, SubjectDTO subjectDTO);

	SubjectDTO getSubjectById(Long subjectId);

	List<SubjectDTO> getAllSubjects();

	void deleteSubject(Long subjectId);

	SubjectDTO getSubjectByCode(String subjectCode);

	SubjectDTO getSubjectByName(String subjectName);

	List<SubjectDTO> getActiveSubjects();

	List<SubjectDTO> getInactiveSubjects();

	boolean existsBySubjectCode(String subjectCode);

	boolean existsBySubjectName(String subjectName);

	long getTotalSubjects();

	long countActiveSubjects();
}