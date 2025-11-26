package com.synapse.synapse.global.exception;

import org.springframework.http.HttpStatus;

import com.synapse.synapse.global.api.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessage implements ResponseMessage {

	/*
	 * 200 OK
	 */
	WK_REQUEST_SUCCESS(HttpStatus.OK.value(), "요청이 성공적으로 처리되었습니다."),
	WK_DATA_RETRIEVED(HttpStatus.OK.value(), "데이터 조회가 완료되었습니다."),
	WK_RESOURCE_UPDATED(HttpStatus.OK.value(), "성공적으로 수정되었습니다."),
	WK_RESOURCE_DELETED(HttpStatus.OK.value(), "성공적으로 삭제되었습니다."),

	USER_LOGIN_SUCCESS(HttpStatus.OK.value(), "로그인이 완료되었습니다."),
	USER_LOGOUT_SUCCESS(HttpStatus.OK.value(), "로그아웃이 완료되었습니다."),
	USER_PROFILE_UPDATED(HttpStatus.OK.value(), "사용자 프로필이 수정되었습니다."),
	USER_EMAIL_VERIFIED(HttpStatus.OK.value(), "이메일 인증이 완료되었습니다."),
	USER_PASSWORD_CHANGED(HttpStatus.OK.value(), "비밀번호가 성공적으로 변경되었습니다."),

	TOKEN_ISSUED(HttpStatus.OK.value(), "토큰이 성공적으로 발급되었습니다."),
	TOKEN_REFRESHED(HttpStatus.OK.value(), "토큰이 갱신되었습니다."),
	SESSION_ACTIVE(HttpStatus.OK.value(), "세션이 유효합니다."),

	MENU_CHOSE_SUCCESS(HttpStatus.OK.value(), "성공적으로 메뉴 선택 하셨습니다"),

	ORDER_UPDATED(HttpStatus.OK.value(), "주문 정보가 수정되었습니다."),
	ORDER_CANCELLED(HttpStatus.OK.value(), "주문이 취소되었습니다."),
	ORDER_COMPLETED(HttpStatus.OK.value(), "주문이 완료되었습니다."),
	ORDER_RETRIEVED(HttpStatus.OK.value(), "주문 내역이 조회되었습니다."),

	PAYMENT_SUCCESS(HttpStatus.OK.value(), "결제가 성공적으로 완료되었습니다."),
	PAYMENT_REFUNDED(HttpStatus.OK.value(), "결제가 성공적으로 환불되었습니다."),
	PAYMENT_HISTORY_RETRIEVED(HttpStatus.OK.value(), "결제 내역이 조회되었습니다."),

	VOICE_RECOGNIZED(HttpStatus.OK.value(), "음성 인식이 성공적으로 완료되었습니다."),
	VOICE_PROCESSED(HttpStatus.OK.value(), "음성 요청이 정상적으로 처리되었습니다."),
	VOICE_SERVICE_AVAILABLE(HttpStatus.OK.value(), "음성 인식 서비스가 정상적으로 동작 중입니다."),

	/*
	 * 201 Created
	 */
	WK_RESOURCE_CREATED(HttpStatus.CREATED.value(), "성공적으로 생성되었습니다."),
	USER_REGISTER_SUCCESS(HttpStatus.CREATED.value(), "회원가입이 완료되었습니다."),
	PAYMENT_METHOD_REGISTERED(HttpStatus.CREATED.value(), "결제 수단이 등록되었습니다."),
	ORDER_CREATED(HttpStatus.CREATED.value(), "주문이 성공적으로 접수되었습니다."),
	ACCESS_TOKEN_REISSUE_SUCCESS(HttpStatus.CREATED.value(), "액세스 토큰 재발급이 완료되었습니다."),

	/*
	 * 204 No Content
	 */
	USER_DELETED(HttpStatus.NO_CONTENT.value(), "어드민 계정이 삭제되었습니다."),
	ORDER_DELETED(HttpStatus.NO_CONTENT.value(), "주문이 삭제되었습니다.");
	private final int status;
	private final String message;
}
