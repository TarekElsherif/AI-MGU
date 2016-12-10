import java.util.ArrayList;

public class Function extends Element {

	private String name;
	ArrayList<Element> params;

	public Function(String n, ArrayList<Element> arr) {
		name = n;
		params = arr;
	}

	public Function(String n) {
		name = n;
		params = new ArrayList<Element>();
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

	
	public String toString() {
		String s = name + "(";
		for (int i = 0; i < params.size(); i++) {
			s = s + params.get(i).toString() + ",";
		}
		s = s + ")";
		return s;
	}

}

