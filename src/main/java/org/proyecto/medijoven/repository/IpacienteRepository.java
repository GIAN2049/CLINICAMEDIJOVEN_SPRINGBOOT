package org.proyecto.medijoven.repository;

 

import java.util.List;

import org.proyecto.medijoven.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
public interface IpacienteRepository  extends JpaRepository<Paciente, Integer>{
	
	//@Query("SELECT p from Paciente p ORDER BY p.id DESC")
	List<Paciente> findTop5ByOrderByIdDesc();

}
