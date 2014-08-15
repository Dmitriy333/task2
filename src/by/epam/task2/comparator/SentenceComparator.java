package by.epam.task2.comparator;

import java.util.Comparator;

import by.epam.task2.substance.Component;
import by.epam.task2.substance.Composite;
import by.epam.task2.substance.CompositeElements;
import by.epam.task2.substance.Word;

public class SentenceComparator implements Comparator<Component> {
	private int countWordsInSentence(Component sentence) {
		int count = 0;
		if (sentence instanceof Composite) {
			if (((Composite) sentence).getAttachment().equals(CompositeElements.SENTENCE)) {
				for (Component word : ((Composite) sentence).getItems()) {
					if (word instanceof Word) {
						if(((Word)word).isPunctSign()!=true){
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	@Override
	public int compare(Component o1, Component o2) {
		if (countWordsInSentence(o1) < countWordsInSentence(o2)) {
			return -1;
		} else if (countWordsInSentence(o1) == countWordsInSentence(o2)) {
			return 0;
		} else {
			return 1;
		}
	}

}
