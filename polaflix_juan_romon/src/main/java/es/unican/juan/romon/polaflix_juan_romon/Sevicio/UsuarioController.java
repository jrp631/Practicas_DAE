package es.unican.juan.romon.polaflix_juan_romon.Sevicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Cargo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Usuario;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.UsuarioRepositorio;
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

    @GetMapping("/{id}/series-empezadas")
    public List<Serie> getSeriesEmpezadas(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        return usuario.map(Usuario::getSeriesPendientes)
                      .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    @GetMapping("/{id}/series-terminadas")
    public List<Serie> getSeriesTerminadas(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        return usuario.map(Usuario::getSeriesTerminadas)
                      .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @GetMapping("/{id}/series-pendientes")
    public List<Serie> getSeriesPendientes(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        return usuario.map(Usuario::getSeriesPendientes)
                      .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @GetMapping("/{id}/cargos")
    public List<Cargo> getAllCargos(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        return usuario.map(Usuario::getCargosUsuario)
                      .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    

    @PostMapping("/{id}/series-empezadas")      
    public String postVerCapitulo(@RequestBody Integer idCapitulo, @RequestBody Integer idSerie) {
        //TODO: process POST request
        
        // ver un capitulo y save 


        return ;
    }
    

}



