package net.petrikainulainen.spring.batch;

import java.io.Serializable;

/**
 * Contains the information that's read from the CSV file.
 *
 */
public class StudentDTO implements Serializable {

	private static final long serialVersionUID = -5488507066073537479L;

	private String agencia;
	private String conta;
	private String saldo;
	private String status;

	public StudentDTO() {
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


	@Override
	public String toString() {
		return agencia + ';' + conta + ';' + saldo + ';' + status;
	}
}
