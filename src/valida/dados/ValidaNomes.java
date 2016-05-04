package valida.dados;

import java.util.regex.*;

public class ValidaNomes {
	
	public ValidaNomes(){
		
	}
	
	//valida nome
	public Matcher validaNovoNome(String newNome){
		String pattern = "^([a-z A-z]){1,50}$";
		Pattern p = Pattern.compile(pattern);
		return p.matcher(newNome);
	}

}
