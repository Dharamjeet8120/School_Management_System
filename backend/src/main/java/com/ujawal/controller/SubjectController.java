package com.ujawal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ujawal.dto.SubjectDTO;
import com.ujawal.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

	private final SubjectService subjectService;

	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@PostMapping
	public ResponseEntity<SubjectDTO> saveSubject(@RequestBody SubjectDTO subjectDTO) {
		return new ResponseEntity<>(subjectService.saveSubject(subjectDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{subjectId}")
	public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDTO subjectDTO) {
		return ResponseEntity.ok(subjectService.updateSubject(subjectId, subjectDTO));
	}

	@GetMapping("/{subjectId}")
	public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long subjectId) {
		return ResponseEntity.ok(subjectService.getSubjectById(subjectId));
	}

	@GetMapping
	public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
		return ResponseEntity.ok(subjectService.getAllSubjects());
	}

	@DeleteMapping("/{subjectId}")
	public ResponseEntity<String> deleteSubject(@PathVariable Long subjectId) {
		subjectService.deleteSubject(subjectId);
		return ResponseEntity.ok("Subject deleted successfully.");
	}

	@GetMapping("/code/{subjectCode}")
	public ResponseEntity<SubjectDTO> getSubjectByCode(@PathVariable String subjectCode) {
		return ResponseEntity.ok(subjectService.getSubjectByCode(subjectCode));
	}

	@GetMapping("/name/{subjectName}")
	public ResponseEntity<SubjectDTO> getSubjectByName(@PathVariable String subjectName) {
		return ResponseEntity.ok(subjectService.getSubjectByName(subjectName));
	}

	@GetMapping("/active")
	public ResponseEntity<List<SubjectDTO>> getActiveSubjects() {
		return ResponseEntity.ok(subjectService.getActiveSubjects());
	}

	@GetMapping("/inactive")
	public ResponseEntity<List<SubjectDTO>> getInactiveSubjects() {
		return ResponseEntity.ok(subjectService.getInactiveSubjects());
	}

	@GetMapping("/exists/code/{subjectCode}")
	public ResponseEntity<Boolean> existsBySubjectCode(@PathVariable String subjectCode) {
		return ResponseEntity.ok(subjectService.existsBySubjectCode(subjectCode));
	}

	@GetMapping("/exists/name/{subjectName}")
	public ResponseEntity<Boolean> existsBySubjectName(@PathVariable String subjectName) {
		return ResponseEntity.ok(subjectService.existsBySubjectName(subjectName));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getTotalSubjects() {
		return ResponseEntity.ok(subjectService.getTotalSubjects());
	}

	@GetMapping("/count/active")
	public ResponseEntity<Long> countActiveSubjects() {
		return ResponseEntity.ok(subjectService.countActiveSubjects());
	}
}