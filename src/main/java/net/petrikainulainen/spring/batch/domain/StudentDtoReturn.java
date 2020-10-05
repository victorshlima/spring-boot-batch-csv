package net.petrikainulainen.spring.batch.domain;

import java.io.Serializable;

/**
 * Contains the information that's read from the CSV file.
 *
 */
public class StudentDtoReturn extends StudentDTO implements Serializable {

	private static final long serialVersionUID = -5488507066073537479L;

	private String agencia;
	private String conta;
	private String saldo;  // alterar para double
	private String status;
	private boolean valido;
	
	public StudentDtoReturn() {
	}

	
	
	public StudentDtoReturn(StudentDTO item, boolean valido) {
		super();
		this.agencia = item.getAgencia();
		this.conta = item.getConta();
		this.saldo = item.getSaldo();
		this.status = item.getStatus();
		this.valido = valido;
	}



	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}
	
	public String getContaFormatada() {
		return conta.replaceAll("\\D", "");
	}
	
	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getSaldo() {
		return saldo;
	}
	
	public Double getSaldoFormatada() {
		return (!getSaldo().isEmpty()) ?Double.valueOf(saldo.replace(",", ".")) : 0.00;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}	

	@Override
	public String toString() {
		return agencia + ';' + conta + ';' + saldo + ';' + status + ';' + valido;
	}
}
