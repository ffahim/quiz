package com.ferdous.quiz.service.usermanagement;

import com.ferdous.quiz.model.User;

public interface UserManagementService {

	void resendPassword(User user);

	void verifyResetPasswordToken(User user, String token);

	void updatePassword(User user, String password);

}
