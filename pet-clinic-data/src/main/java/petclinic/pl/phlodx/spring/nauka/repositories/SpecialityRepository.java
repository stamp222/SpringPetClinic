package petclinic.pl.phlodx.spring.nauka.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.pl.phlodx.spring.nauka.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
