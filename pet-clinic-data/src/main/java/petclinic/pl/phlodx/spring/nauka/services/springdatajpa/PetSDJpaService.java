package petclinic.pl.phlodx.spring.nauka.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import petclinic.pl.phlodx.spring.nauka.model.Pet;
import petclinic.pl.phlodx.spring.nauka.repositories.OwnerRepository;
import petclinic.pl.phlodx.spring.nauka.repositories.PetRepository;
import petclinic.pl.phlodx.spring.nauka.repositories.PetTypeRepository;
import petclinic.pl.phlodx.spring.nauka.repositories.VisitRepository;
import petclinic.pl.phlodx.spring.nauka.services.PetService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {
    private final VisitRepository visitRepository;
    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;


    public PetSDJpaService(VisitRepository visitRepository, OwnerRepository ownerRepository, PetTypeRepository petTypeRepository, PetRepository petRepository) {
        this.visitRepository = visitRepository;
        this.ownerRepository = ownerRepository;
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
