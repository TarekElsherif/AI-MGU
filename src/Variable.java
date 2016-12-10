public class Variable extends Element {
	
	private String name;
	
	public Variable(String n) {
		name = n;
	}
	
	public Variable() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

}