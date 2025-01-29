package com.sk.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int sid;
	private String name;
	private String rollnumber;
	private String subject;
	private String address;
	private String phone;
	private String email;
}
