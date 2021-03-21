package com.ferdous.quiz.service.usermanagement.token;

import java.util.Date;

import com.ferdous.quiz.exceptions.InvalidTokenException;
import com.ferdous.quiz.model.TokenModel;
import com.ferdous.quiz.model.User;

public interface TokenService<T extends TokenModel> {
	T generateTokenForUser(User user);

	void validateTokenForUser(User user, String token) throws InvalidTokenException;

	void invalidateToken(String token);

	void invalidateExpiredTokensPreviousTo(Date date);
}
