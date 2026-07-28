package br.com.cotiinformatica.api_usuarios.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "perfis")
@Setter
@Getter
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 25, nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;
}
