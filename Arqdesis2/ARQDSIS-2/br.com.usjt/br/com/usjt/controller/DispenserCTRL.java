package br.com.usjt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import br.com.usjt.DAO.DispenserDAO;
import br.com.usjt.TO.DispenserTO;
import br.com.usjt.model.Dispenser;
import br.com.usjt.view.TelaAdmDispenser;
import br.com.usjt.view.TelaEscolherOpAdm;

public class DispenserCTRL extends TelaAdmDispenser{

	public DispenserCTRL(Dispenser dispenser) {
		super(dispenser);
		addBtnVoltarListener(new BtnVoltarListener());
		addBtnResetarListener(new BtnResetarListener());
		addBtnConsultarListener(new BtnConsultarListener());
	}


	class BtnVoltarListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			TelaEscolherOpAdm frame = new TelaEscolherOpAdm();
			frame.setVisible(true);
			frame.setSize(200,200);
			dispose();
		}
	}
	class BtnConsultarListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			Dispenser dispenser = new Dispenser();

			ArrayList<DispenserTO> recuper = dispenser.consultarExtratoDeNotas();

			DefaultTableModel dtm = (DefaultTableModel) tblResultado.getModel();

			//se n�o existir nenhuma informa��o consultada ele remove todas as linhas da tabela
			while (tblResultado.getRowCount() > 0) {
				dtm.removeRow(0);
			}

			int k = dtm.getRowCount();  

			try{
				if (k > 0){  
					for (int i=0;i<=k;i++){    
						dtm.removeRow(0);    
					}                
				}  

			}catch(Exception e){

			}
			for(Iterator<DispenserTO> it = recuper.iterator(); it.hasNext();){
				DispenserTO listaLog = it.next();
				dtm.addRow(new Object[] {listaLog.getNota(), listaLog.getQuantidade()});
			}
		}
	}

	class BtnResetarListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {

			DispenserDAO dispenserDAO = new DispenserDAO();

			dispenserDAO.resetarDispencher();


			Dispenser dispenser = new Dispenser();

			ArrayList<DispenserTO> recuper = dispenser.consultarExtratoDeNotas();

			DefaultTableModel dtm = (DefaultTableModel) tblResultado.getModel();

			//se n�o existir nenhuma informa��o consultada ele remove todas as linhas da tabela
			while (tblResultado.getRowCount() > 0) {
				dtm.removeRow(0);
			}

			int k = dtm.getRowCount();  

			try{
				if (k > 0){  
					for (int i=0;i<=k;i++){    
						dtm.removeRow(0);    
					}                
				}  

			}catch(Exception e){

			}
			for(Iterator<DispenserTO> it = recuper.iterator(); it.hasNext();){
				DispenserTO listaLog = it.next();
				dtm.addRow(new Object[] {listaLog.getNota(), listaLog.getQuantidade()});
			}
		}
	}
}