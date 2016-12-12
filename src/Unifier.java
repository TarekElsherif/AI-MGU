
public class Unifier {
	Element fol1;
	Element fol2;
	String idempotent;
	
	public Unifier(){
		
	}
	
	void Unify_id(String fol1, String fol2, String idempotent){
		Parser p = new Parser();
		Element x = p.parse(fol1);
		Element y = p.parse(fol2);
		if(x == y){
			System.out.println("Variables Match");
		} else {
			System.out.println("No Match");
			System.out.println("The lenght of the first element is " + x.getLength());
			System.out.println("The length of the second element is " + y.getLength());
		}
	}
	
	public static void main(String[] args){
		Unifier u = new Unifier();
		u.Unify_id("P(x,g(x),g(f(a)))", "P(x,g(x),g(f(a)))", "lel");
	}
}
