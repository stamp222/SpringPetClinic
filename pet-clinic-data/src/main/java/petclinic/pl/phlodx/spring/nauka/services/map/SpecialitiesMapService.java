package petclinic.pl.phlodx.spring.nauka.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import petclinic.pl.phlodx.spring.nauka.model.Speciality;
import petclinic.pl.phlodx.spring.nauka.services.SpecialtiesService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class SpecialitiesMapService extends AbstractMapService<Speciality, Long> implements SpecialtiesService {
    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}
