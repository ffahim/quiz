package com.ferdous.quiz.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ferdous.quiz.controller.utils.ErrorInfo;
import com.ferdous.quiz.exceptions.ActionRefusedException;
import com.ferdous.quiz.exceptions.InvalidParametersException;
import com.ferdous.quiz.exceptions.InvalidTokenException;
import com.ferdous.quiz.exceptions.ModelVerificationException;
import com.ferdous.quiz.exceptions.ResourceUnavailableException;
import com.ferdous.quiz.exceptions.UnauthorizedActionException;
import com.ferdous.quiz.exceptions.UserAlreadyExistsException;

@ControllerAdvice("com.ferdous.quiz.controller.web")
public class WebExceptionHandler {

	@ExceptionHandler(UnauthorizedActionException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView unauthorizedAction(HttpServletRequest req, Exception ex) {
		return setModelAndView(req.getRequestURL().toString(), ex, HttpStatus.UNAUTHORIZED.value());
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ModelAndView userExists(HttpServletRequest req, Exception ex) {
		return setModelAndView(req.getRequestURL().toString(), ex, HttpStatus.CONFLICT.value());
	}

	@ExceptionHandler(ResourceUnavailableException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView resourceUnavailable(HttpServletRequest req, Exception ex) {
		return setModelAndView(req.getRequestURL().toString(), ex, HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(ModelVerificationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView modelVerificationError(HttpServletRequest req, Exception ex) {
		return setModelAndView(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(InvalidTokenException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView invalidTokenError(HttpServletRequest req, Exception ex) {
		return setModelAndView(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(InvalidParametersException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView invalidParametersError(HttpServletRequest req, Exception ex) {
		return setModelAndView(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(ActionRefusedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ModelAndView actionRefusedError(HttpServletRequest req, Exception ex) {
		return setModelAndView(req.getRequestURL().toString(), ex, HttpStatus.FORBIDDEN.value());
	}

	private ModelAndView setModelAndView(String url, Exception ex, Integer httpCode) {
		ErrorInfo errorInfo = new ErrorInfo(url, ex, httpCode);
		ModelAndView mav = new ModelAndView();

		mav.addObject("errorInfo", errorInfo);
		mav.setViewName("errorPage");
		return mav;
	}
}