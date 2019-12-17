package com.luntan.demo.mappers;

import com.luntan.demo.model.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMapperPlus {
    int incView(Question record);
    int incCommentCount (Question record);
}
