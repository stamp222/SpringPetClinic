package petclinic.pl.phlodx.spring.nauka.services;

import petclinic.pl.phlodx.spring.nauka.model.Pet;

import java.util.Set;

public interface PetService {
    Set<Pet> findAll();
    Pet save(Pet pet);
    Pet findById(Long id);
}
