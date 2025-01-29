package com.sk.student.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.student.dao.StudentDao;
import com.sk.student.dto.StudentRequest;
import com.sk.student.dto.StudentResponse;
import com.sk.student.entity.StudentEntity;
import com.sk.student.iservice.IStudentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentDao repository;
	@Override
	 public String saveStudent(StudentRequest request) {
        try {
            Optional<StudentEntity> existingStudent = repository.findByEmail(request.getEmail());
            if (existingStudent.isPresent()) {
                return "Student record already exists";
            }
            StudentEntity entity = new StudentEntity();
            BeanUtils.copyProperties(request, entity);
            StudentEntity studentEntity = repository.save(entity);
            log.info("Student record saved successfully with id {}",studentEntity.getSid());
            return "Student record saved successfully";
        } catch (Exception e) {
        	log.error("Error occurred while saving the record in DB {}",request);
            return "Error occurred while saving the record in DB";
        }
    }

	@Override
	public boolean deleteStudent(int id) {
	    try {
	        if (repository.existsById(id)) {
	            repository.deleteById(id);
	            log.info("Student record deleted successfully with ID: {}", id);
	            return true;
	        } else {
	            log.warn("Student record with ID: {} not found", id);
	            return false;
	        }
	    } catch (Exception e) {
	        log.error("Error occurred while deleting student record with ID: {}", id, e);
	        throw new RuntimeException("Database error while deleting student");
	    }
	}

	@Override
	 public List<StudentResponse> getAllStudent() {
        try {
            List<StudentEntity> students = repository.findAll();
            log.info("Fetched {} student records", students.size());
            return students.stream().map(student -> {
                StudentResponse response = new StudentResponse();
                BeanUtils.copyProperties(student, response);
                return response;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error occurred while fetching student records", e);
            return List.of();
        }
    }

	@Override
	  public StudentRequest updateStudent(StudentRequest request) {
        try {
            Optional<StudentEntity> optionalStudent = repository.findByEmail(request.getEmail());
            if (optionalStudent.isPresent()) {
                StudentEntity student = optionalStudent.get();
                BeanUtils.copyProperties(request, student, "id");
                repository.save(student);
                log.info("Student record updated successfully with ID: {}", student.getSid());
                return request;
            } else {
                log.warn("Student record not found for email: {}", request.getEmail());
                return null;
            }
        } catch (Exception e) {
            log.error("Error occurred while updating student record: {}", request, e);
            return null;
        }
    }

	@Override
	public StudentResponse getOneStudent(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
