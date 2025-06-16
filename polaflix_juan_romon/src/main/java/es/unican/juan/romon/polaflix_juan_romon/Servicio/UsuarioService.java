package es.unican.juan.romon.polaflix_juan_romon.Servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Capitulo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Cargo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Usuario;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.SerieRepositorio;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    public class UsuarioNoEncontradoException extends Exception {
        public UsuarioNoEncontradoException(String message) {
            super(message);
        }
    }
    public class SerieNoEncontradaException extends Exception {
        public SerieNoEncontradaException(String message) {
            super(message);
        }
    }
    public class CapituloNoEncontradoException extends Exception {
        public CapituloNoEncontradoException(String message) {
            super(message);
        }
    }

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;  

    @Autowired
    private SerieRepositorio serieRepositorio;

    @Transactional
    public Usuario getUsuario(String userId) throws UsuarioNoEncontradoException {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }
    }

    @Transactional
    public List<Serie> getSeriesEmpezadas(String userId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        List<Serie> seriesEmpezadas = null;

        if (usuario.isPresent()) {
            seriesEmpezadas = usuario.get().getSeriesEmpezadas();
        }
        return seriesEmpezadas;
    }

    @Transactional
    public List<Serie> getSeriesTerminadas(String userId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        List<Serie> seriesTerminadas = null;

        if (usuario.isPresent()) {
            seriesTerminadas = usuario.get().getSeriesTerminadas();
        }
        return seriesTerminadas;
    }

    @Transactional
    public List<Serie> getSeriesPendientes(String userId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId)); 
        List<Serie> seriesPendientes = null;

        if (usuario.isPresent()) {
            seriesPendientes = usuario.get().getSeriesPendientes();
        }
        return seriesPendientes;
    }

    @Transactional
    public List<Cargo> getCargos(Usuario usuario, LocalDate fecha_ini, LocalDate fecha_fin) {
        List<Cargo> cargos = usuario.getCargosUsuario();
        
        if (fecha_ini == null &&  fecha_fin == null){
            return cargos; //si no se han introducido fechas, devolvemos todos los cargos
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
        }
        return cargos;
    }


    @Transactional
    public void putSeriePendiente(String userId, String serieId) throws UsuarioNoEncontradoException, SerieNoEncontradaException {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        Optional<Serie> serie = serieRepositorio.findById(Integer.parseInt(serieId));

        if (!usuario.isPresent()) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }
        if (!serie.isPresent()) {
            throw new SerieNoEncontradaException("Serie no encontrada");
        }

        Usuario user = usuario.get();
        Serie seriePendiente = serie.get();

        user.agregarSeriePendiente(seriePendiente);
        usuarioRepositorio.save(user);

    }

    @Transactional
    public void deleteSeriePendiente(String userId, String serieId) throws UsuarioNoEncontradoException, SerieNoEncontradaException {
        Optional<Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        Optional<Serie> serie = serieRepositorio.findById(Integer.parseInt(serieId));

        if (!usuario.isPresent()) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }
        if (!serie.isPresent()) {
            throw new SerieNoEncontradaException("Serie no encontrada");
        }
        Usuario user = usuario.get();
        Serie seriePendiente = serie.get();

        user.eliminarSeriePendiente(seriePendiente);
        usuarioRepositorio.save(user);

    }

    @Transactional
    public void putVerCapitulo(String userId, String serieId, String capituloId) throws UsuarioNoEncontradoException, SerieNoEncontradaException, CapituloNoEncontradoException {
        System.out.println("(Service)putVerCapitulo: " + userId + " " + serieId + " " + capituloId);

        Optional <Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        Optional <Serie> serie = serieRepositorio.findById(Integer.parseInt(serieId));

        if(!usuario.isPresent()) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        } 
        if(!serie.isPresent()) {
            throw new SerieNoEncontradaException("Serie no encontrada");
        }

        Usuario user = usuario.get();
        Serie serieDeCap= serie.get();
        Capitulo capitulo = serieDeCap.getCapituloFromSerie(Integer.parseInt(capituloId));

        if (capitulo == null) {
            throw new CapituloNoEncontradoException("Capitulo no encontrado");
        }
        user.verCapitulo(capitulo);
        usuarioRepositorio.save(user);

    }

    @Transactional 
    public Boolean getCapituloVisto(String userId, String serieId, String capituloId) throws UsuarioNoEncontradoException, SerieNoEncontradaException, CapituloNoEncontradoException  {
        Optional <Usuario> usuario = usuarioRepositorio.findById(Integer.parseInt(userId));
        Optional <Serie> serie = serieRepositorio.findById(Integer.parseInt(serieId));

        if(!usuario.isPresent()) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        } 
        if(!serie.isPresent()) {
            throw new SerieNoEncontradaException("Serie no encontrada");
        }

        Usuario user = usuario.get();
        Serie serieDeCap= serie.get();

        Capitulo capitulo = serieDeCap.getCapituloFromSerie(Integer.parseInt(capituloId));
        if (capitulo == null) {
            throw new CapituloNoEncontradoException("Capitulo no encontrado");
        }
        return user.capituloVisto(capitulo);

    }

}
