package com.answer.question.service;

import com.answer.question.dto.CommentDTO;
import com.answer.question.enums.CommentTypeEnum;
import com.answer.question.enums.NotificationStatusEnum;
import com.answer.question.enums.NotificationTypeEnum;
import com.answer.question.exception.CustomException;
import com.answer.question.exception.CustomizeErrorCode;
import com.answer.question.mapper.*;
import com.answer.question.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private NotificationMapper notificationMapper;


    public void insert(Comment comment,User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExits(comment.getType())) {
            throw new CustomException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbcomment == null) {
                throw new CustomException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(dbcomment.getParentId());

            if (question == null) {
                throw new CustomException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);

            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);
            //创建通知
            createNotify(comment, dbcomment.getCommentator(),commentator.getName(),question.getTitle(),NotificationTypeEnum.REPLY_COMMENT,question.getId());

        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());

            if (question == null) {
                throw new CustomException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            } else {
                question.setCommentCount(1);
                commentMapper.insert(comment);
                questionExtMapper.incCommentCount(question);
                createNotify(comment,question.getCreator(), commentator.getName(),question.getTitle(), NotificationTypeEnum.REPLY_QUESTION,question.getId());

            }

        }



    }

    private void createNotify(Comment comment, Long receiver,
                              String outerTitle, String notifierName,
                              NotificationTypeEnum notificationType,Long outerId) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }


    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }

        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());


        return commentDTOS;
    }
}
