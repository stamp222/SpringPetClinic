package petclinic.pl.phlodx.spring.nauka.services;

import petclinic.pl.phlodx.spring.nauka.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findByLastName(String lastName);
    Set<Owner> findAll();
    Owner save(Owner owner);
    Owner findById(Long id);
}
