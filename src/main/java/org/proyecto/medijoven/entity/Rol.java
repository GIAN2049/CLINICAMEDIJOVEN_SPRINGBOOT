package org.proyecto.medijoven.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rol")
    private String rol;
    
    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;
    
    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<Acceso> listaAcceso;
}