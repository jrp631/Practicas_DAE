package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;

@Entity
@Table(name = "Serie")
public class Serie {

    // TODO: añadir temporadas como atributo -> para la segunda entrega de las practicas
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Vistas.SeriesEmpezadas.class,
               Vistas.SeriesTerminadas.class,
               Vistas.SeriesPendientes.class,
               Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class,
                Vistas.CapituloSerie.class
            }) //TODO CapituloSerie.class})
    private Integer idSerie;
    
    @Enumerated(EnumType.STRING)
    @JsonView({Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class}) //TODO CapituloSerie.class})
    private Categoria esCategoria;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonView({Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class}) //TODO CapituloSerie.class})
    @JsonManagedReference
    private List<Capitulo> capitulosSerieList;
    
    @JsonView({Vistas.SeriesEmpezadas.class,
               Vistas.SeriesTerminadas.class,
               Vistas.SeriesPendientes.class,
               Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class,
               Vistas.CapituloSerie.class}) //TODO CapituloSerie.class})
    private String nombreSerie;

    @JsonView({Vistas.DescripcionSerie.class})
    private String sinopsis;

    @JsonView({Vistas.SeriesEmpezadas.class,
               Vistas.SeriesTerminadas.class,
               Vistas.SeriesPendientes.class,
               Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class,
               Vistas.CapituloSerie.class}) //TODO CapituloSerie.class})
    private Integer numeroTemporadas; // FIXME: atributo inicialiado cuando se hace una llamada de la API -> corerccion

    // empty constructor
    public Serie() {
    }

    public Serie(String nombreSerie, String sinopsis, Categoria categoria) {
        this.nombreSerie = nombreSerie;
        this.sinopsis = sinopsis;
        this.esCategoria = categoria;
        this.capitulosSerieList = new LinkedList<Capitulo>();
        
    }

    public String getNombreSerie() {
        return nombreSerie;
    }
    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Categoria getEsCategoria() {
        return esCategoria;
    }

    public void setEsCategoria(Categoria esCategoria) {
        this.esCategoria = esCategoria;
    }

    public Integer getId() {
        return idSerie;
    }

    public void setCapitulosSerieList(List<Capitulo> capitulosSerieList) {
        this.capitulosSerieList = capitulosSerieList;
    }

    public void addCapitulo(Capitulo capitulo) throws IllegalArgumentException {
        //TODO
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        // check if the capitulo is already in the series
        if (capitulosSerieList.contains(capitulo)) {
            throw new IllegalArgumentException("Capitulo ya existe en la serie");
        }
        // TODO
        capitulosSerieList.add(capitulo);
        //update the numeroTemporadas attribute
        if (numeroTemporadas == null) {
            numeroTemporadas = capitulo.getTemporada();
        }else if (capitulo.getTemporada() > numeroTemporadas) {
            numeroTemporadas = capitulo.getTemporada();
        }
    }

    public Capitulo getCapituloFromSerie(Capitulo capitulo)
    {   

        //TODO
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        // check if the capitulo is already in the series
        if (!capitulosSerieList.contains(capitulo)) {
            throw new IllegalArgumentException("Capitulo no existe en la serie");
        }
        // TODO
        for (Capitulo cap : capitulosSerieList) {
            if (cap.equals(capitulo)) {
                return cap;
            }
        }
        return null;
    }

    public Capitulo getUltimoCapitulo() {
        // check if the series has any chapters
        if (capitulosSerieList.isEmpty()) {
            return null;
        }
        return capitulosSerieList.get(capitulosSerieList.size() - 1);
    }

    public Capitulo getCapituloFromSerie(Integer idCapitulo) {
        //TODO
        // check the argument is not null
        if (idCapitulo == null) {
            throw new IllegalArgumentException("IdCapitulo no puede ser null");
        }
        // check if the capitulo is already in the series
        for (Capitulo cap : capitulosSerieList) {
            if (cap.getIdCapitulo() == idCapitulo) {
                return cap;
            }
        }
        return null;
    }

    public int getNumeroTemporadas() { // FIXME: refactorizar este método para que sea más eficiente -> acceder al atributo directamente cuando este implementado las temporadas como clase
        // loop through the chapters and count the number of seasons
        // int maxTemporada = 1;

        // for (Capitulo capitulo : capitulosSerieList) {
        //     if (capitulo.getTemporada() > maxTemporada) {
        //         maxTemporada = capitulo.getTemporada();
        //     }
        // }

        // return maxTemporada;
        return this.numeroTemporadas != null ? this.numeroTemporadas : 1; // return 1 if the attribute is not initialized
    }

    public List<Capitulo> getCapitulosFromTemporada(Integer temporada) {
        // check the argument is not null
        if (temporada == null) {
            throw new IllegalArgumentException("Temporada no puede ser null");
        }
        // check if the temporada is valid
        if (temporada < 1 || temporada > getNumeroTemporadas()) {
            throw new IllegalArgumentException("Temporada no existe en la serie");
        }
        // add the chapters 
        List<Capitulo> capitulos = new LinkedList<>();
        for (Capitulo capitulo : capitulosSerieList) {
            if (capitulo.getTemporada().equals(temporada)) {
                capitulos.add(capitulo);
            }
        }
        return capitulos;
    }

    @Override
    public String toString() {
        return "Serie{" + "nombreSerie=" + nombreSerie + ", sinopsis=" + sinopsis + ", idSerie=" + idSerie + ", esCategoria=" + esCategoria + '}';
    }

    @Override
    public int hashCode() {
        return idSerie != null ? idSerie.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Serie serie = (Serie) obj;
        return idSerie != null && idSerie.equals(serie.idSerie);
    }

    public List<Capitulo> getCapitulosSerieList() {
        return capitulosSerieList;
    }


}   
