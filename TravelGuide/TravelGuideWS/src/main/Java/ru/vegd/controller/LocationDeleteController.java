package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.vegd.entity.City;
import ru.vegd.entity.Description;
import ru.vegd.repository.CityRepository;
import ru.vegd.repository.DescriptionRepository;
import ru.vegd.util.JsonToEntityConverter;
import ru.vegd.util.ResponseStatusBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@PropertySource("sec.properties")
public class LocationDeleteController {

    @Value("${sec.token}")
    private String token;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private DescriptionRepository descriptionRepo;

    @PostMapping("/location/delete")
    @ResponseBody
    public String addLocation(HttpServletRequest request) throws IOException {

        Map<String, String> resultMap = JsonToEntityConverter.convert(request.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator())));

        if (resultMap != null && token.equals(resultMap.get("key"))) {
            City city = new City();
            Description description = new Description();

            city.setName(resultMap.get("name"));
            Long id = cityRepo.findByName(city.getName()).getId();

            if (id != null) {
                city.setId(id);

                description.setId(id);
                descriptionRepo.delete(description);

                cityRepo.delete(city);

                return ResponseStatusBuilder.getStatusCode(HttpStatus.OK.value()).toString();
            } else {
                return ResponseStatusBuilder.getStatusCode(HttpStatus.BAD_REQUEST.value()).toString();
            }
        } else {
            return ResponseStatusBuilder.getStatusCode(HttpStatus.FORBIDDEN.value()).toString();
        }
    }
}
