import java.awt.*;
import javax.swing.*;

/**
 * Hint is the class extends JFrame
 * contains the method hint to open the hint window
 *
 */
public class Hint extends JFrame {
    JPanel hintPage;
    JLabel hint;

    /**
     *
     * @param x is the specified number for the corresponding hint text
     * @param word is the random word set before
     */
    public Hint(int x,String word){
        //creat a new hint page panel
        hintPage=new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,100);
        this.setVisible(true);
        hintPage.setVisible(true);

        if(x==1) {
            //create the hint1 label which shows that "Word should be 5 letters!"
            hint = new JLabel();
            hint.setHorizontalAlignment(JLabel.CENTER);
            hint.setText("Word should be 5 letters!");
        }
        if(x==2) {
            //create the hint2 label which shows that "Word doesn't exist, plz input again!"
            hint = new JLabel();
            hint.setHorizontalAlignment(JLabel.CENTER);
            hint.setText("Word doesn't exist, plz input again!");
        }
        if(x==3) {
            //create the hint3 label which shows that "Correct! One more Game!"
            hint = new JLabel();
            hint.setHorizontalAlignment(JLabel.CENTER);
            hint.setText("Correct! One more Game!");
        }
        if(x==4) {
            //create the hint4 label which shows that "You lost! Correct word is: " and correct answer
            hint = new JLabel();
            hint.setHorizontalAlignment(JLabel.CENTER);
            hint.setText("You lost! Correct word is: "+word);
        }

        this.add(hint,BorderLayout.CENTER);
        this.setTitle("HINT");
        this.setFocusable(true);
        this.revalidate();
        this.repaint();
        this.setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//when close hint page, the system will not exit

    }

}

