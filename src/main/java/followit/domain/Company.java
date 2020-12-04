package followit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NodeEntity
public class Company {

    @Id @GeneratedValue
    private Long id;
    private String companyName;
    @Relationship(type = "PARTNERSHIP", direction = Relationship.UNDIRECTED)
    private List<Partnership> partners;

    public void addPartner(User user, Date since) {
        partners.add(new Partnership(user, this, since));
    }

    public Company(String companyName) {
        partners = new ArrayList<>();
        this.companyName = companyName;
    }

}
