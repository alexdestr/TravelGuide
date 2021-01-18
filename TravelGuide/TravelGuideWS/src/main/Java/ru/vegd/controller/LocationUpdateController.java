package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class LocationUpdateController {

    @Value("${sec.key}")
    private String key;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private DescriptionRepository descriptionRepo;

    @PostMapping("/location/update")
    @ResponseBody
    public String addLocation(HttpServletRequest request) throws IOException {

        Map<String, String> resultMap = JsonToEntityConverter.convert(request.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator())));

        if (resultMap != null && key.equals(resultMap.get("key"))) {
            Description description = new Description();
            Long id = cityRepo.findByName(resultMap.get("name")).getId();

            if (id != null) {
                description.setId(id);
                description.setDescription(resultMap.get("description"));

                descriptionRepo.save(description);

                return "Successfully updated";
            } else {
                return "City not found";
            }
        } else {
            return "Forbidden";
        }
    }
}
