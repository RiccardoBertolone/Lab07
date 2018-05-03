package it.polito.tdp.poweroutages;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> NERCCbx;

    @FXML
    private TextField MaxYearsBtn;

    @FXML
    private TextField MaxHoursBtn;

    @FXML
    private Button WCABtn;

    @FXML
    private TextArea txtResult;
    
    private Model model;

    @FXML
    void doAnalisi(ActionEvent event) {

    	txtResult.clear();
    	Nerc n ;
    	int x = 0 ;
    	int y = 0;
    	
		n = NERCCbx.getValue() ;
		if (n == null) {
    		txtResult.setText("Non hai selezionato un NERC");
    		return;
		}
    	
    	try {
    		x = Integer.parseInt(MaxYearsBtn.getText()) ;
    		y = Integer.parseInt(MaxHoursBtn.getText()) ;
    	}
    	catch (NumberFormatException e) {
    		txtResult.setText("Devi inserire un numero!");
    		return;
    	}
    	
    	List<PowerOutages> result = model.risolvi(n,  x,  y) ;
    	Collections.sort(result);
    	
    	String lista = "" ;	
    	int people = 0;
    	long hours = 0;
    	for (PowerOutages p : result) {
    		people+=p.getCustumersAffected();
    		LocalDateTime t1 = p.getDateEventBegan() ;
    		hours+=t1.until(p.getDateEventFinished(), ChronoUnit.MINUTES) ;
    		int h = (int) t1.until(p.getDateEventFinished(), ChronoUnit.HOURS) ;
    		lista+=p.getDateEventBegan().getYear()+" "+p.getDateEventBegan().toString()+" "+p.getDateEventFinished().toString()+" "+h+" "+p.getCustumersAffected()+"\n";
    	}
    	
    	hours = hours/60 ;
    	lista=lista.trim() ;
    	
    	txtResult.appendText("Tot people affected: "+people+"\nTot hours of outage: "+hours+"\n"+lista);
    	
    	
    }

    @FXML
    void initialize() {
        assert NERCCbx != null : "fx:id=\"NERCCbx\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert MaxYearsBtn != null : "fx:id=\"MaxYearsBtn\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert MaxHoursBtn != null : "fx:id=\"MaxHoursBtn\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert WCABtn != null : "fx:id=\"WCABtn\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		NERCCbx.getItems().addAll(model.getNercList());		
	}
}
