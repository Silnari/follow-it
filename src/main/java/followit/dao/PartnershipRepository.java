package followit.dao;

import followit.domain.Company;
import followit.domain.Partnership;
import followit.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PartnershipRepository extends Neo4jRepository<Partnership, Long> {

    Partnership findByCompany(Company company);
    Partnership findByUser(User user);
}
