package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.picdiagram;
import model.ruler;
import model.login;
import model.originRuler;


/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class administrator extends originRuler implements ruler, ChangeListener<login> {
    @FXML
    ListView<login> list;
    @FXML
    TextField ID;
    public void already() {
        ID.clear();
	}
    public void initialize() {
    	picdiagram diagram = getModel();
        list.setItems(diagram.gllogin());
        list.getSelectionModel().selectedItemProperty().addListener(this);
        if (diagram.gllogin().size() > 0) {
            list.getSelectionModel().select(0);
        }
    }
    public void doLogoff() {
	    usetoad();
	}
    public void removeclick() {
    	picdiagram label = getModel();
    	int num= list.getSelectionModel().getSelectedIndex();
    	if (num>=0) {
    		label.removelog(num);
    		list.refresh();
    	}
    }
    public void doHelp() {
		asisst(ahelp);
	}
    public void doExit() {
		Platform.exit();
	}
    public void creatclick() {
    	String login= ID.getText().trim();
    	picdiagram diagram = getModel();
        if (login.isEmpty()) {
        	 Alert popup = new Alert(Alert.AlertType.ERROR);
             popup.setContentText("no name");
             popup.showAndWait();
        }else {
        	login member = diagram.gmember(login);
            if (member==null) {
            	diagram.createusername(login);
                ID.clear();
            }else {
            	Alert popup = new Alert(Alert.AlertType.ERROR);
                popup.setContentText("same username is in list");
                popup.showAndWait();
               
            }
        }
    }
    public void gotoAlbumList() {
        seczero();
    }
	@Override
	public void changed(ObservableValue<? extends login> arg0, login arg1, login arg2) {
	}
}
