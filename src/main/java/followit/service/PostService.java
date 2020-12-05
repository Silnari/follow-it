package followit.service;

import com.google.common.collect.ImmutableList;
import followit.dao.PostRepository;
import followit.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void addPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> getAll() {
        return ImmutableList.copyOf(postRepository.findAll()).stream()
                .sorted(Comparator.comparing(Post::getPostedAt).reversed()).collect(Collectors.toList());
    }
}
