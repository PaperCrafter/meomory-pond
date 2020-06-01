package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.model.Post;
import com.nintynine.memorypond.model.projection.PostBoardProjection;
import com.nintynine.memorypond.model.projection.PostPageProjection;
import com.nintynine.memorypond.model.Question;
import com.nintynine.memorypond.model.request.PostRequest;
import com.nintynine.memorypond.repository.CommentRepository;
import com.nintynine.memorypond.repository.MemberRepsitory;
import com.nintynine.memorypond.repository.PostRepository;
import com.nintynine.memorypond.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepsitory memberRepsitory;


    @Transactional(readOnly = true)
    public Page<PostPageProjection> getPostList(Pageable pageable){
        try{
            if(pageable.getPageSize() > 10)
                throw new IllegalArgumentException();
        }catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
        return postRepository.findAllBy(pageable);
    }

    @Transactional(readOnly = true)
    public PostBoardProjection getPost(int postId) {
        return postRepository.findAllById(postId).get(0);
    }

    @Transactional(readOnly = true)
    public int countPostsByUsername(String username) {
        return postRepository.countPostsByMemberUsername(username);
    }


    @Transactional
    public Post createPost(PostRequest postRequest){
        Optional<Member> member = memberRepsitory.findByUsername(postRequest.getUsername());
        Optional<Question> question = questionRepository.findById(postRequest.getQuestionId());
        LocalDateTime currentTime = LocalDateTime.now();

        int weight = calcWeight(question.get().getWeight(), postRequest.getComment().length());
        Post post = Post.builder()
                .content(postRequest.getComment())
                .member(member.get())
                .weight(weight)
                .question(question.get())
                .createAt(currentTime.toString())
                .updateAt(currentTime.toString())
                .build();

        return postRepository.save(post);
    }

    private int calcWeight(int defaultWeight, int stringLength){
        Random random = new Random();
        int weight = defaultWeight + stringLength * (random.nextInt(1) + 1);
        weight = Math.min(weight, 600);
        return weight;
    }

    @Transactional
    public boolean deletePost(int postId){
        commentRepository.deleteAllByPostId(postId);
        postRepository.deleteById(postId);
        return true;
    }
}
