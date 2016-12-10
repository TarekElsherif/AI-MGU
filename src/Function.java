import java.util.ArrayList;

public class Function extends Element {
	
	private String name;
	private ArrayList<Element> params;
	
	public Function (String n, ArrayList<Element> arr) {
		name = n;
		params = arr;
	}
	
	public Function() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Element> getParams() {
		return params;
	}

	public void setParams(ArrayList<Element> params) {
		this.params = params;
	}
}
