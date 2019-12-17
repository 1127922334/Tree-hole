package com.luntan.demo.Service;

import com.luntan.demo.dto.PagesDTO;
import com.luntan.demo.dto.QuestionDTO;
import com.luntan.demo.exception.DIYError;
import com.luntan.demo.exception.enum_ErrorCode;
import com.luntan.demo.mappers.QuestionMapper;
import com.luntan.demo.mappers.QuestionMapperPlus;
import com.luntan.demo.mappers.UsersMapper;
import com.luntan.demo.model.Question;
import com.luntan.demo.model.QuestionExample;
import com.luntan.demo.model.Users;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
   private QuestionMapper questionMapper;
    @Autowired
    private QuestionMapperPlus questionMapperPlus;
    public PagesDTO list(Integer page, Integer size) {

        Integer count = (int)questionMapper.countByExample(new QuestionExample());
        PagesDTO pagesDTOS = new PagesDTO();
        pagesDTOS.set_total_page(count,size);
        //防止输入的页数超出范围
        if (page<1) page=1;
        if (page>pagesDTOS.getTotal_Page()) page=pagesDTOS.getTotal_Page();
        pagesDTOS.set_page_dto(page);
        //计算起始点
        Integer pages= size*(page-1);
        QuestionExample questionExample2 =new QuestionExample();
        questionExample2.setOrderByClause("id desc");
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample2,new RowBounds(pages,size));
        //创建questionDTO数据列表
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //给questionDTO赋值
        for(Question question :questionList){
            Users users = usersMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//复制属性
            questionDTO.setUser(users);
            questionDTOList.add(questionDTO);
        }
        pagesDTOS.setQuestionDTOS(questionDTOList);
        return  pagesDTOS;
    }
//分页
    public PagesDTO list(Long userId, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        //获取数量
        Integer count =  (int)questionMapper.countByExample(questionExample);
        PagesDTO pagesDTOS = new PagesDTO();
        pagesDTOS.set_total_page(count,size);
       //获取页数
        //防止输入的页数超出范围
        if (page<1) page=1;
        if (page>pagesDTOS.getTotal_Page()) page=pagesDTOS.getTotal_Page();
        pagesDTOS.set_page_dto(page);
        //计算起始点
        Integer pages= size*(page-1);
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.setOrderByClause("id desc");
        questionExample1.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample1,new RowBounds(pages,size));
        //创建questionDTO数据列表
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //给questionDTO赋值
        for(Question question :questionList){
            Users users = usersMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//复制属性
            questionDTO.setUser(users);
            questionDTOList.add(questionDTO);
        }
        pagesDTOS.setQuestionDTOS(questionDTOList);
        return  pagesDTOS;
    }
//查询文章
    public QuestionDTO getById(Long id) {
       Question question = questionMapper.selectByPrimaryKey(id);
       if (question == null){
           throw new DIYError(enum_ErrorCode.QUESTION_NOT_FOUND);
       }
       QuestionDTO  questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);//复制属性
         Users users = usersMapper.selectByPrimaryKey(question.getCreator());
         questionDTO.setUser(users);
       return  questionDTO;
    }

    public void CreateUpdate(Question question) {
        if (question.getId()==null){
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insertSelective(question);
        }else {
            int update = questionMapper.updateByPrimaryKeySelective(question);
            if(update < 1 ){
                    throw new DIYError(enum_ErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionMapperPlus.incView(question);
    }
}
