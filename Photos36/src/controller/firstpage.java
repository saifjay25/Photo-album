package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.login;
import model.originRuler;
import model.ruler;
import javafx.scene.control.Alert;



/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class firstpage extends originRuler implements ruler {
    @FXML TextField logger;
    public void already() {
		getModel().sloginname(null);
		logger.clear();
	}
    public void doExit() {
		Platform.exit();
	}
    public void userIDKeyPressed(KeyEvent essence) {
        if (essence.getCode() == KeyCode.ENTER) {
        	logclicked();
        }
	}
    public void doHelp() {
		asisst(phelp);
	}
    public void logclicked() {
    	String identifc= logger.getText().trim();
    	login member = getModel().gmember(identifc);
    	if (member==null) {
    		Alert popup = new Alert(Alert.AlertType.ERROR);
			popup.setContentText("login not found in admin, please add to admin first");
			popup.showAndWait();
    		logger.setText("");
    	}else {
    		getModel().sloginname(member);
        	if (identifc.equalsIgnoreCase("admin")) {
            	adtouser();
        	}else {
            	secfir();
        	}
    	}
    }

}