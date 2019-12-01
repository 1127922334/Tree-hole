package com.luntan.demo.mappers;

import com.luntan.demo.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
        @Insert("insert into users(name,account_id,avatar_url,token,gmt_create,gmt_modified,bio)values(#{name},#{accountId},#{avatarUrl},#{token},#{gmtCreate},#{gmtModified},#{bio})")
        void insert(Users user);

    @Select("select * from users where token = #{token}")//如果不是类需要添加注解@Param
   Users findByToken(@Param("token") String token);

    @Select("select * from users where account_id=#{account_id}")
    Users findByAccount_id(@Param("account_id") String account_id);
}
