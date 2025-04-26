package es.unican.juan.romon.polaflix_juan_romon.Sevicio;

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
import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private SerieRepositorio serieRepositorio;

    @GetMapping(value = "/{id}/series-empezadas")
    @JsonView(Vistas.SeriesEmpezadas.class)
    public ResponseEntity<List<Serie>> getSeriesEmpezadas(@PathVariable("id") String userId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        ResponseEntity<List<Serie>> result;

        if (usuario.isPresent()) {
            result = ResponseEntity.ok(usuario.get().getSeriesTerminadas());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    @GetMapping(value = "/{id}/series-terminadas")
    @JsonView(Vistas.SeriesTerminadas.class)
    public ResponseEntity<List<Serie>> getSeriesTerminadas(@PathVariable("id") String userId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        ResponseEntity<List<Serie>> result;
       
        if (usuario.isPresent()) {
            result = ResponseEntity.ok(usuario.get().getSeriesTerminadas());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    @GetMapping(value = "/{id}/series-pendientes")
    @JsonView(Vistas.SeriesPendientes.class)
    public ResponseEntity<List<Serie>> getSeriesPendientes(@PathVariable("id") String userId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        ResponseEntity<List<Serie>> result;

        if (usuario.isPresent()) {
            result = ResponseEntity.ok(usuario.get().getSeriesPendientes());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
        // return usuario.map(Usuario::getSeriesPendientes)
                    //   .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @GetMapping(value = "/{id}/cargos")
    @JsonView(Vistas.CargosUsuario.class)
    public ResponseEntity<List<Cargo>> getAllCargos(@PathVariable ("id") String id, @RequestParam(required = false) LocalDate fecha_ini, @RequestParam(required = false) LocalDate fecha_fin) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(id));
        ResponseEntity<List<Cargo>> result;

        if (usuario.isPresent()) {
            List<Cargo> cargos = usuario.get().getCargosUsuario();

            if (fecha_ini == null &&  fecha_fin == null){
                result = ResponseEntity.ok(cargos);
            } else { // REFACTORIZAR
                for (Cargo c : cargos) { //retornamos los cargos que se encuentran entre las fechas
                    if (fecha_ini != null && fecha_fin == null) { //cargos despues de una fecha
                        if(c.getFecha().isBefore(fecha_ini)) {
                            cargos.remove(c);
                        }
                    } else if (fecha_ini == null && fecha_fin != null) { // cargos antes de una fecha
                        if (c.getFecha().isAfter(fecha_fin)) {
                            cargos.remove(c);
                        }
                    } else { //cargos entre dos fechas
                        if (c.getFecha().isBefore(fecha_ini) || c.getFecha().isAfter(fecha_fin)) {
                            cargos.remove(c);
                        }
                    } 
                        
                }
                result = ResponseEntity.ok(cargos);
            }
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
        // return usuario.map(Usuario::getCargosUsuario)
                    //   .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    @PutMapping(value = "{id}") // TODO: check implementation
    public ResponseEntity putVerCapitulo(@PathVariable("id") String userId,@RequestBody String serieId ,@RequestBody String capituloId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        Optional<Serie> serie = serieRepositorio.findById(Integer.parseInt(serieId));

        ResponseEntity result;

        if (usuario.isPresent() && serie.isPresent()) {
            Capitulo capitulo = serie.get().getCapituloFromSerie(Integer.parseInt(capituloId));
            usuario.get().verCapitulo(capitulo);
            usuarioRepositorio.save(usuario.get());
            result = ResponseEntity.ok().build();
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    //TODO: put : agregar serie a pendientes

    // TODO: delete : eliminar serie de pendientes

    // @PostMapping(value = "/{id}/series-empezadas")      
    // public String postVerCapitulo(@RequestBody Integer idCapitulo, @RequestBody Integer idSerie) {
    //     //TODO: process POST request
        
    //     // ver un capitulo y save 


    //     return ;
    // }
    

}



