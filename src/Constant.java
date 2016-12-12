public class Constant extends Element {
	
	private String name;
	
	public Constant(String n) {
		name = n;
	}
	
	public Constant() {
		
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
	
	public int getLength(){
		return 1;
	}

}