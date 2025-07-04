package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;
import jakarta.persistence.*;

@Entity
@Table(name = "capitulo")
public class Capitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({ Vistas.CapituloSerie.class,
            Vistas.DescripcionSerie.class,
            Vistas.TemporadaSerie.class })
    private Integer idCapitulo;

    @ManyToOne
    @JsonBackReference
    @JsonView({ Vistas.CapituloSerie.class,
            Vistas.DescripcionSerie.class,
            Vistas.TemporadaSerie.class })
    private Serie esSerie;

    @JsonView({ Vistas.CapituloSerie.class,
            Vistas.DescripcionSerie.class,
            Vistas.TemporadaSerie.class })
    private String titulo;

    @JsonView({ Vistas.CapituloSerie.class,
            Vistas.DescripcionSerie.class,
            Vistas.TemporadaSerie.class })
    private String descripcion;

    @JsonView({ Vistas.CapituloSerie.class,
            Vistas.DescripcionSerie.class,
            Vistas.TemporadaSerie.class })
    private Integer temporada;
    @JsonView({ Vistas.CapituloSerie.class,
            Vistas.DescripcionSerie.class,
            Vistas.TemporadaSerie.class })
    private Integer numeroCapitulo;

    // Constructor por defecto
    public Capitulo() {
    }

    // Constructor
    public Capitulo(String titulo, Integer temporada, Integer numeroCapitulo, Serie esSerie, String descripcion) {
        this.titulo = titulo;
        this.temporada = temporada;
        this.numeroCapitulo = numeroCapitulo;
        this.esSerie = esSerie;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(Integer numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    public Integer getIdCapitulo() {
        return idCapitulo;
    }

    public void setIdCapitulo(Integer idCapitulo) {
        this.idCapitulo = idCapitulo;
    }

    public Serie getEsSerie() {
        return this.esSerie;
    }

    public void setEsSerie(Serie esSerie) {
        this.esSerie = esSerie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        return idCapitulo != null ? idCapitulo.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Capitulo capitulo = (Capitulo) obj;
        return idCapitulo != null && titulo.equals(capitulo.getTitulo())
                && temporada.equals(capitulo.getTemporada())
                && numeroCapitulo.equals(capitulo.getNumeroCapitulo());
    }


    @Override
    public String toString() {
        return "Capitulo{" +
                "idCapitulo=" + idCapitulo +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", temporada=" + temporada +
                ", numeroCapitulo=" + numeroCapitulo +
                '}';
    }

}
