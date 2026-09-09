package com.ujawal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ujawal.dto.SubjectDTO;
import com.ujawal.entity.Subject;
import com.ujawal.exception.ResourceNotFoundException;
import com.ujawal.mapper.SubjectMapper;
import com.ujawal.repository.SubjectRepository;
import com.ujawal.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	private final SubjectRepository subjectRepository;

	public SubjectServiceImpl(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	@Override
	public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
		Subject subject = SubjectMapper.toEntity(subjectDTO);
		return SubjectMapper.toDTO(subjectRepository.save(subject));
	}

	@Override
	public SubjectDTO updateSubject(Long subjectId, SubjectDTO subjectDTO) {

		Subject existingSubject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + subjectId));

		existingSubject.setSubjectCode(subjectDTO.getSubjectCode());
		existingSubject.setSubjectName(subjectDTO.getSubjectName());
		existingSubject.setMaximumMarks(subjectDTO.getMaximumMarks());
		existingSubject.setPassingMarks(subjectDTO.getPassingMarks());
		existingSubject.setDescription(subjectDTO.getDescription());
		existingSubject.setActive(subjectDTO.getActive());

		return SubjectMapper.toDTO(subjectRepository.save(existingSubject));
	}

	@Override
	public SubjectDTO getSubjectById(Long subjectId) {

		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + subjectId));

		return SubjectMapper.toDTO(subject);
	}

	@Override
	public List<SubjectDTO> getAllSubjects() {
		return subjectRepository.findAll().stream().map(SubjectMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteSubject(Long subjectId) {

		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + subjectId));

		subjectRepository.delete(subject);
	}

	@Override
	public SubjectDTO getSubjectByCode(String subjectCode) {

		Subject subject = subjectRepository.findBySubjectCode(subjectCode)
				.orElseThrow(() -> new ResourceNotFoundException("Subject not found with code: " + subjectCode));

		return SubjectMapper.toDTO(subject);
	}

	@Override
	public SubjectDTO getSubjectByName(String subjectName) {

		Subject subject = subjectRepository.findBySubjectName(subjectName)
				.orElseThrow(() -> new ResourceNotFoundException("Subject not found with name: " + subjectName));

		return SubjectMapper.toDTO(subject);
	}

	@Override
	public List<SubjectDTO> getActiveSubjects() {
		return subjectRepository.findByActive(true).stream().map(SubjectMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<SubjectDTO> getInactiveSubjects() {
		return subjectRepository.findByActive(false).stream().map(SubjectMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public boolean existsBySubjectCode(String subjectCode) {
		return subjectRepository.existsBySubjectCode(subjectCode);
	}

	@Override
	public boolean existsBySubjectName(String subjectName) {
		return subjectRepository.existsBySubjectName(subjectName);
	}

	@Override
	public long getTotalSubjects() {
		return subjectRepository.count();
	}

	@Override
	public long countActiveSubjects() {
		return subjectRepository.countByActive(true);
	}
}