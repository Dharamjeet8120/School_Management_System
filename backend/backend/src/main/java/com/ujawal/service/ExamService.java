package com.ujawal.service;

import java.time.LocalDate;
import java.util.List;

import com.ujawal.dto.ExamDTO;
import com.ujawal.entity.ExamType;

public interface ExamService {

	ExamDTO saveExam(ExamDTO examDTO);

	ExamDTO updateExam(Long examId, ExamDTO examDTO);

	ExamDTO getExamById(Long examId);

	List<ExamDTO> getAllExams();

	void deleteExam(Long examId);

	List<ExamDTO> getExamsByName(String examName);

	List<ExamDTO> getExamsByType(ExamType examType);

	List<ExamDTO> getExamsByDate(LocalDate examDate);

	List<ExamDTO> getExamsByAcademicYear(String academicYear);

	List<ExamDTO> getExamsBetweenDates(LocalDate startDate, LocalDate endDate);

	long getTotalExams();

	long countExamsByType(ExamType examType);
}