package com.nintynine.memorypond.Service;

import com.nintynine.memorypond.Model.Member;
import com.nintynine.memorypond.Model.Post;
import com.nintynine.memorypond.Model.Question;
import com.nintynine.memorypond.Model.Request.PostRequest;
import com.nintynine.memorypond.Repository.MemberRepsitory;
import com.nintynine.memorypond.Repository.PostRepository;
import com.nintynine.memorypond.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepsitory memberRepsitory;


    @Transactional(readOnly = true)
    public Page<Post> getPostList(Pageable pageable){
        try{
            if(pageable.getPageSize() > 10)
                throw new IllegalArgumentException();
        }catch(IllegalArgumentException ex){

        }
        return postRepository.findAll(pageable);
    }

    @Transactional
    public Post createPost(PostRequest postRequest){
        Optional<Member> member = memberRepsitory.findByUsername(postRequest.getUsername());
        Optional<Question> question = questionRepository.findById(postRequest.getQuestionId());
        LocalDateTime currentTime = LocalDateTime.now();
        Post post = Post.builder()
                .content(postRequest.getComment())
                .member(member.get())
                .question(question.get())
                .createAt(currentTime.toString())
                .updateAt(currentTime.toString())
                .build();
        return postRepository.save(post);
    }
}
