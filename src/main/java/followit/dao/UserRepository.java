package followit.dao;

import followit.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (u:User) <- [:FOLLOWS] - (following:User) " +
            "WHERE NOT u.login = $userLogin " +
            "WITH u, count(following) AS howManyFollowing " +
            "RETURN u ORDER BY howManyFollowing DESC")
    List<User> findMostFollowedWithoutGiven(@Param("userLogin") String userLogin);
    @Query("MATCH (u:User{login: $userLogin}) - [:FOLLOWS] -> (following:User) <- [:FOLLOWS] -(coFollower:User) " +
            "WHERE NOT (u) - [:FOLLOWS] -> (coFollower) " +
            "WITH coFollower, count(following) AS n " +
            "RETURN coFollower ORDER BY n DESC")
    List<User> findRecommendedToFollow(@Param("userLogin") String userLogin);
    User findByLoginIgnoreCase(String login);
    List<User> findByFollowingLogin(String login);
    boolean existsByLogin(String login);
}
