package com.example.board.service;

import org.springframework.http.ResponseEntity;

import com.example.board.dto.request.user.PatchNicknameRequestDto;
import com.example.board.dto.response.ResponseDto;
import com.example.board.dto.response.user.GetUserResponseDto;

import jakarta.validation.Valid;

public interface UserService {
    
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
		ResponseEntity<ResponseDto> patchNickname(@Valid PatchNicknameRequestDto requestbody);
}