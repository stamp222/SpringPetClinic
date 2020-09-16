package petclinic.pl.phlodx.spring.nauka.services;

import petclinic.pl.phlodx.spring.nauka.model.Vet;

import java.util.Set;

public interface VetService {
    Set<Vet> findAll();
    Vet save(Vet vet);
    Vet findById(Long id);
}
