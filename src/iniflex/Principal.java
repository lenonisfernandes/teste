package iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Principal {

	public static void main(String[] args) {

		List<Funcionario> funcionarios = new ArrayList<Funcionario>(
				Arrays.asList(new Funcionario("Maria", "18/10/2000", "2009.44", "Operador"),
						new Funcionario("João", "12/05/1990", "2284.38", "Operador"),
						new Funcionario("Caio", "02/05/1961", "9836.14", "Coordenador"),
						new Funcionario("Miguel", "14/10/1988", "19119.88", "Diretor"),
						new Funcionario("Alice", "05/01/1995", "2234.68", "Receptionista"),
						new Funcionario("Heitor", "19/11/1999", "1582.72", "Operador"),
						new Funcionario("Arthur", "31/03/1993", "4071.84", "Contador"),
						new Funcionario("Laura", "08/07/1994", "3017.45", "Gerente"),
						new Funcionario("Heloísa", "24/05/2003", "1606.85", "Eletricista"),
						new Funcionario("Helena", "02/09/1996", "2799.93", "Gerente")));

		funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

		funcionarios.stream().forEach(f -> System.out.println(f));

		System.out.println("------------------------\nFuncionários receberam 10% de aumento:");

		funcionarios.stream().forEach(f -> {
			f.setSalario(f.getSalario().multiply(new BigDecimal(1.1)));
			System.out.println(f);
		});

		Map<String, List<Funcionario>> funcionariosGroupedByFuncao = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new,
						Collectors.mapping(Function.identity(), Collectors.toList())));

		System.out.println("------------------------\nFuncionários agrupador por função:");
		funcionariosGroupedByFuncao.values().stream().forEach(list -> {
			System.out.println(list.get(0).getFuncao());
			list.stream().forEach(f -> System.out.println(f));
		});

		System.out.println("------------------------\nFuncionários que fazem aniversário no mês 10 ou 12:");
		funcionarios.stream().filter(
				f -> f.getDataDeNascimento().getMonthValue() == 10 || f.getDataDeNascimento().getMonthValue() == 12)
				.forEach(f -> System.out.println(f));

		System.out.println("------------------------\nFuncionário com a maior idade:");

		List<Funcionario> funcionariosMaisVelhos = new ArrayList<Funcionario>();
		funcionariosMaisVelhos.add(funcionarios.get(0));

		funcionarios.stream().forEach(f -> {
			if (f.calcularIdade() > funcionariosMaisVelhos.get(0).calcularIdade()) {
				funcionariosMaisVelhos.clear();
				funcionariosMaisVelhos.add(f);
			} else if (f.calcularIdade() == funcionariosMaisVelhos.get(0).calcularIdade()) {
				funcionariosMaisVelhos.add(f);
			}
		});

		funcionariosMaisVelhos.stream()
				.forEach(f -> System.out.println(f.getNome() + " - " + f.calcularIdade() + " anos"));

		System.out.println("------------------------\nFuncionários em ordem alfabética:");

		funcionarios.stream().sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
				.forEach(f -> System.out.println(f));

		System.out.println("------------------------\nSoma dos salários:");

		BigDecimal soma = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(Util.exibirValorMonetario(soma));

		System.out.println("------------------------\nQuantidade de salários mínimos por funcionário:");

		funcionarios.stream().forEach(f -> {
			System.out.println(f.getNome() + ": "
					+ Util.exibirValor(f.getSalario().divide(new BigDecimal(1212), 1, RoundingMode.HALF_UP), 1)
					+ " salários mínimos");
		});

	}
	
	

}
