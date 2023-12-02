package org.proyecto.medijoven.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre_usuario", unique = true)
    private String username;

    @Column(name = "contrasena")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
    
    
    @OneToOne
    @JoinColumn(name="id_medico", nullable = true)
    private Medico medico;
    
    @OneToOne
    @JoinColumn(name="id_farmaceutico", nullable = true)
    private Farmaceutico farmaceutico;
   
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = true)
    private Paciente paciente;

}