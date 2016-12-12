
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
		} else if(/*compare lengths*/true){
			
		} 
	}
}
