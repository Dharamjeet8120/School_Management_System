package com.ujawal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujawal.dto.FeeDTO;
import com.ujawal.entity.FeeStatus;
import com.ujawal.service.FeeService;

@RestController
@RequestMapping("/api/fees")
@PreAuthorize("hasAnyRole('ADMIN','ACCOUNTS')")

public class FeeController {

	private final FeeService feeService;

	public FeeController(FeeService feeService) {
		this.feeService = feeService;
	}

	@PostMapping
	public ResponseEntity<FeeDTO> saveFee(@RequestBody FeeDTO feeDTO) {
		return new ResponseEntity<>(feeService.saveFee(feeDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{feeId}")
	public ResponseEntity<FeeDTO> updateFee(@PathVariable Long feeId, @RequestBody FeeDTO feeDTO) {
		return ResponseEntity.ok(feeService.updateFee(feeId, feeDTO));
	}

	@GetMapping("/{feeId}")
	public ResponseEntity<FeeDTO> getFeeById(@PathVariable Long feeId) {
		return ResponseEntity.ok(feeService.getFeeById(feeId));
	}

	@GetMapping
	public ResponseEntity<List<FeeDTO>> getAllFees() {
		return ResponseEntity.ok(feeService.getAllFees());
	}

	@DeleteMapping("/{feeId}")
	public ResponseEntity<String> deleteFee(@PathVariable Long feeId) {
		feeService.deleteFee(feeId);
		return ResponseEntity.ok("Fee deleted successfully.");
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<FeeDTO>> getFeesByStudent(@PathVariable Long studentId) {
		return ResponseEntity.ok(feeService.getFeesByStudent(studentId));
	}

	@GetMapping("/type/{feeType}")
	public ResponseEntity<List<FeeDTO>> getFeesByType(@PathVariable String feeType) {
		return ResponseEntity.ok(feeService.getFeesByType(feeType));
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<FeeDTO>> getFeesByStatus(@PathVariable FeeStatus status) {
		return ResponseEntity.ok(feeService.getFeesByStatus(status));
	}

	@GetMapping("/payment-mode/{paymentMode}")
	public ResponseEntity<List<FeeDTO>> getFeesByPaymentMode(@PathVariable String paymentMode) {
		return ResponseEntity.ok(feeService.getFeesByPaymentMode(paymentMode));
	}

	@GetMapping("/due-date/{dueDate}")
	public ResponseEntity<List<FeeDTO>> getFeesByDueDate(@PathVariable LocalDate dueDate) {
		return ResponseEntity.ok(feeService.getFeesByDueDate(dueDate));
	}

	@GetMapping("/overdue")
	public ResponseEntity<List<FeeDTO>> getOverdueFees() {
		return ResponseEntity.ok(feeService.getOverdueFees());
	}

	@GetMapping("/payment-date/{paymentDate}")
	public ResponseEntity<List<FeeDTO>> getFeesByPaymentDate(@PathVariable LocalDate paymentDate) {
		return ResponseEntity.ok(feeService.getFeesByPaymentDate(paymentDate));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getTotalFees() {
		return ResponseEntity.ok(feeService.getTotalFees());
	}

	@GetMapping("/count/status/{status}")
	public ResponseEntity<Long> countFeesByStatus(@PathVariable FeeStatus status) {
		return ResponseEntity.ok(feeService.countFeesByStatus(status));
	}
}