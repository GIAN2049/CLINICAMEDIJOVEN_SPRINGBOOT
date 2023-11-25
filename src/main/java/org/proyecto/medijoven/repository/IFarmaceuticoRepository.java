package org.proyecto.medijoven.repository;

import java.util.List;

import org.proyecto.medijoven.entity.Farmaceutico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFarmaceuticoRepository extends JpaRepository<Farmaceutico, Integer>{

}
