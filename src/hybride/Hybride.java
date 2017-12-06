package hybride;

import java.util.ArrayList;

import exceptions.BadArgumentException;
import patricia.NodeP;
import patricia.PatriciaTrie;
import tools.Tools;

public class Hybride {
	
	private HNode hd;
	private int nbMots;
	
	public Hybride() {
		hd=null;
		nbMots=0;
	}
	
	
	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- ---- primitives ---- -------------------- */ 

	/**
	 * determine si l'arbre Hybride est vide
	 * @return true si l'arbre est vide, false sinon
	 */
	public boolean isEmpty(){ return hd == null; }

	
	/* -------------------- -------------------- -------------------- */ 
	/* --------------------  fonctions avancees  -------------------- */
	
	
	/**
	 * supprime le mot passer en parametre de l'Hybride
	 * @param w:String mos qu'on veut supprimer
	 * @throws BadArgumentException : si le mot n'est pas un mot valide
	 */
	public void suppression(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		Tools.checkWord("Hybride.suppression", w);

		if(!isEmpty()) 
			if( hd.suppression(null, w) ) hd = null;
	}

	
	/**
	 * TODO : normalement cette methode est testé
	 * liste les mots present dans l'Hybride
	 * @return une ArrayList<String> contenant tous les mots present dans l'Hybride
	 */
	public ArrayList<String> listeMot(){
		ArrayList<String> res = new ArrayList<String>();
		if(!isEmpty()) hd.listeMot(res, "");
		return res;
	}


	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- -------------------- -------------------- */
	
	/* partie joe non fusionnee */
	/* TODO faut que je vois avec toi si elles sont ok */

	
	
	public void add(String s) throws BadArgumentException{
		/* verifier que le mot est correcte */
		Tools.checkWord("Hybride.add", s);
		
		if(hd==null)
			hd=new HNode(null, s.charAt(0)+"", null, null, null);
		hd.add(s.substring(0),nbMots);
		nbMots++;

	}
	
	public boolean rechercher(String w) throws BadArgumentException {
		/* verifier que le mot est correcte */
		Tools.checkWord("Hybride.rechercher", w);

		if(hd != null)
			return hd.rechercher(w);
		
		return false;
	}
	
	public int comptageMot(){
		int compteur = 0;
		
		if(hd != null)
			compteur += hd.comptageMot();
		
		return compteur;
	}
	
	public int prefixe(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		Tools.checkWord("Hybride.prefixe", w);

		if(hd != null){
			return hd.prefixe(w);
		}
		return 0;
	}
	
	public PatriciaTrie toPatriciaTrie() {
		PatriciaTrie n = new PatriciaTrie();
		if(hd!=null) {
			n.initFils();
			NodeP[] tabFils=n.getTabFils();
			tabFils[hd.getPrefix().charAt(0)]=hd.parcoursProfondeur(new StringBuilder(""));
			n.incNbFils();
			HNode previous=hd.getPrevious();
			HNode next=hd.getNext();
			while(next!=null || previous!=null) {
				if(next!=null) {
					tabFils[next.getPrefix().charAt(0)]=next.parcoursProfondeur(new StringBuilder(""));
					next=next.getNext();
					n.incNbFils();;
				}
				if(previous!=null) {
					tabFils[previous.getPrefix().charAt(0)]=previous.parcoursProfondeur(new StringBuilder(""));
					previous=previous.getPrevious();
					n.incNbFils();
				}
			}
		}
		return n;
	}
	
	/* partie Hatem non fusionnee */
	
	/* TODO comment ca se passe si l arbre est vide 3 ou 1 */
	/**
	 * @return int: le nombre de pointeur nul dans l'Hybride
	 */
	public int comptageNil(){
		if( isEmpty() ) return 1;
		return hd.comptageNil();
	}

	
	/* -------------------- -------------------- -------------------- */ 
	/* --------------------  ----- autres -----  -------------------- */

	
	/**
	 * affichage rapide, pas tres claire mais permet de visualiser un peu
	 */
	public void showHybride(){
		if(hd != null) hd.showHNode(0);
		else System.out.println("l'arbre est vide");
	}

	

}


