package com.nintynine.memorypond.Service;

import com.nintynine.memorypond.Model.Comment;
import com.nintynine.memorypond.Model.Member;
import com.nintynine.memorypond.Model.Projection.CommentProjection;
import com.nintynine.memorypond.Model.Request.CommentRequest;
import com.nintynine.memorypond.Repository.CommentRepository;
import com.nintynine.memorypond.Repository.MemberRepsitory;
import com.nintynine.memorypond.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepsitory memberRepsitory;

    public List<CommentProjection> getCommentsByPost(int postId){
        return commentRepository.findAllByPostId(postId);
    }

    public CommentProjection createComment(CommentRequest commentRequest, User user) {
        Optional<Member> member = memberRepsitory.findByUsername(user.getUsername());
        if(member.isPresent()){
            commentRequest.setMemberId(member.get().getId());
        }
        Comment comment = new Comment(commentRequest);
        Comment commetResponse = commentRepository.save(comment);

        return commentRepository.findAllById(commetResponse.getId()).get(0);
    }
}
