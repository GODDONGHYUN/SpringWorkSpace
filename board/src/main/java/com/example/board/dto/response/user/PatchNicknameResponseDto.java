package com.example.board.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.board.dto.response.ResponseCode;
import com.example.board.dto.response.ResponseDto;
import com.example.board.dto.response.ResponseMessage;
import com.example.board.entity.UserEntity;

import lombok.Getter;

@Getter
public class PatchNicknameResponseDto extends ResponseDto {
	private String email;
	private String nickname;

	private PatchNicknameResponseDto(UserEntity userEntity) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.email = userEntity.getEmail();
		this.nickname = userEntity.getNickname();
	}

	public static ResponseEntity<PatchNicknameResponseDto> success(UserEntity userEntity) {
		PatchNicknameResponseDto body = new PatchNicknameResponseDto(userEntity);
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}
