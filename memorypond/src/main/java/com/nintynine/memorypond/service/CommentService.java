package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.Comment;
import com.nintynine.memorypond.model.request.CommentRequest;
import com.nintynine.memorypond.model.response.CommentResponse;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.repository.CommentRepository;
import com.nintynine.memorypond.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPost(int postId){
        return commentRepository.findAllByPostId(postId).stream()
                .map((post)-> CommentResponse.of(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse createComment(CommentRequest commentRequest, CustomUser user) {
        commentRequest.setMemberId(user.getUserId());
        Comment comment = CommentRequest.toComment(entityManager, commentRequest);
        Comment commetResponse = commentRepository.save(comment);
        return CommentResponse.of(commetResponse);
    }
}
