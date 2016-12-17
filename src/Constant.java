public class Constant extends Element {
	
	
	public Constant(String n) {
		super(n);
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