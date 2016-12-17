public class Parser {
	
	public Parser() {
		
	}
	
	public Element parse(String s) {
		String u = s.replaceFirst("\\(", "/");
		String[] parts = u.split("/");
		if (parts.length <= 1) {
			parts[0] = parts[0].replace(")","");
			if (varCheck(parts[0])) {
				return new Variable(parts[0]);
			} else {
				return new Constant(parts[0]);
			}
		}
		String[] elmnts = parts[1].split(",");
		Function f = new Function(parts[0].trim());
		for (int i = 0; i < elmnts.length; i++) {
			f.params.add(parse(elmnts[i]));
		}
		return f;
	}
	
	public boolean varCheck(String s) {
		if (s.equals("u") || s.equals("v") || s.equals("x") || s.equals("y") || s.equals("z")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		String s = "f(x,g(x),y)";
		Parser p = new Parser();
		Function e = (Function)p.parse(s);
		System.out.println(e.getName());
	}

}
