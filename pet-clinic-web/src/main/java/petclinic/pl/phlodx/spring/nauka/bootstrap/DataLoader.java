package petclinic.pl.phlodx.spring.nauka.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.pl.phlodx.spring.nauka.model.Owner;
import petclinic.pl.phlodx.spring.nauka.model.PetType;
import petclinic.pl.phlodx.spring.nauka.model.Vet;
import petclinic.pl.phlodx.spring.nauka.services.OwnerService;
import petclinic.pl.phlodx.spring.nauka.services.PetTypeService;
import petclinic.pl.phlodx.spring.nauka.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService ;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("DOg");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("CAt");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("PetType: " + savedDogPetType.getName() + " is added");
        System.out.println("PetType: " + savedCatPetType.getName() + " is added");

        Owner owner1 = new Owner();
        owner1.setFirstName("Jake");
        owner1.setLastName("Peralta");

        Owner owner2 = new Owner();
        owner2.setFirstName("Emy");
        owner2.setLastName("Santiago");

        ownerService.save(owner1);
        ownerService.save(owner2);

        System.out.println("Owners are loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Magda");
        vet1.setLastName("Ptasik");

        Vet vet2 = new Vet();
        vet2.setFirstName("Jacek");
        vet2.setLastName("Szyper");

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Vets are loaded...");
    }
}
