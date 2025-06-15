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
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Vistas.SeriesEmpezadas.class,
               Vistas.SeriesTerminadas.class,
               Vistas.SeriesPendientes.class,
               Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class,
                Vistas.CapituloSerie.class
            })
    private Integer idSerie;
    
    @Enumerated(EnumType.STRING)
    @JsonView({Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class}) 
    private Categoria esCategoria;

    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JsonView({Vistas.DescripcionSerie.class,
    //            Vistas.AllSeries.class}) 
    // @JsonManagedReference
    // private List<Capitulo> capitulosSerieList;
    
    @JsonView({Vistas.SeriesEmpezadas.class,
               Vistas.SeriesTerminadas.class,
               Vistas.SeriesPendientes.class,
               Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class,
               Vistas.CapituloSerie.class})
    private String nombreSerie;

    @JsonView({Vistas.DescripcionSerie.class})
    private String sinopsis;

    @JsonView({Vistas.SeriesEmpezadas.class,
               Vistas.SeriesTerminadas.class,
               Vistas.SeriesPendientes.class,
               Vistas.DescripcionSerie.class,
               Vistas.AllSeries.class,
               Vistas.CapituloSerie.class})
    private Integer numeroTemporadas; // FIXME: atributo inicialiado cuando se hace una llamada de la API -> corerccion


    // FIXME: nuevos atributos -> requiere refactorización de la clase
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Temporada> temporadasSerie; 
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reparto> repartoSerie; // TODO JPA

    // empty constructor
    public Serie() {
    }

    /**
     * @param nombreSerie Nombre de la serie
     * @param sinopsis Sinopsis de la serie
     * @param categoria Categoría de la serie
     */
    public Serie(String nombreSerie, String sinopsis, Categoria categoria) {
        this.nombreSerie = nombreSerie;
        this.sinopsis = sinopsis;
        this.esCategoria = categoria;
        // this.capitulosSerieList = new LinkedList<Capitulo>(); 
        this.temporadasSerie = new LinkedList<Temporada>();
        this.repartoSerie = new LinkedList<Reparto>();
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

    // public void setCapitulosSerieList(List<Capitulo> capitulosSerieList) { 
    //     this.capitulosSerieList = capitulosSerieList;
    // }

    
    public List<Temporada> getTemporadasSerie() {
        return temporadasSerie;
    }
    public void setTemporadasSerie(List<Temporada> temporadasSerie) {
        this.temporadasSerie = temporadasSerie;
    }
    public void addTemporada(Temporada temporada) {
        if (temporada != null)  {
            temporadasSerie.add(temporada);
        }
    }
    public List<Reparto> getRepartoSerie() {
        return repartoSerie;
    }
    public void setRepartoSerie(List<Reparto> repartoSerie) {
        this.repartoSerie = repartoSerie;
    }
    public void addReparto(Reparto reparto) {
        if (reparto != null &&  !repartoSerie.contains(reparto)) {
            repartoSerie.add(reparto);
        }
    }
    
    public void addCapitulo(Integer temporada, Capitulo capitulo) throws IllegalArgumentException {        
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        if (temporada == null) {
            // add the chapter to the last season
            Temporada lastTemporada = temporadasSerie.get(getNumeroTemporadas() - 1); // get the last season
            lastTemporada.addCapitulo(capitulo); // add the chapter to the last season
        } else {
            // check if the temporada is valid
            if (temporada < 1 || temporada > getNumeroTemporadas()) {
                throw new IllegalArgumentException("Temporada no existe en la serie");
            }
            // add the chapter to the specified season
            Temporada temp = temporadasSerie.get(temporada - 1); // get the season
            temp.addCapitulo(capitulo); // add the chapter to the season
        }
    }


    public Capitulo getUltimoCapitulo() {
        return temporadasSerie.get(getNumeroTemporadas() - 1).getUltimoCapitulo(); // return the last chapter of the last season
    }



    public Capitulo getCapituloFromSerie(Integer idCapitulo) { 
        // check the argument is not null
        if (idCapitulo == null) {
            throw new IllegalArgumentException("IdCapitulo no puede ser null");
        }
        for (Temporada temporada : temporadasSerie) {
            List<Capitulo> capitulosTemporada = temporada.getCapitulosTemporada();
            for (Capitulo capitulo : capitulosTemporada) {
                if (capitulo.getIdCapitulo().equals(idCapitulo)) {
                    return capitulo; // return the chapter if it is found
                }
            }
        }

        // // check if the capitulo is already in the series
        // for (Capitulo cap : capitulosSerieList) {
        //     if (cap.getIdCapitulo() == idCapitulo) {
        //         return cap;
        //     }
        // }
        return null;
    }

    public Capitulo getCapituloFromSerie(Integer temporada, Integer numeroCapitulo) {
        // check the arguments are not null
        if (temporada == null || numeroCapitulo == null) {
            throw new IllegalArgumentException("Temporada o numeroCapitulo no pueden ser null");
        }
        // check if the temporada is valid
        if (temporada < 1 || temporada > getNumeroTemporadas()) {
            throw new IllegalArgumentException("Temporada no existe en la serie");
        }

        Capitulo cap = temporadasSerie.get(temporada - 1).getCapitulo(numeroCapitulo); // get the chapter from the season
        return cap;
    }

    public int getNumeroTemporadas() { 
        // return this.numeroTemporadas != null ? this.numeroTemporadas : 1; // return 1 if the attribute is not initialized
        return temporadasSerie.size(); // return the number of seasons
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
        // List<Capitulo> capitulos = new LinkedList<Capitulo>();
        // for (Capitulo capitulo : capitulosSerieList) {
        //     if (capitulo.getTemporada().equals(temporada)) {
        //         capitulos.add(capitulo);
        //     }
        // }
        // return capitulos;
        return temporadasSerie.get(temporada - 1).getCapitulosTemporada(); // return the chapters of the season
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

    // public List<Capitulo> getCapitulosSerieList() {
    //     return capitulosSerieList;
    // }

    public List<Capitulo> getCapitulosSerie() {
        List<Capitulo> capitulos = new LinkedList<Capitulo>();
        for (Temporada temporada : temporadasSerie) {
            capitulos.addAll(temporada.getCapitulosTemporada());
        }
        return capitulos;
    }


}   
