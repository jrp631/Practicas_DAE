package es.unican.juan.romon.polaflix_juan_romon.Controlador;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Capitulo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.SerieRepositorio;
import es.unican.juan.romon.polaflix_juan_romon.Servicio.SerieService;
import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieRepositorio serieRepositorio;

    @Autowired
    private SerieService serieService;

    @GetMapping ()
    @JsonView(Vistas.AllSeries.class)
    public ResponseEntity<List<Serie>> getAllSeries() {
        // return serieRepositorio.findAll();
        // Optional<List<Serie>> series = Optional.of(serieRepositorio.findAll());
        ResponseEntity<List<Serie>> result;

        // if (series.isPresent()) {
        //     result = ResponseEntity.ok(series.get());
        // } else {
        //     result = ResponseEntity.notFound().build();
        // }
        List<Serie> series = serieService.getAllSeries();
        if (series.isEmpty()) {
            result = ResponseEntity.notFound().build();
        } else {
            result = ResponseEntity.ok(series);
        }

        return result;
    }

    // TODO getSeriesByNombre

    @GetMapping("/{id}")
    @JsonView(Vistas.DescripcionSerie.class)
    public ResponseEntity<Serie> getSerieById(@PathVariable("id") String serieId) {
        
        // Optional<Serie> serie = serieRepositorio.findById(Integer.parseInt(serieId));
        ResponseEntity<Serie> result;

        // if (serie.isPresent()) {
        //     result = ResponseEntity.ok(serie.get());
        // } else {
        //     result = ResponseEntity.notFound().build();
        // }
        
        Serie serie = null;
        
        try {
            serie = serieService.getSerieById(serieId);
        } catch (SerieService.SerieNoEncontradaException e) {
            result = ResponseEntity.notFound().build();
        }
        if (serie != null) {
            result = ResponseEntity.ok(serie);
        } else {
            result = ResponseEntity.notFound().build();
        }


        return result;
    }



    @GetMapping("/{id}/capitulos")
    @JsonView(Vistas.CapituloSerie.class)
    public ResponseEntity<List<Capitulo>> getCapitulosBySerie(@PathVariable ("id") String serieId) {
        Optional<Serie> serie = serieRepositorio.findById(Integer.parseInt(serieId));
        ResponseEntity<List<Capitulo>> result;
        if (serie.isPresent()) {
            // return serie.get().getCapitulosSerieList();
            result = ResponseEntity.ok(serie.get().getCapitulosSerieList());
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @GetMapping("/{idSerie}/capitulos/{idCapitulo}")
    @JsonView(Vistas.CapituloSerie.class)
    public ResponseEntity<Capitulo> getCapituloFromSerie(@PathVariable("idSerie") String serieId, @PathVariable("idCapitulo") String capituloId) {
        Integer idSerie = Integer.parseInt(serieId), idCapitulo = Integer.parseInt(capituloId);
    
        Optional<Serie> serie = serieRepositorio.findById(idSerie);
    
        ResponseEntity<Capitulo> result =  ResponseEntity.notFound().build(); 
        if (serie.isPresent()) { // no existe la serie indicada 
            result = ResponseEntity.notFound().build();
            // return result;
        }
        // return resultado;
        
        List<Capitulo> captitulo = serie.get().getCapitulosSerieList();
        for (Capitulo c : captitulo) {
            if (c.getIdCapitulo() == idCapitulo) {
                result = ResponseEntity.ok(c);
            }
        }        
        return result;
     } 
}
