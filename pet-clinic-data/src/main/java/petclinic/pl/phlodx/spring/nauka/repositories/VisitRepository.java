package petclinic.pl.phlodx.spring.nauka.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.pl.phlodx.spring.nauka.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
