package com.ujawal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujawal.entity.Fee;
import com.ujawal.entity.FeeStatus;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

	List<Fee> findByStudent_StudentId(Long studentId);

	List<Fee> findByFeeType(String feeType);

	List<Fee> findByStatus(FeeStatus status);

	List<Fee> findByPaymentMode(String paymentMode);

	List<Fee> findByDueDate(LocalDate dueDate);

	List<Fee> findByDueDateBefore(LocalDate date);

	List<Fee> findByPaymentDate(LocalDate paymentDate);

	List<Fee> findByTransactionId(String transactionId);

	long countByStatus(FeeStatus status);
}