package by.epam.task2.substance;

import java.util.List;

public interface Component {
	void add(Component c);
	List<Component> getItems();
	String toString();
}
