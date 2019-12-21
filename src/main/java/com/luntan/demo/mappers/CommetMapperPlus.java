package com.luntan.demo.mappers;

import com.luntan.demo.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommetMapperPlus {
    int incCommentCount (Comment comment);
}
