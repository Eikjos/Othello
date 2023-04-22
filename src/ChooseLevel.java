import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseLevel extends JPanel {

    private Othello othello;

    public ChooseLevel(Othello g) {
        this.othello = g;
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        JPanel p = new JPanel(); {
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
            JButton back = new JButton("Retour");
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    othello.back();
                }
            });
            p.add(back);
        }
        this.add(p, BorderLayout.NORTH);
        p = new JPanel(); {
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

            Dimension d = new Dimension(200, 200);

            JButton easy = new JButton("Facile");
            JButton medium = new JButton("Moyen");
            JButton difficult = new JButton("Difficile");

            easy.setPreferredSize(d);
            medium.setPreferredSize(d);
            difficult.setPreferredSize(d);

            p.add(Box.createHorizontalGlue());
            p.add(easy);
            p.add(medium);
            p.add(difficult);
            p.add(Box.createHorizontalGlue());
        };
        this.add(p, BorderLayout.CENTER);
    }


}
