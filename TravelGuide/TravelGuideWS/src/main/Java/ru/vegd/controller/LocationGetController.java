package ru.vegd.controller;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.vegd.entity.City;
import ru.vegd.entity.Description;
import ru.vegd.repository.CityRepository;
import ru.vegd.repository.DescriptionRepository;
import ru.vegd.utils.ResponseBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LocationGetController {
    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private DescriptionRepository descriptionRepo;

    @GetMapping(value = "location/get")
    @ResponseBody
    public String getLocation(@RequestParam("name") String name, Model model, HttpServletResponse response) {
        if (name != null && !name.isEmpty()) {
            Optional<City> city = Optional.ofNullable(cityRepo.findByName(name));
            if (city.isPresent()) {
                Optional<Description> description = descriptionRepo.findById(city.get().getId());
                if (description.isPresent()) {
                    return new ResponseBuilder().build(name, description.get().getDescription()).toString();
                }
            }
        }
        return "Not found.";
    }
}
