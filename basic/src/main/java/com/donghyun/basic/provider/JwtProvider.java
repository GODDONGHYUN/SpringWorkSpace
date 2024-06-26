package com.donghyun.basic.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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
@Component
public class JwtProvider {

	// JWT 암호화에 사용되는 비밀키는 보안 관리가 되어야 함
	// 코드에 직접적으로 시크릿 키를 작성하는 것은 보안상 좋지 않음
	// 해결책
	// ^ 1. application.properties / application.yml에 등록
	// ^ - application.properties 혹은 application.yaml에 시크릿 키를 작성
	// ^- @Value()를 이용하여 데이터를 가져옴
	// ^ - 주의사항  : application.properties / application.yaml을 gitignore에 등록 해야함
	@Value("${jwt.secret-key}")
	private String secretKey;

	// ^ 2. 시스템의 환경변수로 등록하여 사용
	// ^ - OS 자체의 시스템 환경 변수에 시크릿 키를 작성
	// ^ - Spring에서 Enviorment 객체를 이용하여 값을 가져옴

	// ^ 3. 외부 데이터 관리 도구를 사용
	// ^ - 해당 서버가 아닌 타 서버에 등록 된 Vault 도구를 사용하여 시크릿 키를 관리
	// ^ - OS 부팅 시에 Vault 서버에 접근하여 시크릿 키를 가져와서 사용하는 방법
	// ^ 매 부팅시 다른 시크릿 키를 제공해줌
	// # JWT 생성

	public String create(String principle) {
			// 만료 시간
			Date expiredDate = Date.from(Instant.now().plus(4, ChronoUnit.HOURS));
			// 시크릿 키 생성
			Key  key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

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

	public String validation(String jwt) {

		// JWT 검증 결과로 나타나는 페이로드가 저장 될 변수
		Claims claims = null;

		// 시크릿 키 생성
		Key  key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

		try {
				// 시크릿 키로 JWT 복호화 작업
				claims = Jwts.parserBuilder()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(jwt)
						.getBody();
		} catch (Exception exception) {
				exception.printStackTrace();
				return null;
		}

		return claims.getSubject();
	}
}
