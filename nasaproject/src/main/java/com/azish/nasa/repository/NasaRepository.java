package com.azish.nasa.repository;

import com.azish.nasa.entity.Nasa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasaRepository extends CrudRepository<Nasa, Integer> {
}
