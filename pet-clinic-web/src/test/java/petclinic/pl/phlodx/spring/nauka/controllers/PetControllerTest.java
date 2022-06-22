package petclinic.pl.phlodx.spring.nauka.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import petclinic.pl.phlodx.spring.nauka.model.Owner;
import petclinic.pl.phlodx.spring.nauka.model.PetType;
import petclinic.pl.phlodx.spring.nauka.services.OwnerService;
import petclinic.pl.phlodx.spring.nauka.services.PetService;
import petclinic.pl.phlodx.spring.nauka.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetService petService;

    @InjectMocks
    PetController petController;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    MockMvc mockMvc;

    Owner owner;

    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {

        owner = Owner.builder().id(1L).build();

        petTypes = new HashSet<>();

        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        petController = new PetController(petService, ownerService, petTypeService);

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void populatePetTypes() {
    }

    @Test
    void findOwner() {
    }

    @Test
    void initOwnerBinder() {
    }
}