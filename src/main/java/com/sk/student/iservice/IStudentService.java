package com.sk.student.iservice;

import java.util.List;

import com.sk.student.dto.StudentRequest;
import com.sk.student.dto.StudentResponse;

public interface IStudentService {

	public String saveStudent(StudentRequest request);
	public boolean deleteStudent(int id);
	public List<StudentResponse> getAllStudent();
	public StudentRequest updateStudent(StudentRequest request);
	public StudentResponse getOneStudent(String email);
	
}
