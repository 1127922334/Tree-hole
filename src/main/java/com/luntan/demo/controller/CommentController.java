package com.luntan.demo.controller;

import com.luntan.demo.Service.CommentService;
import com.luntan.demo.dto.CommentCreateDTO;
import com.luntan.demo.dto.CommentDTO;
import com.luntan.demo.dto.ResultDTO;
import com.luntan.demo.enums.CommentTypeEnums;
import com.luntan.demo.exception.DIYError;
import com.luntan.demo.exception.enum_ErrorCode;
import com.luntan.demo.mappers.CommentMapper;
import com.luntan.demo.model.Comment;
import com.luntan.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public  Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                        HttpServletRequest request){
       Users users = (Users) request.getSession().getAttribute("user");
       if (users==null){
           return ResultDTO.errorOf(enum_ErrorCode.NO_LOGIN);
       }
       if (commentCreateDTO == null||commentCreateDTO.getComment() == null||commentCreateDTO.getComment() ==""){
           throw  new DIYError(enum_ErrorCode.CONTENT_IS_EMPTY);
       }
        Comment comment = new Comment();
        comment.setComment(commentCreateDTO.getComment());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(users.getId());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        commentService.insert(comment);

        return ResultDTO.okOf(null);
    }
    @ResponseBody
    @RequestMapping("/comment/{id}")
    public  ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){
       List<CommentDTO>  commentDTOList = commentService.listBytargetId(id,CommentTypeEnums.COMMENT);
        return ResultDTO.okOf(commentDTOList);
    }
}
