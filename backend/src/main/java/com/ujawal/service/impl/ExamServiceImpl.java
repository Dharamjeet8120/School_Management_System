package com.ujawal.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ujawal.dto.ExamDTO;
import com.ujawal.entity.Exam;
import com.ujawal.entity.ExamType;
import com.ujawal.exception.ResourceNotFoundException;
import com.ujawal.mapper.ExamMapper;
import com.ujawal.repository.ExamRepository;
import com.ujawal.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	private final ExamRepository examRepository;

	public ExamServiceImpl(ExamRepository examRepository) {
		this.examRepository = examRepository;
	}

	@Override
	public ExamDTO saveExam(ExamDTO examDTO) {
		Exam exam = ExamMapper.toEntity(examDTO);
		return ExamMapper.toDTO(examRepository.save(exam));
	}

	@Override
	public ExamDTO updateExam(Long examId, ExamDTO examDTO) {

		Exam existingExam = examRepository.findById(examId)
				.orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + examId));

		existingExam.setExamName(examDTO.getExamName());
		existingExam.setExamType(examDTO.getExamType());
		existingExam.setExamDate(examDTO.getExamDate());
		existingExam.setTotalMarks(examDTO.getTotalMarks());
		existingExam.setPassingMarks(examDTO.getPassingMarks());
		existingExam.setAcademicYear(examDTO.getAcademicYear());
		existingExam.setDescription(examDTO.getDescription());

		return ExamMapper.toDTO(examRepository.save(existingExam));
	}

	@Override
	public ExamDTO getExamById(Long examId) {

		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + examId));

		return ExamMapper.toDTO(exam);
	}

	@Override
	public List<ExamDTO> getAllExams() {
		return examRepository.findAll().stream().map(ExamMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteExam(Long examId) {

		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + examId));

		examRepository.delete(exam);
	}

	@Override
	public List<ExamDTO> getExamsByName(String examName) {
		return examRepository.findByExamName(examName).stream().map(ExamMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<ExamDTO> getExamsByType(ExamType examType) {
		return examRepository.findByExamType(examType).stream().map(ExamMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<ExamDTO> getExamsByDate(LocalDate examDate) {
		return examRepository.findByExamDate(examDate).stream().map(ExamMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<ExamDTO> getExamsByAcademicYear(String academicYear) {
		return examRepository.findByAcademicYear(academicYear).stream().map(ExamMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ExamDTO> getExamsBetweenDates(LocalDate startDate, LocalDate endDate) {
		return examRepository.findByExamDateBetween(startDate, endDate).stream().map(ExamMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public long getTotalExams() {
		return examRepository.count();
	}

	@Override
	public long countExamsByType(ExamType examType) {
		return examRepository.countByExamType(examType);
	}
}