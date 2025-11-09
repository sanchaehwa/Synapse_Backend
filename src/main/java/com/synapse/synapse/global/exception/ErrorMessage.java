package com.synapse.synapse.global.exception;

import com.synapse.synapse.global.api.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage implements ResponseMessage {
	//공통 에러(WK)
	WK_ENUM_VALUE_BAD_REQUEST(400, "WK1001", "요청한 값이 유효하지 않습니다."),
	WK_VALIDATION_MISSING(400, "WK1002", "요청 값이 비어있습니다."),
	WK_VALIDATION_NULL_OR_BLANK(400, "WK1003", "필수 요청 값이 누락되었습니다."),
	WK_VALIDATION_LENGTH_EXCEEDED(400, "WK1004", "요청 값이 길이를 초과했습니다."),
	WK_NO_PERMISSION(403, "WK1005", "권한이 없습니다. "),

	//사용자 관련 에러(USR)
	INVALID_CREDENTIAL(400, "USR1001", "이메일 또는 비밀번호가 일치하지않습니다"),
	DUPLICATE_EMAIL(400, "USR1003", "이미 사용중인 이메일 주소 입니다"),
	INVALID_EMAIL_CODE(400, "USR1004", "이메일 인증 코드가 올바르지 않습니다."),
	EXPIRED_EMAIL_CODE(400, "USR1005", "이메일 인증 코드가 만료되었습니다."),
	INVALID_EMAIL_VERIFICATION(400, "USR1006", "이메일 인증에 실패했습니다."),
	INVALID_PASSWORD_MIN_LENGTH(400, "USR1007", "비밀번호는 최소 8자 이상이어야 합니다."),
	INVALID_PASSWORD_MAX_LENGTH(400, "USR1008", "비밀번호는 최대 64자를 초과할 수 없습니다."),
	INVALID_PASSWORD_NUMBER(400, "USR1009", "비밀번호는 최소 1개의 숫자를 포함해야 합니다."),
	INVALID_PASSWORD_SPECIAL_CHAR(400, "USR1010", "비밀번호는 최소 1개의 특수문자를 포함해야 합니다."),
	NOT_FOUND_USER(404, "USR2001", "사용자를 찾을 수 없습니다"),

	SESSION_EXPIRED(401, "AUTH1001", "세션이 만료되었습니다. 다시 로그인해주세요."),
	LOGIN_REQUIRED(401, "AUTH1002", "로그인 후 진행해주세요."),
	ADMIN_PRIVILEGE_REQUIRED(403, "AUTH2001", "관리자 권한이 필요합니다."),
	INVALID_TOKEN(401, "AUTH1003", "유효하지 않은 토큰입니다."),

	//주문 관련
	NOT_FOUND_ORDER(404, "ORD1001", "주문 내역을 찾을 수 없습니다."),
	NOT_FOUND_MENU(404, "ORD1002", "메뉴 / 상품을 찾을 수 없습니다."),
	QUANTITY_EXCEEDED(400, "ORD1003", "주문 가능 수량이 초과되었습니다."),
	NOT_FOUND_OPTION(404, "ORD1004", "존재하지 않는 옵션 / 사이즈 입니다."),
	MINIMUM_ORDER_REQUIRED(400, "ORD1005", "하나 이상 주문해야 합니다"),

	//결제 관련
	INVALID_CARD_INFORMATION(400, "PAY1001", "카드 정보가 올바르지 않습니다."),
	PAYMENT_AUTHORIZATION_DECLINED(400, "PAY1002", "결제 승인이 거절되었습니다."),
	NOT_FOUND_PAYMENT_METHOD(404, "PAY1003", "존재하지 않는 결제수단입니다."),
	ALREADY_PROCESSED(400, "PAY1004", "이미 처리된 결제입니다."),

	//음성인식 관련
	VOICE_RECOGNITION_FAILED(400, "VOICE1001", "음성 인식 실패 / 인식 불가"),

	//메뉴 관련
	NOT_FOUND_CONDITION_CATEGORY(404, "MENU1001", "해당 조건을 찾을 수 없습니다"),
	NOT_FOUND_CATEGORY(404, "MENU1002", "카테고리를 찾을 수 없습니다"),
	// 서버 에러
	INTERNAL_SERVER_ERROR(500, "INT5000", "서버 내부 오류가 발생했습니다."),

	//매장 정보 관련
	NOT_FOUND_STORE(404, "STORE1001", "가게를 찾을 수 없습니다");

	private final int status;    // HTTP 상태 코드
	private final String code;   // 에러 코드
	private final String message; // 에러 메시지
}
