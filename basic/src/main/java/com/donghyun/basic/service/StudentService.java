package com.donghyun.basic.service;

import org.springframework.http.ResponseEntity;

import com.donghyun.basic.dto.request.student.PostStudentRequestDto;

public interface StudentService {
    ResponseEntity<String> postStudent(PostStudentRequestDto dto);
}
