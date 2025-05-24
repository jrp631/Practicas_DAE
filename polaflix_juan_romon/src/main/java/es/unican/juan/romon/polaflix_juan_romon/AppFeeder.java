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
		// Usuario u2 = ur.findById(2).get();
		Serie s1 = sr.findById(1).get();
		Serie s2 = sr.findById(2).get();
		Serie s3 = sr.findById(3).get();
		Serie s5 = sr.findById(5).get();
		Serie s6 = sr.findById(6).get();
		Serie s7 = sr.findById(7).get();
		Serie s8 = sr.findById(8).get();
		Serie s9 = sr.findById(9).get();
		Serie s10 = sr.findById(10).get();
		Serie s15 = sr.findById(15).get();
		Serie s16 = sr.findById(16).get();
		Serie s17 = sr.findById(17).get();
		Serie s18 = sr.findById(18).get();
		
		u1.agregarSeriePendiente(s1); //comment 
		u1.agregarSeriePendiente(s2);
		u1.agregarSeriePendiente(s3);
		u1.agregarSeriePendiente(s10);
		u1.agregarSeriePendiente(s5);
		u1.agregarSeriePendiente(s6);
		u1.agregarSeriePendiente(s7);
		u1.agregarSeriePendiente(s8);
		u1.agregarSeriePendiente(s9);
		// u2.agregarSeriePendiente(s2);
		u1.agregarSerieTerminada(s15);
		u1.agregarSerieTerminada(s16);
		u1.agregarSerieTerminada(s17);	
		u1.agregarSerieTerminada(s18);


		// usuario 2 ve capitulos 
		Capitulo c1 = s2.getCapitulosSerieList().get(0);
		Capitulo c2 = s2.getCapitulosSerieList().get(1);
		
		// u2.verCapitulo(c1); // se genera un cargo al verlo?
		// u2.verCapitulo(c2); // se genera un cargo al verlo?

		// List<Cargo> cargos = u2.getCargosUsuario();

	
		ur.save(u1);
		// ur.save(u2);

		
		// System.out.println("Cargos de " + u1.getNombre() + ":");
		// for (Cargo c : cargos) {
		// 	System.out.println("Cargo: " + c.getIdCargo() + " - " + c.getNombreSerie() + " - " + c.getPrecio());
		// }

		// cr.saveAll(cargos);


		System.out.println("Application feeded");
	}

	private void feedUsuarios() {
		// Usuario u1 = new Usuario("Paco","paco@carSharing.es");
		// Usuario u2 = new Usuario("Lola","lola@carSharing.es");
		// ur.save(u1);
		// ur.save(u2);

		Usuario u1 = new Usuario("Paco", "pacopasswd", "ES1234567890123456789012", true);
		// Usuario u2 = new Usuario("Lola", "lolapasswd", "ES1234567890123456789013", false);

		ur.save(u1);
		// ur.save(u2);

	}

	private void feedSeries() {
		
		// house_of_cards
		// modern_family
		// it_crowd
		// game_of_thrones
		// el_secreto_de_puente_viejo
		// cars
		// cars2
		// cars3
		// rey_leon
		Serie s3 = new Serie("House of Cards", "Un político corrupto busca venganza", Categoria.GOLD);
		Serie s4 = new Serie("Modern Family", "Una familia disfuncional", Categoria.SILVER);
		Serie s5 = new Serie("The IT Crowd", "Un grupo de informáticos en una empresa", Categoria.GOLD);
		Serie s6 = new Serie("El Secreto de Puente Viejo", "Una serie de amor y misterio", Categoria.SILVER);
		Serie s7 = new Serie("Cars", "Un coche de carreras se pierde en un pueblo", Categoria.GOLD);
		Serie s8 = new Serie("Cars 2", "Un coche de carreras se convierte en espía", Categoria.SILVER);
		Serie s9 = new Serie("Cars 3", "Un coche de carreras se enfrenta a un nuevo rival", Categoria.GOLD);
		Serie s10 = new Serie("El Rey Leon", "Un león joven se convierte en rey", Categoria.SILVER);
		
	
		// doctor_who
		// el_chiringuito_de_pepe
		// the_walking_dead
		// the_sopranos

		Serie s11 = new Serie("Doctor Who", "Un extraterrestre viaja en el tiempo", Categoria.GOLD);
		Serie s12 = new Serie("El Chiringuito de Pepe", "Un chiringuito en la playa", Categoria.SILVER);
		Serie s13 = new Serie("The Walking Dead", "Un grupo de supervivientes en un mundo postapocalíptico", Categoria.GOLD);
		Serie s14 = new Serie("The Sopranos", "Un mafioso italoamericano y su familia", Categoria.SILVER);

		// true_detective
		// anclados
		// forbrydelsen
		// pluton_verbenero
		// the_wire

		Serie s15 = new Serie("True Detective", "Dos detectives investigan un asesinato", Categoria.GOLD);
		Serie s16 = new Serie("Anclados", "Un grupo de personas en un barco", Categoria.SILVER);
		Serie s17 = new Serie("Forbrydelsen", "Una detective investiga un asesinato", Categoria.GOLD);
		Serie s18 = new Serie("Pluton Verbenero", "Un grupo de amigos en un pueblo", Categoria.SILVER);
		Serie s19 = new Serie("The Wire", "Un grupo de policías y criminales en Baltimore", Categoria.GOLD);
		
		Serie s1 = new Serie("Breaking Bad", "Un profesor de quimica se convierte en fabricante de metanfetamina", Categoria.GOLD);
		Serie s2 = new Serie("Game of Thrones", "Luchas por el trono de hierro", Categoria.SILVER);

		Capitulo c1 = new Capitulo("Pilot", 1, 1, s1);
		Capitulo c2 = new Capitulo("Cat's in the Bag", 1, 2, s1);
		//capitulos de la segunda temporada de Breaking Bad
		Capitulo c7 = new Capitulo("Seven Thirty-Seven", 2, 1, s1);
		Capitulo c8 = new Capitulo("Grilled", 2, 2, s1);
		Capitulo c9 = new Capitulo("Over", 2, 3, s1);
		
		s1.addCapitulo(c1);
		s1.addCapitulo(c2);
		s1.addCapitulo(c7);
		s1.addCapitulo(c8);
		s1.addCapitulo(c9);
		
		Capitulo c3 = new Capitulo("Winter is Coming", 1, 1, s2);
		Capitulo c4 = new Capitulo("The Kingsroad", 1, 2, s2);

		s2.addCapitulo(c3);
		s2.addCapitulo(c4);

		Capitulo c5 = new Capitulo("The Kingsroad", 1, 1, s3);
		Capitulo c6 = new Capitulo("The House of Cards", 1, 2, s3);
		s3.addCapitulo(c5);
		s3.addCapitulo(c6);



		sr.save(s1);
		sr.save(s2);
		sr.save(s3);
		sr.save(s4);
		sr.save(s5);
		sr.save(s6);
		sr.save(s7);
		sr.save(s8);
		sr.save(s9);
		sr.save(s10);
		sr.save(s11);
		sr.save(s12);
		sr.save(s13);
		sr.save(s14);
		sr.save(s15);
		sr.save(s16);
		sr.save(s17);
		sr.save(s18);
		sr.save(s19);
	}
	


}
