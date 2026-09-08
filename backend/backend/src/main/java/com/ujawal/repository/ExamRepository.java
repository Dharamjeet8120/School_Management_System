package com.ujawal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujawal.entity.Exam;
import com.ujawal.entity.ExamType;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

	List<Exam> findByExamName(String examName);

	List<Exam> findByExamType(ExamType examType);

	List<Exam> findByExamDate(LocalDate examDate);

	List<Exam> findByAcademicYear(String academicYear);

	List<Exam> findByExamDateBetween(LocalDate startDate, LocalDate endDate);

	long countByExamType(ExamType examType);
}