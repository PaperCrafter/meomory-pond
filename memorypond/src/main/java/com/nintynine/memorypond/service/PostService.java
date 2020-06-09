package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.Post;
import com.nintynine.memorypond.model.Question;
import com.nintynine.memorypond.model.projection.PostBoardProjection;
import com.nintynine.memorypond.model.projection.PostPageProjection;
import com.nintynine.memorypond.model.request.PostRequest;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.repository.CommentRepository;
import com.nintynine.memorypond.repository.PostRepository;
import com.nintynine.memorypond.repository.QuestionRepository;
import com.nintynine.memorypond.util.WeightUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;

    private final EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public Post createPost(PostRequest postRequest, CustomUser user){
        Optional<Question> question = questionRepository.findById(postRequest.getQuestionId());
        int weight = WeightUtil.calcWeight(question.get().getWeight(), postRequest.getComment().length());
        postRequest.setMemberId(user.getUserId());
        postRequest.setWeight(weight);

        Post post = PostRequest.toPost(entityManager, postRequest);
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(int postId){
        commentRepository.deleteAllByPostId(postId);
        postRepository.deleteById(postId);
    }
}
