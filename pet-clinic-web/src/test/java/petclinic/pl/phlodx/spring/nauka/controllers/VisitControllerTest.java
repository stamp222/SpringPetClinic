package petclinic.pl.phlodx.spring.nauka.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;
import petclinic.pl.phlodx.spring.nauka.model.Owner;
import petclinic.pl.phlodx.spring.nauka.model.Pet;
import petclinic.pl.phlodx.spring.nauka.model.PetType;
import petclinic.pl.phlodx.spring.nauka.services.PetService;
import petclinic.pl.phlodx.spring.nauka.services.VisitService;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "Yet another visit";

    @InjectMocks
    VisitController visitController;

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    MockMvc mockMvc;

    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<String, String>();
    private URI visitsUri;

    @BeforeEach
    void setUp() {
        visitController = new VisitController(petService,visitService);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
        Long petId = 1L;
        Long ownerId = 1L;
        when(petService.findById(anyLong())).thenReturn(
                Pet.builder()
                        .id(petId)
                        .birthDay(LocalDate.of(2018, 11, 13))
                        .name("Kittie")
                        .visits(new HashSet<>())
                        .owner(Owner.builder().id(ownerId).lastName("Doe").firstName("Jogn").build())
                        .petType(PetType.builder().name("Dog").build())
                        .build()
        );

        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);

    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void processCreationForm() throws Exception {

        mockMvc.perform(post(visitsUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2018-11-11")
                .param("description", YET_ANOTHER_VISIT_DESCRIPTION))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name(REDIRECT_OWNERS_1))
                    .andExpect(model().attributeExists("visit"));
    }
}