import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Data is the class contains two methods that read file and set random word
 */
public class Data
{
	private ArrayList<String> wordList;
	private String randomWord;

	/**
	 * call two method
	 */
	public Data() {
		setWordList();
		setRandomWord();
	}
	/**
	 * generate a random index within 0 to the size of list
	 * get the word based on the random index
	 */
	private void setRandomWord() {
		int index = (new Random()).nextInt(this.wordList.size());
		this.randomWord = this.wordList.get(index);
	}

	/**
	 *  Read data line by line from specified file
	 *  Then store all the data in the array list
	 */
	private void setWordList() {
		try {
			String filePath = "./WordleList.txt";
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			ArrayList<String> list = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
			this.wordList = list;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @return wordlist of WordleList text file
	 */
	public ArrayList<String> getWordList() {
		return wordList;
	}

	/**
	 *
	 * @return randomWord set by setRandomWord method
	 */
	public String getRandomWord() {
		return randomWord;
	}
}