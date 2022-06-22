package petclinic.pl.phlodx.spring.nauka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import petclinic.pl.phlodx.spring.nauka.model.Owner;
import petclinic.pl.phlodx.spring.nauka.services.OwnerService;

import java.util.List;
import java.util.Set;

@RequestMapping({"/owners"})
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
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

        List<Owner> allByLastNameLike = ownerService.findAllByLastNameLike(owner.getLastName());

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
}
