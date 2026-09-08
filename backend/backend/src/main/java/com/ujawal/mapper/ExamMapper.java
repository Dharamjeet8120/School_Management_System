package com.ujawal.mapper;

import com.ujawal.dto.ExamDTO;
import com.ujawal.entity.Exam;

public class ExamMapper {

	// Entity To DTO
	public static ExamDTO toDTO(Exam exam) {

		ExamDTO dto = new ExamDTO();

		dto.setExamId(exam.getExamId());
		dto.setExamName(exam.getExamName());
		dto.setExamType(exam.getExamType());
		dto.setExamDate(exam.getExamDate());
		dto.setTotalMarks(exam.getTotalMarks());
		dto.setPassingMarks(exam.getPassingMarks());
		dto.setAcademicYear(exam.getAcademicYear());
		dto.setDescription(exam.getDescription());

		return dto;
	}

	// DTO To Entity
	public static Exam toEntity(ExamDTO dto) {

		Exam exam = new Exam();

		exam.setExamId(dto.getExamId());
		exam.setExamName(dto.getExamName());
		exam.setExamType(dto.getExamType());
		exam.setExamDate(dto.getExamDate());
		exam.setTotalMarks(dto.getTotalMarks());
		exam.setPassingMarks(dto.getPassingMarks());
		exam.setAcademicYear(dto.getAcademicYear());
		exam.setDescription(dto.getDescription());

		return exam;
	}
}