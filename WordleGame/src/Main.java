import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

/**
 * @ProjectName:WordleGame
 * @Time:2022/6/6
 * @author Xiaoyi Liu
 * @version 1.0
 */
public class Main extends JFrame{

    JPanel startPage;
    JButton startButton;
    JButton rulesButton;
    /**
     * Launch the application.
     * @param args no input
     */
    public static void main(String[] args) {

        Main frame = new Main();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * Create the frame.
     * set the start page with two buttons which can listen to the click action
     */
    public Main() {
        setTitle("Wordle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,700, 300);
        startPage = new JPanel();
        startPage.setBackground(Color.WHITE);
        setContentPane(startPage);
        startPage.setLayout(null);
        startPage.setVisible(true);

        //when click start button will open the game page
        startButton = new JButton("Start");
        startButton.addActionListener(e -> new Frame());
        startButton.setBounds(150, 80, 150, 100);
        startPage.add(startButton);

        //when click rules button will open the rules page
        rulesButton = new JButton("Rules");
        rulesButton.addActionListener(e -> new Rules());
        rulesButton.setBounds(400, 80, 150, 100);
        startPage.add(rulesButton);

    }
}