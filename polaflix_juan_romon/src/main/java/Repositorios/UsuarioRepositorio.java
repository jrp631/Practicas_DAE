package Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Usuario;

public interface  UsuarioRepositorio extends JpaRepository<Usuario, Integer> {}