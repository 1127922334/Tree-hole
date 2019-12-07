package com.luntan.demo.mappers;

import com.luntan.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtcreate},#{gmtmodified},#{creator},#{tag})")
    void Create(Question question);
    @Select("select * from question limit #{pages},#{size}")
    List<Question> list(@Param(value = "pages")Integer pages,@Param(value = "size")Integer size);
    @Select("select count(1) from question")
    Integer CountNumber();
}
