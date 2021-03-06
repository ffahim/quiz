package com.ferdous.quiz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferdous.quiz.exceptions.ActionRefusedException;
import com.ferdous.quiz.exceptions.ResourceUnavailableException;
import com.ferdous.quiz.exceptions.UnauthorizedActionException;
import com.ferdous.quiz.model.Answer;
import com.ferdous.quiz.model.Question;
import com.ferdous.quiz.repository.AnswerRepository;

@Service("AnswerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {

	private static final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionService questionService;

	@Autowired
	public AnswerServiceImpl(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@Override
	public Answer find(Long id) throws ResourceUnavailableException {
		Answer answer = answerRepository.findOne(id);

		if (answer == null) {
			logger.error("Answer " + id + " not found");
			throw new ResourceUnavailableException("Answer " + id + " not found");
		}

		return answer;
	}

	@Override
	public Answer save(Answer answer) throws UnauthorizedActionException {
		return answerRepository.save(answer);
	}

	@Override
	public Answer update(Answer newAnswer) throws ResourceUnavailableException, UnauthorizedActionException {
		Answer currentAnswer = find(newAnswer.getId());

		mergeAnswers(currentAnswer, newAnswer);
		return answerRepository.save(currentAnswer);
	}

	@Override
	public void delete(Answer answer) throws ResourceUnavailableException, UnauthorizedActionException {

		if (questionService.checkIsCorrectAnswer(answer.getQuestion(), answer.getId())) {
			throw new ActionRefusedException("The correct answer can't be deleted");
		}

		answerRepository.delete(answer);
	}

	private void mergeAnswers(Answer currentAnswer, Answer newAnswer) {
		currentAnswer.setText(newAnswer.getText());

		if (newAnswer.getOrder() != null) {
			currentAnswer.setOrder(newAnswer.getOrder());
		}
	}

	@Override
	public List<Answer> findAnswersByQuestion(Question question) {
		return answerRepository.findByQuestionOrderByOrderAsc(question);
	}

	@Override
	public int countAnswersInQuestion(Question question) {
		return answerRepository.countByQuestion(question);
	}

}
