package iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

public class Util {
	
	public static String exibirValorMonetario(BigDecimal valor) {
		return "R$ "+ exibirValor(valor, 2);
	}
	
	public static DateTimeFormatter dateFormatter(){
		 return DateTimeFormatter
		          .ofPattern("dd/MM/yyyy");
	}
	
	public static String exibirValor(BigDecimal valor, int casasDecimais) {
		valor = valor.setScale(casasDecimais, RoundingMode.HALF_UP);

		String s = valor.toString().replace(".", ",");
		if (s.length()>6)
			return s.substring(0, s.length()-6) + "." + s.substring(s.length()-6);
		else 
			return s;

	}
	

}
