package com.ferdous.quiz.service.accesscontrol;

import org.springframework.stereotype.Service;

import com.ferdous.quiz.model.Question;

@Service
public class AccessControlServiceQuestion extends AccessControlServiceUserOwned<Question> {

}
