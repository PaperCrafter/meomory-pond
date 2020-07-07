package com.nintynine.memorypond.service;

import com.nintynine.memorypond.domain.exception.ResourceNotFoundException;
import com.nintynine.memorypond.domain.entity.Comment;
import com.nintynine.memorypond.domain.request.CommentRequest;
import com.nintynine.memorypond.domain.response.CommentResponse;
import com.nintynine.memorypond.domain.security.CustomUser;
import com.nintynine.memorypond.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Comment commetResponse;
        try {
            commentRequest.setMemberId(user.getUserId());
            Comment comment = CommentRequest.toComment(commentRequest);
            commetResponse = commentRepository.save(comment);
        }catch (Exception e){
            throw new ResourceNotFoundException("게시글이 존재하지 않습니다.");
        }
        return CommentResponse.of(commetResponse);
    }
}
