package followit.dao;

import followit.domain.Post;
import followit.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PostRepository extends Neo4jRepository<Post, Long> {

    List<Post> findByAuthor(User user);
}
