package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

		// 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}

	// Test si l'on retourne bien le volume horaire de l'UE uniquement si l'enseignant l'enseigne (le test porte sur un enseignant qui n'enseigne AUCUNE UE)
	@Test
	public void testObtenirHeureUESiEnseigne() {
		try {
			untel.heuresPrevuesPourUE(uml);
			fail( "cet appel doit lever une exception");
		} catch (IllegalStateException e) {

		}

	}

	//Test si une intervention est bien ajoutée dans l'emploi du temps de l'enseignant
	@Test
	public void testInterventionAjouteeAEmploiDuTemps(){
		Salle b107 = new Salle( "b107" , 98 );
		Intervention inter1 = new Intervention(TypeIntervention.TP, uml, b107, "26-09-2025" , 2, 14 );
		untel.ajouteIntervention( inter1 );
		assertTrue( untel.getEmploiDuTemps().contains( inter1 ));
	}

	//Test si la fonction sous service renvoie bien true si en sous service
	@Test
	public void testEnSousService(){
		untel.ajouteEnseignement(uml, 90, 10, 20); // 120 heures au total < 192
		assertTrue(untel.enSousService() );
	}

	//Test si la fonction sous service renvoie bien false si pas en sous service
	@Test
	public void testPasEnSousService(){
		untel.ajouteEnseignement(uml, 90, 14, 180); // total > 192
		assertFalse(untel.enSousService() );
	}
	//Test que si un enseignant enseigne déjà des UE et qu'on demande le nb d'heures qu'il a dans une UE qu'il enseigne pas, on a une exception
	@Test
	public void testObtenirHeureUESiEnseigne2() {
		untel.ajouteEnseignement(java, 90, 14, 180);
		try {
			untel.heuresPrevuesPourUE(uml);
			fail("cet appel doit lever une exception");
		} catch (IllegalStateException e) {

		}
	}
	//Test si une intervention est bien ajouté à l'emploi du temps s'il n'est pas vide
	@Test
	public void testInterventionAjouteeAEmploiDuTemps2(){
		Salle b107 = new Salle( "b107" , 98 );
		Intervention inter1 = new Intervention(TypeIntervention.TP, uml, b107, "26-09-2025" , 2, 14 );
		Intervention inter2 = new Intervention(TypeIntervention.TD, java, b107, "03-01-2025" , 4, 8 );
		untel.ajouteIntervention( inter1 );
		untel.ajouteIntervention( inter2 );

		assertTrue( untel.getEmploiDuTemps().contains( inter2 ));
	}


}
