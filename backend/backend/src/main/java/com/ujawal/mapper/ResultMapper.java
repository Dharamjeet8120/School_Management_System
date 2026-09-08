package com.ujawal.mapper;

import com.ujawal.dto.ResultDTO;
import com.ujawal.entity.Result;

public class ResultMapper {

	// Entity To DTO
	public static ResultDTO toDTO(Result result) {

		ResultDTO dto = new ResultDTO();

		dto.setResultId(result.getResultId());

		if (result.getStudent() != null) {
			dto.setStudentId(result.getStudent().getStudentId());
		}

		if (result.getExam() != null) {
			dto.setExamId(result.getExam().getExamId());
		}

		if (result.getSubject() != null) {
			dto.setSubjectId(result.getSubject().getSubjectId());
		}

		dto.setObtainedMarks(result.getObtainedMarks());
		dto.setTotalMarks(result.getTotalMarks());
		dto.setGrade(result.getGrade());
		dto.setPassStatus(result.getPassStatus());
		dto.setRemarks(result.getRemarks());

		return dto;
	}

	// DTO To Entity — student/exam/subject NOT set here, service layer resolves IDs
	public static Result toEntity(ResultDTO dto) {

		Result result = new Result();

		result.setResultId(dto.getResultId());
		result.setObtainedMarks(dto.getObtainedMarks());
		result.setTotalMarks(dto.getTotalMarks());
		result.setGrade(dto.getGrade());
		result.setPassStatus(dto.getPassStatus());
		result.setRemarks(dto.getRemarks());

		return result;
	}
}