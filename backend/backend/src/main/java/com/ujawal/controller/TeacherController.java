package com.ujawal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujawal.dto.TeacherDTO;
import com.ujawal.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
@PreAuthorize("hasRole('ADMIN')")
public class TeacherController {

	private final TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@PostMapping
	public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
		return new ResponseEntity<>(teacherService.saveTeacher(teacherDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{teacherId}")
	public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDTO teacherDTO) {
		return ResponseEntity.ok(teacherService.updateTeacher(teacherId, teacherDTO));
	}

	@GetMapping("/{teacherId}")
	public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long teacherId) {
		return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
	}

	@GetMapping
	public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
		return ResponseEntity.ok(teacherService.getAllTeachers());
	}

	@DeleteMapping("/{teacherId}")
	public ResponseEntity<String> deleteTeacher(@PathVariable Long teacherId) {
		teacherService.deleteTeacher(teacherId);
		return ResponseEntity.ok("Teacher deleted successfully.");
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<TeacherDTO> getTeacherByEmployeeId(@PathVariable String employeeId) {
		return ResponseEntity.ok(teacherService.getTeacherByEmployeeId(employeeId));
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<TeacherDTO> getTeacherByEmail(@PathVariable String email) {
		return ResponseEntity.ok(teacherService.getTeacherByEmail(email));
	}

	@GetMapping("/firstname/{firstName}")
	public ResponseEntity<List<TeacherDTO>> getTeachersByFirstName(@PathVariable String firstName) {
		return ResponseEntity.ok(teacherService.getTeachersByFirstName(firstName));
	}

	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<List<TeacherDTO>> getTeachersByLastName(@PathVariable String lastName) {
		return ResponseEntity.ok(teacherService.getTeachersByLastName(lastName));
	}

	@GetMapping("/qualification/{qualification}")
	public ResponseEntity<List<TeacherDTO>> getTeachersByQualification(@PathVariable String qualification) {
		return ResponseEntity.ok(teacherService.getTeachersByQualification(qualification));
	}

	@GetMapping("/specialization/{specialization}")
	public ResponseEntity<List<TeacherDTO>> getTeachersBySpecialization(@PathVariable String specialization) {
		return ResponseEntity.ok(teacherService.getTeachersBySpecialization(specialization));
	}

	@GetMapping("/type/{teacherType}")
	public ResponseEntity<List<TeacherDTO>> getTeachersByTeacherType(@PathVariable String teacherType) {
		return ResponseEntity.ok(teacherService.getTeachersByTeacherType(teacherType));
	}

	@GetMapping("/subject/{subjectId}")
	public ResponseEntity<List<TeacherDTO>> getTeachersBySubject(@PathVariable Long subjectId) {
		return ResponseEntity.ok(teacherService.getTeachersBySubject(subjectId));
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<List<TeacherDTO>> getTeachersByCity(@PathVariable String city) {
		return ResponseEntity.ok(teacherService.getTeachersByCity(city));
	}

	@GetMapping("/state/{state}")
	public ResponseEntity<List<TeacherDTO>> getTeachersByState(@PathVariable String state) {
		return ResponseEntity.ok(teacherService.getTeachersByState(state));
	}

	@GetMapping("/active")
	public ResponseEntity<List<TeacherDTO>> getActiveTeachers() {
		return ResponseEntity.ok(teacherService.getActiveTeachers());
	}

	@GetMapping("/inactive")
	public ResponseEntity<List<TeacherDTO>> getInactiveTeachers() {
		return ResponseEntity.ok(teacherService.getInactiveTeachers());
	}

	@GetMapping("/exists/employee/{employeeId}")
	public ResponseEntity<Boolean> existsByEmployeeId(@PathVariable String employeeId) {
		return ResponseEntity.ok(teacherService.existsByEmployeeId(employeeId));
	}

	@GetMapping("/exists/email/{email}")
	public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
		return ResponseEntity.ok(teacherService.existsByEmail(email));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getTotalTeachers() {
		return ResponseEntity.ok(teacherService.getTotalTeachers());
	}

	@GetMapping("/count/active")
	public ResponseEntity<Long> countActiveTeachers() {
		return ResponseEntity.ok(teacherService.countActiveTeachers());
	}
}