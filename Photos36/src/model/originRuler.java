package model;

import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class originRuler {
    public static final String ahelp= "/Photos/view/ahelp.fxml";
    public static final String phelp= "/Photos/view/phelp.fxml";
	public final static String way= "photo.dat";
    protected static Scene slog = null;
    public static Scene sadmin = null;
    public static Scene sname= null;
    public static Scene sceneAlbum= null;
    public static ruler controluser= null;
    public static ruler cadmin= null;
    public static ruler clogin = null;
    public static ruler cgroup = null;
    public static Stage lplat= null;
    public static Stage aplat= null;
    public static void start(Stage first) throws Exception {
		{	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(originRuler.class.getResource("/Photos/view/owner.fxml"));
		Parent root = loader.load();
		sadmin = new Scene(root);
		cadmin = loader.getController();
		}
		{	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(originRuler.class.getResource("/Photos/view/first.fxml"));
			Parent root = loader.load();
			slog = new Scene(root);
			controluser = loader.getController();
		}
		{	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(originRuler.class.getResource("/Photos/view/albums.fxml"));
			Parent root = loader.load();
			sceneAlbum = new Scene(root);
			cgroup = loader.getController();
		}
		{	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(originRuler.class.getResource("/Photos/view/second.fxml"));
		Parent root = loader.load();
		sname = new Scene(root);
		clogin = loader.getController();
		}
		first.setTitle("");
		first.setResizable(false);
		lplat = first;
		aplat = new Stage();
		first.setTitle("");
		aplat.setResizable(false);
		havefir();
	}
    public static void asisst(String fx) {
    	Parent root;
		try {
			root = FXMLLoader.load(originRuler.class.getResource(fx));
	    	Stage stage = new Stage();
	    	stage.initModality(Modality.APPLICATION_MODAL);
			Scene look = new Scene(root);
			stage.setScene(look);
			stage.setTitle("Help");
			stage.setResizable(false);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    private static picdiagram diagram;
    public void backaluse() {
        aplat.setScene(sceneAlbum);
        cgroup.already();
        aplat.show();
    }
    public static void adtouser() {
		lplat.setScene(sadmin);
		cadmin.already();
		lplat.show();
    }
    public static void putdiagram() {
		 if (diagram!=null) {
	        diagram.washup(true);
	         try {
	            FileOutputStream output = new FileOutputStream(way);
	            ObjectOutputStream outer = new ObjectOutputStream(output);
	            outer.writeObject(diagram);
	            outer.close();
	            output.close();
	         }
	         catch (IOException i) {
	             i.printStackTrace();
	         }
		 }
	 }
    public static void usetoad() {
    	havefir();
    }
    public static void logad() {
    	aplat.hide();
    	havefir();
    }
    private static void havefir() {
    	lplat.setScene(slog);
		controluser.already();
		lplat.show();
    }
    public static void secfir() {
		lplat.hide();
		movesec();
    }
    public static void seczero() {
		lplat.hide();
		movesec();
    }
    public static picdiagram getModel() {
        if (diagram == null) {
            try {
                FileInputStream getf= new FileInputStream(way);
                ObjectInputStream yes= new ObjectInputStream(getf);
                diagram = (picdiagram)yes.readObject();
                diagram.washup(false);
                yes.close();
                getf.close();
            }
            catch(IOException | ClassNotFoundException i) {
                diagram = null;
            }
            if (diagram==null) {
                diagram=new picdiagram();
                diagram.createusername("admin");
                diagram.createusername("stock");
                diagram.sloginname(diagram.gmember("stock"));
                login user = diagram.nowloginuser();
                collection album = user.creategroup("album1");
                album.createp(picture.makepic("stockphotos/giraffe1.jpg", null));
                album.createp(picture.makepic("stockphotos/giraff2.jpg", null));
                album.createp(picture.makepic("stockphotos/giraffe3.jpg", null));
                album.createp(picture.makepic("stockphotos/giraffe4.jpg", null));
                album.createp(picture.makepic("stockphotos/giraffe5.jpg", null));
            }
        }
        return diagram;
    }
    public static void thirdsec() {
		movesec();
	}
    private static void movesec() {
		aplat.setScene(sname);
		clogin.already();
		aplat.show();
	}
    
}
