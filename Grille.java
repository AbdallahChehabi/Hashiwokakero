package hashiwokakero;

import java.util.Scanner;

/** represente la grille **/

public class Grille {

	public Object[][] board;
	public int size;

	Scanner sc = new Scanner(System.in);

	/**************************************************************************
	 * le constructeur de la Grille
	 **************************************************************************/
	public Grille(int n) {
		size = n;
		board = new Object[size][size]; 
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = new Pont();
			}
		}
	}

	public void addSommet() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int a;
				boolean b = true;
				while (b) {

					System.out.println("saisir le Sommet de la case [" + i + "][" + j + "] ou 0");
					String s = sc.next();
					System.out.println();
					if (s == "0" || s == "") {
						b = false;
						break;
					}
					try {
						a = Integer.parseInt(s);
						b = false;
					} catch (NumberFormatException e) {
						System.out.println("veuillez saisir un chiffre entre 0 et 8");
						b = true;
						continue;
					}
					if (a > 8) {
						System.out.println("veuillez saisir une valeure entre 0 et 8");
						b = true;
					}
					if (a > 0)
						this.board[i][j] = new Sommet(a);
				}
			}

		}
	}

	public void showBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j].getClass() == (new Sommet(0).getClass())) {
					System.out.print(" " + board[i][j].toString() + " ");
				} else {
					System.out.print(" " + board[i][j].toString() + " ");
				}
			}
			System.out.println();
		}
	}

	public String ToString() {
		return "h";
	}

	/*************************************************
	 * methode pour trouver les connexion possible entre Sommets
	 ************************************************/
	public void findPossibleConnections() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y].getClass().equals(Sommet.class)) {

					for (int y2 = y - 1; y2 >= 0; y2--) {
						if (board[x][y2].getClass().equals(Sommet.class)) {
							((Sommet) board[x][y]).setNorthSommet((Sommet) board[x][y2]);
							y2 = -1;
						}
					}

					for (int x2 = x - 1; x2 >= 0; x2--) {
						if (board[x2][y].getClass().equals(Sommet.class)) {
							((Sommet) board[x][y]).setWestSommet((Sommet) board[x2][y]);
							x2 = -1;
						}
					}

					for (int x2 = x + 1; x2 < size; x2++) {
						if (board[x2][y].getClass().equals(Sommet.class)) {
							((Sommet) board[x][y]).setEastSommet((Sommet) board[x2][y]);
							x2 = 10;
						}
					}
					for (int y2 = y + 1; y2 < size; y2++) {
						if (board[x][y2].getClass().equals(Sommet.class)) {
							((Sommet) board[x][y]).setSouthSommet((Sommet) board[x][y2]);
							y2 = 10;
						}
					}
				}
			}
		}
	}

	/**********************************************************************************************************
	 * methode pour ajouter un Pont entre deux Sommets
	 **********************************************************************************************************/
	public void addPont(Sommet n1, Sommet n2) {
		n1.dec();
		n2.dec();

		int x = 0;
		int y = 0;
		while (board[x][y] != n1) {
			y = 0;
			while (board[x][y] != n1 && y < size - 1) {
				y++;
			}
			if (board[x][y] != n1)
				x++;
		}

		if (n1.getNorthSommet() == n2) {
			n1.setNorthPont(n1.getNorthPont() + 1);
			n2.setSouthPont(n2.getSouthPont() + 1);
			for (int y2 = y - 1; y2 >= 0; y2--) {
				if (board[x][y2] == n2) {
					break;
				} else {
					((Pont) board[x][y2]).setPontType(((Pont) board[x][y2]).getPontType() + 1);
					((Pont) board[x][y2]).setPontDirection(2);
				}
			}
		} else if (n1.getEastSommet() == n2) {
			n1.setEastPont(n1.getEastPont() + 1);
			n2.setWestPont(n2.getWestPont() + 1);
			for (int x2 = x + 1; x2 < size; x2++) {
				if (board[x2][y] == n2) {
					break;
				} else {
					((Pont) board[x2][y]).setPontType(((Pont) board[x2][y]).getPontType() + 1);
					((Pont) board[x2][y]).setPontDirection(1);
				}
			}
		} else if (n1.getSouthSommet() == n2) {
			n1.setSouthPont(n1.getSouthPont() + 1);
			n2.setNorthPont(n2.getNorthPont() + 1);
			for (int y2 = y + 1; y2 < size; y2++) {
				if (board[x][y2] == n2) {
					break;
				} else {
					((Pont) board[x][y2]).setPontType(((Pont) board[x][y2]).getPontType() + 1);
					((Pont) board[x][y2]).setPontDirection(2);
				}
			}
		} else if (n1.getWestSommet() == n2) {
			n1.setWestPont(n1.getWestPont() + 1);
			n2.setEastPont(n2.getEastPont() + 1);
			for (int x2 = x - 1; x2 >= 0; x2--) {
				if (board[x2][y] == n2) {
					break;
				} else {
					((Pont) board[x2][y]).setPontType(((Pont) board[x2][y]).getPontType() + 1);
					((Pont) board[x2][y]).setPontDirection(1);
				}
			}
		} else
			System.out.println("Error: Grille.addPont() method: n2 is not a valid parameter for n1");
	}

	/***********************************************************************************************************
	 * Methode pour supprimer un pont entre deux Sommets
	 ***********************************************************************************************************/
	public void removePont(Sommet n1, Sommet n2) {

		n1.inc();
		n2.inc();

		int x = 0;
		int y = 0;
		while (board[x][y] != n1) {
			y = 0;
			while (board[x][y] != n1 && y < size - 1) {
				y++;
			}
			if (board[x][y] != n1)
				x++;
		}

		if (n1.getNorthSommet() == n2) {
			n1.setNorthPont(n1.getNorthPont() - 1);
			n2.setSouthPont(n2.getSouthPont() - 1);
			for (int y2 = y - 1; y2 >= 0; y2--) {
				if (board[x][y2] == n2) {
					break;
				} else {
					((Pont) board[x][y2]).setPontType(((Pont) board[x][y2]).getPontType() - 1);
					if (((Pont) board[x][y2]).getPontType() == 0)
						((Pont) board[x][y2]).setPontDirection(0);
				}
			}
		} else if (n1.getEastSommet() == n2) {
			n1.setEastPont(n1.getEastPont() - 1);
			n2.setWestPont(n2.getWestPont() - 1);
			for (int x2 = x + 1; x2 < size; x2++) {
				if (board[x2][y] == n2) {
					break;
				} else {
					((Pont) board[x2][y]).setPontType(((Pont) board[x2][y]).getPontType() - 1);
					if (((Pont) board[x2][y]).getPontType() == 0)
						((Pont) board[x2][y]).setPontDirection(0);
				}
			}
		} else if (n1.getSouthSommet() == n2) {
			n1.setSouthPont(n1.getSouthPont() - 1);
			n2.setNorthPont(n2.getNorthPont() - 1);
			for (int y2 = y + 1; y2 < size; y2++) {
				if (board[x][y2] == n2) {
					break;
				} else {
					((Pont) board[x][y2]).setPontType(((Pont) board[x][y2]).getPontType() - 1);
					if (((Pont) board[x][y2]).getPontType() == 0)
						((Pont) board[x][y2]).setPontDirection(0);
				}
			}
		} else if (n1.getWestSommet() == n2) {
			n1.setWestPont(n1.getWestPont() - 1);
			n2.setEastPont(n2.getEastPont() - 1);
			for (int x2 = x - 1; x2 >= 0; x2--) {
				if (board[x2][y] == n2) {
					break;
				} else {
					((Pont) board[x2][y]).setPontType(((Pont) board[x2][y]).getPontType() - 1);
					if (((Pont) board[x2][y]).getPontType() == 0)
						((Pont) board[x2][y]).setPontDirection(0);
				}
			}
		} else
			System.out.println("Error: Grille.addPont() method: n2 is not a valid parameter for n1");
	}

	/*************************************************************************
	 * Retourne true si le jeu est gagne
	 *************************************************************************/
	public boolean checkIfWon() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y].getClass().equals(Sommet.class)) {
					if (((Sommet) board[x][y]).getpoidCourant() > 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean checkIfPossibleMoves() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y].getClass().equals(Sommet.class)) {
					if (((Sommet) board[x][y]).getpoidCourant() > 0) {
						// if there is a valid move with this Sommet, return true
						if (isValidAdd((Sommet) board[x][y], ((Sommet) board[x][y]).getNorthSommet())) {
							return true;
						}
						if (isValidAdd((Sommet) board[x][y], ((Sommet) board[x][y]).getEastSommet())) {
							return true;
						}
						if (isValidAdd((Sommet) board[x][y], ((Sommet) board[x][y]).getSouthSommet())) {
							return true;
						}
						if (isValidAdd((Sommet) board[x][y], ((Sommet) board[x][y]).getWestSommet())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isValidAdd(Sommet n1, Sommet n2) {

		if (n1 == null || n2 == null) {
			return false;
		}
		if (n1.getpoidCourant() == 0 || n2.getpoidCourant() == 0) {
			return false;
		}

		if (n1.getNorthSommet() != n2 && n1.getEastSommet() != n2 && n1.getSouthSommet() != n2
				&& n1.getWestSommet() != n2) {
			return false;
		}

		int x = 0;
		int y = 0;
		while (board[x][y] != n1) {
			y = 0;
			while (board[x][y] != n1 && y < size - 1) {
				y++;
			}
			if (board[x][y] != n1)
				x++;
		}

		if (n1.getNorthSommet() == n2) {
			if (n1.getNorthPont() == 2)
				return false;
			else {

				for (int y2 = y - 1; y2 >= 0; y2--) {
					if (board[x][y2] == n2) {
						break;
					} else {
						if (((Pont) board[x][y2]).getPontDirection() == 1)
							return false;
					}
				}
			}
		}
		if (n1.getEastSommet() == n2) {
			if (n1.getEastPont() == 2)
				return false;
			else {

				for (int x2 = x + 1; x2 < size; x2++) {
					if (board[x2][y] == n2) {
						break;
					} else {
						if (((Pont) board[x2][y]).getPontDirection() == 2)
							return false;
					}
				}
			}
		}
		if (n1.getSouthSommet() == n2) {
			if (n1.getSouthPont() == 2)
				return false;
			else {
				
				for (int y2 = y + 1; y2 < size; y2++) {
					if (board[x][y2] == n2) {
						break;
					} else {
						if (((Pont) board[x][y2]).getPontDirection() == 1)
							return false;
					}
				}
			}
		}
		if (n1.getWestSommet() == n2) {
			if (n1.getWestPont() == 2)
				return false;
			else {

				for (int x2 = x - 1; x2 >= 0; x2--) {
					if (board[x2][y] == n2) {
						break;
					} else {
						if (((Pont) board[x2][y]).getPontDirection() == 2)
							return false;
					}
				}
			}
		}

		return true;
	}

	public Object[][] getBoard() {
		return board;
	}
}
