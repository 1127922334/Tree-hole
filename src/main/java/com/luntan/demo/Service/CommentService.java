package com.luntan.demo.Service;

import com.luntan.demo.dto.CommentDTO;
import com.luntan.demo.enums.CommentTypeEnums;
import com.luntan.demo.exception.DIYError;
import com.luntan.demo.exception.enum_ErrorCode;
import com.luntan.demo.mappers.*;
import com.luntan.demo.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionMapperPlus questionMapperPlus;
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    CommetMapperPlus commetMapperPlus;
    //实现事务回滚
    @Transactional
    public void insert(Comment comment) {

        if (comment.getParentId()== null|| comment.getParentId()==0){
            throw  new DIYError(enum_ErrorCode.COMMENT_NOT_FOUND);
        }
        if (comment.getType()==null|| !CommentTypeEnums.isExist(comment.getType())){
            throw  new DIYError(enum_ErrorCode.TYPE_PARAM_ERROR);
        }
        if (comment.getType() == CommentTypeEnums.COMMENT.getType()){
            //回复评论
          Comment comment1 =  commentMapper.selectByPrimaryKey(comment.getParentId());
          if (comment1==null){
              throw new DIYError(enum_ErrorCode.COMMENT_NOT_FOUND);
          }
            //增加评论数
            Comment comment2 = new Comment();
            comment2.setId(comment.getParentId());
            comment2.setCommentCount(1);
            commentMapper.insertSelective(comment);
          commetMapperPlus.incCommentCount(comment2);


        }else {
            //回复问题
           Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question==null){
                throw  new DIYError(enum_ErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionMapperPlus.incCommentCount(question);
        }
    }

    public List<CommentDTO> listBytargetId(Long id, CommentTypeEnums type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type.getType());
        if (type.getType()==1){
            commentExample.setOrderByClause("gmt_create desc");
        }else {
            commentExample.setOrderByClause("gmt_create asc");
        }

        List<Comment> comments= commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        //获取评论者
        //使用表达式获取去重复的评论人
      Set<Long> commentators =  comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> usersId = new ArrayList<>();
        usersId.addAll(commentators);
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andIdIn(usersId);
        List<Users> usersList =  usersMapper.selectByExample(usersExample);

        //使用表达式将查询到的评论人信息转换成Map,键值对
       Map<Long,Users> usersMap= usersList.stream().collect(Collectors.toMap(users->users.getId(), users->users));

        //转化comment为commentDTO
      List<CommentDTO> commentDTOList =  comments.stream().map(comment -> {
           CommentDTO commentDTO = new CommentDTO();
          BeanUtils.copyProperties(comment,commentDTO);
          commentDTO.setUser(usersMap.get(comment.getCommentator()));
           return commentDTO;
       }).collect(Collectors.toList());
        return commentDTOList;
    }
}
