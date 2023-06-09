package Othello.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {

    private final int size = 8;
    // les cases du tableau représenté par une matrice
    private Cell[][] cells;

    private Game game;

    public Board(Cell[][] cells) {
        this.cells = cells;
    }

    public Board(Game g)
    {
        this.game = g;
        this.setLayout(new GridLayout(8, 8));
        cells = new Cell[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                cells[i][j] = new Cell(g, i, j);
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
        cells[size / 2 - 1][size / 2 - 2].setPlayable(true);
        cells[size / 2][size / 2 + 1].setPlayable(true);
    }

    public Cell[][] getCells() {
        var result = new Cell[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                var cell = this.cells[i][j];
                var newCell = new Cell(cell.getGame(), i, j);
                newCell.setPlayer(cell.getPlayer());
                newCell.setPlayable(cell.isPlayable());
                result[i][j] = newCell;
            }
        }
        return result;
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

    public int getNumberTokenForPlayer(Player player) {
        int count = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (cells[i][j].getPlayer() == player) {
                    ++count;
                }
            }
        }
        return count;
    }

    public List<Cell> getPossibilities() {
        List<Cell> result = new ArrayList<Cell>();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (cells[i][j].isPlayable()) {
                    result.add(cells[i][j]);
                }
            }
        }
        return result;
    }

    public void play(int x, int y, Player player) {
        int colBottom = -1;
        int colTop = -1;
        int rowRight = -1;
        int rowLeft = -1;
        int diagTopLeft = -1;
        int diagTopRight = -1;
        int diagBottomLeft = -1;
        int diagBottomRight = -1;
        cells[x][y].setPlayer(player);
        // col bottom
        for (int i = x + 1; i < size; ++i) {
            if (cells[i][y].getPlayer() == player) {
                colBottom = i;
            }
            if (cells[i][y].getPlayer() == null) {
                break;
            }
        }
        // col top
        for (int i = x - 1; i >= 0; --i) {
            if (cells[i][y].getPlayer() == player) {
                colTop = i;
            }
            if (cells[i][y].getPlayer() == null) {
                break;
            }
        }
        // row right
        for (int i = y + 1; i < size; ++i) {
            if (cells[x][i].getPlayer() == player) {
                rowRight = i;
            }
            if (cells[x][i].getPlayer() == null) {
                break;
            }
        }
        // row left
        for (int i = y - 1; i >= 0; --i) {
            if (cells[x][i].getPlayer() == player) {
                rowLeft = i;
            }
            if (cells[x][i].getPlayer() == null) {
                break;
            }
        }
        // diag top-left
        for (int i = 1; i <= Math.min(x, y); ++i) {
            if (cells[x - i][y - i].getPlayer() == player) {
                diagTopLeft = i;
            }
            if (cells[x - i][y - i].getPlayer() == null) {
                break;
            }
        }
        // diag top-right
        for (int i = 1; i <= Math.min(x, size - 1 - y); ++i) {
            if (cells[x - i][y + i].getPlayer() == player) {
                diagTopRight = i;
            }
            if (cells[x - i][y + i].getPlayer() == null) {
                break;
            }
        }
        // diag bottom-left
        for (int i = 1; i <= Math.min(size - 1 - x, y); ++i) {
            if (cells[x + i][y - i].getPlayer() == player) {
                diagBottomLeft = i;
            }
            if (cells[x + i][y - i].getPlayer() == null) {
                break;
            }
        }
        // diag bottom-right
        for (int i = 1; i <= Math.min(size - 1 - x, size - 1 - y); ++i) {
            if (cells[x + i][y + i].getPlayer() == player) {
                diagBottomRight = i;
            }
            if (cells[x + i][y + i].getPlayer() == null) {
                break;
            }
        }

        // reverse
        // col bottom
        if (colBottom != -1) {
            for (int i = x + 1; i < colBottom; ++i) {
                cells[i][y].setPlayer(player);
            }
        }
        // col top
        if (colTop != -1) {
            for (int i = x - 1; i > colTop; --i) {
                cells[i][y].setPlayer(player);
            }
        }
        // row right
        if (rowRight != -1) {
            for (int i = y + 1; i < rowRight; ++i) {
                cells[x][i].setPlayer(player);
            }
        }
        // row left
        if (rowLeft != -1) {
            for (int i = y - 1; i > rowLeft; --i) {
                cells[x][i].setPlayer(player);
            }
        }
        // diag top-left
        if (diagTopLeft != -1) {
            for (int i = 1; i <= diagTopLeft; ++i) {
                cells[x - i][y - i].setPlayer(player);
            }
        }
        // diag top right
        if (diagTopRight != -1) {
            for (int i = 1; i <= diagTopRight; ++i) {
                cells[x - i][y + i].setPlayer(player);
            }
        }
        // diag bottom left
        if (diagBottomLeft != -1) {
            for (int i = 1; i <= diagBottomLeft; ++i) {
                cells[x + i][y - i].setPlayer(player);
            }
        }
        // diag bottom right
        if (diagBottomRight != -1) {
            for (int i = 1; i <= diagBottomRight; ++i) {
                cells[x + i][y + i].setPlayer(player);
            }
        }
    }

    public void refreshPossibilities(Player player) {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                cells[i][j].setPlayable(false);
            }
        }
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (cells[i][j].getPlayer() == player) {
                    for (var points : checkIsPlayable(i, j, player)) {
                        cells[points.x][points.y ].setPlayable(true);
                    }
                }
            }
        }
    }

    public  ArrayList<Point> checkIsPlayable(int x, int y, Player player) {
        player = player == Player.White ? Player.Black : Player.White;
        ArrayList<Point> coord = new ArrayList<Point>();
        // col bottom
        int i = x + 1;
        while (i < size && cells[i][y].getPlayer() == player) {
            ++i;
        }
        if (i != x + 1 && i < size && cells[i][y].getPlayer() == null) {
            coord.add(new Point(i, y));
        }
        // col top
        i = x - 1;
        while (i >= 0 && cells[i][y].getPlayer() == player) {
            --i;
        }
        if (i != x - 1 && i >= 0 && cells[i][y].getPlayer() == null) {
            coord.add(new Point(i, y));
        }
        // row right
        i = y + 1;
        while (i < size && cells[x][i].getPlayer() == player) {
            ++i;
        }
        if (i < size && i != y + 1 && cells[x][i].getPlayer() == null ) {
            coord.add(new Point(x, i));
        }
        // row left
        i = y - 1;
        while (i >= 0 && cells[x][i].getPlayer() == player) {
            --i;
        }
        if (i >= 0 && i != y - 1 && cells[x][i].getPlayer() == null) {
            coord.add(new Point(x, i));
        }
        // diag top-left
        i = 1;
        while (i <= Math.min(x, y) && x - i >= 0 && y - i >= 0 && cells[x - i][y - i].getPlayer() == player) {
            ++i;
        }
        if (x - i >= 0 && y - i >= 0 && i != 1 && cells[x - i][y - i].getPlayer() == null) {
            coord.add(new Point(x - i, y - i));
        }
        // diag top-right
        i = 1;
        while (i <= Math.min(x, size - 1 - y) && x - i >= 0 && y + i < size && cells[x - i][y + i].getPlayer() == player) {
            ++i;
        }
        if (x - i >= 0 && y + i < size && i != 1 && cells[x - i][y + i].getPlayer() == null) {
            coord.add(new Point(x - i, y + i));
        }
        // diag bottom-left
        i = 1;
        while (i <= Math.min(size - 1 - x, y) && x + i < size && y - i >= 0 && cells[x + i][y - i].getPlayer() == player) {
            ++i;
        }
        if (x + i < size && y - i >= 0 && i != 1 && cells[x + i][y - i].getPlayer() == null) {
            coord.add(new Point(x + i, y - i));
        }
        // diag bottom-right
        i = 1;
        while (i <= Math.min(size - 1- x, size - 1 - y) && x + i < size && y + i < size && cells[x + i][y + i].getPlayer() == player) {
            ++i;
        }
        if (i != 1 && x + i < size && y + i < size && cells[x + i][y + i].getPlayer() == null) {
            coord.add(new Point(x + i, y + i));
        }

        return coord;
    }

    public static Cell[][] getNewBoardAfterMove(Cell[][] node, Cell cell, Player player) {
        var b = new Board(node);
        b.play(cell.getCoordinate().x, cell.getCoordinate().y, player);
        var c = b.getCells()[cell.getCoordinate().x][cell.getCoordinate().y];
        c.setPlayer(player);
        c.setPlayable(false);
        return b.getCells();
    }

    public static List<Cell> getPossibilities(Cell[][] node, Player player) {
        var b = new Board(node);
        return b.getPossibilities();
    }
}
