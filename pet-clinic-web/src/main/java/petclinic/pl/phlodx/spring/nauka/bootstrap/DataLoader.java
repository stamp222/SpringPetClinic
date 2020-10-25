package petclinic.pl.phlodx.spring.nauka.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.pl.phlodx.spring.nauka.model.Owner;
import petclinic.pl.phlodx.spring.nauka.model.Vet;
import petclinic.pl.phlodx.spring.nauka.services.OwnerService;
import petclinic.pl.phlodx.spring.nauka.services.VetService;
import petclinic.pl.phlodx.spring.nauka.services.map.OwnerServiceMap;
import petclinic.pl.phlodx.spring.nauka.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService ;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Jake");
        owner1.setId(1L);
        owner1.setLastName("Peralta");

        Owner owner2 = new Owner();
        owner2.setFirstName("Emy");
        owner2.setId(2L);
        owner2.setLastName("Santiago");

        ownerService.save(owner1);
        ownerService.save(owner2);

        System.out.println("Owners are loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Magda");
        vet1.setId(1L);
        vet1.setLastName("Ptasik");

        Vet vet2 = new Vet();
        vet2.setFirstName("Jacek");
        vet2.setId(2L);
        vet2.setLastName("Szyper");

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Vets are loaded...");
    }
}
