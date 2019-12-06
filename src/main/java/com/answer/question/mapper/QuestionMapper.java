package com.answer.question.mapper;

import com.answer.question.dto.QuestionDTO;
import com.answer.question.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question")
    List<QuestionDTO> selectbypagehelper();

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByuserId(@Param(value = "userId")Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId")Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id")Integer id);

    @Update("update question set title = #{title},description = #{description},gmt_modified = #{gmtModified},tag= #{tag} where id = #{id}")
    void update(Question question);
}
