package ru.vegd.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vegd.entity.City;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {
    City findByName(String name);
}
