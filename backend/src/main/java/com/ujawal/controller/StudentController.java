package com.ujawal.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ujawal.dto.StudentDTO;
import com.ujawal.service.StudentService;

@RestController
@RequestMapping("/api/students")
@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")

public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
		return new ResponseEntity<>(studentService.saveStudent(studentDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{studentId}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
		return ResponseEntity.ok(studentService.updateStudent(studentId, studentDTO));
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
		return ResponseEntity.ok(studentService.getStudentById(studentId));
	}

	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.ok("Student deleted successfully.");
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
		return ResponseEntity.ok(studentService.getStudentByEmail(email));
	}

	@GetMapping("/roll/{rollNumber}")
	public ResponseEntity<StudentDTO> getStudentByRollNumber(@PathVariable String rollNumber) {
		return ResponseEntity.ok(studentService.getStudentByRollNumber(rollNumber));
	}

	@GetMapping("/firstname/{firstName}")
	public ResponseEntity<List<StudentDTO>> getStudentsByFirstName(@PathVariable String firstName) {
		return ResponseEntity.ok(studentService.getStudentsByFirstName(firstName));
	}

	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<List<StudentDTO>> getStudentsByLastName(@PathVariable String lastName) {
		return ResponseEntity.ok(studentService.getStudentsByLastName(lastName));
	}

	@GetMapping("/gender/{gender}")
	public ResponseEntity<List<StudentDTO>> getStudentsByGender(@PathVariable String gender) {
		return ResponseEntity.ok(studentService.getStudentsByGender(gender));
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<StudentDTO>> getStudentsByStatus(@PathVariable String status) {
		return ResponseEntity.ok(studentService.getStudentsByStatus(status));
	}

	@GetMapping("/class/{classId}")
	public ResponseEntity<List<StudentDTO>> getStudentsByClass(@PathVariable Long classId) {
		return ResponseEntity.ok(studentService.getStudentsByClass(classId));
	}

	@GetMapping("/search")
	public ResponseEntity<List<StudentDTO>> searchStudents(@RequestParam String keyword) {
		return ResponseEntity.ok(studentService.searchStudents(keyword));
	}

	@GetMapping("/exists/email/{email}")
	public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
		return ResponseEntity.ok(studentService.existsByEmail(email));
	}

	@GetMapping("/exists/roll/{rollNumber}")
	public ResponseEntity<Boolean> existsByRollNumber(@PathVariable String rollNumber) {
		return ResponseEntity.ok(studentService.existsByRollNumber(rollNumber));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getTotalStudents() {
		return ResponseEntity.ok(studentService.getTotalStudents());
	}

	@GetMapping("/count/status/{status}")
	public ResponseEntity<Long> countStudentsByStatus(@PathVariable String status) {
		return ResponseEntity.ok(studentService.countStudentsByStatus(status));
	}
}