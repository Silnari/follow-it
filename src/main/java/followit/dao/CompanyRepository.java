package followit.dao;

import followit.domain.Company;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {

    Company findByCompanyName(String companyName);
}
