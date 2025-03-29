package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "capitulo")
public class Capitulo {
    private String titulo;
    private Integer temporada;
    private Integer numeroCapitulo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCapitulo;
    
    @ManyToOne
    private Serie esSerie;

    static Integer id_para_capitulo = 0;
    
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
