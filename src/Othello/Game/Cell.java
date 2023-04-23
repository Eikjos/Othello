package Othello.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton {

    // Si nous pouvons poser une jeton sur cette case
    private boolean playable;

    // Le jeton que contient la case, null sinon
    private Player player;

    private Game game;

    public Cell(Game g) {
        this.game = g;
        this.playable = false;
        this.player = null;
        this.setBackground(Color.decode("#33753C"));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playable && player == null) {

                }
            }
        });
    }

    public void setPlayable(boolean isPlayable) {
        if (player != null) {
            playable = false;
        }
        this.playable = isPlayable;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        if (player != null) {
            int radius = getWidth() / 3;
            g.setColor(player == Player.Black ? Color.BLACK : Color.WHITE);
            g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        } else {
            if (playable && game.getPlayer() == Player.Black) {
                g.setColor(Color.decode("#93d29e"));
                g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
            }
        }
    }


}
