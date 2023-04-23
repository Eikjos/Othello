package Othello.Game;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private final int size = 8;
    // les cases du tableau représenté par une matrice
    private Cell[][] cells;

    public Board(Game g) {
        this.setLayout(new GridLayout(8, 8));
        cells = new Cell[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                cells[i][j] = new Cell(g);
                this.add(cells[i][j]);
            }
        }
        // initialisation des cases pour le début de la partie
        cells[size / 2 - 1][size / 2 - 1].setPlayer(Player.White);
        cells[size / 2 - 1][size / 2].setPlayer(Player.Black);
        cells[size / 2][size / 2].setPlayer(Player.White);
        cells[size / 2][size / 2 - 1].setPlayer(Player.Black);
        cells[size / 2 + 1][size / 2].setPlayable(true);
        cells[size / 2  - 2][size / 2 - 1].setPlayable(true);
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public JPanel display() {
        JPanel board = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                board.add(cells[i][j]);
            }
        }
        return board;
    }
}
