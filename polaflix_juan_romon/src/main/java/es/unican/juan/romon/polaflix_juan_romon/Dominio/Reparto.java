package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Reparto")
public class Reparto { // TODO JPA
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //TODO vistas 
    private int idReparto;

    private String nombre;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    // @ManyToMany(cascade = CascadeType.MERGE)
    // private List<Serie> series; 
    @ManyToOne(cascade = CascadeType.ALL)
    private Serie serie;


    public Reparto() {
        // Constructor vacío
    }

    public Reparto(String nombre, Rol rol,Serie serie) {
        this.nombre = nombre;
        this.rol = rol;
        // this.series = new ArrayList<Serie>();
        this.serie = serie;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    // public List<Serie> getSeries() {
    //     return series;
    // }

    // public void setSeries(List<Serie> series) {
    //     this.series = series;
    // }

    // public void addSerie(Serie serie) {
    //     if (!this.series.contains(serie)) {
    //         this.series.add(serie);
    //         // serie.addReparto(this); // Aseguramos la relación bidireccional
    //     }
    // }

    @Override
    public String toString() {
        return "Reparto{" +
                "nombre='" + nombre + '\'' +
                ", rol=" + rol +
                '}';
    }   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reparto)) return false;
        Reparto reparto = (Reparto) o;
        return nombre.equals(reparto.nombre) && rol == reparto.rol;
    }
}
