package es.unican.juan.romon.polaflix_juan_romon.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Capitulo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.SerieRepositorio;
import jakarta.transaction.Transactional;

@Service
public class SerieService {

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
    private SerieRepositorio serieRepositorio;

    @Transactional
    public List<Serie> getAllSeries() {
        List<Serie> series = serieRepositorio.findAll();
        return series;
    }

    @Transactional
    public Serie getSerieById(String serieId) throws SerieNoEncontradaException {
        Optional<Serie> serie = serieRepositorio.findById(Integer.parseInt(serieId));
        if (serie.isEmpty()) {
            throw new SerieNoEncontradaException("Serie no encontrada con id: " + serieId);
        }
        return serie.get();
    }

    @Transactional
    public List<Capitulo> getCapitulosFromSerie(String idSerie) throws SerieNoEncontradaException {
        Optional<Serie> serie = serieRepositorio.findById(Integer.parseInt(idSerie));
        if (serie.isEmpty()) {
            throw new SerieNoEncontradaException("Serie no encontrada con id: " + idSerie);
        }
        return serie.get().getCapitulosSerieList();
    }

    @Transactional
    public Capitulo getCapituloFromSerie (String idSerie, String idCapitulo) throws SerieNoEncontradaException, CapituloNoEncontradoException {
        Optional<Serie> serie = serieRepositorio.findById(Integer.parseInt(idSerie));
        if (serie.isEmpty()) {
            throw new SerieNoEncontradaException("Serie no encontrada con id: " + idSerie);
        }
        Capitulo cap = serie.get().getCapituloFromSerie(Integer.parseInt(idCapitulo));
        if (cap == null) {
            throw new CapituloNoEncontradoException("Capitulo no encontrado con id: " + idCapitulo);
        }
        return cap;
    }


}


