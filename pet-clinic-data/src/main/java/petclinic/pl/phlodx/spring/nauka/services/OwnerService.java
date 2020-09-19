package petclinic.pl.phlodx.spring.nauka.services;

import petclinic.pl.phlodx.spring.nauka.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);

}
