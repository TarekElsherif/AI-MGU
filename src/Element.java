import java.util.ArrayList;

public class Element {

	String name;

	public Element(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return 0;
	}

	public Element getValue() {
		return null;
	}

	public void setValue(Element value) {

	}
	
	public ArrayList<Element> getParams(){
		return null;
	}
}