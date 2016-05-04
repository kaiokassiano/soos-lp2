package valida.dados;

import java.util.regex.*;

public class ValidaSenhas {
	
	public ValidaSenhas(String newSenha){
		
	}
	
	//valida senha
	public Matcher validaNovaSenha(String newSenha){
		String pattern = "^([a-zA-Z0-9]){8,12}$";
		Pattern p = Pattern.compile(pattern);
		return p.matcher(newSenha);
	}
}
