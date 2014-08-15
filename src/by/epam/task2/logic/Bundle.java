package by.epam.task2.logic;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class Bundle {
	private Logger logger = Logger.getLogger(Bundle.class);
	private ResourceBundle bundle;

	public Bundle(){
		Locale locale = new Locale("by", "Be");
		try {
			bundle = ResourceBundle.getBundle("property.prop", locale);
		} catch (MissingResourceException e) {
			logger.error(e + " —читывание файла регул€рных выражений");
			e.getStackTrace();
		}
	}
	public String getValue(String key) {
		return bundle.getString(key);
	}
}
