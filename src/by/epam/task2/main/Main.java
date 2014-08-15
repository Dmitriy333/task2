package by.epam.task2.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import by.epam.task2.logic.Task;
import by.epam.task2.parser.Parser;
import by.epam.task2.substance.Component;
import by.epam.task2.substance.Composite;
import by.epam.task2.substance.Word;

public class Main {
	static {
		new DOMConfigurator().doConfigure("log4j.xml",
				LogManager.getLoggerRepository());
	}
	static Logger logger = Logger.getLogger(Main.class);

	public static void printSortedIncreasedStrings(Composite sentences) {
		System.out
		.println("\n2. ¬ывести все предложени€ заданного текста в пор€дке возрастани€ количества слов.\n"
				+ "(ѕометка: если в строке стоит одиночный символ - знак пунктуации, то это тоже расцениваетс€ как предложение.\n"
				+ "«нак пунктуации словом не €вл€етс€):");
		StringBuilder sb = new StringBuilder();
		for (Component sentence : sentences.getItems()) {
			sb.append(sentence).append("\n");
		}
		System.out.println(sb);
	}

	public static void printWordsFromQuestSent(List<Word> words) {
		System.out.println("\n4. ¬о всех вопросительных предложени€х текста найти и напечатать без"+
				  "повторений слова заданной длины:");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< words.size(); i++){
			if(i==words.size()-1){
				sb.append(words.get(i).getData()).append(".");
			}
			else{
				sb.append(words.get(i).getData()).append(", ");
			}
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		Task tasker = new Task();
		String fileName = "resources/test.txt";
		Parser parser = new Parser(fileName);
		Composite textComposite = (Composite) parser
				.parseText(parser.getText());
		System.out.println(textComposite);
		
		Composite sortedSentences = (Composite) tasker
				.getIncreasedListOfSentences(textComposite);
		printSortedIncreasedStrings(sortedSentences);
		ArrayList<Word> words = tasker.getWordsFromQuestSent(textComposite, 4);
		printWordsFromQuestSent(words);

	}
}
