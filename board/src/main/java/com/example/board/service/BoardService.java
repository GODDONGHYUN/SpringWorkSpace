package com.example.board.service;

import org.springframework.http.ResponseEntity;

import com.example.board.dto.response.board.GetLatestListResponseDto;

public interface BoardService {
    ResponseEntity<? super GetLatestListResponseDto> getLatestList();
}