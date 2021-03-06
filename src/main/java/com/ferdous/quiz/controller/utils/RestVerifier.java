package com.ferdous.quiz.controller.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import com.ferdous.quiz.exceptions.ModelVerificationException;

public class RestVerifier {
	private static final Logger logger = LoggerFactory.getLogger(RestVerifier.class);

	public static void verifyModelResult(BindingResult result) throws ModelVerificationException {
		if (result.hasErrors()) {
			logger.error(result.toString());
			throw new ModelVerificationException(result.getFieldError().getDefaultMessage());
		}
	}
}
