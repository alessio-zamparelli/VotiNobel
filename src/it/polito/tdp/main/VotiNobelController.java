package it.polito.tdp.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import it.polito.tdp.model.Esame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VotiNobelController {

	Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtInput;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnReset;

	@FXML
	void doCalcolaCombinazione(ActionEvent event) {
		try {
			int numeroCrediti = Integer.parseInt(txtInput.getText());
			List<Esame> voti = model.calcolaSottoinsiemeEsami(numeroCrediti);
//    			txtResult.setText(voti.toString());
			txtResult.setText(String.format("La media è di: %.2f\n", model.getMedia(voti)));
			for (Esame esame : voti)
				txtResult.appendText(String.format("Codice: %-7s, Crediti: %2s, Voto: %2d, Esame: %-45s \n", esame.getCodins(),
						esame.getCrediti(), esame.getVoto(), esame.getNomeCorso()));

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire un numero di crediti > 0");
		}
	}

	@FXML
	void doReset(ActionEvent event) {
		// reset the UI
		txtInput.clear();
		txtResult.clear();
	}

	@FXML
	void initialize() {
		assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'VotiNobel.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'VotiNobel.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'VotiNobel.fxml'.";

		// Utilizzare questo font per incolonnare correttamente i dati
		txtResult.setStyle("-fx-font-family: monospace");
		txtResult.setFont(Font.font(null, FontWeight.BOLD, 12));
		
		

	}

	public void setModel(Model model) {

		this.model = model;
	}
}
