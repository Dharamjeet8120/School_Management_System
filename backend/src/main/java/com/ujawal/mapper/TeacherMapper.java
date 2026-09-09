package com.ujawal.mapper;

import com.ujawal.dto.TeacherDTO;
import com.ujawal.entity.Teacher;

public class TeacherMapper {

    // Entity To DTO
    public static TeacherDTO toDTO(Teacher teacher) {

        TeacherDTO dto = new TeacherDTO();

        dto.setTeacherId(teacher.getTeacherId());
        dto.setEmployeeId(teacher.getEmployeeId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setGender(teacher.getGender());
        dto.setDateOfBirth(teacher.getDateOfBirth());
        dto.setEmail(teacher.getEmail());
        dto.setMobileNumber(teacher.getMobileNumber());
        dto.setAadhaarNumber(teacher.getAadhaarNumber());

        dto.setQualification(teacher.getQualification());
        dto.setSpecialization(teacher.getSpecialization());
        dto.setExperienceYears(teacher.getExperienceYears());
        dto.setJoiningDate(teacher.getJoiningDate());
        dto.setSalary(teacher.getSalary());
        dto.setTeacherType(teacher.getTeacherType());

        if (teacher.getSubject() != null) {
            dto.setSubjectId(teacher.getSubject().getSubjectId());
        }

        dto.setClassTeacherOf(teacher.getClassTeacherOf());

        dto.setAddressLine1(teacher.getAddressLine1());
        dto.setAddressLine2(teacher.getAddressLine2());
        dto.setCity(teacher.getCity());
        dto.setState(teacher.getState());
        dto.setPincode(teacher.getPincode());

        dto.setEmergencyContactName(teacher.getEmergencyContactName());
        dto.setEmergencyContactNumber(teacher.getEmergencyContactNumber());
        dto.setRelationship(teacher.getRelationship());

        dto.setBankName(teacher.getBankName());
        dto.setAccountNumber(teacher.getAccountNumber());
        dto.setIfscCode(teacher.getIfscCode());

        dto.setActive(teacher.getActive());

        return dto;
    }

    // DTO To Entity — subject NOT set here, service layer resolves subjectId
    public static Teacher toEntity(TeacherDTO dto) {

        Teacher teacher = new Teacher();

        teacher.setTeacherId(dto.getTeacherId());
        teacher.setEmployeeId(dto.getEmployeeId());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setGender(dto.getGender());
        teacher.setDateOfBirth(dto.getDateOfBirth());
        teacher.setEmail(dto.getEmail());
        teacher.setMobileNumber(dto.getMobileNumber());
        teacher.setAadhaarNumber(dto.getAadhaarNumber());

        teacher.setQualification(dto.getQualification());
        teacher.setSpecialization(dto.getSpecialization());
        teacher.setExperienceYears(dto.getExperienceYears());
        teacher.setJoiningDate(dto.getJoiningDate());
        teacher.setSalary(dto.getSalary());
        teacher.setTeacherType(dto.getTeacherType());

        teacher.setClassTeacherOf(dto.getClassTeacherOf());

        teacher.setAddressLine1(dto.getAddressLine1());
        teacher.setAddressLine2(dto.getAddressLine2());
        teacher.setCity(dto.getCity());
        teacher.setState(dto.getState());
        teacher.setPincode(dto.getPincode());

        teacher.setEmergencyContactName(dto.getEmergencyContactName());
        teacher.setEmergencyContactNumber(dto.getEmergencyContactNumber());
        teacher.setRelationship(dto.getRelationship());

        teacher.setBankName(dto.getBankName());
        teacher.setAccountNumber(dto.getAccountNumber());
        teacher.setIfscCode(dto.getIfscCode());

        teacher.setActive(dto.getActive());

        return teacher;
    }
}