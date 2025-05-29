package es.unican.juan.romon.polaflix_juan_romon.Dominio;


import java.time.LocalDate;
import java.util.*; //LinkedList
import jakarta.persistence.*;

@Entity
@Table(name = "CapituloVistoSeries")
public class CapituloVistoSeries {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCapituloVisto;
    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Serie perteneceSerie;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CapituloVisto> listaCapitulosVistos;

    int capituloVistoCount = 0; // contador de capitulos diferentes vistos
    
    // @OneToMany (cascade = CascadeType.MERGE)
    // private List<Cargo> cargosAsociados;

    // empty constructor
    public CapituloVistoSeries() {
    }

    public CapituloVistoSeries(Usuario usuario, Serie perteneceSerie) {
        this.usuario = usuario;
        this.perteneceSerie = perteneceSerie;
        this.listaCapitulosVistos = new ArrayList<CapituloVisto>();
        // this.cargosAsociados = new ArrayList<Cargo>();
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

    public List<CapituloVisto> getListaCapitulosVistos() {
        return listaCapitulosVistos;
    }

    public int getCapituloVistoCount() {
        return capituloVistoCount;
    }

    public void addCapituloVisto(Capitulo capitulo) {
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        // creamos capitulo Visto
        CapituloVisto capituloVisto = new CapituloVisto(capitulo.getTemporada(), capitulo.getNumeroCapitulo(), capitulo.getTitulo());
        // añadimos a la lista de capitulos vistos si no lo esta
        if (!listaCapitulosVistos.contains(capituloVisto)) {
            listaCapitulosVistos.add(capituloVisto);
            capituloVistoCount++;
        }
        // generamos un cargo
        // Serie serie = capitulo.getEsSerie();
        // LocalDate fecha = LocalDate.now();
        // Cargo cargo = new Cargo(serie.getEsCategoria(), false, fecha, serie.getNombreSerie());
        // añadimos el cargo a la lista de cargos asociados
        // cargosAsociados.add(cargo);
        // return cargo;
    }

    public Boolean capituloVisto(Capitulo capitulo) {
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        // creamos capitulo Visto
        CapituloVisto capituloVisto = new CapituloVisto(capitulo.getTemporada(), capitulo.getNumeroCapitulo(), capitulo.getTitulo());
        // comprobamos si ya esta en la lista de capitulos vistos
        return listaCapitulosVistos.contains(capituloVisto);
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
