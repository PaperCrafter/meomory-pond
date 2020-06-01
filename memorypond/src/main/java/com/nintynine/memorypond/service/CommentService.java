package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.Comment;
import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.model.projection.CommentProjection;
import com.nintynine.memorypond.model.request.CommentRequest;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.repository.CommentRepository;
import com.nintynine.memorypond.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepsitory memberRepsitory;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<CommentProjection> getCommentsByPost(int postId){
        return commentRepository.findAllByPostId(postId);
    }

    public CommentProjection createComment(CommentRequest commentRequest, CustomUser user) {
        commentRequest.setMemberId(user.getUserId());
        Optional<Member> member = memberRepsitory.findByUsername(user.getUsername());
        if(member.isPresent()){
            commentRequest.setMemberId(member.get().getId());
        }
        Comment comment = new Comment(commentRequest);
        Comment commetResponse = commentRepository.save(comment);

        return commentRepository.findAllById(commetResponse.getId()).get(0);
    }
}
