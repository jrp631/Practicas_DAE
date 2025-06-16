package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;
import jakarta.persistence.*;

@Entity
@Table(name = "Temporada")
public class Temporada { // TODO vistas

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Vistas.DescripcionSerie.class,
                Vistas.AllSeries.class})
    private int idTemporada;

    @JsonView({Vistas.DescripcionSerie.class,
                Vistas.AllSeries.class})
    private int numeroTemporada;

    /**
     * EAGER: cada vez que se carga una temporada, se cargan todos los capítulos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonView({Vistas.DescripcionSerie.class,
                Vistas.AllSeries.class})
    private List<Capitulo> capitulosTemporada;

    //Empty constructor
    public Temporada() {

    }

    public Temporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
        capitulosTemporada = new LinkedList<Capitulo>();
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public List<Capitulo> getCapitulosTemporada() {
        return capitulosTemporada;
    }

    public void setCapitulosTemporada(List<Capitulo> capitulosTemporada) {
        this.capitulosTemporada = capitulosTemporada;
    }

    public Capitulo getCapitulo(int numeroCapitulo) {
        for (Capitulo capitulo : capitulosTemporada) {
            if (capitulo.getNumeroCapitulo().equals(numeroCapitulo)) {
                return capitulo;
            }
        }
        return null; // O lanzar una excepción si no se encuentra
    }

    public void addCapitulo(Capitulo capitulo) {
        if (capitulosTemporada != null) {
            capitulosTemporada.add(capitulo); // no comprobamos si el capítulo ya existe
        } else {
            throw new IllegalStateException("La lista de capítulos no está inicializada.");
        }
    }

    public int getNumeroCapitulos() {
        if (capitulosTemporada != null) {
            return capitulosTemporada.size();
        } else {
            throw new IllegalStateException("La lista de capítulos no está inicializada.");
        }
    }

    public Capitulo getUltimoCapitulo() {
        return capitulosTemporada.get(getNumeroCapitulos() - 1);
    }

}
