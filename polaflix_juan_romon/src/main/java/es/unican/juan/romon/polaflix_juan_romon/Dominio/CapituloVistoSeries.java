package es.unican.juan.romon.polaflix_juan_romon.Dominio;


import java.time.LocalDate;
import java.util.*; //LinkedList
import jakarta.persistence.*;

@Entity
@Table(name = "CapituloVistoSeries")
public class CapituloVistoSeries {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCapituloVisto;
    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Serie perteneceSerie;

    @OneToMany(cascade = CascadeType.ALL)
    private LinkedList<CapituloVisto> listaCapitulosVistos;
    
    @OneToMany
    private LinkedList<Cargo> cargosAsociados;

    // empty constructor
    public CapituloVistoSeries() {
    }

    public CapituloVistoSeries(Usuario usuario, Serie perteneceSerie) {
        this.usuario = usuario;
        this.perteneceSerie = perteneceSerie;
        this.listaCapitulosVistos = new LinkedList<CapituloVisto>();
        this.cargosAsociados = new LinkedList<Cargo>();
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
    @Override
    public int hashCode() {
        return idCapituloVisto != null ? idCapituloVisto.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CapituloVistoSeries capituloVistoSeries = (CapituloVistoSeries) obj;
        return idCapituloVisto != null && idCapituloVisto.equals(capituloVistoSeries.idCapituloVisto);        
    }

}
