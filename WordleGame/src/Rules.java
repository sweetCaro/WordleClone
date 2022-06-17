import java.awt.*;
import javax.swing.*;

public class Rules extends JFrame {
    JPanel rulesPage;

    public Rules() {
        TextField textField = new TextField();
        rulesPage=new JPanel();
        add(textField);

        this.setLayout(new BorderLayout());//BorderLayout can provide 5 areas to hold components
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,200);
        this.setVisible(true);
        rulesPage.setVisible(true);

        JLabel rules = new JLabel("<html><body><p align=\"center\">GAME RULES:<br/>6 chances to guess a 5-letter word!<br/>The letter is in the word but wrong position: Turn yellow<br/>Same Letter on right position: Turn green<br/>Not contain: Turn grey</p></body></html>");
        rules.setHorizontalAlignment(JLabel.CENTER);
        this.add(rules,BorderLayout.CENTER);

        this.setTitle("Rules");
        this.setFocusable(true);
        this.revalidate();
        this.repaint();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}