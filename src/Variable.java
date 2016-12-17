public class Variable extends Element {
	
	private Element value;
	
	public Variable(String n) {
		super(n);
	}
	
	public Variable(String n, Element v){
		super(n);
		if(v instanceof Function){
			value = new Function(v.getName(), v.getParams());
		} else if(v instanceof Constant){
			value = new Constant(v.getName());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(Element v){
		if(v instanceof Constant){
			this.value = new Constant(v.getName());
		} else if(v instanceof Variable){
			this.value = new Variable(v.getName(), v.getValue());
		} else if(v instanceof Function){
			this.value = new Function(v.getName(), v.getParams());
		}
	}
	
	public Element getValue(){
		return value;
	}
	
	public String toString() {
		return name;
	}
	
	public int getLength(){
		return 1;
	}

}