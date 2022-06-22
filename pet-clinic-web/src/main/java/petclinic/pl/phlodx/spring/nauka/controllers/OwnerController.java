package petclinic.pl.phlodx.spring.nauka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import petclinic.pl.phlodx.spring.nauka.model.Owner;
import petclinic.pl.phlodx.spring.nauka.services.OwnerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RequestMapping({"/owners"})
@Controller
public class OwnerController {

    private static final String CREATE_OR_UPDATE_OWNER_FORM= "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }

    @InitBinder
    public void setAllowedField(WebDataBinder databinder) {
        databinder.setDisallowedFields("id");
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> allByLastNameLike = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        if(allByLastNameLike.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if(allByLastNameLike.size() == 1) {
            owner = allByLastNameLike.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", allByLastNameLike);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().id(1L).build());
        return CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            Owner save = ownerService.save(owner);
            return "redirect:/owners/" + save.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String updateCreationOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable Long ownerId, @Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            owner.setId(ownerId);
            Owner save = ownerService.save(owner);
            return "redirect:/owners/" + save.getId();
        }
    }
}
