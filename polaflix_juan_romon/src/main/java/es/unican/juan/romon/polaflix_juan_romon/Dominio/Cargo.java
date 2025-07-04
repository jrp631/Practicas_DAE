package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.juan.romon.polaflix_juan_romon.Vistas.Vistas;
import jakarta.persistence.*;

@Entity
@Table(name = "Cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @JsonView(Vistas.CargosUsuario.class)
    private Integer idCargo;

    //importe date datos_capitulo id_cargo 
    @JsonView(Vistas.CargosUsuario.class)
    private float precio;
    @JsonView(Vistas.CargosUsuario.class)
    private boolean pagado;
    @JsonView(Vistas.CargosUsuario.class)
    private LocalDate fecha;
    @JsonView(Vistas.CargosUsuario.class)
    private String nombreSerie;


    /**
     * EAGER: solo es un usuario por lo que no hay problema en cargarlo siempre
     * MERGE: si se actualiza el usuario, se actualiza el cargo
     */
    @ManyToOne (cascade = CascadeType.MERGE,fetch = FetchType.EAGER) 
    private Usuario usuario;

    //empty constructor
    public Cargo() {}

    // constructor 
    public Cargo(Categoria categoria, boolean pagado, LocalDate fecha, String nombreSerie) {
        switch (categoria) {
            case GOLD:
                this.precio = 1.50f;
                break;
            case SILVER:
                this.precio = 0.70f;
                break;
            default:
                this.precio = 0.50f;
                break;
        }
        this.pagado = pagado;
        this.fecha = fecha;
        this.nombreSerie = nombreSerie;
    }

    // metodos
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreSerie() {
        return nombreSerie;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    void pagar() {
        this.pagado = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cargo cargo = (Cargo)obj;
        return idCargo != null && idCargo.equals(cargo.idCargo);
    }

    @Override
    public int hashCode() { 
        return idCargo != null ? idCargo.hashCode() : super.hashCode();
    }
}
