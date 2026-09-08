package com.ujawal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujawal.entity.SchoolClasss;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClasss, Long> {

	Optional<SchoolClasss> findByClassNameAndSection(String className, String section);

	List<SchoolClasss> findByClassName(String className);

	List<SchoolClasss> findBySection(String section);

	List<SchoolClasss> findByAcademicYear(String academicYear);

	List<SchoolClasss> findByClassTeacher_TeacherId(Long teacherId);

	boolean existsByClassNameAndSection(String className, String section);

	long countByAcademicYear(String academicYear);
}