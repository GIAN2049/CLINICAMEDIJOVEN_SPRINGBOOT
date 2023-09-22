package org.proyecto.medijoven.repository;

import java.util.List;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Farmaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{
		 
	@Query("SELECT f from Farmaco f where f.categoria.id_categoria=?1")
	public List<Farmaco> findFarmacoByIdCategoria(int id);
	
}
