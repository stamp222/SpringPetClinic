package petclinic.pl.phlodx.spring.nauka.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import petclinic.pl.phlodx.spring.nauka.model.PetType;
import petclinic.pl.phlodx.spring.nauka.services.PetTypeService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale)  {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType findPetType : findPetTypes) {
            if (findPetType.getName().equals(text)) {
                return findPetType;
            }

        }
        throw new ParseException("Type not found " + text , 0);
    }
}
