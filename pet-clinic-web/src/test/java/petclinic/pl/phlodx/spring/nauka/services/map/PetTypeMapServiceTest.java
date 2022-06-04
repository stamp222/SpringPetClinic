package petclinic.pl.phlodx.spring.nauka.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petclinic.pl.phlodx.spring.nauka.model.PetType;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetTypeMapServiceTest {

    private final Long TYPE_TYPE_ID = 1L;

    PetTypeMapService petTypeService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petTypeService.save(PetType.builder().name("Vishala").build());
    }

    @Test
    void findAll() {
        Set<PetType> all = petTypeService.findAll();
        assertEquals(all.size(),1);
    }

    @Test
    void deleteById() {
        petTypeService.deleteById(TYPE_TYPE_ID);
        assertEquals(0, petTypeService.findAll().size());
    }

    @Test
    void delete() {
        petTypeService.delete(petTypeService.findById(TYPE_TYPE_ID));
        assertEquals(0,petTypeService.findAll().size());
    }

    @Test
    void save() {
        PetType petType = new PetType("Aligator");
        PetType save = petTypeService.save(petType);
        assertNotNull(save);
        assertNotNull(save.getId());

    }

    @Test
    void findById() {
        PetType byId = petTypeService.findById(1L);
        assertNotNull(byId);
        assertEquals(TYPE_TYPE_ID, byId.getId());
    }
}