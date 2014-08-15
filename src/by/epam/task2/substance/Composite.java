package by.epam.task2.substance;

import java.util.ArrayList;

public class Composite implements Component {
	private ArrayList<Component> compositeElements = new ArrayList<Component>();
	private CompositeElements attachment;

	@Override
	public void add(Component c) {
		compositeElements.add(c);
	}

	public Component getChild(int i) {
		return this.compositeElements.get(i);
	}

	@Override
	public ArrayList<Component> getItems() {
		return this.compositeElements;

	}
	public void setItems(ArrayList<Component> compositeElements){
		this.compositeElements = compositeElements;
	}
	
	public CompositeElements getAttachment() {
		return attachment;
	}

	public void setAttachment(CompositeElements attachment) {
		this.attachment = attachment;
	}
	@Override
	public String toString() {
		boolean firstParagraph = true;
		StringBuilder sb = new StringBuilder();
		for (Component componentElement : this.compositeElements) {
			if (componentElement instanceof Composite) {
				Composite composite = (Composite) componentElement;
				if (composite.getAttachment().equals(CompositeElements.PARAGRAPH)) {
					if (!firstParagraph) {
						sb.append("\n");
					}
					firstParagraph = false;
				}
			}
			sb.append(componentElement.toString());
		}
		return sb.toString();
	}
}
