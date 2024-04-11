package com.donghyun.basic.provider;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

// # JWT :
// ? JSON WEB TOKEN , RFC 7519 표준에 정의 된 json 형식의 문자열을 포함하고 있는 토큰
// ? 인증 및 인가에 사용
// ? 암호화가 되어 있어 클라이언트와 서버 간의 안정한 데이터 교환을 할 수 있음
// ^ 헤더(Header) , 페이로드(Payload) , 서명(Signature)으로 구성 됨

// * 헤더(Header)  : 토큰의 유형 (일반적으로 jwt)과 암호화 알고리즘이 포함 되어있음
// * 페이로드(Payload) : 클라이언트 혹은 서버가 상대방에게 전달할 데이터가 포함되어 있음 (작성자, 접근 주체의 정보, 작성시간, 만료시간 등)
// * 서명(Signature : 헤더와 페이로드를 합쳐서 인코딩하고 지정한 비밀키로 암호화한 데이터
public class JwtProvider {
	

	// # JWT 생성

	public String create(String principle) {
			// 만료 시간
			Date expiredDate = Date.from(Instant.now().plus(4, ChronoUnit.HOURS));
			// 비밀키 생성
			Key  key = Keys.hmacShaKeyFor("qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwerasdfzxcv".getBytes(StandardCharsets.UTF_8));

			// JWT 생성
			String jwt =  Jwts.builder()
			// 서명 (서명에 사용할 비밀키, 서명에 사용할 암호화 알고리즘)
			.signWith(key, SignatureAlgorithm.HS256)
			// 페이로드
			// 작성자
			.setSubject(principle)
			// 생성시간
			.setIssuedAt(expiredDate)
			// 만료시간
			.setExpiration(expiredDate)
			// 위의 내용을 압축(인코딩)
			.compact();

			return jwt;
	}
}
