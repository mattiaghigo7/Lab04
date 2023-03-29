package it.polito.tdp.lab04.model;

import java.util.List;
import it.polito.tdp.lab04.DAO.*;

public class Model {

	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public List<Studente> cercaStudenteMatricola(int matricola) {
		return this.studenteDAO.cercaStudenteMatricola(matricola);
	}
	
	public List<Studente> getIscrittiCorso(String codins){
		return this.studenteDAO.getIscrittiCorso(codins);
	}
	
	public List<Corso> getCorsiMatricola(int matricola){
		return this.corsoDAO.getCorsiMatricola(matricola);
	}
	
	public boolean inscriviStudenteACorso(int matricola, String codins) {
		return this.corsoDAO.inscriviStudenteACorso(matricola, codins);
	}
}
