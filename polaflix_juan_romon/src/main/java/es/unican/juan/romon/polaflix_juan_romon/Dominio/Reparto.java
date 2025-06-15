package es.unican.juan.romon.polaflix_juan_romon.Dominio;

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

    public Reparto() {
        // Constructor vacío
    }

    public Reparto(String nombre, Rol rol) {
        this.nombre = nombre;
        this.rol = rol;
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
