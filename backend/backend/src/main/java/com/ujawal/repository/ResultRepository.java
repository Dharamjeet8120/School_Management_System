package com.ujawal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujawal.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

	List<Result> findByStudent_StudentId(Long studentId);

	List<Result> findByExam_ExamId(Long examId);

	List<Result> findBySubject_SubjectId(Long subjectId);

	List<Result> findByPassStatus(Boolean passStatus);

	List<Result> findByGrade(String grade);

	List<Result> findByStudent_StudentIdAndExam_ExamId(Long studentId, Long examId);

	long countByPassStatus(Boolean passStatus);
}