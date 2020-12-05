package followit.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;

@Data
@NodeEntity
public class Post {

    @Id @GeneratedValue
    private Long id;
    private String content;
    private Date postedAt;
    @Relationship(type = "POSTED_BY")
    private User author;
}
