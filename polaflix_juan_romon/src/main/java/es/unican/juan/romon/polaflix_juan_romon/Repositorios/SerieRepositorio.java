package es.unican.juan.romon.polaflix_juan_romon.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import java.util.List;


public interface SerieRepositorio extends JpaRepository<Serie, Integer> {

    List<Serie> findByNombreSerie(String nombreSerie);

}
