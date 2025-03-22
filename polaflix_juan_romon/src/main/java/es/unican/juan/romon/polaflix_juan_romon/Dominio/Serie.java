package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Capitulo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Serie {
    
    
    private String nombreSerie;
    private String sinopsis;
    private Integer key;
    private Categoria esCategoria;
    // private Integer numCapitulos;
    // private Integer numTemportadas;

    private LinkedList<Capitulo> capitulosSerieList;
    
    public Serie(String nombreSerie, String sinopsis, Categoria categoria) {
        this.nombreSerie = nombreSerie;
        this.sinopsis = sinopsis;
        this.esCategoria = categoria;
        this.capitulosSerieList = new LinkedList<Capitulo>();
        
        this.key = hashCode();
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
    public Integer getHey() {
        return key;
    }
    public void setKey(Integer key) {
        this.key = key;
    }

    public Categoria getEsCategoria() {
        return esCategoria;
    }

    public void setEsCategoria(Categoria esCategoria) {
        this.esCategoria = esCategoria;
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
        return "Serie{" + "nombreSerie=" + nombreSerie + ", sinopsis=" + sinopsis + ", key=" + key + ", esCategoria=" + esCategoria + '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombreSerie == null) ? 0 : nombreSerie.hashCode());
        result = prime * result + ((sinopsis == null) ? 0 : sinopsis.hashCode());
        result = prime * result + ((esCategoria == null) ? 0 : esCategoria.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Serie other = (Serie) obj;
        if (nombreSerie == null) {
            if (other.nombreSerie != null)
                return false;
        } else if (!nombreSerie.equals(other.nombreSerie))
            return false;
        if (sinopsis == null) {
            if (other.sinopsis != null)
                return false;
        } else if (!sinopsis.equals(other.sinopsis))
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (esCategoria != other.esCategoria)
            return false;
        if (capitulosSerieList == null) {
            if (other.capitulosSerieList != null)
                return false;
        } else if (!capitulosSerieList.equals(other.capitulosSerieList))
            return false;
        return true;
    }


}   
