package com.ujawal.mapper;

import com.ujawal.dto.SubjectDTO;
import com.ujawal.entity.Subject;

public class SubjectMapper {

	public static SubjectDTO toDTO(Subject subject) {

		SubjectDTO dto = new SubjectDTO();

		dto.setSubjectId(subject.getSubjectId());
		dto.setSubjectCode(subject.getSubjectCode());
		dto.setSubjectName(subject.getSubjectName());
		dto.setMaximumMarks(subject.getMaximumMarks());
		dto.setPassingMarks(subject.getPassingMarks());
		dto.setDescription(subject.getDescription());
		dto.setActive(subject.getActive());

		return dto;
	}

	public static Subject toEntity(SubjectDTO dto) {

		Subject subject = new Subject();

		subject.setSubjectId(dto.getSubjectId());
		subject.setSubjectCode(dto.getSubjectCode());
		subject.setSubjectName(dto.getSubjectName());
		subject.setMaximumMarks(dto.getMaximumMarks());
		subject.setPassingMarks(dto.getPassingMarks());
		subject.setDescription(dto.getDescription());
		subject.setActive(dto.getActive());

		return subject;
	}
}