package by.epam.task2.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import by.epam.task2.logic.Bundle;
import by.epam.task2.substance.Component;
import by.epam.task2.substance.Composite;
import by.epam.task2.substance.CompositeElements;
import by.epam.task2.substance.Word;

public class Parser {
	private Bundle bundle;
	private StringBuilder sb = new StringBuilder();
	private Logger logger = Logger.getLogger(Parser.class);

	public Parser(String fileName) {
		File f = new File(fileName);
		bundle = new Bundle();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e + " Ошибка при чтении файла " + fileName);
		}
	}

	public String getText() {
		return sb.toString();
	}

	public Component parseText(String text) {
		Composite textComposite = new Composite();
		textComposite.setAttachment(CompositeElements.TEXT);
		String paragraphPattern = bundle.getValue("paragraph_pattern");
		String[] paragr = (text.toString()).split(paragraphPattern);
		if (paragr.length > 0) {
			for (int i = 0; i < paragr.length; i++) {
				Composite paragraphComposite = (Composite) parseParagraph(paragr[i]);
				paragraphComposite.setAttachment(CompositeElements.PARAGRAPH);
				textComposite.add(paragraphComposite);
			}
		}
		return textComposite;
	}

	private Component parseParagraph(String text) {
		String sentencePattern = bundle.getValue("sentence_pattern");
		String[] sentences = text.split(sentencePattern);
		Composite sentenceComposite = new Composite();
		sentenceComposite.setAttachment(CompositeElements.SENTENCE);
		if (sentences.length > 0) {
			for (int i = 0; i < sentences.length; i++) {
				Composite wordComposite = (Composite) parseIntoWords(sentences[i]);
				wordComposite.setAttachment(CompositeElements.SENTENCE);
				sentenceComposite.add(wordComposite);
			}
		}
		return sentenceComposite;
	}

	private Component parseIntoWords(String text) {
		Composite wordComposite = new Composite();
		String wordPattern = bundle.getValue("word_pattern");
		String puncPattern = bundle.getValue("punct_pattern");
		Pattern pWord = Pattern.compile(wordPattern);
		Pattern pPunct = Pattern.compile(puncPattern);
		Matcher mWord = pWord.matcher(text);
		while (mWord.find()) {
			Word word = new Word(mWord.group());
			Matcher mPunct = pPunct.matcher(mWord.group());
			if (mPunct.find()) {
				word.setPunctSign(true);
			} else {
				word.setPunctSign(false);
			}
			wordComposite.add(word);
		}
		return wordComposite;
	}

}
