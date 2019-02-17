package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class picdiagram implements Serializable {
    private static final long serialVersionUID = 1;
    private ObservableList<login> listUsers;
    private ArrayList<login> listOfUsersToKeep;
    private login currentUser;
    public picdiagram() {
        listUsers= FXCollections.observableArrayList();
        currentUser= null;
        listOfUsersToKeep= null;
    }
    public ObservableList<login> gllogin() {
        return listUsers;
    }
    public login gmember(String login) {
        return listUsers.stream().filter(user->user.glogin().equalsIgnoreCase(login)).findFirst().orElse(null);
    }
    public void removelog(int num) {
        if (num>=0 && num<listUsers.size()) {
            if (!listUsers.get(num).glogin().equalsIgnoreCase(login.administrator)) {
                listUsers.remove(num);
            }else {
                Alert popup = new Alert(Alert.AlertType.ERROR);
                popup.setTitle("wrong");
                popup.setContentText("admin can't be removed");
                popup.showAndWait();
            }
        }
    }
    public login  nowloginuser() {
        return currentUser;
    }
    public void sloginname(login nlogin) {
        this.currentUser = nlogin;
    }
    public void washup(boolean in) {
        if (!(in)) {
        	listUsers = FXCollections.observableList(listOfUsersToKeep);
            listOfUsersToKeep = null;
            for (login u : listUsers) {
                u.rinseup(false);
            }
        } else {
        	listOfUsersToKeep = new ArrayList<>(listUsers);
            listUsers = null;
            for (login u : listOfUsersToKeep) {
                u.rinseup(true);
            }
        }
    }
    public void createusername(String login) {
        login member = new login(login);
        asisster.addOrRetrieveOrderedList(listUsers, member);
    }
}