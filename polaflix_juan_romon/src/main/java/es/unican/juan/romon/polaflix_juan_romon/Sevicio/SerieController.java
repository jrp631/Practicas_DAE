package es.unican.juan.romon.polaflix_juan_romon.Sevicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Capitulo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.SerieRepositorio;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieRepositorio serieRepositorio;

    @GetMapping
    public List<Serie> getAllSeries() {
        return serieRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Serie> getSerieById(@PathVariable Integer id) {
        return serieRepositorio.findById(id);
    }

    @GetMapping("/{id}/capitulos")
    public List<Capitulo> getCapitulosBySerie(@PathVariable Integer id) {
        Optional<Serie> serie = serieRepositorio.findById(id);
        if (serie.isPresent()) {
            return serie.get().getCapitulosSerieList();
        } else {
            return null; // or throw an exception
        }
    }
}
