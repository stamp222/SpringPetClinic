package petclinic.pl.phlodx.spring.nauka.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import petclinic.pl.phlodx.spring.nauka.model.Owner;
import petclinic.pl.phlodx.spring.nauka.model.Pet;
import petclinic.pl.phlodx.spring.nauka.model.Visit;
import petclinic.pl.phlodx.spring.nauka.services.PetService;
import petclinic.pl.phlodx.spring.nauka.services.VisitService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
@Slf4j
@RequestMapping
public class VisitController {

    private static final String VIEWS_VISITS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";
    private final VisitService visitService;
    private final PetService petService;


    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @InitBinder
    public void initBinder(WebDataBinder databinder) {
        databinder.setDisallowedFields("id");

        databinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport()  {
            @Override
            public void setAsText(String text) {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("visit")
    public Visit populatePetAndVisit(@PathVariable Long petId, Model model, Owner owner) {
        Pet pet = petService.findById(petId);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }


    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initCreationForm(Model model, @PathVariable Long petId) {
        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processCreationForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
        } else {
            log.info("visit Saved");
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }

}
