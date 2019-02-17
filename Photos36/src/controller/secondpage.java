package controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.*;

import java.time.LocalDate;
import java.util.List;


/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class secondpage extends originRuler implements EventHandler<MouseEvent>, ruler, ChangeListener<collection> {
    @FXML TableView<collection> 	tview;
    @FXML TableColumn 	colal;
	@FXML TableColumn 	colpho;
	@FXML TableColumn 	colfirst;
	@FXML TableColumn 	collast;
	@FXML
	ListView<pictags> ltag;
    @FXML TextField llamo;
    @FXML TextField trep;
    @FXML TextField createalbum;
    @FXML DatePicker sdate;
    @FXML DatePicker ldate;
    public void already() {
    	login login = getModel().nowloginuser();
    	ObservableList<collection> collection = login.glal();
    	ltag.setItems(login.greps().getTags());
    	if (login.glal().size() > 0) {
        	tview.getSelectionModel().select(0);
        }
        if (login.greps().getTags().size() > 0) {
        	ltag.getSelectionModel().select(0);
        }
    	for (collection a:collection) {
    		a.timer();
    	}
    	tview.setItems(collection);
    	tview.refresh();
	}
    @FXML
    public void initialize() {
    	tview.setEditable(true);
    	tview.setRowFactory(tableView -> {
            final TableRow<collection> left= new TableRow<>();
            final ContextMenu info= new ContextMenu();
            final MenuItem detroyi= new MenuItem("delete");
            detroyi.setOnAction(event -> tview.getItems().remove(left.getItem()));
            info.getItems().add(detroyi);
            left.contextMenuProperty().bind( Bindings.when(left.emptyProperty()) .then((ContextMenu)null).otherwise(info));
            return left ;
        });
    	tview.getSelectionModel().selectedItemProperty().addListener(this);
    	ldate.setValue(LocalDate.now());
    	sdate.setValue(ldate.getValue().minusDays(30));
    	tview.setOnMouseClicked(event -> {
			if (event.getClickCount() != 1 || !event.getButton().equals(MouseButton.PRIMARY)) {
				if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    backaluse();
                }
			}
		});
    	
    }
	@Override
	public void handle(MouseEvent arg0) {
	}
    public void doLogoff() {
	    logad();
	}
    public void doExit() {
		Platform.exit();
	}
    public void createtag() {
		String login = llamo.getText().trim();
		String num = trep.getText().trim();
        login username = getModel().nowloginuser();
        if (login.length()>0 && num.length()>0) {
            boolean truth = username.greps().addTag(login, num);
            if (!(truth)) {
            	
            } else {
            	ltag.refresh();
                llamo.setText("");
                trep.setText("");   
            }
        }
	}
    public void removetagclicked() {
		login login = getModel().nowloginuser();
		int num= ltag.getSelectionModel().getSelectedIndex();
		login.removetcon(num);
		ltag.refresh();
	}
    public void dsearchclicked() {
		login login = getModel().nowloginuser();
		if ( ldate.getValue()==null||sdate.getValue()==null ) {
		    Alert alert = new Alert(Alert.AlertType.ERROR, "put first date and last date.", ButtonType.YES);
		    alert.showAndWait();
		}else {
			String text="";
			login.gtime().sfirstd(sdate.getValue());
			login.gtime().slastd(ldate.getValue());
			List<picture> piclist = login.setime();
			if (piclist.size()>0) {
				collection sprout= new collection(collection.albumdate, piclist);
				sprout.timer();
				login.changegroup(sprout);
	    		login.setgroup(sprout);
	    		backaluse();
			}else {
				text= "no pictures had tag";
			    Alert popup= new Alert(Alert.AlertType.ERROR, text, ButtonType.YES);
			    popup.showAndWait();
			}
		}
	}
    public void tsearchclicked() {
    	String text="";
		login login = getModel().nowloginuser();
		tagrules trule= login.greps();
		if (trule.getTags().size()==0) {
		    Alert alert = new Alert(Alert.AlertType.INFORMATION, "put search rules", ButtonType.YES);
		    alert.showAndWait();
		}else {
			List<picture> lst = login.setag();
			if (lst.size()>0) {
				collection newOne = new collection(collection.albumtag, lst);
				newOne.timer();
				login.changegroup(newOne);
	    		login.setgroup(newOne);
	    		backaluse();
			}else {
				text= "no pictures for condition";
			    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text, ButtonType.YES);
			    alert.showAndWait();
			}
		}
	}
    public void addalclicked() {
		login name= getModel().nowloginuser();
		String group= createalbum.getText().trim();
		if (group.length()>0) {
			name.creategroup(group);
	    	tview.refresh();
			createalbum.setText("");
		}
	}
    @Override
	public void changed(ObservableValue<? extends collection> look, collection first, collection last) {
    	picdiagram diagram = getModel();
    	login login = diagram.nowloginuser();
    	if (last!=null) {
    		login.setgroup(last);
    	}
	}
    public void doHelp() {
		asisst(phelp);
	}
}