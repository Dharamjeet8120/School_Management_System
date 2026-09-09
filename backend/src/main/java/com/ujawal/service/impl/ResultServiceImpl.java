package com.ujawal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ujawal.dto.ResultDTO;
import com.ujawal.entity.Exam;
import com.ujawal.entity.Result;
import com.ujawal.entity.Student;
import com.ujawal.entity.Subject;
import com.ujawal.exception.ResourceNotFoundException;
import com.ujawal.mapper.ResultMapper;
import com.ujawal.repository.ExamRepository;
import com.ujawal.repository.ResultRepository;
import com.ujawal.repository.StudentRepository;
import com.ujawal.repository.SubjectRepository;
import com.ujawal.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

	private final ResultRepository resultRepository;
	private final StudentRepository studentRepository;
	private final ExamRepository examRepository;
	private final SubjectRepository subjectRepository;

	public ResultServiceImpl(ResultRepository resultRepository, StudentRepository studentRepository,
			ExamRepository examRepository, SubjectRepository subjectRepository) {
		this.resultRepository = resultRepository;
		this.studentRepository = studentRepository;
		this.examRepository = examRepository;
		this.subjectRepository = subjectRepository;
	}

	private void applyRelations(Result result, ResultDTO dto) {

		if (dto.getStudentId() != null) {
			Student student = studentRepository.findById(dto.getStudentId())
					.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + dto.getStudentId()));
			result.setStudent(student);
		}

		if (dto.getExamId() != null) {
			Exam exam = examRepository.findById(dto.getExamId())
					.orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + dto.getExamId()));
			result.setExam(exam);
		}

		if (dto.getSubjectId() != null) {
			Subject subject = subjectRepository.findById(dto.getSubjectId())
					.orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + dto.getSubjectId()));
			result.setSubject(subject);
		}
	}

	@Override
	public ResultDTO saveResult(ResultDTO resultDTO) {

		Result result = ResultMapper.toEntity(resultDTO);
		applyRelations(result, resultDTO);

		return ResultMapper.toDTO(resultRepository.save(result));
	}

	@Override
	public ResultDTO updateResult(Long resultId, ResultDTO resultDTO) {

		Result existingResult = resultRepository.findById(resultId)
				.orElseThrow(() -> new ResourceNotFoundException("Result not found with ID: " + resultId));

		existingResult.setObtainedMarks(resultDTO.getObtainedMarks());
		existingResult.setTotalMarks(resultDTO.getTotalMarks());
		existingResult.setGrade(resultDTO.getGrade());
		existingResult.setPassStatus(resultDTO.getPassStatus());
		existingResult.setRemarks(resultDTO.getRemarks());

		applyRelations(existingResult, resultDTO);

		return ResultMapper.toDTO(resultRepository.save(existingResult));
	}

	@Override
	public ResultDTO getResultById(Long resultId) {

		Result result = resultRepository.findById(resultId)
				.orElseThrow(() -> new ResourceNotFoundException("Result not found with ID: " + resultId));

		return ResultMapper.toDTO(result);
	}

	@Override
	public List<ResultDTO> getAllResults() {
		return resultRepository.findAll().stream().map(ResultMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteResult(Long resultId) {

		Result result = resultRepository.findById(resultId)
				.orElseThrow(() -> new ResourceNotFoundException("Result not found with ID: " + resultId));

		resultRepository.delete(result);
	}

	@Override
	public List<ResultDTO> getResultsByStudent(Long studentId) {
		return resultRepository.findByStudent_StudentId(studentId).stream().map(ResultMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ResultDTO> getResultsByExam(Long examId) {
		return resultRepository.findByExam_ExamId(examId).stream().map(ResultMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ResultDTO> getResultsBySubject(Long subjectId) {
		return resultRepository.findBySubject_SubjectId(subjectId).stream().map(ResultMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ResultDTO> getResultsByGrade(String grade) {
		return resultRepository.findByGrade(grade).stream().map(ResultMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<ResultDTO> getPassedStudents() {
		return resultRepository.findByPassStatus(true).stream().map(ResultMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<ResultDTO> getFailedStudents() {
		return resultRepository.findByPassStatus(false).stream().map(ResultMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<ResultDTO> getStudentExamResults(Long studentId, Long examId) {
		return resultRepository.findByStudent_StudentIdAndExam_ExamId(studentId, examId).stream()
				.map(ResultMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public long getTotalResults() {
		return resultRepository.count();
	}

	@Override
	public long countPassedStudents() {
		return resultRepository.countByPassStatus(true);
	}
}