package hashiwokakero;

import java.util.Scanner;

public class Jeu {


	
	public static void main(String [] args){
		Scanner sc= new Scanner(System.in);
//		System.out.println("bienvenue, veuillez saisir la taille de la matrice");
//		int c=sc.nextInt();
		Grille jeu = new Grille(7);
//		jeu.addSommet();
		jeu.board[0][0]=new Sommet(4);
		jeu.board[0][2]=new Sommet(3);
		jeu.board[0][4]=new Sommet(3);
		jeu.board[0][6]=new Sommet(3);
		jeu.board[1][1]=new Sommet(2);
		jeu.board[1][5]=new Sommet(4);
		jeu.board[2][0]=new Sommet(3);
		jeu.board[2][3]=new Sommet(3);
		jeu.board[2][6]=new Sommet(3);
		jeu.board[4][0]=new Sommet(2);
		jeu.board[4][3]=new Sommet(8);
		jeu.board[4][5]=new Sommet(4);
		jeu.board[5][4]=new Sommet(1);
		jeu.board[5][6]=new Sommet(3);
		jeu.board[6][1]=new Sommet(1);
		jeu.board[6][3]=new Sommet(4);
		jeu.board[6][5]=new Sommet(1);
		jeu.findPossibleConnections();
		jeu.showBoard();
		System.out.println("Try to solve");
		Solver s= new Solver(jeu);
		if (s.connexe(jeu.board)){
					jeu.showBoard();
		}

		
	}
	


}
