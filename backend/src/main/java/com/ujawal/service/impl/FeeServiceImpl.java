package com.ujawal.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ujawal.dto.FeeDTO;
import com.ujawal.entity.Fee;
import com.ujawal.entity.FeeStatus;
import com.ujawal.entity.Student;
import com.ujawal.exception.ResourceNotFoundException;
import com.ujawal.mapper.FeeMapper;
import com.ujawal.repository.FeeRepository;
import com.ujawal.repository.StudentRepository;
import com.ujawal.service.FeeService;

@Service
public class FeeServiceImpl implements FeeService {

	private final FeeRepository feeRepository;
	private final StudentRepository studentRepository;

	public FeeServiceImpl(FeeRepository feeRepository, StudentRepository studentRepository) {
		this.feeRepository = feeRepository;
		this.studentRepository = studentRepository;
	}

	private void applyStudent(Fee fee, FeeDTO dto) {

		if (dto.getStudentId() != null) {
			Student student = studentRepository.findById(dto.getStudentId())
					.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + dto.getStudentId()));
			fee.setStudent(student);
		}
	}

	@Override
	public FeeDTO saveFee(FeeDTO feeDTO) {

		Fee fee = FeeMapper.toEntity(feeDTO);
		applyStudent(fee, feeDTO);

		return FeeMapper.toDTO(feeRepository.save(fee));
	}

	@Override
	public FeeDTO updateFee(Long feeId, FeeDTO feeDTO) {

		Fee existingFee = feeRepository.findById(feeId)
				.orElseThrow(() -> new ResourceNotFoundException("Fee not found with ID: " + feeId));

		existingFee.setFeeType(feeDTO.getFeeType());
		existingFee.setAmount(feeDTO.getAmount());
		existingFee.setPaidAmount(feeDTO.getPaidAmount());
		existingFee.setRemainingAmount(feeDTO.getRemainingAmount());
		existingFee.setDueDate(feeDTO.getDueDate());
		existingFee.setPaymentDate(feeDTO.getPaymentDate());
		existingFee.setStatus(feeDTO.getStatus());
		existingFee.setPaymentMode(feeDTO.getPaymentMode());
		existingFee.setTransactionId(feeDTO.getTransactionId());
		existingFee.setRemarks(feeDTO.getRemarks());

		applyStudent(existingFee, feeDTO);

		return FeeMapper.toDTO(feeRepository.save(existingFee));
	}

	@Override
	public FeeDTO getFeeById(Long feeId) {

		Fee fee = feeRepository.findById(feeId)
				.orElseThrow(() -> new ResourceNotFoundException("Fee not found with ID: " + feeId));

		return FeeMapper.toDTO(fee);
	}

	@Override
	public List<FeeDTO> getAllFees() {
		return feeRepository.findAll().stream().map(FeeMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteFee(Long feeId) {

		Fee fee = feeRepository.findById(feeId)
				.orElseThrow(() -> new ResourceNotFoundException("Fee not found with ID: " + feeId));

		feeRepository.delete(fee);
	}

	@Override
	public List<FeeDTO> getFeesByStudent(Long studentId) {
		return feeRepository.findByStudent_StudentId(studentId).stream().map(FeeMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<FeeDTO> getFeesByType(String feeType) {
		return feeRepository.findByFeeType(feeType).stream().map(FeeMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<FeeDTO> getFeesByStatus(FeeStatus status) {
		return feeRepository.findByStatus(status).stream().map(FeeMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<FeeDTO> getFeesByPaymentMode(String paymentMode) {
		return feeRepository.findByPaymentMode(paymentMode).stream().map(FeeMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<FeeDTO> getFeesByDueDate(LocalDate dueDate) {
		return feeRepository.findByDueDate(dueDate).stream().map(FeeMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<FeeDTO> getOverdueFees() {
		return feeRepository.findByDueDateBefore(LocalDate.now()).stream().map(FeeMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<FeeDTO> getFeesByPaymentDate(LocalDate paymentDate) {
		return feeRepository.findByPaymentDate(paymentDate).stream().map(FeeMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public long getTotalFees() {
		return feeRepository.count();
	}

	@Override
	public long countFeesByStatus(FeeStatus status) {
		return feeRepository.countByStatus(status);
	}
}