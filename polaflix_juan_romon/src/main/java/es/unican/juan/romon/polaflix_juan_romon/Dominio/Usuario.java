package es.unican.juan.romon.polaflix_juan_romon.Dominio;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.CapituloVistoSeries;

import java.util.*; //List

import org.hibernate.dialect.function.array.ArraySliceUnnestFunction;

public class Usuario {

    private String nombre;
    private String password;
    private String IBAN;
    private Boolean tarifaPlana;

    // Lists & Maps for series 
    private ArrayList<Serie> seriesTerminadas;  
    private HashMap<Integer, Serie> seriesPendientesMap; //HashMap para series pendientes
    private LinkedList<CapituloVistoSeries> capitulosVistosSerie;

    // Structures to organize Cargos
    // hashmaps of Cargos By Month_year
    private HashMap<Integer, LinkedList<Cargo>> cargosByMonthYear;


    // constructor
    public Usuario(String nombre, String password, String IBAN, Boolean tarifaPlana) {
        this.nombre = nombre;
        this.password = password;
        this.IBAN = IBAN;
        this.tarifaPlana = tarifaPlana;
        this.seriesTerminadas = new ArrayList<Serie>();
        this.seriesPendientesMap = new HashMap<Integer, Serie>();
        this.capitulosVistosSerie = new LinkedList<CapituloVistoSeries>();
        this.cargosByMonthYear = new HashMap<Integer, LinkedList<Cargo>>();
    }

    // metodos

    //ver capitulo 
    public void verCapitulo(Capitulo capitulo) {
        //TODO
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        Cargo cargo;
        CapituloVistoSeries capVistoSerie;
        // check if the serie of the capitulo is in the list of seriesPendientes
        Serie serie_de_cap = capitulo.getEsSerie();
        // is it the first time the user sees a capitulo of the serie?
        if (seriesPendientesMap.get(serie_de_cap.hashCode()) == null) {
            // first time watching
            capVistoSerie = new CapituloVistoSeries(null, serie_de_cap);
            cargo = capVistoSerie.addCapituloVisto(capitulo);
            capitulosVistosSerie.add(capVistoSerie);

            // quitamos de la lista de pendientes
            seriesPendientesMap.remove(serie_de_cap.hashCode());
        } else {
            capVistoSerie = getCapituloVistoSeries(capitulo);
            cargo = capVistoSerie.addCapituloVisto(capitulo);
        }
        // TODO add cargo to the lists of cargos
        addCargoToList(cargo);


    }
    
    
    // añadir serie para ver 
    public void agregarSeriePendiente(Serie serie) { 

        //check if serie is not null
        if (serie == null) {
            throw new IllegalArgumentException("Serie no puede ser null");
        }

        //check if the serie is not already in the list
        if (seriesPendientesMap.get(serie.hashCode()) == null) {
            seriesPendientesMap.put(serie.hashCode(), serie);
        }
        //if the serie is already in the list, do nothing
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String iBAN) {
        IBAN = iBAN;
    }

    public Boolean getTarifaPlana() {
        return tarifaPlana;
    }

    public void setTarifaPlana(Boolean tarifaPlana) {
        this.tarifaPlana = tarifaPlana;
    }

    public ArrayList<Serie> getSeriesTerminadas() {
        return seriesTerminadas;
    }

    public void setSeriesTerminadas(ArrayList<Serie> seriesTerminadas) {
        this.seriesTerminadas = seriesTerminadas;
    }

    public HashMap<Integer, Serie> getSeriesPendientesMap() {
        return seriesPendientesMap;
    }

    public void setSeriesPendientes(HashMap<Integer, Serie> seriesPendientes) {
        this.seriesPendientesMap = seriesPendientes;
    }

    public LinkedList<CapituloVistoSeries> getCapitulosVistosSerie() {
        return capitulosVistosSerie;
    }

    public void setCapitulosVistosSerie(LinkedList<CapituloVistoSeries> capitulosVistosSerie) {
        this.capitulosVistosSerie = capitulosVistosSerie;
    }

    private CapituloVistoSeries getCapituloVistoSeries(Capitulo capitulo) {
        // check the argument is not null
        if (capitulo == null) {
            throw new IllegalArgumentException("Capitulo no puede ser null");
        }
        Serie serie_de_cap = capitulo.getEsSerie();
        // check the entry in the list of capitulosVistosSerie
        for (CapituloVistoSeries csv : capitulosVistosSerie) {
            if (csv.getPerteneceSerie().equals(serie_de_cap)) {
                return csv;
            }
        }
        return null;
    }

    private void addCargoToList(Cargo cargo) 
    {
        if (cargo == null) {
            throw new IllegalArgumentException("Cargo no puede ser null");
        }
        // get the month_year of the cargo -> hashcode
        Integer month_year = cargo.hashCode();
        // get the list of cargos for the month_year
        LinkedList<Cargo> cargos = cargosByMonthYear.get(month_year);
        // if the list does not exist, create it
        if (cargos == null) {
            cargos = new LinkedList<Cargo>();
            cargosByMonthYear.put(month_year, cargos);
        }
        // add the cargo to the list
        cargos.add(cargo);

    }
    
}
