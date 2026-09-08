package com.ujawal.mapper;

import com.ujawal.dto.StudentDTO;
import com.ujawal.entity.Student;

public class StudentMapper {

	// Entity To DTO
	public static StudentDTO toDTO(Student student) {

		StudentDTO dto = new StudentDTO();

		dto.setStudentId(student.getStudentId());
		dto.setAdmissionNumber(student.getAdmissionNumber());
		dto.setFirstName(student.getFirstName());
		dto.setLastName(student.getLastName());
		dto.setDateOfBirth(student.getDateOfBirth());
		dto.setGender(student.getGender());

		if (student.getSchoolClass() != null) {
			dto.setClassId(student.getSchoolClass().getClassId());
		}

		if (student.getClassTeacher() != null) {
			dto.setTeacherId(student.getClassTeacher().getTeacherId());
		}

		dto.setSection(student.getSection());
		dto.setRollNumber(student.getRollNumber());
		dto.setBloodGroup(student.getBloodGroup());
		dto.setReligion(student.getReligion());
		dto.setCaste(student.getCaste());
		dto.setNationality(student.getNationality());

		dto.setStudentEmail(student.getStudentEmail());
		dto.setStudentMobile(student.getStudentMobile());
		dto.setStatus(student.getStatus());

		dto.setFatherName(student.getFatherName());
		dto.setFatherMobile(student.getFatherMobile());
		dto.setFatherOccupation(student.getFatherOccupation());
		dto.setMotherName(student.getMotherName());
		dto.setMotherMobile(student.getMotherMobile());
		dto.setMotherOccupation(student.getMotherOccupation());
		dto.setGuardianName(student.getGuardianName());
		dto.setGuardianMobile(student.getGuardianMobile());

		dto.setAddressLine1(student.getAddressLine1());
		dto.setAddressLine2(student.getAddressLine2());
		dto.setCity(student.getCity());
		dto.setState(student.getState());
		dto.setPincode(student.getPincode());

		dto.setAdmissionDate(student.getAdmissionDate());
		dto.setAdmissionFee(student.getAdmissionFee());
		dto.setPreviousSchool(student.getPreviousSchool());
		dto.setAcademicYear(student.getAcademicYear());
		dto.setActive(student.getActive());

		dto.setBirthCertificateNumber(student.getBirthCertificateNumber());
		dto.setAadhaarNumber(student.getAadhaarNumber());
		dto.setTransferCertificateNumber(student.getTransferCertificateNumber());

		return dto;
	}

	// DTO To Entity — relations (schoolClass/classTeacher) NOT set here,
	// service layer resolves classId/teacherId since mapper has no repository
	// access
	public static Student toEntity(StudentDTO dto) {

		Student student = new Student();

		student.setStudentId(dto.getStudentId());
		student.setAdmissionNumber(dto.getAdmissionNumber());
		student.setFirstName(dto.getFirstName());
		student.setLastName(dto.getLastName());
		student.setDateOfBirth(dto.getDateOfBirth());
		student.setGender(dto.getGender());
		student.setSection(dto.getSection());
		student.setRollNumber(dto.getRollNumber());
		student.setBloodGroup(dto.getBloodGroup());
		student.setReligion(dto.getReligion());
		student.setCaste(dto.getCaste());
		student.setNationality(dto.getNationality());

		student.setStudentEmail(dto.getStudentEmail());
		student.setStudentMobile(dto.getStudentMobile());
		student.setStatus(dto.getStatus());

		student.setFatherName(dto.getFatherName());
		student.setFatherMobile(dto.getFatherMobile());
		student.setFatherOccupation(dto.getFatherOccupation());
		student.setMotherName(dto.getMotherName());
		student.setMotherMobile(dto.getMotherMobile());
		student.setMotherOccupation(dto.getMotherOccupation());
		student.setGuardianName(dto.getGuardianName());
		student.setGuardianMobile(dto.getGuardianMobile());

		student.setAddressLine1(dto.getAddressLine1());
		student.setAddressLine2(dto.getAddressLine2());
		student.setCity(dto.getCity());
		student.setState(dto.getState());
		student.setPincode(dto.getPincode());

		student.setAdmissionDate(dto.getAdmissionDate());
		student.setAdmissionFee(dto.getAdmissionFee());
		student.setPreviousSchool(dto.getPreviousSchool());
		student.setAcademicYear(dto.getAcademicYear());
		student.setActive(dto.getActive());

		student.setBirthCertificateNumber(dto.getBirthCertificateNumber());
		student.setAadhaarNumber(dto.getAadhaarNumber());
		student.setTransferCertificateNumber(dto.getTransferCertificateNumber());

		return student;
	}
}