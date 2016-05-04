package valida.dados;

import java.util.regex.*;

public class ValidaMatricula {
	
	public ValidaMatricula(){
		
	}
	
	public Matcher validaMatricula(String matricula){
		String pattern = "^([1-3]{1}(0-9){7})$";
		Pattern p = Pattern.compile(pattern);
		return p.matcher(matricula);
	}
}
