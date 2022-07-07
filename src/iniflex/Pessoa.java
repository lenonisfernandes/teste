package iniflex;

import java.time.LocalDate;

public class Pessoa {
	
	private String nome;
	private LocalDate dataDeNascimento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public String getDataDeNascimentoAsString() {
		return getDataDeNascimento().format(Util.dateFormatter());
	}
	
	@Override
	public String toString() {
		
		return getNome() + " - " + getDataDeNascimentoAsString();
	}
	
	public int calcularIdade() {
		if (LocalDate.now().getMonthValue()<getDataDeNascimento().getMonthValue() 
				&& LocalDate.now().getDayOfYear()<getDataDeNascimento().getDayOfYear()) {
			return  LocalDate.now().getYear() - getDataDeNascimento().getYear();
		}
		else {
			 return LocalDate.now().getYear() - getDataDeNascimento().getYear() - 1;
		}
	}

}
