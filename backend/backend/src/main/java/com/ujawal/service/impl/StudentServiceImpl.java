package com.ujawal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ujawal.dto.StudentDTO;
import com.ujawal.entity.SchoolClasss;
import com.ujawal.entity.Student;
import com.ujawal.entity.Teacher;
import com.ujawal.exception.ResourceNotFoundException;
import com.ujawal.mapper.StudentMapper;
import com.ujawal.repository.SchoolClassRepository;
import com.ujawal.repository.StudentRepository;
import com.ujawal.repository.TeacherRepository;
import com.ujawal.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final SchoolClassRepository schoolClassRepository;
	private final TeacherRepository teacherRepository;

	public StudentServiceImpl(StudentRepository studentRepository, SchoolClassRepository schoolClassRepository,
			TeacherRepository teacherRepository) {
		this.studentRepository = studentRepository;
		this.schoolClassRepository = schoolClassRepository;
		this.teacherRepository = teacherRepository;
	}

	private void applyRelations(Student student, StudentDTO dto) {

		if (dto.getClassId() != null) {
			SchoolClasss schoolClass = schoolClassRepository.findById(dto.getClassId())
					.orElseThrow(() -> new ResourceNotFoundException("Class not found with ID: " + dto.getClassId()));
			student.setSchoolClass(schoolClass);
		}

		if (dto.getTeacherId() != null) {
			Teacher teacher = teacherRepository.findById(dto.getTeacherId()).orElseThrow(
					() -> new ResourceNotFoundException("Teacher not found with ID: " + dto.getTeacherId()));
			student.setClassTeacher(teacher);
		}
	}

	@Override
	public StudentDTO saveStudent(StudentDTO studentDTO) {

		Student student = StudentMapper.toEntity(studentDTO);
		applyRelations(student, studentDTO);

		return StudentMapper.toDTO(studentRepository.save(student));
	}

	@Override
	public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {

		Student existingStudent = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

		existingStudent.setFirstName(studentDTO.getFirstName());
		existingStudent.setLastName(studentDTO.getLastName());
		existingStudent.setGender(studentDTO.getGender());

		applyRelations(existingStudent, studentDTO);

		return StudentMapper.toDTO(studentRepository.save(existingStudent));
	}

	@Override
	public StudentDTO getStudentById(Long studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

		return StudentMapper.toDTO(student);
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		return studentRepository.findAll().stream().map(StudentMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteStudent(Long studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

		studentRepository.delete(student);
	}

	@Override
	public StudentDTO getStudentByEmail(String email) {

		Student student = studentRepository.findByStudentEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));

		return StudentMapper.toDTO(student);
	}

	@Override
	public StudentDTO getStudentByRollNumber(String rollNumber) {

		Student student = studentRepository.findByRollNumber(rollNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with roll number: " + rollNumber));

		return StudentMapper.toDTO(student);
	}

	@Override
	public List<StudentDTO> getStudentsByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName).stream().map(StudentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<StudentDTO> getStudentsByLastName(String lastName) {
		return studentRepository.findByLastName(lastName).stream().map(StudentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<StudentDTO> getStudentsByGender(String gender) {
		return studentRepository.findByGender(gender).stream().map(StudentMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<StudentDTO> getStudentsByStatus(String status) {
		return studentRepository.findByStatus(status).stream().map(StudentMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<StudentDTO> getStudentsByClass(Long classId) {
		return studentRepository.findBySchoolClass_ClassId(classId).stream().map(StudentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<StudentDTO> searchStudents(String keyword) {
		return studentRepository.searchStudents(keyword).stream().map(StudentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public boolean existsByEmail(String email) {
		return studentRepository.existsByStudentEmail(email);
	}

	@Override
	public boolean existsByRollNumber(String rollNumber) {
		return studentRepository.existsByRollNumber(rollNumber);
	}

	@Override
	public long getTotalStudents() {
		return studentRepository.count();
	}

	@Override
	public long countStudentsByStatus(String status) {
		return studentRepository.countByStatus(status);
	}
}