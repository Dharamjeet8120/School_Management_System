package com.ujawal.service;

import java.time.LocalDate;
import java.util.List;

import com.ujawal.dto.FeeDTO;
import com.ujawal.entity.FeeStatus;

public interface FeeService {

	FeeDTO saveFee(FeeDTO feeDTO);

	FeeDTO updateFee(Long feeId, FeeDTO feeDTO);

	FeeDTO getFeeById(Long feeId);

	List<FeeDTO> getAllFees();

	void deleteFee(Long feeId);

	List<FeeDTO> getFeesByStudent(Long studentId);

	List<FeeDTO> getFeesByType(String feeType);

	List<FeeDTO> getFeesByStatus(FeeStatus status);

	List<FeeDTO> getFeesByPaymentMode(String paymentMode);

	List<FeeDTO> getFeesByDueDate(LocalDate dueDate);

	List<FeeDTO> getOverdueFees();

	List<FeeDTO> getFeesByPaymentDate(LocalDate paymentDate);

	long getTotalFees();

	long countFeesByStatus(FeeStatus status);
}