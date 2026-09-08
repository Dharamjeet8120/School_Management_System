package com.ujawal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ujawal.dto.ExamDTO;
import com.ujawal.entity.ExamType;
import com.ujawal.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

	private final ExamService examService;

	public ExamController(ExamService examService) {
		this.examService = examService;
	}

	@PostMapping
	public ResponseEntity<ExamDTO> saveExam(@RequestBody ExamDTO examDTO) {
		return new ResponseEntity<>(examService.saveExam(examDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{examId}")
	public ResponseEntity<ExamDTO> updateExam(@PathVariable Long examId, @RequestBody ExamDTO examDTO) {
		return ResponseEntity.ok(examService.updateExam(examId, examDTO));
	}

	@GetMapping("/{examId}")
	public ResponseEntity<ExamDTO> getExamById(@PathVariable Long examId) {
		return ResponseEntity.ok(examService.getExamById(examId));
	}

	@GetMapping
	public ResponseEntity<List<ExamDTO>> getAllExams() {
		return ResponseEntity.ok(examService.getAllExams());
	}

	@DeleteMapping("/{examId}")
	public ResponseEntity<String> deleteExam(@PathVariable Long examId) {
		examService.deleteExam(examId);
		return ResponseEntity.ok("Exam deleted successfully.");
	}

	@GetMapping("/name/{examName}")
	public ResponseEntity<List<ExamDTO>> getExamsByName(@PathVariable String examName) {
		return ResponseEntity.ok(examService.getExamsByName(examName));
	}

	@GetMapping("/type/{examType}")
	public ResponseEntity<List<ExamDTO>> getExamsByType(@PathVariable ExamType examType) {
		return ResponseEntity.ok(examService.getExamsByType(examType));
	}

	@GetMapping("/date/{examDate}")
	public ResponseEntity<List<ExamDTO>> getExamsByDate(@PathVariable LocalDate examDate) {
		return ResponseEntity.ok(examService.getExamsByDate(examDate));
	}

	@GetMapping("/academic-year/{academicYear}")
	public ResponseEntity<List<ExamDTO>> getExamsByAcademicYear(@PathVariable String academicYear) {
		return ResponseEntity.ok(examService.getExamsByAcademicYear(academicYear));
	}

	@GetMapping("/between")
	public ResponseEntity<List<ExamDTO>> getExamsBetweenDates(@RequestParam LocalDate startDate,
			@RequestParam LocalDate endDate) {
		return ResponseEntity.ok(examService.getExamsBetweenDates(startDate, endDate));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getTotalExams() {
		return ResponseEntity.ok(examService.getTotalExams());
	}

	@GetMapping("/count/type/{examType}")
	public ResponseEntity<Long> countExamsByType(@PathVariable ExamType examType) {
		return ResponseEntity.ok(examService.countExamsByType(examType));
	}
}