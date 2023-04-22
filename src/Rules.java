import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JPanel {

    private Othello othello;
    public Rules(Othello g) {
        this.othello = g;
        setLayout(new BorderLayout());
        JButton back = new JButton("Retour");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                othello.back();
            }
        });
        JLabel label = new JLabel("Règle du jeu");
        label.setFont(new Font("Calibri", Font.BOLD, 40));
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        {
            panel.add(back);
            JPanel q = new JPanel(new GridBagLayout());
            q.add(label, new GridBagConstraints());
            panel.add(q);
        }
        add(panel, BorderLayout.NORTH);
        add(DisplayRules(), BorderLayout.CENTER);
    }

    private JScrollPane DisplayRules() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        {
            JPanel q = new JPanel(new BorderLayout());
            {
                JLabel label = new JLabel("But du jeu");
                label.setFont(new Font("Calibri", Font.BOLD, 20));
                label.setForeground(new Color(255, 60, 0));
                label.setVerticalTextPosition(SwingConstants.CENTER);
                q.add(label, BorderLayout.NORTH);
                JTextArea text = new JTextArea();
                text.setEditable(false);
                text.setLineWrap(true);
                text.setText("Avoir plus de pions de sa couleur que l’adversaire à la fin de la partie. Celle-ci s’achève lorsque aucun des deux joueurs ne peut plus jouer de coup légal. Cela intervient généralement lorsque les 64 cases sont occupées.");
                text.setFont(new Font("Calibri", Font.PLAIN, 12));
                q.add(text, BorderLayout.CENTER);
            }
            q.setMinimumSize(new Dimension(600, 100));
            q.setPreferredSize(new Dimension(600, 100));
            q.setMaximumSize(new Dimension(600, 100));
            panel.add(q);
            q = new JPanel(new BorderLayout());
            {
                JLabel label = new JLabel("Position de départ");
                label.setFont(new Font("Calibri", Font.BOLD, 20));
                label.setForeground(new Color(255, 60, 0));
                JTextArea text = new JTextArea();
                text.setEditable(false);
                text.setLineWrap(true);
                text.setFont(new Font("Calibri", Font.PLAIN, 12));
                text.setText("Au début de la partie, deux pions noirs sont placés en e4 et d5 et deux pions blancs sont placés en d4 et e5 (voir figure 1).\n" +
                        "\n" +
                        "Noir commence toujours et les deux adversaires jouent ensuite à tour de rôle.");
                ImagePanel image = new ImagePanel("image/depart.jpg");
                image.setPreferredSize(new Dimension(300, 100));
                q.add(label, BorderLayout.NORTH);
                q.add(text, BorderLayout.CENTER);
                q.add(image, BorderLayout.WEST);
            }
            q.setMinimumSize(new Dimension(600, 300));
            q.setPreferredSize(new Dimension(600, 300));
            q.setMaximumSize(new Dimension(600, 300));
            panel.add(q);
            q = new JPanel(new BorderLayout());
            {
                JLabel label = new JLabel("La pose d'un pion");
                label.setFont(new Font("Calibri", Font.BOLD, 20));
                label.setForeground(new Color(255, 60, 0));
                JTextArea text = new JTextArea();
                text.setEditable(false);
                text.setLineWrap(true);
                text.setFont(new Font("Calibri", Font.PLAIN, 12));
                text.setText("A son tour de jeu, le joueur doit poser un pion de sa couleur sur une case vide" +
                        " de l’othellier, adjacente à un pion adverse. Il doit également, en posant son pion," +
                        " encadrer un ou plusieurs pions adverses entre le pion qu’il pose et un pion" +
                        " à sa couleur, déjà placé sur l’othellier. Cette prise en sandwich peut se faire" +
                        " aussi bien horizontalement ou verticalement, qu’en diagnonale. Le joueur retourne le" +
                        " ou les pions qu’il vient d’encadrer, qui devient ainsi de sa couleur. Les pions ne" +
                        " sont ni retirés de l’othellier, ni déplacés d’une case à l’autre.");
                ImagePanel image = new ImagePanel("image/pose.jpg");
                image.setPreferredSize(new Dimension(300, 100));
                q.add(label, BorderLayout.NORTH);
                q.add(text, BorderLayout.CENTER);
                q.add(image, BorderLayout.WEST);
            }
            q.setMinimumSize(new Dimension(600, 350));
            q.setPreferredSize(new Dimension(600, 350));
            q.setMaximumSize(new Dimension(600, 350));
            panel.add(q);
            q = new JPanel(new BorderLayout());
            {
                JLabel label = new JLabel("Fin de partie");
                label.setFont(new Font("Calibri", Font.BOLD, 20));
                label.setForeground(new Color(255, 60, 0));
                JTextArea text = new JTextArea();
                text.setEditable(false);
                text.setLineWrap(true);
                text.setFont(new Font("Calibri", Font.PLAIN, 12));
                text.setText("La partie est terminée lorsque aucun des deux joueurs ne peut plus jouer." +
                        " Cela arrive généralement lorsque les 64 cases sont occupées." +
                        " Mais il se peut qu’il reste des cases vides où personne ne peut plus jouer :" +
                        " par exemple lorsque tous les pions deviennent d’une même couleur après un retournement." +
                        " Ou bien comme sur cette position.\n" +
                        "\n" +
                        "Aucun des deux joueurs ne peut jouer en b1 puisque aucun retournement n’est possible." +
                        " On compte les pions pour déterminer le score. Les cases vides sont données au vainqueur. " +
                        "Ici, Blanc a 29 pions, Noir 34 et il y a une case vide. Donc Noir gagne par 35 à 29.");
                ImagePanel image = new ImagePanel("image/fin.jpg");
                image.setPreferredSize(new Dimension(300, 100));
                q.add(label, BorderLayout.NORTH);
                q.add(text, BorderLayout.CENTER);
                q.add(image, BorderLayout.WEST);
            }
            q.setMinimumSize(new Dimension(600, 350));
            q.setPreferredSize(new Dimension(600, 350));
            q.setMaximumSize(new Dimension(600, 350));
            panel.add(q);
            panel.setMaximumSize(new Dimension(650, 400));
        }
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMinimumSize(new Dimension(650, 600));
        scrollPane.setPreferredSize(new Dimension(650, 600));
        scrollPane.getViewport().setMinimumSize(new Dimension(650, 600));
        scrollPane.getViewport().setPreferredSize(new Dimension(650, 600));
        return scrollPane;
    }
}
