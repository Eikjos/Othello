package Othello.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Othello.Othello;

// classe principale qui permet de gérer le jeu
public class Game extends JPanel {

    // le joueur qui doit jouer
    private Player player;

    private JLabel scoreBlack;
    private JLabel scoreWhite;
    private Othello othello;
    // Le plateau de jeu
    private Board board;

    private Level level;

    public Game(Othello othello, Level level) {
        this.level = level;
        this.othello = othello;
        this.player = Player.Black;
        this.board = new Board(this);
        this.setLayout(new BorderLayout());
        this.add(showScore(), BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
    }

    public Player getPlayer() {
        return player;
    }

    public JPanel showScore() {
        var panel = new JPanel(new BorderLayout()); {
            var p = new JPanel(); {
                p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
                JButton back = new JButton("Retour");
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        othello.back();
                    }
                });
                p.add(back);
                var label = new JLabel("Joueur 1 (Noir)");
                p.add(label);
            }
            panel.add(p, BorderLayout.WEST);
            p = new JPanel(); {
                p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
                var label = new JLabel("Joueur 2 (Blanc)");
                label.setHorizontalTextPosition(JLabel.CENTER);
                p.add(label);
            }
            panel.add(p, BorderLayout.EAST);
            p = new JPanel(); {
                p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
                p.add(Box.createHorizontalGlue());
                scoreBlack = new JLabel("2");
                scoreWhite = new JLabel("2");
                var label = new JLabel(" : ");
                p.add(scoreBlack);
                p.add(label);
                p.add(scoreWhite);
                p.add(Box.createHorizontalGlue());
            }
            panel.add(p, BorderLayout.CENTER);
        }
        return panel;
    }

    public void play(int x, int y) {
        // retourner les pions qui sont encadré et mettre à jour le score
        board.play(x, y, player);
        // calculer les possibilités pour le prochain coup
        if (player == Player.Black) {
            player = Player.White;
        } else {
            player = Player.Black;
        }
        board.refreshPossibilities(player);
        refreshScore();
        var possibilities = board.getPossibilities();
        // au tour de l'IA
        if (possibilities.isEmpty()) {
            finish();
        } else {
            Cell cell = Minimax.solve(board.getCells(), Player.White, level.getDepth());
            board.play(cell.getCoordinate().x, cell.getCoordinate().y, player);
            // au tour de joueur
            player = Player.Black;
            board.refreshPossibilities(player);
            refreshScore();
            possibilities = board.getPossibilities();
            if (possibilities.isEmpty()) {
                finish();
            }
        }
    }

    public void finish() {
        var p = Integer.parseInt(scoreBlack.getText()) > Integer.parseInt(scoreWhite.getText()) ? Player.Black : Player.White;
        JOptionPane d = new JOptionPane();
        d.showMessageDialog(this, "Le joueur " + p + " a gagné la partie",
                "Partie terminé", JOptionPane.INFORMATION_MESSAGE);
    }

    public void refreshScore() {
        scoreWhite.setText(Integer.toString(board.getNumberTokenForPlayer(Player.White)));
        scoreBlack.setText(Integer.toString(board.getNumberTokenForPlayer(Player.Black)));
    }
}
