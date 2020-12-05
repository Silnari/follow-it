package followit.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @Relationship(type = "PARTNERSHIP")
    private List<Partnership> sponsors;
    @Relationship(type = "FOLLOWS")
    private Set<User> following;

    public User() {
        sponsors = new ArrayList<>();
        following = new HashSet<>();
    }

    public User(String login, String firstName, String lastName) {
        sponsors = new ArrayList<>();
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
