package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.entity.City;
import ru.vegd.entity.Description;
import ru.vegd.repository.CityRepository;
import ru.vegd.repository.DescriptionRepository;

import java.util.Optional;

import static ru.vegd.controller.PathConstants.PATH_LOCATION_UPDATE;
import static ru.vegd.controller.PathConstants.REDIRECT;

@Controller
public class LocationUpdateController {
    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private DescriptionRepository descriptionRepo;

    @GetMapping("/location/update")
    public String form(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("descr", new Description());

        return PATH_LOCATION_UPDATE;
    }

    @PostMapping("/location/updateLocation")
    public String addLocation(
            @ModelAttribute("city") City city,
            @ModelAttribute("descr") Description description) {

        Optional<City> nCity = Optional.ofNullable(cityRepo.findByName(city.getName()));

        if (nCity.isPresent()) {
            description.setId(nCity.get().getId());
            descriptionRepo.save(description);
        }

        return REDIRECT;
    }
}
