package com.ujawal.mapper;

import com.ujawal.dto.FeeDTO;
import com.ujawal.entity.Fee;

public class FeeMapper {

	// Entity To DTO
	public static FeeDTO toDTO(Fee fee) {

		FeeDTO dto = new FeeDTO();

		dto.setFeeId(fee.getFeeId());

		if (fee.getStudent() != null) {
			dto.setStudentId(fee.getStudent().getStudentId());
		}

		dto.setFeeType(fee.getFeeType());
		dto.setAmount(fee.getAmount());
		dto.setPaidAmount(fee.getPaidAmount());
		dto.setRemainingAmount(fee.getRemainingAmount());
		dto.setDueDate(fee.getDueDate());
		dto.setPaymentDate(fee.getPaymentDate());
		dto.setStatus(fee.getStatus());
		dto.setPaymentMode(fee.getPaymentMode());
		dto.setTransactionId(fee.getTransactionId());
		dto.setRemarks(fee.getRemarks());

		return dto;
	}

	// DTO To Entity — student NOT set here, service layer resolves studentId
	public static Fee toEntity(FeeDTO dto) {

		Fee fee = new Fee();

		fee.setFeeId(dto.getFeeId());
		fee.setFeeType(dto.getFeeType());
		fee.setAmount(dto.getAmount());
		fee.setPaidAmount(dto.getPaidAmount());
		fee.setRemainingAmount(dto.getRemainingAmount());
		fee.setDueDate(dto.getDueDate());
		fee.setPaymentDate(dto.getPaymentDate());
		fee.setStatus(dto.getStatus());
		fee.setPaymentMode(dto.getPaymentMode());
		fee.setTransactionId(dto.getTransactionId());
		fee.setRemarks(dto.getRemarks());

		return fee;
	}
}