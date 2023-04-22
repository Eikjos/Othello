package Game;

import javax.swing.*;

// classe principale qui permet de gérer le jeu
public class Game extends JPanel {

    // le joueur qui doit jouer
    private Player player;


    // Le plateau de jeu
    private Board board;

    public Game() {
        this.player = Player.Black;
        this.board = new Board(this);
    }
}
