package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;
import jakarta.persistence.*;

@Entity
@Table(name = "capitulo")
public class Capitulo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Vistas.CapituloSerie.class})
    private Integer idCapitulo;
    
    @ManyToOne
    @JsonBackReference
    @JsonView({Vistas.CapituloSerie.class})
    
    private Serie esSerie;

    @JsonView({Vistas.CapituloSerie.class})
    private String titulo;
    @JsonView({Vistas.CapituloSerie.class})
    private Integer temporada;
    @JsonView({Vistas.CapituloSerie.class})
    private Integer numeroCapitulo;

   
    //Constructor por defecto
    public Capitulo(){}


    //Constructor 
    public Capitulo(String titulo, Integer temporada, Integer numeroCapitulo, Serie esSerie) {
        this.titulo = titulo;
        this.temporada = temporada;
        this.numeroCapitulo = numeroCapitulo;
        this.esSerie = esSerie;
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

    @Override
    public int hashCode() {
        return idCapitulo != null ? idCapitulo.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Capitulo capitulo = (Capitulo) obj;
        return idCapitulo != null && idCapitulo.equals(capitulo.idCapitulo);
    }
    
}
