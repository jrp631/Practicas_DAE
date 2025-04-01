package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import jakarta.persistence.*;

import java.util.LinkedList;

@Entity
@Table(name = "Serie")
public class Serie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSerie;
    

    @Enumerated(EnumType.STRING)
    private Categoria esCategoria;

    @OneToMany(cascade = CascadeType.PERSIST)
    private LinkedList<Capitulo> capitulosSerieList;
    
    private String nombreSerie;
    private String sinopsis;

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

    public LinkedList<Capitulo> getCapitulosSerieList() {
        return capitulosSerieList;
    }


}   
