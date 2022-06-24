package hashiwokakero;

/*************************************************************************************************
 * le Solver de la grille demandee
 *************************************************************************************************/
public class Solver {

	private Grille game;

	/** Tableau qui contient tous les Sommets **/
	private Sommet[] Sommets;

	/** Nombre de Sommets **/
	private int numOfSommets;

	/** true si le y'a une solution **/
	private boolean isSolved;

	/*****************************
	 * Constructeur d'une solveur
	 *******************/
	public Solver(Grille g) {
		game = g;
		createArray();
		numOfSommets = Sommets.length;
		isSolved = false;
		int n = 1; // representer le Sommet a qui connecter le Sommet 0
		while (n <= (numOfSommets - 1) && !isSolved) {
			if (!solve(Sommets[0], Sommets[n])) {
				n++;
			} else {
				isSolved = true;
			}
		}
		if (!isSolved || !connexe(game.getBoard())) {
			System.out.println("Il ne y'a pas de solution pour cet exemple");
		}
	}

	/******************************************************************
	 * Methode pour mettre l'ensemble de Sommets dans un tableau pour les parcourir
	 ******************************************************************/
	public void createArray() {
		Object[][] board = game.getBoard();
		int count = 0;
		for (int x = 0; x < game.size; x++) {
			for (int y = 0; y < game.size; y++) {
				if (board[x][y].getClass().equals(Sommet.class)) {
					count++;
				}
			}
		}
		Sommets = new Sommet[count];
		int num = 0;
		for (int x = 0; x < game.size; x++) {
			for (int y = 0; y < game.size; y++) {
				if (board[x][y].getClass().equals(Sommet.class)) {
					Sommets[num] = (Sommet) board[x][y];
					num++;
				}
			}
		}
	}

	/******************************************************************
	 * Methode qui retourne true si la solution est connexe
	 ******************************************************************/
	public boolean connexe(Object board[][]) {
		Sommet[] co = new Sommet[numOfSommets * 4];
		int count = 0;
		for (int x = 0; x < game.size; x++) {
			for (int y = 0; y < game.size; y++) {
				if (board[x][y].getClass().equals(Sommet.class)) {
					co[count] = (Sommet) board[x][y];
					count++;
					break;
				}
			}
			break;
		}
		for (int x = 0; x < game.size; x++) {
			for (int y = 0; y < game.size; y++) {
				if (board[x][y].getClass().equals(Sommet.class)) {
					for (int p = 0; p < count; p++) {
						if (co[p].indice == ((Sommet) board[x][y]).indice) {
							if (((Sommet) board[x][y]).getSouthPont() > 0) {
								boolean n11 = true;
								for (int n1 = 0; p < count; p++) {
									if (co[n1].indice == ((Sommet) board[x][y]).getSouthSommet().indice) {
//									co[count]=((Sommet)board[x][y]).getSouthSommet();
//									count++;
										n11 = false;
									}
								}
								if (n11) {
									co[count] = ((Sommet) board[x][y]).getSouthSommet();
									count++;
								}
							}
							if (((Sommet) board[x][y]).getEastPont() > 0) {
								boolean n22 = true;
								for (int n2 = 0; p < count; p++) {
									if (co[n2].indice == ((Sommet) board[x][y]).getEastSommet().indice) {
										n22 = false;
									}
								}
								if (n22) {
									co[count] = ((Sommet) board[x][y]).getEastSommet();
									count++;
								}
							}
							if (((Sommet) board[x][y]).getNorthPont() > 0) {
								boolean n33 = true;
								for (int n3 = 0; p < count; p++) {
									if (co[n3].indice == ((Sommet) board[x][y]).getNorthSommet().indice) {
										n33 = false;
									}
								}
								if (n33) {
									co[count] = ((Sommet) board[x][y]).getNorthSommet();
									count++;
								}
							}
							if (((Sommet) board[x][y]).getWestPont() > 0) {
								boolean n44 = true;
								for (int n4 = 0; p < count; p++) {
									if (co[n4].indice == ((Sommet) board[x][y]).getWestSommet().indice) {
										n44 = false;
									}
								}
								if (n44) {
									co[count] = ((Sommet) board[x][y]).getWestSommet();
									count++;
								}
							}

							break;
						}
					}
				}
			}
		}
		if (count >= numOfSommets) {
			return true;
		} else
			return false;

	}

	/***********************************************************************
	 * methode qui retourne true si y'a une solution
	 ***********************************************************************/
	public boolean solve(Sommet n1, Sommet n2) {
		// si les Sommets sont les memes
		if (n1 == n2) {
			return false;
		}

		if (game.isValidAdd(n1, n2)) {
			game.addPont(n1, n2);
		} else {

			return false;
		}
		if (game.checkIfWon()) {

			return true;
		} else if (!game.checkIfPossibleMoves()) {

			game.removePont(n1, n2);
			return false;
		}

		else {

			int pSommet1 = 0;
			int pSommet2 = 0;
			for (int i = 0; i < numOfSommets - 1; i++) {
				if (n1 == Sommets[i])
					pSommet1 = i;
				if (n2 == Sommets[i])
					pSommet2 = i;
			}

			do {

				if (Sommets[pSommet1].getpoidCourant() == 0 || pSommet2 == numOfSommets - 1) {
					do {
						pSommet1++;
						pSommet2 = pSommet1;
					} while (Sommets[pSommet1].getpoidCourant() == 0 && pSommet1 < numOfSommets - 1);
				}

				else {
					pSommet2++;
				}

				if (Sommets[pSommet1].getNorthSommet() == Sommets[pSommet2]
						|| Sommets[pSommet1].getEastSommet() == Sommets[pSommet2]
						|| Sommets[pSommet1].getSouthSommet() == Sommets[pSommet2]
						|| Sommets[pSommet1].getWestSommet() == Sommets[pSommet2]) {
					if (solve(Sommets[pSommet1], Sommets[pSommet2])) {
						return true;
					}
				}
			} while (!(pSommet2 >= (numOfSommets - 2) && pSommet1 >= (numOfSommets - 1)));

			if (!solve(n1, n2)) {
				game.removePont(n1, n2);
				return false;
			}
		}
		return true;
	}
}
