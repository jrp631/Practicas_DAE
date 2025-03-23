package es.unican.juan.romon.polaflix_juan_romon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.unican.juan.romon.polaflix_juan_romon.Dominio.Capitulo;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Categoria;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Serie;
import es.unican.juan.romon.polaflix_juan_romon.Dominio.Usuario;

@SpringBootTest
class PolaflixJuanRomonApplicationTests {

	Serie serie = new Serie("Breaking Bad", "Un profesor de quimica se convierte en un narcotraficante", Categoria.GOLD);
	Capitulo capitulo1 = new Capitulo("Piloto", 1, 1, serie);
	Capitulo capitulo2 = new Capitulo("Cat's in the Bag", 1, 2, serie);

	Usuario usuario = new Usuario("Juan", "1234", "ES123456789", false);
	@Test
	void contextLoads() {
	}

	@Test
	void testCapituloSerie() {
		// Test the functionality of the Capitulo and Serie classes
		//Create a couple of capitulos 
		serie.addCapitulo(capitulo1);
		serie.addCapitulo(capitulo2);
		// check if the capitulos are in the list of capitulos of the serie
		assertEquals(serie.getCapitulosSerieList().size(), 2);
		assertEquals(serie.getCapitulosSerieList().get(0), capitulo1);
		assertEquals(serie.getCapitulosSerieList().get(1), capitulo2);

		// TODO: check order of the caps

	}

	@Test
	void testSeriesKey() {
		// Test the functionality of the Serie class
		// check the key of the serie
		assertEquals(serie.getHey(), serie.hashCode());
	
		// Test that two two different series have different keys
		Serie serie2 = new Serie("The Wire", "Un drama policiaco en Baltimore", Categoria.SILVER);
		assertEquals(serie2.getHey(), serie2.hashCode());
		assertEquals(serie.getHey() != serie2.getHey(), true);
	}

	@Test
	void testAgregarSeriePendiente(){
		// with no series pendientes
		assertEquals(usuario.getSeriesPendientesMap().size(), 0);
		// add a serie pendiente		
		usuario.agregarSeriePendiente(serie);
		// check the serie is in the list of series pendientes
		assertEquals(usuario.getSeriesPendientesMap().size(), 1);
		// check the serie is the one we added
		assertEquals(usuario.getSeriesPendientesMap().get(serie.hashCode()), serie);
		// add another serie pendiente
		Serie serie2 = new Serie("The Wire", "Un drama policiaco en Baltimore", Categoria.SILVER);
		usuario.agregarSeriePendiente(serie2);
		// check the serie is in the list of series pendientes
		assertEquals(usuario.getSeriesPendientesMap().size(), 2);
		// check the serie is the one we added
		assertEquals(usuario.getSeriesPendientesMap().get(serie2.hashCode()), serie2);

		// add a serie that is already in the list
		usuario.agregarSeriePendiente(serie2);
		// check the serie is in the list of series pendientes
		assertEquals(usuario.getSeriesPendientesMap().size(), 2);
	}

	@Test
	void testVerCapitulo(){
		// Check the user has no cargos yet
		assertEquals(usuario.getCargosByMonthYear().size(), 0);

		// add a serie pendiente
		usuario.agregarSeriePendiente(serie);
		// check the serie is in the list of series pendientes
		assertEquals(usuario.getSeriesPendientesMap().size(), 1);
		// watch a capitulo from the serie we added
		Serie serie_de_cap = capitulo1.getEsSerie();
		System.out.println(serie_de_cap.toString());

		usuario.verCapitulo(capitulo1);
		// check the serie is not in the list of series pendientes
		assertEquals(usuario.getSeriesPendientesMap().size(), 0);
		// check the user has a cargo
		// assertEquals(usuario.getCargosByMonthYear().size(), 1);

	}

}
