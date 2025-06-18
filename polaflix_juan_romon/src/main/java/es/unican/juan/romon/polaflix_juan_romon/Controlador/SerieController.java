package es.unican.juan.romon.polaflix_juan_romon.Controlador;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping ()
    @JsonView(Vistas.AllSeries.class)
    public ResponseEntity<List<Serie>> getAllSeries() {
        ResponseEntity<List<Serie>> result;
        List<Serie> series = serieService.getAllSeries();
        result = ResponseEntity.ok(series);

        return result;
    }

    // TODO getSeriesByNombre

    @GetMapping("/{id}")
    @JsonView(Vistas.DescripcionSerie.class)
    public ResponseEntity<Serie> getSerieById(@PathVariable("id") String serieId) {
        
        ResponseEntity<Serie> result;
               
        try {
            Serie serie = serieService.getSerieById(serieId);
            result = ResponseEntity.ok(serie);
        } catch (SerieService.SerieNoEncontradaException e) {
            result = ResponseEntity.notFound().build();
        }
        return result;
        
    }



    @GetMapping("/{id}/capitulos")
    @JsonView(Vistas.CapituloSerie.class)
    public ResponseEntity<List<Capitulo>> getCapitulosBySerie(@PathVariable ("id") String serieId) {
        ResponseEntity<List<Capitulo>> result;
        try {
            List<Capitulo> capitulos = serieService.getCapitulosFromSerie(serieId);
            result = ResponseEntity.ok(capitulos);
        } catch (SerieService.SerieNoEncontradaException e) {
            result = ResponseEntity.notFound().build();
        } 

        return result;
    }

    @GetMapping("/{idSerie}/temporada/{idTemporada}")
    @JsonView(Vistas.TemporadaSerie.class)
    public ResponseEntity<List<Capitulo>> getCapitulosFromTemporada(@PathVariable("idSerie") String serieId, @PathVariable("idTemporada") String temporadaId) {
        ResponseEntity<List<Capitulo>> result;
        try {
            List<Capitulo> capitulos = serieService.getCapitulosFromTemporada(serieId, temporadaId);
            result = ResponseEntity.ok(capitulos);
        } catch (SerieService.SerieNoEncontradaException e) {
            result = ResponseEntity.notFound().build(); // 404 serie not found
        }
        return result;
    }

    @GetMapping("/{idSerie}/temporada/{idTemporada}/capitulos/{idCapitulo}")
    @JsonView(Vistas.CapituloSerie.class)
    public ResponseEntity<Capitulo> getCapituloFromTemporada(@PathVariable("idSerie") String serieId, @PathVariable("idTemporada") String temporadaId, @PathVariable("idCapitulo") String capituloId) {
        ResponseEntity<Capitulo> result;

        try {
            Capitulo capitulo = serieService.getCapituloFromTemporada(serieId, temporadaId, capituloId);
            result = ResponseEntity.ok(capitulo);
        } catch (SerieService.SerieNoEncontradaException e) {
            result = ResponseEntity.notFound().build(); // 404 serie not found
        } catch (SerieService.CapituloNoEncontradoException e) {
            result = ResponseEntity.notFound().build(); // 404 capitulo not found
        } 
        return result;
    }

    @GetMapping("/{idSerie}/capitulos/{idCapitulo}") // FIXME -> borrar??? es necesario tenerlo??
    @JsonView(Vistas.CapituloSerie.class)
    public ResponseEntity<Capitulo> getCapituloFromSerie(@PathVariable("idSerie") String serieId, @PathVariable("idCapitulo") String capituloId) {
        ResponseEntity<Capitulo> result;
        try {
            Capitulo capitulo = serieService.getCapituloFromSerie(serieId, capituloId);
            result = ResponseEntity.ok(capitulo);
        } catch (SerieService.SerieNoEncontradaException e) {
            result = ResponseEntity.notFound().build(); // 404 serie not found
        } catch (SerieService.CapituloNoEncontradoException e) {
            result = ResponseEntity.notFound().build(); // 404 capitulo not found   
        }
        return result;
     } 
}
