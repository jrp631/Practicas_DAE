package es.unican.juan.romon.polaflix_juan_romon.Sevicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Cargo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Usuario;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.UsuarioRepositorio;
import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

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
    public ResponseEntity<List<Cargo>> getAllCargos(@PathVariable ("id") String id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(id));
        ResponseEntity<List<Cargo>> result;

        if (usuario.isPresent()) {
            result = ResponseEntity.ok(usuario.get().getCargosUsuario());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
        // return usuario.map(Usuario::getCargosUsuario)
                    //   .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    

    // @PostMapping(value = "/{id}/series-empezadas")      
    // public String postVerCapitulo(@RequestBody Integer idCapitulo, @RequestBody Integer idSerie) {
    //     //TODO: process POST request
        
    //     // ver un capitulo y save 


    //     return ;
    // }
    

}



