package com.ferdous.quiz.service.usermanagement;

import com.ferdous.quiz.model.User;

public interface RegistrationService {
	User startRegistration(User user);

	User continueRegistration(User user, String token);

	boolean isRegistrationCompleted(User user);
}
