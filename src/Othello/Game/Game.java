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

    private Othello othello;
    // Le plateau de jeu
    private Board board;

    public Game(Othello othello) {
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
                var label = new JLabel("2 : 2");
                p.add(label);
                p.add(Box.createHorizontalGlue());
            }
            panel.add(p, BorderLayout.CENTER);
        }
        return panel;
    }
}
