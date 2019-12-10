package com.luntan.demo.Service;

import com.luntan.demo.dto.PagesDTO;
import com.luntan.demo.dto.QuestionDTO;
import com.luntan.demo.mappers.QuestionMapper;
import com.luntan.demo.mappers.UserMapper;
import com.luntan.demo.model.Question;
import com.luntan.demo.model.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private   UserMapper userMapper;
    @Autowired
   private QuestionMapper questionMapper;

    public PagesDTO list(Integer page, Integer size) {
        Integer count = questionMapper.CountNumber();
        PagesDTO pagesDTOS = new PagesDTO();
        pagesDTOS.set_total_page(count,size);
        //防止输入的页数超出范围
        if (page<1) page=1;
        if (page>pagesDTOS.getTotal_Page()) page=pagesDTOS.getTotal_Page();
        pagesDTOS.set_page_dto(page);
        //计算起始点
        Integer pages= size*(page-1);
        List<Question> questionList = questionMapper.list(pages,size);
        //创建questionDTO数据列表
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //给questionDTO赋值
        for(Question question :questionList){
            Users users = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//复制属性
            questionDTO.setUser(users);
            questionDTOList.add(questionDTO);
        }

        pagesDTOS.setQuestionDTOS(questionDTOList);
        return  pagesDTOS;
    }
//分页
    public PagesDTO list(Integer userId, Integer page, Integer size) {
        //获取数量
        Integer count = questionMapper.CountNumber();

        PagesDTO pagesDTOS = new PagesDTO();
        pagesDTOS.set_total_page(count,size);
       //获取页数
        //防止输入的页数超出范围
        if (page<1) page=1;
        if (page>pagesDTOS.getTotal_Page()) page=pagesDTOS.getTotal_Page();
        pagesDTOS.set_page_dto(page);
        //计算起始点
        Integer pages= size*(page-1);
        List<Question> questionList = questionMapper.listByUserId(userId,pages,size);
        //创建questionDTO数据列表
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //给questionDTO赋值
        for(Question question :questionList){
            Users users = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//复制属性
            questionDTO.setUser(users);
            questionDTOList.add(questionDTO);
        }
        pagesDTOS.setQuestionDTOS(questionDTOList);
        return  pagesDTOS;
    }

    public QuestionDTO getById(Integer id) {
       Question question = questionMapper.getById(id);
       QuestionDTO  questionDTO=new QuestionDTO();
       BeanUtils.copyProperties(question,questionDTO);
       Users users = userMapper.findById(question.getCreator());
       questionDTO.setUser(users);
       return  questionDTO;
    }

    public void CreateUpdate(Question question) {
        if (question.getId()==null){
            questionMapper.Create(question);
        }else {
            questionMapper.update_s(question);
        }
    }
}
