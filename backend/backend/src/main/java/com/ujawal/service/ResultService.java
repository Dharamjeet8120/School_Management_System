package com.ujawal.service;

import java.util.List;

import com.ujawal.dto.ResultDTO;

public interface ResultService {

	ResultDTO saveResult(ResultDTO resultDTO);

	ResultDTO updateResult(Long resultId, ResultDTO resultDTO);

	ResultDTO getResultById(Long resultId);

	List<ResultDTO> getAllResults();

	void deleteResult(Long resultId);

	List<ResultDTO> getResultsByStudent(Long studentId);

	List<ResultDTO> getResultsByExam(Long examId);

	List<ResultDTO> getResultsBySubject(Long subjectId);

	List<ResultDTO> getResultsByGrade(String grade);

	List<ResultDTO> getPassedStudents();

	List<ResultDTO> getFailedStudents();

	List<ResultDTO> getStudentExamResults(Long studentId, Long examId);

	long getTotalResults();

	long countPassedStudents();
}