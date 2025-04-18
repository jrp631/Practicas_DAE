package es.unican.juan.romon.polaflix_juan_romon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Capitulo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Cargo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Categoria;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Usuario;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.CargoRespositorio;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.SerieRepositorio;
import es.unican.juan.romon.polaflix_juan_romon.Repositorios.UsuarioRepositorio;

@Component
public class AppFeeder implements CommandLineRunner {
	
	// @Autowired
	// protected UsuarioRepository ur;
	// @Autowired
	// protected ViajeRepository vr;
	// @Autowired
	// protected ConductorRepository cr;

	@Autowired
	protected SerieRepositorio sr;

	@Autowired
	protected UsuarioRepositorio ur;

	// @Autowired
	// protected CargoRespositorio cr;
	
	@Override
	public void run(String... args) throws Exception {
		feedUsuarios();

		feedSeries();


		// feedConductores();
		// feedViajes();
		
		// testViajeRepository();

		// Los usuarios añaden series a pendientes

		Usuario u1 = ur.findById(1).get();
		Usuario u2 = ur.findById(2).get();
		Serie s1 = sr.findById(1).get();
		Serie s2 = sr.findById(2).get();
		
		u1.agregarSeriePendiente(s1);
		u2.agregarSeriePendiente(s2);

		// usuario 2 ve capitulos 
		Capitulo c1 = s2.getCapitulosSerieList().get(0);
		Capitulo c2 = s2.getCapitulosSerieList().get(1);
		
		u2.verCapitulo(c1); // se genera un cargo al verlo?
		u2.verCapitulo(c2); // se genera un cargo al verlo?

		List<Cargo> cargos = u2.getCargosUsuario();

	
		ur.save(u1);
		ur.save(u2);

		
		System.out.println("Cargos de " + u2.getNombre() + ":");
		for (Cargo c : cargos) {
			System.out.println("Cargo: " + c.getIdCargo() + " - " + c.getNombreSerie() + " - " + c.getPrecio());
		}

		// cr.saveAll(cargos);


		System.out.println("Application feeded");
	}

	private void feedUsuarios() {
		// Usuario u1 = new Usuario("Paco","paco@carSharing.es");
		// Usuario u2 = new Usuario("Lola","lola@carSharing.es");
		// ur.save(u1);
		// ur.save(u2);

		Usuario u1 = new Usuario("Paco", "pacopasswd", "ES1234567890123456789012", true);
		Usuario u2 = new Usuario("Lola", "lolapasswd", "ES1234567890123456789013", false);

		ur.save(u1);
		ur.save(u2);

	}

	private void feedSeries() {
		Serie s1 = new Serie("Breaking Bad", "Un profesor de quimica se convierte en fabricante de metanfetamina", Categoria.GOLD);
		Serie s2 = new Serie("Game of Thrones", "Luchas por el trono de hierro", Categoria.SILVER);

		Capitulo c1 = new Capitulo("Pilot", 1, 1, s1);
		Capitulo c2 = new Capitulo("Cat's in the Bag", 1, 2, s1);
		
		s1.addCapitulo(c1);
		s1.addCapitulo(c2);
		
		Capitulo c3 = new Capitulo("Winter is Coming", 1, 1, s2);
		Capitulo c4 = new Capitulo("The Kingsroad", 1, 2, s2);

		s2.addCapitulo(c3);
		s2.addCapitulo(c4);

		sr.save(s1);
		sr.save(s2);
	}
	
	// private void feedConductores() {
	// 	Conductor c = new Conductor("Travis","travisBickle@carsharing.es",17);
	// 	ur.save(c);
	// }

	// private void feedViajes() {
		
	// 	Conductor c = cr.findById("Travis").get();
		
	// 	Localizacion l11 = new PuntoConocido("Santander","Facultad de Ciencias"); 
	// 	Localizacion l12 = new PuntoConocido("Cadiz", "Playa de la Caleta");
	// 	Localizacion l21 = new PuntoConocido("Cadiz", "Playa de la Caleta"); 
	// 	Localizacion l22 = new PuntoConocido("Santander","Facultad de Ciencias");
		
		
	// 	SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
	// 	Date ida = null;
	// 	Date vuelta = null;
	// 	try {
	// 		ida = dateParser.parse("01-04-2019");
	// 		vuelta = dateParser.parse("07-04-2019");
	// 	} catch (ParseException e) {
	// 		e.printStackTrace();
	// 	}
		
	// 	Viaje v1 = new Viaje(l11,l12,c,ida,6000,8000,3);
	// 	Viaje v2 = new Viaje(l21,l22,c,vuelta,6000,8000,3);
		
	// 	vr.save(v1);
	// 	vr.save(v2);
	// }
	
	// private void testViajeRepository() {
		
	// 	SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
	// 	Date sample = null;
	// 	try {
	// 		sample = dateParser.parse("01-01-2020");
	// 	} catch (ParseException e) {
	// 		System.out.println("Crujo parseando fecha");
	// 		e.printStackTrace();
	// 	}
		
	// 	// Set<Viaje> viajes = vr.findByOrigenCiudadAndDestinoCiudad("Santander","Cadiz");
	// 	Set<Viaje> viajes = vr.findByOrigenAndDestino("Santander","Cadiz");
		
	// 	System.out.println("Viajes recuperados = " + viajes.size());
	
	// 	for(Viaje v : viajes) {
	// 		System.out.println("Viaje in " + v.getFecha());
	// 	}
		
	// 	viajes = vr.findByOrigen_CiudadAndFechaBeforeOrderByPrecio("Santander", sample);

	// 	System.out.println("================================");
		
	// 	System.out.println("Viajes recuperados = " + viajes.size());
			
	// }

}
