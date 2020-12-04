package followit.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

@Data
@RelationshipEntity(type = "PARTNERSHIP")
public class Partnership {

    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private User user;
    @EndNode
    private Company company;
    @Property
    private Date since;

    public Partnership(User user, Company company, Date since) {
        this.user = user;
        this.company = company;
        this.since = since;
    }
}
