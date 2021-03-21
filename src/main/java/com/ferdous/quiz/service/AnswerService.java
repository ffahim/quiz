package com.ferdous.quiz.service;

import java.util.List;

import com.ferdous.quiz.exceptions.ResourceUnavailableException;
import com.ferdous.quiz.exceptions.UnauthorizedActionException;
import com.ferdous.quiz.model.Answer;
import com.ferdous.quiz.model.Question;

public interface AnswerService {
	Answer save(Answer answer) throws UnauthorizedActionException;

	Answer find(Long id) throws ResourceUnavailableException;

	Answer update(Answer newAnswer) throws UnauthorizedActionException, ResourceUnavailableException;

	void delete(Answer answer) throws UnauthorizedActionException, ResourceUnavailableException;

	List<Answer> findAnswersByQuestion(Question question);

	int countAnswersInQuestion(Question question);
}
