package com.luntan.demo.mappers;

import com.luntan.demo.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
        @Insert("insert into users(name,account_id,avatar_url,token,gmt_create,gmt_modified)values(#{name},#{accountId},#{avatarUrl},#{token},#{gmtCreate},#{gmtModified})")
        void insert(Users user);

    @Select("select * from users where  token = #{token}")
   Users findByToken(String token);
}
