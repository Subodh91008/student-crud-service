package com.sk.student.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.student.entity.StudentEntity;

public interface StudentDao extends JpaRepository<StudentEntity, Integer> {

	Optional<StudentEntity> findByEmail(String email);
}
