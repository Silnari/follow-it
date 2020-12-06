package followit.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NodeEntity
public class User {

    @Id @GeneratedValue
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    @Relationship(type = "FOLLOWS")
    private Set<User> following;

    public User() {
        following = new HashSet<>();
    }

    public User(String login, String firstName, String lastName) {
        following = new HashSet<>();
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void follows(User user) {
        following.add(user);
    }

    public void unfollow(User user) {
        following.removeIf(u -> u.getLogin().equals(user.getLogin()));
    }
}
