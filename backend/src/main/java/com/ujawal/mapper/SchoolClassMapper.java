package com.ujawal.mapper;

import com.ujawal.dto.SchoolClassDTO;
import com.ujawal.entity.SchoolClasss;

public class SchoolClassMapper {

	// Entity To DTO
	public static SchoolClassDTO toDTO(SchoolClasss schoolClass) {

		SchoolClassDTO dto = new SchoolClassDTO();

		dto.setClassId(schoolClass.getClassId());
		dto.setClassName(schoolClass.getClassName());
		dto.setSection(schoolClass.getSection());
		dto.setRoomNumber(schoolClass.getRoomNumber());
		dto.setCapacity(schoolClass.getCapacity());
		dto.setAcademicYear(schoolClass.getAcademicYear());

		if (schoolClass.getClassTeacher() != null) {
			dto.setTeacherId(schoolClass.getClassTeacher().getTeacherId());
		}

		return dto;
	}

	// DTO To Entity — classTeacher NOT set here, service layer resolves teacherId
	public static SchoolClasss toEntity(SchoolClassDTO dto) {

		SchoolClasss schoolClass = new SchoolClasss();

		schoolClass.setClassId(dto.getClassId());
		schoolClass.setClassName(dto.getClassName());
		schoolClass.setSection(dto.getSection());
		schoolClass.setRoomNumber(dto.getRoomNumber());
		schoolClass.setCapacity(dto.getCapacity());
		schoolClass.setAcademicYear(dto.getAcademicYear());

		return schoolClass;
	}
}