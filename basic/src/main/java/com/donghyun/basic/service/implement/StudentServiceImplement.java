package com.donghyun.basic.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.donghyun.basic.dto.request.student.PostStudentRequestDto;
import com.donghyun.basic.service.StudentService;

@Service
public class StudentServiceImplement implements StudentService {

    @Override
    public ResponseEntity<String> postStudent(PostStudentRequestDto dto) {
        return null;
    }
    
}
