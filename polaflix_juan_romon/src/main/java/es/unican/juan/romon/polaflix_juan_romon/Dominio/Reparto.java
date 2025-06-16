package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Reparto")
public class Reparto { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //TODO vistas 
    private int idReparto;

    private String nombre;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToOne(cascade = CascadeType.ALL)
    private Serie serie;


    public Reparto() {
        // Constructor vacío
    }

    public Reparto(String nombre, Rol rol,Serie serie) {
        this.nombre = nombre;
        this.rol = rol;
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
