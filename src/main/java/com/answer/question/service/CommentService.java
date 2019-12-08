package com.answer.question.service;

import com.answer.question.enums.CommentTypeEnum;
import com.answer.question.exception.CustomException;
import com.answer.question.exception.CustomizeErrorCode;
import com.answer.question.mapper.CommentMapper;
import com.answer.question.mapper.QuestionExtMapper;
import com.answer.question.mapper.QuestionMapper;
import com.answer.question.model.Comment;
import com.answer.question.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;


    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExits(comment.getType())) {
            throw new CustomException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbcomment == null){
                throw new CustomException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);

        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());

            if (question == null){
                throw new CustomException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }else{
                question.setCommentCount(1);
                commentMapper.insert(comment);
                questionExtMapper.incCommentCount(question);
            }

        }


    }
}
