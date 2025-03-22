package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Usuario;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;

import java.time.LocalDate;
import java.util.*; //LinkedList

public class CapituloVistoSeries {

    static Integer idCapituloVistoSeries = 0;

    private Integer idCapituloVisto;
    private Usuario usuario;
    private Serie perteneceSerie;

    private LinkedList<CapituloVisto> listaCapitulosVistos;
    private LinkedList<Cargo> cargosAsociados;


    public CapituloVistoSeries(Usuario usuario, Serie perteneceSerie) {
        this.idCapituloVisto = idCapituloVistoSeries;
        this.usuario = usuario;
        this.perteneceSerie = perteneceSerie;
        this.listaCapitulosVistos = new LinkedList<CapituloVisto>();
        this.cargosAsociados = new LinkedList<Cargo>();
        idCapituloVistoSeries++;
    }

    public Integer getIdCapituloVisto() {
        return idCapituloVisto;
    }

    public void setIdCapituloVisto(Integer idCapituloVisto) {
        this.idCapituloVisto = idCapituloVisto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Serie getPerteneceSerie() {
        return perteneceSerie;
    }

    public void setPerteneceSerie(Serie perteneceSerie) {
        this.perteneceSerie = perteneceSerie;
    }

    public LinkedList<CapituloVisto> getListaCapitulosVistos() {
        return listaCapitulosVistos;
    }

    public Cargo addCapituloVisto(Capitulo capitulo) {
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        // creamos capitulo Visto
        CapituloVisto capituloVisto = new CapituloVisto(capitulo.getTemporada(), capitulo.getNumeroCapitulo(), capitulo.getTitulo());
        // añadimos a la lista de capitulos vistos si no lo esta
        if (!listaCapitulosVistos.contains(capituloVisto)) {
            listaCapitulosVistos.add(capituloVisto);
        }
        // generamos un cargo
        Serie serie = capitulo.getEsSerie();
        LocalDate fecha = LocalDate.now();
        Cargo cargo = new Cargo(serie.getEsCategoria(), false, fecha, serie.getNombreSerie());
        // añadimos el cargo a la lista de cargos asociados
        cargosAsociados.add(cargo);
        return cargo;
    }


}
