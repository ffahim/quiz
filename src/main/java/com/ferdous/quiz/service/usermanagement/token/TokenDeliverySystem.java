package com.ferdous.quiz.service.usermanagement.token;

import org.springframework.scheduling.annotation.Async;

import com.ferdous.quiz.model.TokenModel;
import com.ferdous.quiz.model.TokenType;
import com.ferdous.quiz.model.User;

public interface TokenDeliverySystem {
	@Async
	void sendTokenToUser(TokenModel token, User user, TokenType tokenType);
}
