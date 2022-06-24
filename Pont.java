package hashiwokakero;

/*******************************************************************************
 * representation des ponts entre noeuds
 *******************************************************************************/
public class Pont {

	/**
	 * 0 == aucun Pont, 1 = un Pont, 2 = two Ponts
	 **/
	private int PontType;

	/**
	 * 0 = no Pont, 1 = verticale, 2 = horizontale
	 **/
	private int PontDirection;

	public Pont() {
		PontType = 0;
		PontDirection = 0;
	}

	public int getPontType() {
		return PontType;
	}

	public void setPontType(int PontType) {
		this.PontType = PontType;
	}

	public int getPontDirection() {
		return PontDirection;
	}

	public void setPontDirection(int PontDirection) {
		this.PontDirection = PontDirection;
	}

	// Methode pour afficher les Ponts
	public String toString() {
		if (this.PontDirection == 2 && this.PontType > 0) {
			if (this.PontType == 1) {
				return "―";
			} else {
				return "=";
			}
		} else if (this.PontDirection == 1 && this.PontType > 0) {
			if (this.PontType == 1) {
				return "ǀ";
			} else {
				return "ǁ";
			}
		} else
			return " ";
	}
}
