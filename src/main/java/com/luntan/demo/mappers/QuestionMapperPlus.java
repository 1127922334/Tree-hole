package com.luntan.demo.mappers;

import com.luntan.demo.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapperPlus {
    int incView(Question record);
    int incCommentCount (Question record);
    List<Question> selectRelated(Question question);
    List<Question> selectHot();

}
