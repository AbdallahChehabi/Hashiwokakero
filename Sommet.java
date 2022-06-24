package hashiwokakero;

/***********************************************************************
 * Representation des iles sous forme de Sommet
 * 
 **********************************************************************/
public class Sommet {
	/**
	 * Compter les Sommets cres
	 **/

	public static int indicee = 0;
	public int indice;

	/**
	 * represente le poids de chaque Sommet
	 **/
	private int poid;

	/**
	 * represente le nombre de pont construit
	 **/
	private int poidCourant;

	/** les Sommets voisins **/
	private Sommet northSommet, eastSommet, southSommet, westSommet;

	/**
	 * les ponts vers les voisins
	 **/
	private int northPont, eastPont, southPont, westPont;

	/**************************
	 * Constructeur de Sommet
	 ***************************/
	public Sommet(int d) {
		poid = d;
		poidCourant = d;
		indice = indicee;
		indicee++;
		northPont = 0;
		eastPont = 0;
		southPont = 0;
		westPont = 0;
	}

	/*****************************************
	 * decrementer les ponts
	 ****************************************/
	public void dec() {
		poidCourant--;
	}

	/*****************************************
	 * Incrementer les ponts
	 ****************************************/
	public void inc() {
		poidCourant++;
	}

	public int getpoidCourant() {
		return poidCourant;
	}

	public int getpoid() {
		return poid;
	}

	public void setNorthSommet(Sommet n) {
		northSommet = n;
	}

	public Sommet getNorthSommet() {
		return northSommet;
	}

	public void setEastSommet(Sommet n) {
		eastSommet = n;
	}

	public Sommet getEastSommet() {
		return eastSommet;
	}

	public void setSouthSommet(Sommet n) {
		southSommet = n;
	}

	public Sommet getSouthSommet() {
		return southSommet;
	}

	public void setWestSommet(Sommet n) {
		westSommet = n;
	}

	public Sommet getWestSommet() {
		return westSommet;
	}

	public int getNorthPont() {
		return northPont;
	}

	public void setNorthPont(int northPont) {
		this.northPont = northPont;
	}

	public int getEastPont() {
		return eastPont;
	}

	public void setEastPont(int eastPont) {
		this.eastPont = eastPont;
	}

	public int getSouthPont() {
		return southPont;
	}

	public void setSouthPont(int southPont) {
		this.southPont = southPont;
	}

	public int getWestPont() {
		return westPont;
	}

	public void setWestPont(int westPont) {
		this.westPont = westPont;
	}

	@Override
	public String toString() {
		return "" + this.getpoid();
	}

	public static int getIndice() {
		return indicee;
	}

	public static void setIndice(int indice) {
		Sommet.indicee = indice;
	}
}