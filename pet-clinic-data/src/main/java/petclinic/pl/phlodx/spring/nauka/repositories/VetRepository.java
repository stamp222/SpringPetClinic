package petclinic.pl.phlodx.spring.nauka.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.pl.phlodx.spring.nauka.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
