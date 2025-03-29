package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "CapituloVisto")
public class CapituloVisto {


    static Integer id_paraCapituloVisto = 0;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCapituloVisto;
    private Integer Temporada;
    private Integer numeroCapitulo;
    private String titulo;
    
    private Date fechaVisto;

    public CapituloVisto (Integer Temporada, Integer numeroCapitulo, String titulo) {
        this.Temporada = Temporada;
        this.numeroCapitulo = numeroCapitulo;
        this.titulo = titulo;
        this.fechaVisto = new Date();
    }

    public static Integer getId_paraCapituloVisto() {
        return id_paraCapituloVisto;
    }

    public static void setId_paraCapituloVisto(Integer id_paraCapituloVisto) {
        CapituloVisto.id_paraCapituloVisto = id_paraCapituloVisto;
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
    public int hashCode() { //TODO: cambiar a JPA
        return idCapituloVisto != null ? idCapituloVisto.hashCode() : super.hashCode();
    }

    @Override 
    public boolean equals(Object obj) { // TODO: cambiar a JPA
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CapituloVisto other = (CapituloVisto) obj;
        return idCapituloVisto != null && idCapituloVisto.equals(other.idCapituloVisto);
    }


}
