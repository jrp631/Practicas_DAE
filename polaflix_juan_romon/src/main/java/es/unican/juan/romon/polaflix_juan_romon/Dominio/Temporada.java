package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.util.List;

public class Temporada { // TODO JPA

    private int numeroTemporada;

    private List<Capitulo> capitulosTemporada;

    public Temporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
        capitulosTemporada = null;
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

}
