package com.ujawal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ujawal.dto.TeacherDTO;
import com.ujawal.entity.Subject;
import com.ujawal.entity.Teacher;
import com.ujawal.exception.ResourceNotFoundException;
import com.ujawal.mapper.TeacherMapper;
import com.ujawal.repository.SubjectRepository;
import com.ujawal.repository.TeacherRepository;
import com.ujawal.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	private final TeacherRepository teacherRepository;
	private final SubjectRepository subjectRepository;

	public TeacherServiceImpl(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
		this.teacherRepository = teacherRepository;
		this.subjectRepository = subjectRepository;
	}

	private void applySubject(Teacher teacher, TeacherDTO dto) {

		if (dto.getSubjectId() != null) {
			Subject subject = subjectRepository.findById(dto.getSubjectId()).orElseThrow(
					() -> new ResourceNotFoundException("Subject not found with ID: " + dto.getSubjectId()));
			teacher.setSubject(subject);
		}
	}

	@Override
	public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {

		Teacher teacher = TeacherMapper.toEntity(teacherDTO);
		applySubject(teacher, teacherDTO);

		return TeacherMapper.toDTO(teacherRepository.save(teacher));
	}

	@Override
	public TeacherDTO updateTeacher(Long teacherId, TeacherDTO teacherDTO) {

		Teacher existingTeacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacherId));

		existingTeacher.setFirstName(teacherDTO.getFirstName());
		existingTeacher.setLastName(teacherDTO.getLastName());
		existingTeacher.setEmail(teacherDTO.getEmail());
		existingTeacher.setMobileNumber(teacherDTO.getMobileNumber());
		existingTeacher.setQualification(teacherDTO.getQualification());
		existingTeacher.setSpecialization(teacherDTO.getSpecialization());
		existingTeacher.setExperienceYears(teacherDTO.getExperienceYears());
		existingTeacher.setSalary(teacherDTO.getSalary());
		existingTeacher.setTeacherType(teacherDTO.getTeacherType());
		existingTeacher.setActive(teacherDTO.getActive());

		applySubject(existingTeacher, teacherDTO);

		return TeacherMapper.toDTO(teacherRepository.save(existingTeacher));
	}

	@Override
	public TeacherDTO getTeacherById(Long teacherId) {

		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacherId));

		return TeacherMapper.toDTO(teacher);
	}

	@Override
	public List<TeacherDTO> getAllTeachers() {
		return teacherRepository.findAll().stream().map(TeacherMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteTeacher(Long teacherId) {

		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacherId));

		teacherRepository.delete(teacher);
	}

	@Override
	public TeacherDTO getTeacherByEmployeeId(String employeeId) {

		Teacher teacher = teacherRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with Employee ID: " + employeeId));

		return TeacherMapper.toDTO(teacher);
	}

	@Override
	public TeacherDTO getTeacherByEmail(String email) {

		Teacher teacher = teacherRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with Email: " + email));

		return TeacherMapper.toDTO(teacher);
	}

	@Override
	public List<TeacherDTO> getTeachersByFirstName(String firstName) {
		return teacherRepository.findByFirstName(firstName).stream().map(TeacherMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getTeachersByLastName(String lastName) {
		return teacherRepository.findByLastName(lastName).stream().map(TeacherMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getTeachersByQualification(String qualification) {
		return teacherRepository.findByQualification(qualification).stream().map(TeacherMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getTeachersBySpecialization(String specialization) {
		return teacherRepository.findBySpecialization(specialization).stream().map(TeacherMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getTeachersByTeacherType(String teacherType) {
		return teacherRepository.findByTeacherType(teacherType).stream().map(TeacherMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getTeachersBySubject(Long subjectId) {
		return teacherRepository.findBySubject_SubjectId(subjectId).stream().map(TeacherMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getTeachersByCity(String city) {
		return teacherRepository.findByCity(city).stream().map(TeacherMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getTeachersByState(String state) {
		return teacherRepository.findByState(state).stream().map(TeacherMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getActiveTeachers() {
		return teacherRepository.findByActive(true).stream().map(TeacherMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<TeacherDTO> getInactiveTeachers() {
		return teacherRepository.findByActive(false).stream().map(TeacherMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public boolean existsByEmployeeId(String employeeId) {
		return teacherRepository.existsByEmployeeId(employeeId);
	}

	@Override
	public boolean existsByEmail(String email) {
		return teacherRepository.existsByEmail(email);
	}

	@Override
	public long getTotalTeachers() {
		return teacherRepository.count();
	}

	@Override
	public long countActiveTeachers() {
		return teacherRepository.countByActive(true);
	}
}