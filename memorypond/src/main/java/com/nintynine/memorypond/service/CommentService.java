package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.Comment;
import com.nintynine.memorypond.model.request.CommentRequest;
import com.nintynine.memorypond.model.response.CommentResponse;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPost(int postId){
        return commentRepository.findAllByPostId(postId).stream()
                .map((post)-> CommentResponse.of(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse createComment(CommentRequest commentRequest, CustomUser user) {
        commentRequest.setMemberId(user.getUserId());
        Comment comment = CommentRequest.toComment(commentRequest);
        Comment commetResponse = commentRepository.save(comment);
        return CommentResponse.of(commetResponse);
    }
}
