public class Variable extends Element {
	
	private String name;
	private String value;
	
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
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public String toString() {
		return name;
	}
	
	public int getLength(){
		return 1;
	}

}