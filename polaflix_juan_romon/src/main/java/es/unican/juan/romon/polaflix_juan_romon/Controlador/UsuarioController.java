package es.unican.juan.romon.polaflix_juan_romon.Controlador;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Capitulo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Cargo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Usuario;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.SerieRepositorio;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.UsuarioRepositorio;
import es.unican.juan.romon.polaflix_juan_romon.Servicio.SerieService;
import es.unican.juan.romon.polaflix_juan_romon.Servicio.UsuarioService;
import es.unican.juan.romon.polaflix_juan_romon.Servicio.UsuarioService.CapituloNoEncontradoException;
import es.unican.juan.romon.polaflix_juan_romon.Servicio.UsuarioService.SerieNoEncontradaException;
import es.unican.juan.romon.polaflix_juan_romon.Servicio.UsuarioService.UsuarioNoEncontradoException;
import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    @JsonView(Vistas.DatosUsuario.class)
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") String userId) {
        ResponseEntity<Usuario> result;
        try {
            Usuario usuario = usuarioService.getUsuario(userId);
            result = ResponseEntity.ok(usuario);
        } catch (UsuarioNoEncontradoException e) {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @GetMapping(value = "/{id}/series-empezadas")
    @JsonView(Vistas.SeriesEmpezadas.class)
    public ResponseEntity<List<Serie>> getSeriesEmpezadas(@PathVariable("id") String userId) {
        ResponseEntity<List<Serie>> result;
        try {
            List<Serie> seriesEmpezadas = usuarioService.getSeriesEmpezadas(userId);
            result = ResponseEntity.ok(seriesEmpezadas);
        } catch (UsuarioNoEncontradoException e) {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @GetMapping(value = "/{id}/series-terminadas")
    @JsonView(Vistas.SeriesTerminadas.class)
    public ResponseEntity<List<Serie>> getSeriesTerminadas(@PathVariable("id") String userId) {
        ResponseEntity<List<Serie>> result;
        List<Serie> seriesTerminadas = usuarioService.getSeriesTerminadas(userId);
        
        if (seriesTerminadas.size() >= 0) {
            result = ResponseEntity.ok(seriesTerminadas);
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    @GetMapping(value = "/{id}/series-pendientes")
    @JsonView(Vistas.SeriesPendientes.class)
    public ResponseEntity<List<Serie>> getSeriesPendientes(@PathVariable("id") String userId) {
        ResponseEntity<List<Serie>> result;
        try {
            List<Serie> seriesPendientes = usuarioService.getSeriesPendientes(userId);
            result = ResponseEntity.ok(seriesPendientes);
        } catch (UsuarioNoEncontradoException e) {
            result = ResponseEntity.notFound().build(); // 404 user not found
        }
        


        return result;
    }

    @GetMapping(value = "/{id}/cargos")
    @JsonView(Vistas.CargosUsuario.class)
    public ResponseEntity<List<Cargo>> getAllCargos(@PathVariable ("id") String id, @RequestParam(required = false) LocalDate fecha_ini, @RequestParam(required = false) LocalDate fecha_fin) {
        ResponseEntity<List<Cargo>> result;

        try {
            Usuario usuario = usuarioService.getUsuario(id);
            List<Cargo> cargos = usuarioService.getCargos(usuario, fecha_ini, fecha_fin);
            result = ResponseEntity.ok(cargos);
        } catch (UsuarioNoEncontradoException e) {
            result = ResponseEntity.notFound().build(); // 404 user not found
        }

        return result;
    }
    
    // FIXME 
    @PutMapping(value = "/{id}/series-empezadas/{serieId}/capitulos/{capituloId}") // TODO: check implementation
    public ResponseEntity<String> putVerCapitulo(@PathVariable("id") String userId,@PathVariable("serieId") String idSerie ,@PathVariable ("capituloId") String idCapitulo) {
        ResponseEntity<String> result;
        System.out.println("putVerCapitulo: " + userId + " " + idSerie + " " + idCapitulo);
        try {
            usuarioService.putVerCapitulo(userId, idSerie, idCapitulo);
            result = ResponseEntity.ok("Capitulo visto");
        } catch (UsuarioNoEncontradoException e) {
            result = ResponseEntity.badRequest().build(); // 404 user not found
        } catch(SerieNoEncontradaException e) { 
            result = ResponseEntity.badRequest().build(); // 404 serie not found
        } catch(CapituloNoEncontradoException e) { 
            result = ResponseEntity.badRequest().build(); // 404 capitulo not found
        }

        return result;
    }

    @PutMapping(value = "{id}/series-pendientes/{serieId}")
    public ResponseEntity<String> putAgregarSeriePendiente(@PathVariable("id") String userId, @PathVariable String serieId) {
        ResponseEntity<String> result;

        try {
            usuarioService.putSeriePendiente(userId, serieId);
            result = ResponseEntity.ok("Serie añadida a pendientes");
        } catch (UsuarioNoEncontradoException e1) {
            result = ResponseEntity.badRequest().build(); // 404 user not found
        } catch (SerieNoEncontradaException e) { // 404 serie not found
            result = ResponseEntity.badRequest().build();
        }

        return result;
    }

    
    @DeleteMapping(value = "/{id}/series-pendientes/{idSerie}")
    public ResponseEntity<String> deleteSeriePendiente(@PathVariable("id") String userId, @PathVariable("idSerie") String serieId) {
        ResponseEntity<String> result;
        // TODO 
        try {
            usuarioService.deleteSeriePendiente(userId, serieId);
            result = ResponseEntity.ok("Serie eliminada de pendientes");
        } catch (UsuarioNoEncontradoException e1) {
            result = ResponseEntity.badRequest().build(); // 404 user not found
        } catch (SerieNoEncontradaException e) { // 404 serie not found
            result = ResponseEntity.badRequest().build();
        }

        return result;
    }

    @GetMapping(value = "/{id}/series-empezadas/{serieId}/capitulos/{capituloId}")
    public ResponseEntity <Boolean> getCapituloVisto(@PathVariable("id") String userId, @PathVariable("serieId") String serieId, @PathVariable("capituloId") String capituloId) {
        ResponseEntity<Boolean> result;
        boolean capituloVisto = false; 
        
        try {
            capituloVisto = usuarioService.getCapituloVisto(userId, serieId, capituloId);
        } catch (UsuarioNoEncontradoException e) {
            result = ResponseEntity.notFound().build(); // 404 user not found
            return result;
        } catch (SerieNoEncontradaException e) {
            result = ResponseEntity.notFound().build(); // 404 serie not found
            return result;
        } catch (CapituloNoEncontradoException e) {
            result = ResponseEntity.notFound().build(); // 404 capitulo not found
            return result;
        }

        if (capituloVisto) { // if statement for debugging purposes
            result = ResponseEntity.ok(true);
            System.out.println("Capitulo visto: " + capituloVisto);
        } else {
            result = ResponseEntity.ok(false);
            // System.out.println("Capitulo no visto: " + capituloVisto);
        }

        return result;

    }

}



