package com.example.board.service;

import org.springframework.http.ResponseEntity;

import com.example.board.dto.request.user.PatchNicknameRequestDto;
import com.example.board.dto.response.ResponseDto;
import com.example.board.dto.response.user.GetUserResponseDto;



public interface UserService {
    
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
		ResponseEntity<ResponseDto> patchNickname(PatchNicknameRequestDto dto);
}