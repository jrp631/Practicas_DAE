package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.time.LocalDate;
import java.util.*; //List

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;
    @Column(unique = true)
    private String nombre;
    private String paswd;
    private String IBAN;
    private Boolean tarifaPlana;

    // Lists & Maps for series
    @ManyToMany(fetch = FetchType.EAGER)//@OneToMany
    private List<Serie> seriesTerminadas;

    @ManyToMany(fetch = FetchType.EAGER)//@OneToMany (fetch = FetchType.EAGER)
    private List<Serie> seriesPendientes;

    @ManyToMany(fetch = FetchType.EAGER)//@OneToMany (fetch = FetchType.EAGER)
    private List<Serie> seriesEmpezadas;


    // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "usuario")  
    private List<CapituloVistoSeries> capitulosVistosSerie;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "usuario")  // (fetch = FetchType.EAGER)
    private List<Cargo> cargosUsuario;

    public Usuario() {
        // empty constructor
    }

    // constructor
    public Usuario(String nombre, String paswd, String IBAN, Boolean tarifaPlana) {
        this.nombre = nombre;
        this.paswd = paswd;
        this.IBAN = IBAN;
        this.tarifaPlana = tarifaPlana;
        this.seriesTerminadas = new ArrayList<>();
        this.seriesPendientes = new ArrayList<>();
        this.capitulosVistosSerie = new ArrayList<>();
        this.cargosUsuario = new ArrayList<>();
    }

    // metodos

    //ver capitulo
    public void verCapitulo(Capitulo capitulo) {
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        Cargo cargo;
        CapituloVistoSeries capVistoSerie;
        // check if the serie of the capitulo is in the list of seriesPendientes
        Serie serie_de_cap = capitulo.getEsSerie();
        if (seriesPendientes.contains(serie_de_cap)) {   // first time watching
            capVistoSerie = new CapituloVistoSeries(this, serie_de_cap);
            
            capitulosVistosSerie.add(capVistoSerie);
            // quitamos de la lista de pendientes
            seriesPendientes.remove(serie_de_cap);
            seriesEmpezadas.add(serie_de_cap);
        } else { // already watching
            capVistoSerie = getCapituloVistoSeries(capitulo);
        }
        capVistoSerie.addCapituloVisto(capitulo);
        cargo = new Cargo(serie_de_cap.getEsCategoria(), false, LocalDate.now(), serie_de_cap.getNombreSerie());
        cargo.setUsuario(this);
        addCargoToList(cargo);

        //si la serie habia sido terminada, se quita de la lista de terminadas 
        if (seriesTerminadas.contains(serie_de_cap)) {
            seriesTerminadas.remove(serie_de_cap);
            seriesEmpezadas.add(serie_de_cap);
        }

        System.out.println(capitulo.toString());
        //hay mas capitulos en la serie??
        if (serie_de_cap.getUltimoCapitulo().equals(capitulo)) {
            System.out.println("Ultimo capitulo visto de la serie " + serie_de_cap.getNombreSerie());
            seriesEmpezadas.remove(serie_de_cap);
            seriesTerminadas.add(serie_de_cap);
        }

    }

    public List<Serie> getSeriesEmpezadas() {
        return seriesEmpezadas;
    }

    // añadir serie para ver
    public void agregarSeriePendiente(Serie serie) {

        //check if serie is not null
        if (serie == null) {
            throw new IllegalArgumentException("Serie no puede ser null");
        }

        if (seriesPendientes.contains(serie) == false && seriesEmpezadas.contains(serie) == false) {
            seriesPendientes.add(serie);
        }
    }

    public void eliminarSeriePendiente(Serie serie) {
        //check if serie is not null
        if (serie == null) {
            throw new IllegalArgumentException("Serie no puede ser null");
        }

        if (seriesPendientes.contains(serie)) {
            seriesPendientes.remove(serie);
        }
    }
    public void agregarSerieTerminada(Serie serie) {
        //check if serie is not null
        if (serie == null) {
            throw new IllegalArgumentException("Serie no puede ser null");
        }

        if (!seriesTerminadas.contains(serie) && !seriesEmpezadas.contains(serie) ) {
            seriesTerminadas.add(serie);
        }
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPaswd() {
        return paswd;
    }
    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }
    public String getIBAN() {
        return IBAN;
    }
    public void setIBAN(String iBAN) {
        IBAN = iBAN;
    }
    public Boolean getTarifaPlana() {
        return tarifaPlana;
    }
    public void setTarifaPlana(Boolean tarifaPlana) {
        this.tarifaPlana = tarifaPlana;
    }
    public List<Serie> getSeriesTerminadas() {
        return seriesTerminadas;
    }
    public void setSeriesTerminadas(List<Serie> seriesTerminadas) {
        this.seriesTerminadas = seriesTerminadas;
    }
    public List<Serie> getSeriesPendientes() {
        return seriesPendientes;
    }
    public void setSeriesPendientes(List<Serie> seriesPendientes) {
        this.seriesPendientes = seriesPendientes;
    }
    public List<CapituloVistoSeries> getCapitulosVistosSerie() {
        return capitulosVistosSerie;
    }
    public void setCapitulosVistosSerie(List<CapituloVistoSeries> capitulosVistosSerie) {
        this.capitulosVistosSerie = capitulosVistosSerie;
    }
    public List<Cargo> getCargosUsuario() {
        return cargosUsuario;
    }
   
    private CapituloVistoSeries getCapituloVistoSeries(Capitulo capitulo) {
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        Serie serie_de_cap = capitulo.getEsSerie();
        // check the entry in the list of capitulosVistosSerie
        for (CapituloVistoSeries csv : capitulosVistosSerie) {
            if (csv.getPerteneceSerie().equals(serie_de_cap)) {
                return csv;
            }
        }
        return null;
    }

    private void addCargoToList(Cargo cargo)
    {
        if (cargo == null) {
            throw new IllegalArgumentException("Cargo no puede ser null");
        }
        // add the cargo to the list of cargos
        cargosUsuario.add(cargo);
    }

    public Boolean capituloVisto(Capitulo capitulo) {
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        // Serie serie_de_cap = capitulo.getEsSerie();
        CapituloVistoSeries capVistoSerie = getCapituloVistoSeries(capitulo);
        if (capVistoSerie != null) {
            return capVistoSerie.capituloVisto(capitulo);
        } else {
            System.out.println("NO CSV: " + capitulo.getTitulo());
            return false;
        }
    }

}
