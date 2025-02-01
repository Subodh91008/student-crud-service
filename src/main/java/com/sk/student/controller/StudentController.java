package com.sk.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.student.dto.StudentRequest;
import com.sk.student.dto.StudentResponse;
import com.sk.student.iservice.IStudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
	
	@Autowired
	private IStudentService service;

	@PostMapping("/save")
	public ResponseEntity<String> saveStudent(@RequestBody StudentRequest request ){
		String saveStudent = service.saveStudent(request);
		return ResponseEntity.ok(saveStudent);
	}
	@GetMapping("/getallStudent")
	public ResponseEntity<List<StudentResponse>> getallStudent(){
		return ResponseEntity.ok(service.getAllStudent());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
	    try {
	    	
	        boolean isDeleted = service.deleteStudent(id);
	        if (isDeleted) {
	            return ResponseEntity.ok("Student record deleted successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student record not found");
	        }
	    } catch (Exception e) {
	        log.error("Error occurred while deleting student with ID: {}", id, e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while deleting the student record");
	    }
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody StudentRequest request){
		service.updateStudent(request);
		return ResponseEntity.ok("Succesfully student record updated");
	}
	
}
