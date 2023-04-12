import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class Game extends JFrame {

    private Stack<Container> stack;
    public Game() {
        stack = new Stack<Container>();
        Menu menu = new Menu(this);
        this.add(menu);
        this.setTitle("Othello");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(650, 700));
        this.pack();
        this.setVisible(true);
    }

    public void back() {
        this.setContentPane(stack.pop());
        this.invalidate();
        this.validate();
    }

    public void navigate(Container container) {
        stack.push(this.getContentPane());
        this.setContentPane(container);
        this.invalidate();
        this.validate();
    }
    public static void main(String[] args) {
        new Game();
    }
}