package it.polito.tdp.lab04;

import java.net.URL;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox check;

    @FXML
    private ComboBox<String> cmbCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtResult;
    
    @FXML
    void doCheck(ActionEvent event) {
    	int matricola;
    	this.check.setDisable(true);
    	try {
        	matricola = Integer.parseInt(this.txtMatricola.getText());
    	} catch(NumberFormatException e){
    		this.txtResult.setText("Inserire una matricola valida");
    		this.txtMatricola.clear();
    		this.check.setDisable(false);
    		this.check.setSelected(false);
    		return;
    	}
    	List<Studente> risultato = new ArrayList<Studente>();
    	risultato = this.model.cercaStudenteMatricola(matricola);
    	if (risultato.size()==0) {
    		this.txtResult.setText("Inserire una matricola valida");
    		this.check.setDisable(false);
    		this.check.setSelected(false);
    		this.txtMatricola.clear();
    		return;
    	}
    	for (Studente i : risultato) {
    		this.txtCognome.setText(i.getCognome());
        	this.txtNome.setText(i.getNome());
    	}
    }
    
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricola;
    	try {
        	matricola = Integer.parseInt(this.txtMatricola.getText());
    	} catch(NumberFormatException e){
    		this.txtResult.setText("Inserire una matricola valida");
    		this.txtMatricola.clear();
    		this.check.setDisable(false);
    		this.check.setSelected(false);
    		return;
    	}
    	String codins = this.cmbCorsi.getValue();
    	if(codins==null) {
        	List<Corso> risultato = new ArrayList<Corso>();
        	risultato = this.model.getCorsiMatricola(matricola);
        	if (risultato.size()==0) {
        		this.txtResult.setText("Inserire una matricola valida");
        		this.check.setDisable(false);
        		this.check.setSelected(false);
        		this.txtMatricola.clear();
        		return;
        	}
        	this.txtResult.clear();
        	for (Corso c : risultato) {
        		this.txtResult.appendText(c+"\n");
        	}
    	}
    	else {
    		boolean a = false;
    		List<Studente> risultato = new ArrayList<Studente>();
    		risultato = this.model.getIscrittiCorso(codins);
    		for (Studente s : risultato) {
    			if(s.getMatricola()==matricola) {
    				this.txtResult.setText("Studente già iscritto a questo corso");
    				a=true;
    			}
    		}
    		if (a==false) {
    			this.txtResult.setText("Studente non iscritto a questo corso");
    		}
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	String codins = this.cmbCorsi.getValue();
    	if (codins==null) {
    		this.txtResult.setText("Selezionare codice di un corso");
    		return;
    	}
    	List<Studente> risultato = new ArrayList<Studente>();
    	risultato = this.model.getIscrittiCorso(codins);
    	this.txtResult.clear();
    	for (Studente s : risultato) {
    		this.txtResult.appendText(s+"\n");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	int matricola;
    	try {
        	matricola = Integer.parseInt(this.txtMatricola.getText());
    	} catch(NumberFormatException e){
    		this.txtResult.setText("Inserire una matricola valida");
    		this.txtMatricola.clear();
    		this.check.setDisable(false);
    		this.check.setSelected(false);
    		return;
    	}
    	String codins = this.cmbCorsi.getValue();
    	if (codins==null || codins=="") {
    		this.txtResult.setText("Selezionare codice di un corso");
    		return;
    	}
    	boolean a = this.model.inscriviStudenteACorso(matricola, codins);
    	if (a==true) {
    		this.txtResult.setText("Studente iscritto al corso!");
    	}
    	else if(a==false) {
    		this.txtResult.setText("Studente già iscritto al corso!");
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    	this.txtCognome.clear();
    	this.txtResult.clear();
    	this.check.setDisable(false);
    	this.check.setSelected(false);
    }

    @FXML
    void initialize() {
        assert check != null : "fx:id=\"check\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
      
    }

    public void setModel(Model model) {
    	this.model = model;
    	List<Corso> l = this.model.getTuttiICorsi();
    	 this.cmbCorsi.getItems().add("");
    	 for(int i=0;i<l.size();i++) {
         	String s = l.get(i).getCodins();
         	this.cmbCorsi.getItems().add(s);
         }
    }
}
