package petclinic.pl.phlodx.spring.nauka.services;

import petclinic.pl.phlodx.spring.nauka.model.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}
