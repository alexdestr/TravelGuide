package ru.vegd.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vegd.entity.Description;

public interface DescriptionRepository extends CrudRepository<Description, Long> {
}
