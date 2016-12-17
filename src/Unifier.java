import java.util.ArrayList;

public class Unifier {

	public Unifier() {
	}

	void Unify_id(String fol1, String fol2, String idem) {
		ArrayList<Element> MGU = new ArrayList<Element>();
		Parser p = new Parser();
		String refined = "";
		Element x = p.parse(fol1);
		System.out.println("First FOL statement: " + x.toString());
		Idempotence(x, idem);
		System.out.println("First FOL after applying " + idem + " idempotence: " + x.toString());
		Element y = p.parse(fol2);
		System.out.println("Second FOL statement: " + y.toString());
		Idempotence(y, idem);
		System.out.println("Second FOL after applying " + idem + " idempotence: " + y.toString());
		Unification(x, y, MGU);
		Refine(MGU, refined);
		if (MGU.isEmpty() || !checkForConstants(MGU)) {
			System.out.println("Terms cannot be unified (MGU doesn't exist)");
			return;
		}
		Clean(MGU);
		printMGU(MGU);

	}
	
	public void Idempotence(Element x, String idem){
		if(idem == ""){
			return;
		}
		
		if( x instanceof Function && x.getName().equals(idem)){
			int paramSize = x.getParams().size();
			if(paramSize == 1 && x.getParams().get(0) instanceof Function && x.getParams().get(0).getName().equals(idem)){
				//x.setValue(x.getParams().get(0).getValue());
				//System.out.println("First we had this " + x.toString());
				Function idemX = (Function)x.getParams().get(0);
				//System.out.println("here " + idemX.toString());
				x.getParams().clear();
				x.getParams().add(idemX.getParams().get(0));
				//System.out.println("Now it's this " + x.toString());
			} else if(paramSize == 2 && x.getParams().get(0).getName().equals(x.getParams().get(1).getName())){
				System.out.println("First we had this " + x.toString());
				x = x.getParams().get(0);
			}
		} else if(x instanceof Function){
			for(int i = 0; i < x.getParams().size(); i++){
				Idempotence(x.getParams().get(i), idem);
			}
		}
	}

	public void Unification(Element x, Element y, ArrayList<Element> MGU) {
		if (compareElements(x, y)) {
			System.out.println("Variables Match");
		} else {
			if ((x instanceof Constant && y instanceof Constant) || (x instanceof Constant && y instanceof Function)
					|| (x instanceof Function && y instanceof Constant)) {
				return;
			} else if ((x instanceof Constant && y instanceof Variable)
					|| (x instanceof Function && y instanceof Variable)) {
				if ((containsElement(MGU, y) == -1) || (containsElement(MGU, y) != -1
						&& !(MGU.get(containsElement(MGU, y)).getValue() instanceof Constant))) {
					if (x instanceof Constant) {
						y.setValue((Constant) x);
						MGU.add((Constant) x);
					} else {
						String s = getFunctionParams(x.getParams());
						if (!(s.contains(y.getName()))) {
							y.setValue((Function) x);
							MGU.add((Function) x);
						} else {
							return;
						}
					}

					MGU.add((Variable) y);
				}
			} else if ((x instanceof Variable && y instanceof Constant)
					|| (x instanceof Variable && y instanceof Function)) {
				if ((containsElement(MGU, x) == -1) || (containsElement(MGU, x) != -1
						&& !(MGU.get(containsElement(MGU, x)).getValue() instanceof Constant))) {
					if (y instanceof Constant) {
						x.setValue((Constant) y);
						MGU.add((Constant) y);
					} else {
						String s = getFunctionParams(y.getParams());
						if (!(s.contains(x.getName()))) {
							x.setValue((Function) y);
							MGU.add((Function) y);
						} else {
							return;
						}
					}
					MGU.add((Variable) x);
				}
			} else if (x instanceof Variable && y instanceof Variable) {
				if (containsElement(MGU, x) != -1) {
					y.setValue(MGU.get(containsElement(MGU, x)).getValue());
					MGU.add(y.getValue());
					MGU.add((Variable) y);
				} else if (containsElement(MGU, y) != -1) {
					x.setValue(MGU.get(containsElement(MGU, y)).getValue());
					MGU.add(x.getValue());
					MGU.add((Variable) x);
				}
			} else if (x instanceof Function && y instanceof Function && x.getLength() == y.getLength()) {
				Function funcX = (Function) x;
				Function funcY = (Function) y;
				for (int i = 0; i < x.getLength(); i++) {
					Unification(funcX.params.get(i), funcY.params.get(i), MGU);
				}
			}
		}
	}

	public void Refine(ArrayList<Element> MGU, String refined) {
		ArrayList<Element> refineMGU = new ArrayList<Element>();
		ArrayList<Variable> variables = new ArrayList<Variable>();
		variables = getAllVariables(MGU);
		for (int i = 0; i < variables.size(); i++) {
			Variable currentVar = variables.get(i);
			String name = "";
			Element value = null;
			boolean first = true;
			
			for (int k = 0; k < MGU.size(); k++) {
				if (MGU.get(k) instanceof Function) {
					String parameters = getFunctionParams(MGU.get(k).getParams());
					if (parameters.contains(currentVar.getName())) {
						int location = containsElement(MGU.get(k).getParams(), currentVar);
						MGU.get(k).getParams().remove(location);
						MGU.get(k).getParams().add(currentVar.getValue());
					}
				}
			}
			
			for (int j = 1; j < MGU.size(); j += 2) {
				if (MGU.get(j) instanceof Variable && first && MGU.get(j).getName().equals(currentVar.getName())) {
					name = MGU.get(j).name;
					value = MGU.get(j).getValue();
					first = false;
				} else if (MGU.get(j) instanceof Variable && !first && MGU.get(j).getName().equals(name)
						&& !(refined.contains(currentVar.getName()))) {
					refined += currentVar.getName();
					Unification(value, MGU.get(j).getValue(), MGU);
					Refine(MGU, refined);
				}
			}
		}
	}

	public boolean checkForConstants(ArrayList<Element> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Constant) {
				return true;
			}
		}

		return false;
	}

	public void Clean(ArrayList<Element> MGU) {
		String keep = "";
		for (int i = MGU.size() - 1; i >= 0; i -= 2) {
			String name = MGU.get(i).getName();
			if (keep.contains(name)) {
				MGU.remove(i);
				MGU.remove(i - 1);
			} else {
				keep += name;
			}
		}
	}

	public String getFunctionParams(ArrayList<Element> list) {
		String returns = "";
		for (int i = 0; i < list.size(); i++) {
			returns += list.get(i).getName();
		}
		return returns;
	}

	public ArrayList<Variable> getAllVariables(ArrayList<Element> list) {
		String added = "";
		ArrayList<Variable> returnList = new ArrayList<Variable>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Variable && !(added.contains(list.get(i).getName()))) {
				added += list.get(i).getName();
				returnList.add((Variable) list.get(i));
			}
		}

		return returnList;
	}

	public void printMGU(ArrayList<Element> MGU) {
		String printOut = "Result: {";
		for (int i = 0; i < MGU.size(); i += 2) {
			if (i != 0) {
				printOut += ", ";
			}
			if (MGU.get(i) instanceof Function) {
				printOut += MGU.get(i) + "/" + MGU.get(i + 1).getName();
			} else {
				printOut += MGU.get(i).getName() + "/" + MGU.get(i + 1).getName();
			}

		}
		printOut += "}";
		System.out.println(printOut);
	}

	public int containsElement(ArrayList<Element> list, Element x) {
		for (int i = 0; i < list.size(); i++) {
			if (x.getName().equals(list.get(i).getName())) {
				return i;
			}
		}
		return -1;
	}

	public boolean compareElements(Element x, Element y) {
		if (x instanceof Constant && y instanceof Constant) {
			if (x.getName().compareTo(y.getName()) == 0) {
				return true;
			} else {
				return false;
			}
		} else if (x instanceof Variable && y instanceof Variable) {
			if (compareElements(x.getValue(), y.getValue())) {
				return true;
			} else {
				return false;
			}
		} else if (x instanceof Function && y instanceof Function) {
			if (x.getName().compareTo(y.getName()) == 0 && x.getLength() == y.getLength()) {
				return compareFunctionElements((Function) x, (Function) y);
			} else {
				return false;
			}
		} else if (x instanceof Variable && y instanceof Constant && compareElements(x.getValue(), y)) {
			return true;
		} else if (y instanceof Variable && x instanceof Constant && compareElements(y.getValue(), x)) {
			return true;
		}
		return false;
	}

	public boolean compareFunctionElements(Function x, Function y) {
		for (int i = 0; i < x.getLength(); i++) {
			if (!compareElements(x.params.get(i), y.params.get(i))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Unifier u = new Unifier();

		System.out.println("\nFirst Case:");
		u.Unify_id("P(a,y,f(y))", "P(z,z,u)", "f");

		System.out.println("\nSecond Case:");
		u.Unify_id("P(x,g(x),g(f(a)))", "P(f(u),v,v)", "");

		System.out.println("\nThird Case:");
		u.Unify_id("f(x,g(x),x)", "f(g(u),g(g(z)),z)", "f");
		
		System.out.println("\nFourth Case:");
		u.Unify_id("f(x,g(x),x)", "f(g(u),g(g(z)),z)", "g");

		System.out.println("\nFifth Case:");
		u.Unify_id("f(x,g(x))", "f(g(x),x)", "g");
		

	}
}
