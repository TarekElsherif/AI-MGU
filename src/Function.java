import java.util.ArrayList;

public class Function extends Element {
	
	ArrayList<Element> params;

	public Function(String n, ArrayList<Element> arr) {
		super(n);
		params = arr;
	}

	public Function(String n) {
		super(n);
		params = new ArrayList<Element>();
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
	
	public int getLength(){
		return params.size();
	}

	
	public String toString() {
		String s = name + "(";
		for (int i = 0; i < params.size(); i++) {
			if (i == params.size() - 1)
				s = s + params.get(i).toString();
			else 
				s = s + params.get(i).toString() + ",";
		}
		s = s + ")";
		return s;
	}

}

