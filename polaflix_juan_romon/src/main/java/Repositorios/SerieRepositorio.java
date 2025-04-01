package Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;

public interface SerieRepositorio extends JpaRepository<Serie, Integer> {}
