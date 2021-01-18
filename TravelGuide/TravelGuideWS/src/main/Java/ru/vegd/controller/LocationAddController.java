package ru.vegd.controller;

import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.vegd.entity.City;
import ru.vegd.entity.Description;
import ru.vegd.repository.CityRepository;
import ru.vegd.repository.DescriptionRepository;
import ru.vegd.util.JsonToEntityConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@PropertySource("sec.properties")
public class LocationAddController {

    @Value("${sec.key}")
    private String key;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private DescriptionRepository descriptionRepo;

    @PostMapping("/location/add")
    @ResponseBody
    public String addLocation(HttpServletRequest request) throws IOException {

        Map<String, String> resultMap = JsonToEntityConverter.convert(request.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator())));

        if (resultMap != null && key.equals(resultMap.get("key"))) {
            City city = new City();
            Description description = new Description();

            city.setName(resultMap.get("name"));
            if (cityRepo.findByName(city.getName()) == null) {
                cityRepo.save(city);
                Long cityId = cityRepo.findByName(city.getName()).getId();

                description.setId(cityId);
                description.setDescription(resultMap.get("description"));
                descriptionRepo.save(description);

                return "Successfully created";
            } else {
                return "City already exist";
            }
        } else {
            return "Forbidden";
        }
    }
}
