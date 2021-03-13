package petclinic.pl.phlodx.spring.nauka.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.pl.phlodx.spring.nauka.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
