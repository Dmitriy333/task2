package by.epam.task2.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import by.epam.task2.comparator.SentenceComparator;
import by.epam.task2.substance.Component;
import by.epam.task2.substance.Composite;
import by.epam.task2.substance.CompositeElements;
import by.epam.task2.substance.Word;

public class Task {
	private Composite onlySentences = new Composite();
	private ArrayList<Word> words = new ArrayList<Word>();

	private Component getOnlySentences(Component text) {
		for (Component component : text.getItems()) {
			if (component instanceof Composite) {
				if (((Composite) component).getAttachment().equals(
						CompositeElements.SENTENCE)) {
					Composite sent = new Composite();
					sent.setAttachment(((Composite) component).getAttachment());
					sent.setItems(((Composite) component).getItems());
					onlySentences.add(sent);
				} else {
					getOnlySentences(component);
				}
			}
		}
		return onlySentences;
	}
	
	private boolean isSameWord(List<Word> words, Word comparingWord) {
		for (Word word : words) {
			if (word.getData().equals(comparingWord.getData())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isQuestSentence(Component sentence) {
		if (sentence instanceof Composite) {
			if (((Composite) sentence).getAttachment().equals(
					CompositeElements.SENTENCE)) {
				for (Component word : ((Composite) sentence).getItems()) {
					Word wrd = (Word) word;
					if (wrd.isPunctSign()) {
						if (wrd.getData().equals("?")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public Component getIncreasedListOfSentences(Component text) {
		Composite onlySentences = (Composite) getOnlySentences(text);
		Comparator<Component> comparator = new SentenceComparator();
		Collections.sort(onlySentences.getItems(), comparator);
		return onlySentences;
	}

	public ArrayList<Word> getWordsFromQuestSent(Component text,
			int lengthOfWord) {
		for (Component sentence : onlySentences.getItems()) {
			if (((Composite) sentence).getAttachment().equals(CompositeElements.SENTENCE)) {
				if (isQuestSentence(sentence)) {
					Composite sent = (Composite) sentence;
					for (Component word : sent.getItems()) {
						Word wrd = (Word) word;
						if (wrd.getData().length() > 0
								&& (wrd.getData().length() == lengthOfWord)) {
							Word gWord = new Word(wrd.getData().toLowerCase());
							if (!isSameWord(words, gWord)) {
								words.add(gWord);
							}
						}
					}
				}
			}
			else{
				getWordsFromQuestSent(sentence, lengthOfWord);
			}
		}
		return words;
	}

}
