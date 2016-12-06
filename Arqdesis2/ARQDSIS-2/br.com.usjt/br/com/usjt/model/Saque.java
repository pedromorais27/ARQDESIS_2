package br.com.usjt.model;

import java.util.Date;
import javax.swing.JOptionPane;
import br.com.usjt.DAO.ContaDAO;
import br.com.usjt.TO.ContaTO;
import br.com.usjt.TO.MovimentoTO;
import br.com.usjt.TO.SaqueTO;

public class Saque extends Movimento {

	private Dispenser dispenser;
	private int conta, agencia;
	private Double saque;

	public Saque(Movimento movimento) {
		dispenser = new Dispenser();
	} 

	public int getConta() {
		return conta;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setConta(int conta) {
		this.conta = conta;
		setChanged();
		notifyObservers();
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
		setChanged();
		notifyObservers();
	}

	public Double getSaque() {
		return saque;
	}

	public void setSaque(Double saque) {
		this.saque = saque;
		setChanged();
		notifyObservers();
	}

	public boolean fazerSaque(double valorSacar){
		ContaDAO contaDAO = new ContaDAO();
		SaqueTO saqueTO = new SaqueTO();
		ContaTO contaTO = contaDAO.selectSaldo(getConta());

		double saldoAtual = contaTO.getSaldo();
		boolean teste; //variavel para teste no junit
		
		if(saldoAtual >= valorSacar){
			dispenser.contarNotas(valorSacar);

			double novosaldo = saldoAtual - valorSacar;

			saqueTO.setSaque(novosaldo);
			contaTO.setNumConta(getConta());

			contaDAO.updateSaque(contaTO, saqueTO);

			Date dataDeHoje = new Date(); 

			MovimentoTO movimentoTO = new MovimentoTO();
			movimentoTO.setDataDoMovimento(dataDeHoje);
			movimentoTO.setValorDaOperacao(valorSacar);

			contaTO.setAgencia(getAgencia());
			contaTO.setNumConta(getConta());
			
			ContaTO contaTODestino = new ContaTO();
			
			contaTODestino.setAgencia(getAgencia());
			contaTODestino.setNumConta(getConta());

			geraMovimento(contaTO, movimentoTO, contaTODestino, "Debito em Conta corrente");
			teste = true;
		}
		else{
			JOptionPane.showMessageDialog(null, "Saldo insuficiente para saque");
			teste = false;
		}
		return teste;
	}
}