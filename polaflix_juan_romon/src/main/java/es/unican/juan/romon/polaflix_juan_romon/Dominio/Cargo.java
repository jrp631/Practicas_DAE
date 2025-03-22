package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import java.time.LocalDate;

public class Cargo {
    
    //importe date datos_capitulo id_cargo 
    private float precio;
    private boolean pagado;
    private LocalDate fecha;
    private String nombreSerie;

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

    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }
    void pagar() {
        this.pagado = true;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        int month = (fecha == null) ? 0 : fecha.getMonthValue();
        int year = (fecha == null) ? 0 : fecha.getYear();
        result = prime * result + month;
        result = prime * result + year;
        return result;
    }
}
