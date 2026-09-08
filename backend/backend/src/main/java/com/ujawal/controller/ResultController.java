package com.ujawal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ujawal.dto.ResultDTO;
import com.ujawal.service.ResultService;

@RestController
@RequestMapping("/api/results")
public class ResultController {

	private final ResultService resultService;

	public ResultController(ResultService resultService) {
		this.resultService = resultService;
	}

	@PostMapping
	public ResponseEntity<ResultDTO> saveResult(@RequestBody ResultDTO resultDTO) {
		return new ResponseEntity<>(resultService.saveResult(resultDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{resultId}")
	public ResponseEntity<ResultDTO> updateResult(@PathVariable Long resultId, @RequestBody ResultDTO resultDTO) {
		return ResponseEntity.ok(resultService.updateResult(resultId, resultDTO));
	}

	@GetMapping("/{resultId}")
	public ResponseEntity<ResultDTO> getResultById(@PathVariable Long resultId) {
		return ResponseEntity.ok(resultService.getResultById(resultId));
	}

	@GetMapping
	public ResponseEntity<List<ResultDTO>> getAllResults() {
		return ResponseEntity.ok(resultService.getAllResults());
	}

	@DeleteMapping("/{resultId}")
	public ResponseEntity<String> deleteResult(@PathVariable Long resultId) {
		resultService.deleteResult(resultId);
		return ResponseEntity.ok("Result deleted successfully.");
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<ResultDTO>> getResultsByStudent(@PathVariable Long studentId) {
		return ResponseEntity.ok(resultService.getResultsByStudent(studentId));
	}

	@GetMapping("/exam/{examId}")
	public ResponseEntity<List<ResultDTO>> getResultsByExam(@PathVariable Long examId) {
		return ResponseEntity.ok(resultService.getResultsByExam(examId));
	}

	@GetMapping("/subject/{subjectId}")
	public ResponseEntity<List<ResultDTO>> getResultsBySubject(@PathVariable Long subjectId) {
		return ResponseEntity.ok(resultService.getResultsBySubject(subjectId));
	}

	@GetMapping("/grade/{grade}")
	public ResponseEntity<List<ResultDTO>> getResultsByGrade(@PathVariable String grade) {
		return ResponseEntity.ok(resultService.getResultsByGrade(grade));
	}

	@GetMapping("/passed")
	public ResponseEntity<List<ResultDTO>> getPassedStudents() {
		return ResponseEntity.ok(resultService.getPassedStudents());
	}

	@GetMapping("/failed")
	public ResponseEntity<List<ResultDTO>> getFailedStudents() {
		return ResponseEntity.ok(resultService.getFailedStudents());
	}

	@GetMapping("/student/{studentId}/exam/{examId}")
	public ResponseEntity<List<ResultDTO>> getStudentExamResults(@PathVariable Long studentId,
			@PathVariable Long examId) {
		return ResponseEntity.ok(resultService.getStudentExamResults(studentId, examId));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getTotalResults() {
		return ResponseEntity.ok(resultService.getTotalResults());
	}

	@GetMapping("/count/passed")
	public ResponseEntity<Long> countPassedStudents() {
		return ResponseEntity.ok(resultService.countPassedStudents());
	}
}