package petclinic.pl.phlodx.spring.nauka.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petclinic.pl.phlodx.spring.nauka.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerMapService ownerServiceMap;
    private final Long OWNER_ID = 1L;
    final String LAST_NAME = "Peralta";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerServiceMap.save(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(OWNER_ID);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(OWNER_ID));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id1 = 2L;
        Owner owner = ownerServiceMap.save(Owner.builder().id(id1).build());
        assertEquals(id1, owner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(OWNER_ID);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(LAST_NAME);
        assertNotNull(smith);
        assertEquals(OWNER_ID, smith.getId());
    }
    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("foo");
        assertNull(smith);
    }

}