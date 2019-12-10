package com.luntan.demo.mappers;

import com.luntan.demo.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtcreate},#{gmtmodified},#{creator},#{tag})")
    void Create(Question question);
    @Select("select * from question order by gmt_create desc  limit #{pages},#{size}")
    List<Question> list(@Param(value = "pages")Integer pages,@Param(value = "size")Integer size);
    @Select("select count(1) from question")
    Integer CountNumber();
    @Select("select * from question where creator=#{userId} by gmt_create desc  limit #{pages},#{size}")
    List<Question> listByUserId(@Param("userId")Integer userId, @Param(value = "pages")Integer pages,@Param(value = "size")Integer size);
    @Select("select * from question where id =#{id}")
    Question getById(@Param("id") Integer id);
    @Update("update question  set title=#{title}, description=#{description},gmt_modified=#{gmtmodified},tag=#{tag} where id=#{id}")
    void update_s(Question question);
}
