package es.unican.juan.romon.polaflix_juan_romon.Dominio;

public class Reparto { // TODO JPA
    private String nombre;
    private Rol rol;

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
