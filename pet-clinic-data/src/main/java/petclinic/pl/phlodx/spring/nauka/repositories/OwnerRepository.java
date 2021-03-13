package petclinic.pl.phlodx.spring.nauka.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.pl.phlodx.spring.nauka.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
