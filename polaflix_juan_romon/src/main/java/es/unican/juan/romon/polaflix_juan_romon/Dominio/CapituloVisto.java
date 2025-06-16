package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.util.Date;
import jakarta.persistence.*;

@Entity 
@Table(name = "CapituloVisto")
public class CapituloVisto {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCapituloVisto;
    private Integer Temporada;
    private Integer numeroCapitulo;
    private String titulo;
    
    private Date fechaVisto;

    // empty constructor
    public CapituloVisto() {
    }
    public CapituloVisto (Integer Temporada, Integer numeroCapitulo, String titulo) {
        this.Temporada = Temporada;
        this.numeroCapitulo = numeroCapitulo;
        this.titulo = titulo;
        this.fechaVisto = new Date();
    }

    public Integer getIdCapituloVisto() {
        return idCapituloVisto;
    }

    public void setIdCapituloVisto(Integer idCapituloVisto) {
        this.idCapituloVisto = idCapituloVisto;
    }

    public Integer getTemporada() {
        return Temporada;
    }

    public void setTemporada(Integer temporada) {
        Temporada = temporada;
    }

    public Integer getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(Integer numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    public Date getFechaVisto() {
        return fechaVisto;
    }

    public void setFechaVisto(Date fechaVisto) {
        this.fechaVisto = fechaVisto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() { 
        return idCapituloVisto != null ? idCapituloVisto.hashCode() : super.hashCode();
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CapituloVisto other = (CapituloVisto) obj;
        boolean temporadaMatch = (Temporada != null && Temporada.equals(other.getTemporada()));
        boolean numeroCapituloMatch = (numeroCapitulo != null && numeroCapitulo.equals(other.getNumeroCapitulo()));
        boolean tituloMatch = (titulo != null && titulo.equals(other.getTitulo()));

        System.out.println("(" + other.getTemporada() + " | "+ Temporada +  ") Temporada match: " + temporadaMatch);
        System.out.println("(" + other.getNumeroCapitulo() + " | "+ numeroCapitulo +  ") numeroCapitulo match: " + numeroCapituloMatch);
        System.out.println("(" + other.getTitulo() + " | "+ titulo +  ") titulo match: " + tituloMatch);

        return temporadaMatch && numeroCapituloMatch && tituloMatch;
    }

    @Override
    public String toString() {
        return "CapituloVisto{" +
                "idCapituloVisto=" + idCapituloVisto +
                ", Temporada=" + Temporada +
                ", numeroCapitulo=" + numeroCapitulo +
                ", titulo='" + titulo + '\'' +
                ", fechaVisto=" + fechaVisto +
                '}';
    }


}
