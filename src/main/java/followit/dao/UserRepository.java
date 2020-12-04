package followit.dao;

import followit.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long> {

    User findByLogin(String login);
    List<User> findByFollowingLogin(String login);
}
