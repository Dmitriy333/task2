package by.epam.task2.substance;

import java.util.List;

public class Word implements Component {
	private String data;
	private boolean punctSignBool=false; 
	public Word(String data) {
		this.data = data;
	}
	public void setData(String data){
		this.data = data;
	}
	public String getData(){
		return data;
	}

	@Override
	public void add(Component c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Component> getItems() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.punctSignBool){
			sb.append(this.data).append(" ");
		}
		else{
			sb.append(this.data).append(" ");
		}
		return sb.toString();
	}
	@Override 
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(null == obj){
			return false;
		}
		if(getClass() != obj.getClass()){
			return false;
		}
		Word word = (Word)obj;
		if(this.data.equals(word.getData())==false || (this.punctSignBool!=word.isPunctSign())){
			return false;
		}
			return true;
	}

	public boolean isPunctSign(){
		return punctSignBool;
	}

	public void setPunctSign(boolean isPunctSign) {
		this.punctSignBool = isPunctSign;
	}
	

}
