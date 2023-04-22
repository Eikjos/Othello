import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel
{
    private final String title =  "Menu";
    private final String buttonTitle1 = "Jouer";
    private final String buttonTitle2 = "Règle du jeu";

    private Othello othello;

    public Menu(Othello g) {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        this.othello = g;

        JButton button1 = new JButton(buttonTitle1);
        JButton button2 = new JButton(buttonTitle2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                othello.navigate(new ChooseLevel(othello));
            }
        });
        button1.setPreferredSize(new Dimension(100, 50));
        button2.setPreferredSize(new Dimension(100, 50));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                othello.navigate(new Rules(othello));
            }
        });
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(button1, new GridBagConstraints());
        panel.add(button2, new GridBagConstraints());
        JLabel label = new JLabel(title);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.BOLD, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(Color.WHITE);
        this.add(label, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }
}
