import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * class Frame implements interface ActionListener and KeyListener
 */
public class Frame extends JFrame implements ActionListener, KeyListener {

	JPanel gamePanel, buttonsPanel;
	JButton[][] buttons = new JButton[6][5];
	JButton clear;

	boolean moveOn, over;
	boolean[] yellow, green;
	int rows, characters;

	Data data;
	ArrayList<String> wordList;
	String word;

	/**
	 *method that create the page of game
	 */
	public Frame() {
		data = new Data();
		wordList = data.getWordList();//Get the wordlist arraylist
		word = data.getRandomWord();//Get a random word
		System.out.println(word);//The correct word in every time of games will show on the command window

		rows=0;
		characters =0;
		moveOn=true;//flag determines if game can continue
		yellow =new boolean[5];//flags of array determine which letter can turn yellow
		green =new boolean[5];//flags of array determine which letter can turn green

		this.setLayout(new BorderLayout());//BorderLayout can provide 5 areas to hold components
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setVisible(true);
		this.addKeyListener(this);
		this.setTitle("Wordle Game");
		this.setFocusable(true);
		this.revalidate();
		this.repaint();
		this.setLocationRelativeTo(null);

		gamePanel = new JPanel();//Create the 6-row 5-column word entering panel
		gamePanel.setLayout(new GridLayout(6,5));
		gamePanel.setVisible(true);

		clear = new JButton();//Create the clear button
		clear.setForeground(Color.darkGray);//set color
		clear.setText("CLEAR");//set text
		clear.setFont(new Font(null, Font.ITALIC, 25));//set font style
		clear.setBackground(Color.blue);//set button background color
		clear.addActionListener(this);//Tell the button that will listen its action
		buttonsPanel = new JPanel();//Create the buttons panel
		buttonsPanel.setVisible(true);
		buttonsPanel.add(clear);//at last add clear button to the buttonsPanel
		
		for(int rows=0; rows<buttons.length; rows++) {//add these word buttons to game panel
			for(int columns=0; columns<buttons[0].length; columns++) {
				buttons[rows][columns] = new JButton();
				buttons[rows][columns].setBackground(Color.cyan);
				buttons[rows][columns].setEnabled(false);
				buttons[rows][columns].setFont(new Font(null, Font.ITALIC, 20));
				gamePanel.add(buttons[rows][columns]);
			}
		}
		this.add(gamePanel);
		this.add(buttonsPanel,BorderLayout.SOUTH);//specify which area to place components define above
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//when close hint page, the system will not exit
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(!over) {
			int enter = event.getKeyCode();

			if(enter==8) {//when enter backspace key can clear the word button
				if(characters>0){
					characters--;
					moveOn =true;
					buttons[rows][characters].setText("");
				}
			}

			if(enter==10) {//when enter the enter key will judge if it is existed word and make some hints
				if(rows<6) {//the word rows are less than 6
					if(characters<5) {//when the letters are less than 5, letters of this row will be cleared
						int countOfCharacters=characters;
						for(int i=0;i<countOfCharacters;i++)
						{
							moveOn =true;
							characters--;
							buttons[rows][characters].setText("");
						}
						new Hint(1,word);//new window shows that the word should have 5 letters
					}
					if(characters==5) {
						StringBuilder wordInput= new StringBuilder();
						for(int i=0; i<5; i++)//build the letters of this row to a string
							wordInput.append(buttons[rows][i].getText());

						if(isExists(wordInput.toString().toLowerCase())){//judge this string of word if it is an existed word
							compare(wordInput.toString().toLowerCase());//compare with the given random word first
							winOrLose();// examine if is correct answer and over this game
							characters =0;
							rows++;
							moveOn =true;
						}
						else {//when the inputWord is not an existed word, letters of this row will be cleared
							for(int i=0;i<5;i++)
							{
								moveOn =true;
								characters--;
								buttons[rows][characters].setText("");
							}
							new Hint(2,word);//new window shows that the word is not existed
						}
					}
				}
			}

			if(enter<=90 && enter>=65) {//when enter English letters
				if(moveOn) {
					if(characters <5) {
						buttons[rows][characters].setText(String.valueOf((char)enter));
						characters++;
					}
					else//when reach 5 letters, cannot input letter anymore
						moveOn =false;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {}

	@Override
	public void keyReleased(KeyEvent event) {}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == clear) {
			this.dispose();
			new Frame();
		}
	}

	/** method that checks if the inputWord exists in the word list
	 * @param inputWord is the word that user enter on the word buttons
	 */
	public boolean isExists(String inputWord) {
		return wordList.contains(inputWord);
	}

	/** method that compare the inputWord and the correct word
	 * @param inputWord is the word that user enter on the word buttons
	 */
	public void compare(String inputWord) {
		for(int i=0; i<5; i++) {
			yellow[i]=false; //reset
			green[i]=false;
		}

		//make two another temp arraylists of characters conserve the given word and inputWord for comparison
		ArrayList<Character> tempInputWord=new ArrayList<>();
		for(int i=0; i<inputWord.length(); i++)
			tempInputWord.add(inputWord.charAt(i));
		ArrayList<Character> tempRandomWord= new ArrayList<>();
		for(int i=0; i<word.length(); i++)
			tempRandomWord.add(word.charAt(i));

		//if same letter and same position, button turns green
		for(int i=0; i<5; i++){
			if((buttons[rows][i].getText().toLowerCase()).equals(tempRandomWord.get(i).toString())) {
				buttons[rows][i].setBackground(Color.GREEN);
				green[i]=true;
			}
		}

		//if contains that letter but different position, button turns yellow
		for(int i=0; i<tempInputWord.size(); i++){
			for (Character character : tempRandomWord) {
				if (character == tempInputWord.get(i)) {
					if (!green[i]) {//the button has not changed before
						buttons[rows][i].setBackground(Color.YELLOW);
						yellow[i] = true;
						break;
					}
				}
			}
		}

		//if not contain this letter, button turns grey
		for(int i=0; i<5; i++) {
			if(!yellow[i]&&!green[i])//the button has not changed before
				buttons[rows][i].setBackground(Color.lightGray);
		}
	}

	/**
	 * method that judge if it is winning and over this game
	 */
	public void winOrLose() {
		int i = 0;
		for (boolean b : green) if (b) i++;
		//if flag array of green are all true, win this game
		if(i==5) {
			over =true;//game over
			new Hint(3,word);
		}
		if(rows==5&i!=5) {//if wrong inputWord at the row 6
			new Hint(4,word);
			over =true;//game over
		}
	}
}