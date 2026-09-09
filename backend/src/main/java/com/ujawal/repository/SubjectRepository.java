package com.ujawal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujawal.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	Optional<Subject> findBySubjectCode(String subjectCode);

	Optional<Subject> findBySubjectName(String subjectName);

	List<Subject> findByActive(Boolean active);

	boolean existsBySubjectCode(String subjectCode);

	boolean existsBySubjectName(String subjectName);

	long countByActive(Boolean active);
}