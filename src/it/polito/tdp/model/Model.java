package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.dao.EsameDAO;

public class Model {

	// esami letti dal database
	private List<Esame> esami;

	// gestione della ricorsione
	private List<Esame> best;

	// media migliore
	private double media_best;

	public Model() {
		EsameDAO dao = new EsameDAO();
		this.esami = dao.getTuttiEsami();
	}

	/**
	 * Trova la combinazione di corsi avente la media dei voti massima
	 * 
	 * @param numeroCrediti
	 * @return l'elenco dei corsi ottimale, oppure {@code null} se non esiste alcuna
	 *         combinazione di corsi la cui somma è pari esattamente ad m.
	 */
	public List<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {

//		best = new ArrayList<Esame>();
		best = null;
		media_best = 0.0;

		Set<Esame> parziale = new HashSet<Esame>();

		cerca(parziale, 0, numeroCrediti);

		return best;
	}

	private void cerca(Set<Esame> parziale, int L, int m) {

		// casi terminali?
		// se questa soluzione parziale ha più di m crediti esco
		int crediti = sommaCrediti(parziale);
		
		if (crediti > m)
			return;

		if (crediti == m) {
			double media = calcolaMedia(parziale);
			if (media > media_best) {
				// trovata una soluzione migliore
				best = new ArrayList<Esame>(parziale);
				System.out.format("Trovato: %s", parziale);
				media_best = media;
				return;
			} else {
				System.out.format("Non trovato un cazzo", parziale);
				return;
			}
		}

		// di sicuro qui ho crediti < m

		if (L == esami.size())
			return;

		// generiamo sotto-problemi
		// esami[L] è da aggiungere o no?

		// provo a non aggiungerlo
		cerca(parziale, L + 1, m);

		// provo ad aggiungerlo
		parziale.add(esami.get(L));
		cerca(parziale, L + 1, m);

		parziale.remove(esami.get(L));

	}

	private double calcolaMedia(Set<Esame> parziale) {
		double media = 0.0;
		int crediti = 0;
		for (Esame esame : parziale) {
			media += esame.getVoto()*esame.getCrediti();
			crediti += esame.getCrediti();
		}
		
		return media/crediti;
	}

	public double getMedia(List<Esame> voti) {
		double media = 0.0;
		int crediti = 0;
		for (Esame esame : voti) {
			media += esame.getVoto()*esame.getCrediti();
			crediti += esame.getCrediti();
		}
		
		return media/crediti;
	}
	
	private int sommaCrediti(Set<Esame> parziale) {
//		int crediti = 0;
//		for (Esame esame : parziale) 
//			crediti += esame.getCrediti();
//		return crediti;
		return parziale.stream().mapToInt(a->a.getCrediti()).sum();
	}

}
