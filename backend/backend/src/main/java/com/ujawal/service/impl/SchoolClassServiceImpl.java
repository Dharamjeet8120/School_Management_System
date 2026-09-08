package com.ujawal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ujawal.dto.SchoolClassDTO;
import com.ujawal.entity.SchoolClasss;
import com.ujawal.entity.Teacher;
import com.ujawal.exception.ResourceNotFoundException;
import com.ujawal.mapper.SchoolClassMapper;
import com.ujawal.repository.SchoolClassRepository;
import com.ujawal.repository.TeacherRepository;
import com.ujawal.service.SchoolClassService;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

	private final SchoolClassRepository schoolClassRepository;
	private final TeacherRepository teacherRepository;

	public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository, TeacherRepository teacherRepository) {
		this.schoolClassRepository = schoolClassRepository;
		this.teacherRepository = teacherRepository;
	}

	private void applyTeacher(SchoolClasss schoolClass, SchoolClassDTO dto) {

		if (dto.getTeacherId() != null) {
			Teacher teacher = teacherRepository.findById(dto.getTeacherId()).orElseThrow(
					() -> new ResourceNotFoundException("Teacher not found with ID: " + dto.getTeacherId()));
			schoolClass.setClassTeacher(teacher);
		}
	}

	@Override
	public SchoolClassDTO saveClass(SchoolClassDTO schoolClassDTO) {

		SchoolClasss schoolClass = SchoolClassMapper.toEntity(schoolClassDTO);
		applyTeacher(schoolClass, schoolClassDTO);

		return SchoolClassMapper.toDTO(schoolClassRepository.save(schoolClass));
	}

	@Override
	public SchoolClassDTO updateClass(Long classId, SchoolClassDTO schoolClassDTO) {

		SchoolClasss existingClass = schoolClassRepository.findById(classId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found with ID: " + classId));

		existingClass.setClassName(schoolClassDTO.getClassName());
		existingClass.setSection(schoolClassDTO.getSection());
		existingClass.setRoomNumber(schoolClassDTO.getRoomNumber());
		existingClass.setCapacity(schoolClassDTO.getCapacity());
		existingClass.setAcademicYear(schoolClassDTO.getAcademicYear());

		applyTeacher(existingClass, schoolClassDTO);

		return SchoolClassMapper.toDTO(schoolClassRepository.save(existingClass));
	}

	@Override
	public SchoolClassDTO getClassById(Long classId) {

		SchoolClasss schoolClass = schoolClassRepository.findById(classId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found with ID: " + classId));

		return SchoolClassMapper.toDTO(schoolClass);
	}

	@Override
	public List<SchoolClassDTO> getAllClasses() {
		return schoolClassRepository.findAll().stream().map(SchoolClassMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteClass(Long classId) {

		SchoolClasss schoolClass = schoolClassRepository.findById(classId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found with ID: " + classId));

		schoolClassRepository.delete(schoolClass);
	}

	@Override
	public SchoolClassDTO getClassByNameAndSection(String className, String section) {

		SchoolClasss schoolClass = schoolClassRepository.findByClassNameAndSection(className, section)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found"));

		return SchoolClassMapper.toDTO(schoolClass);
	}

	@Override
	public List<SchoolClassDTO> getClassesByName(String className) {
		return schoolClassRepository.findByClassName(className).stream().map(SchoolClassMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<SchoolClassDTO> getClassesBySection(String section) {
		return schoolClassRepository.findBySection(section).stream().map(SchoolClassMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<SchoolClassDTO> getClassesByAcademicYear(String academicYear) {
		return schoolClassRepository.findByAcademicYear(academicYear).stream().map(SchoolClassMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<SchoolClassDTO> getClassesByTeacher(Long teacherId) {
		return schoolClassRepository.findByClassTeacher_TeacherId(teacherId).stream().map(SchoolClassMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public boolean existsByClassNameAndSection(String className, String section) {
		return schoolClassRepository.existsByClassNameAndSection(className, section);
	}

	@Override
	public long getTotalClasses() {
		return schoolClassRepository.count();
	}

	@Override
	public long countClassesByAcademicYear(String academicYear) {
		return schoolClassRepository.countByAcademicYear(academicYear);
	}
}