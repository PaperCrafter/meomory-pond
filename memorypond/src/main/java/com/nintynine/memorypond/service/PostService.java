package com.nintynine.memorypond.service;

<<<<<<< HEAD
import com.nintynine.memorypond.domain.entity.Post;
import com.nintynine.memorypond.domain.entity.Question;
import com.nintynine.memorypond.domain.request.PostRequest;
import com.nintynine.memorypond.domain.response.PostResponse;
import com.nintynine.memorypond.domain.security.CustomUser;
=======
import com.nintynine.memorypond.model.Post;
import com.nintynine.memorypond.model.Question;
import com.nintynine.memorypond.model.projection.PostBoardProjection;
import com.nintynine.memorypond.model.projection.PostPageProjection;
import com.nintynine.memorypond.model.request.PostRequest;
import com.nintynine.memorypond.model.response.PostResponse;
import com.nintynine.memorypond.model.user.CustomUser;
>>>>>>> 8dbe5ea692db25efb21a0eb2658d196666f53a63
import com.nintynine.memorypond.repository.CommentRepository;
import com.nintynine.memorypond.repository.PostRepository;
import com.nintynine.memorypond.repository.QuestionRepository;
import com.nintynine.memorypond.util.WeightUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
=======
import javax.persistence.EntityManager;
import java.util.List;
>>>>>>> 8dbe5ea692db25efb21a0eb2658d196666f53a63
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Page<PostResponse> getPostList(Pageable pageable){
        try{
            if(pageable.getPageSize() > 10)
                throw new IllegalArgumentException();
        }catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
<<<<<<< HEAD
        Page<Post> postPage = postRepository.findAll(pageable);
=======
        Page<Post> postPage = postRepository.findAllBy(pageable);
>>>>>>> 8dbe5ea692db25efb21a0eb2658d196666f53a63
        return postPage.map((entity)-> PostResponse.of(entity));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(int postId) {
        Post postResponse = postRepository.findById(postId).get();
        return PostResponse.of(postResponse);
    }

    @Transactional(readOnly = true)
    public int countPostsByUsername(String username) {
        return postRepository.countPostsByMemberUsername(username);
    }


    @Transactional
    public PostResponse createPost(PostRequest postRequest, CustomUser user){
        Optional<Question> question = questionRepository.findById(postRequest.getQuestionId());
        int weight = WeightUtils.calcWeight(question.get().getWeight(), postRequest.getComment().length());
        postRequest.setMemberId(user.getUserId());
        postRequest.setWeight(weight);

        Post post = PostRequest.toPost(postRequest);
        return PostResponse.of(postRepository.save(post));
    }

    @Transactional
    public void deletePost(int postId){
        commentRepository.deleteAllByPostId(postId);
        postRepository.deleteById(postId);
    }
}
