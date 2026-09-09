package com.ujawal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ujawal.dto.SchoolClassDTO;
import com.ujawal.service.SchoolClassService;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassController {

	private final SchoolClassService schoolClassService;

	public SchoolClassController(SchoolClassService schoolClassService) {
		this.schoolClassService = schoolClassService;
	}

	@PostMapping
	public ResponseEntity<SchoolClassDTO> saveClass(@RequestBody SchoolClassDTO schoolClassDTO) {
		return new ResponseEntity<>(schoolClassService.saveClass(schoolClassDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{classId}")
	public ResponseEntity<SchoolClassDTO> updateClass(@PathVariable Long classId,
			@RequestBody SchoolClassDTO schoolClassDTO) {
		return ResponseEntity.ok(schoolClassService.updateClass(classId, schoolClassDTO));
	}

	@GetMapping("/{classId}")
	public ResponseEntity<SchoolClassDTO> getClassById(@PathVariable Long classId) {
		return ResponseEntity.ok(schoolClassService.getClassById(classId));
	}

	@GetMapping
	public ResponseEntity<List<SchoolClassDTO>> getAllClasses() {
		return ResponseEntity.ok(schoolClassService.getAllClasses());
	}

	@DeleteMapping("/{classId}")
	public ResponseEntity<String> deleteClass(@PathVariable Long classId) {
		schoolClassService.deleteClass(classId);
		return ResponseEntity.ok("Class deleted successfully.");
	}

	@GetMapping("/details")
	public ResponseEntity<SchoolClassDTO> getClassByNameAndSection(@RequestParam String className,
			@RequestParam String section) {
		return ResponseEntity.ok(schoolClassService.getClassByNameAndSection(className, section));
	}

	@GetMapping("/name/{className}")
	public ResponseEntity<List<SchoolClassDTO>> getClassesByName(@PathVariable String className) {
		return ResponseEntity.ok(schoolClassService.getClassesByName(className));
	}

	@GetMapping("/section/{section}")
	public ResponseEntity<List<SchoolClassDTO>> getClassesBySection(@PathVariable String section) {
		return ResponseEntity.ok(schoolClassService.getClassesBySection(section));
	}

	@GetMapping("/academic-year/{academicYear}")
	public ResponseEntity<List<SchoolClassDTO>> getClassesByAcademicYear(@PathVariable String academicYear) {
		return ResponseEntity.ok(schoolClassService.getClassesByAcademicYear(academicYear));
	}

	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<List<SchoolClassDTO>> getClassesByTeacher(@PathVariable Long teacherId) {
		return ResponseEntity.ok(schoolClassService.getClassesByTeacher(teacherId));
	}

	@GetMapping("/exists")
	public ResponseEntity<Boolean> existsByClassNameAndSection(@RequestParam String className,
			@RequestParam String section) {
		return ResponseEntity.ok(schoolClassService.existsByClassNameAndSection(className, section));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getTotalClasses() {
		return ResponseEntity.ok(schoolClassService.getTotalClasses());
	}

	@GetMapping("/count/academic-year/{academicYear}")
	public ResponseEntity<Long> countClassesByAcademicYear(@PathVariable String academicYear) {
		return ResponseEntity.ok(schoolClassService.countClassesByAcademicYear(academicYear));
	}
}