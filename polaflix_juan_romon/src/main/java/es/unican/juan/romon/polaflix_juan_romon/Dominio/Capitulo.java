package es.unican.juan.romon.polaflix_juan_romon.Dominio;

public class Capitulo {
    private String titulo;
    private Integer temporada;
    private Integer numeroCapitulo;
    private Integer idCapitulo;
    private Serie esSerie;

    static Integer id_para_capitulo = 0;
    
    
    //Constructor 
    public Capitulo(String titulo, Integer temporada, Integer idCapitulo, Serie esSerie) {
        this.titulo = titulo;
        this.temporada = temporada;
        this.idCapitulo = idCapitulo;
        this.numeroCapitulo = id_para_capitulo;
        this.esSerie = esSerie;
        id_para_capitulo++;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((temporada == null) ? 0 : temporada.hashCode());
        result = prime * result + ((numeroCapitulo == null) ? 0 : numeroCapitulo.hashCode());
        result = prime * result + ((idCapitulo == null) ? 0 : idCapitulo.hashCode());
        result = prime * result + ((esSerie == null) ? 0 : esSerie.hashCode());
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
        Capitulo other = (Capitulo) obj;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (temporada == null) {
            if (other.temporada != null)
                return false;
        } else if (!temporada.equals(other.temporada))
            return false;
        if (numeroCapitulo == null) {
            if (other.numeroCapitulo != null)
                return false;
        } else if (!numeroCapitulo.equals(other.numeroCapitulo))
            return false;
        if (idCapitulo == null) {
            if (other.idCapitulo != null)
                return false;
        } else if (!idCapitulo.equals(other.idCapitulo))
            return false;
        if (esSerie == null) {
            if (other.esSerie != null)
                return false;
        } else if (!esSerie.equals(other.esSerie))
            return false;
        return true;
    }
    
}
