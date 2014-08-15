package by.epam.task2.comparator;

import java.util.Comparator;

import by.epam.task2.substance.Word;

public class WordComparator implements Comparator<Word> {

	@Override
	public int compare(Word o1, Word o2) {
		if (o1.getData().length() < o2.getData().length()) {
			return -1;
		} else if (o1.getData().length() == o2.getData().length()) {
			return 0;
		} else {
			return 1;
		}
	}

}
