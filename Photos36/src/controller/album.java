package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.util.List;


/**
 * @author Saif Jame
 * @author Philip Aqulina
 */
public class album extends originRuler implements EventHandler<MouseEvent>, ChangeListener<Tab>, ruler {
	static final String indigo="-fx-border-color: blue;\n" +"-fx-border-style: solid;\n" +"-fx-border-width: 3;\n";
    static final String white = "-fx-border-color: white;\n" +"-fx-border-style: solid;\n" +"-fx-border-width: 3;\n";
    @FXML
    TilePane layout;
    @FXML 
    ListView<pictags> tlist;
    @FXML
    TabPane section;
    @FXML Pagination page;
    @FXML TextField tval;
	@FXML TextField tname;
    ObservableList<Node> list;
    final ContextMenu punk = new ContextMenu();
    Menu dup = new Menu("duplicate ");
    Menu trans = new Menu("transport ");
	final ContextMenu foundation = new ContextMenu();
    @Override
	public void already() {
    	section.getSelectionModel().select(0);
    	login user = getModel().nowloginuser();
    	collection album = user.nowgroupp();
    	ObservableList<collection> albums =user.glal();
    	list.clear();
    	List<picture> photoList = album.retrieveplist();
        for (picture onePhoto : photoList) {
            BorderPane viewWrapper = onePhoto.gpicvi(this, white);
            list.add(viewWrapper);
        }
    	int index = album.getn();
        curpicture(index, true);
	}
    public album() {
    	album controller = this;
    	{	MenuItem cmItemAdd = new MenuItem("Add");
	    	cmItemAdd.setOnAction(e -> {
                login user = getModel().nowloginuser();
                collection album = user.nowgroupp();
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Open Photo File");
                File file = chooser.showOpenDialog(new Stage());
                if (file!=null) {
                    picture photo = picture.makepic(file.getAbsolutePath(), file);
                    int index = album.createphoal(null, photo);
                    BorderPane viewWrapper = null;
                    if (photo != null) {
                        viewWrapper = photo.gpicvi(controller, white);
                    }
                    list.add(index, viewWrapper);
                    curpicture(index, true);
                }
            });
	    	foundation.getItems().add(cmItemAdd);
    	}
    	MenuItem cmItemAdd = new MenuItem("Add");
    	cmItemAdd.setOnAction(e -> {
            picture onePhoto = (picture)punk.getUserData();
            login user = getModel().nowloginuser();
            collection album = user.nowgroupp();
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(new Stage());
            if (file!=null) {
                picture photo = picture.makepic(file.getAbsolutePath(), file);
                int oldcurrIndex = album.getn();
                int index = album.createphoal(onePhoto, photo);
				BorderPane viewWrapper = null;
				if (photo != null) {
					viewWrapper = photo.gpicvi(controller, white);
				}
                list.add(index, viewWrapper);
                if (index<=oldcurrIndex) {
                	BorderPane vw = (BorderPane)list.get(oldcurrIndex+1);
                    vw.setStyle(white);
                }
                curpicture(index, true);
            }
        });
    	punk.getItems().add(cmItemAdd);
    	punk.getItems().add(dup);
    	punk.getItems().add(trans);
    	MenuItem cmItemDelete = new MenuItem("Delete");
    	cmItemDelete.setOnAction(e -> {
            picture onePhoto = (picture)punk.getUserData();
            login user = getModel().nowloginuser();
            collection album = user.nowgroupp();
            int index = album.removepho(onePhoto);
            list.remove(index);
            int indexCurr = album.getn();
            curpicture(indexCurr, true);
        });
    	punk.getItems().add(cmItemDelete);
	}
    @FXML
    public void initialize() {
    	list = layout.getChildren();
    	layout.setOnMouseClicked(this);
        section.getSelectionModel().selectedItemProperty().addListener(this);
        section.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        page.setPageFactory(pagenum -> {
            login login= getModel().nowloginuser();
            collection group= login.nowgroupp();
            if (group.length() > 0) {
                album.this.curpicture(pagenum, false);
                picture nowpic= group.curp();
                return nowpic.gnpic(arg0 -> section.getSelectionModel().select(0));
            }else {
                return null;
            }
        });
    }
    @Override
	public void handle(MouseEvent event) {
		Object jection = event.getSource();
		if (jection  instanceof TilePane) {
	        if (event.getButton() == MouseButton.SECONDARY) {
	        	foundation.show(layout, event.getScreenX(), event.getScreenY());
	        }
	        event.consume();
		}else if (jection instanceof ImageView) {
			ImageView show = (ImageView)jection ;
	        if (event.getButton() == MouseButton.SECONDARY) {
	        	Object jectin1 = show.getUserData();
            	picture picture = (picture)jectin1;
            	picdiagram label = getModel();
            	login user = label.nowloginuser();
            	ObservableList<collection> albums =user.glal();
            	collection album = user.nowgroupp();
                List<collection> comline = asisster.filter(albums, album, (t,u)->t!=u &&(!t.getAlbumName().equalsIgnoreCase(album.albumdate) && !t.getAlbumName().equalsIgnoreCase(album.albumtag)));
            	dup.getItems().clear();
            	for (collection a : comline) {
                	MenuItem thing = new MenuItem(a.getAlbumName());
                	thing.setOnAction(e -> {
                        MenuItem mthing =  (MenuItem)e.getSource();
                        login login1 = getModel().nowloginuser();
                        picture pic1 = (picture)punk.getUserData();
                        String prey = mthing.getText();
                        login1.cpic(pic1, prey );
                    });
                	dup.getItems().add(thing);
            	}
            	trans.getItems().clear();
            	for (collection a : comline) {
                	MenuItem thingal = new MenuItem(a.getAlbumName());
                	thingal.setOnAction(e -> {
                        MenuItem it =  (MenuItem)e.getSource();
                        picture pic13 = (picture)punk.getUserData();
                        String alprey = it.getText();
                        login login13 = getModel().nowloginuser();
                        collection com13 = login13.nowgroupp();
                        int number = com13.phindex(pic13);
                        list.remove(number);
                        login13.mpic(pic13, alprey);
                        int now = com13.getn();
                        curpicture(now, true);
                    });
                	trans.getItems().add(thingal);
            	}
	        	punk.setUserData(picture);
	        	punk.show(show, event.getScreenX(), event.getScreenY());
	        }else if (event.getButton().equals(MouseButton.PRIMARY)) {
	        	 if (event.getClickCount() == 1) {
		            Object ob = show.getUserData();
		            picture onpi = (picture)ob;
		            curpicture(onpi);
		         } else {
		            Object obj1 = show.getUserData();
		            picture pic = (picture)obj1;
		            curpicture(pic);
		            section.getSelectionModel().select(1);
		         }
	        }
	        event.consume();
		}
	}
    public void curpicture(picture pic) {
    	login use = getModel().nowloginuser();
    	collection group = use.nowgroupp();
    	int num = group.phindex(pic);
    	curpicture(num, true);
    }
    public void curpicture(int curnum, boolean yesal) {
    	login login= getModel().nowloginuser();
    	collection files= login.nowgroupp();
    	curnum= files.curnum(curnum);
		int origin= files.getn();
		picture create = files.curp();
    	if (curnum> -1) {
	    	if (list.size()>0) {
	    		if (origin==-1) {
			        BorderPane now = (BorderPane)list.get(curnum);
			        now.setStyle(indigo);
	    		}else if (origin==curnum) {
	    			BorderPane neww = (BorderPane)list.get(curnum);
			        neww.setStyle(indigo);
		    	}else {
		    		 BorderPane pre = (BorderPane)list.get(origin);
				     BorderPane rap = (BorderPane)list.get(curnum);
				     pre.setStyle(white);
				     rap.setStyle(indigo);
			       
		    	}
	    	}
	        tlist.setItems(create.gtreps());
	        if (create.gtreps().size() > 0) {
	        	tlist.getSelectionModel().select(0);
	        }
	        if (yesal) {
	        	page.setPageCount(files.length());
	        	page.setCurrentPageIndex(curnum);
	        }
    	}
    }
    
    public void doLogoff() {
	    logad();
	}
    public void doExit() {
		Platform.exit();
	}
    public void gotoAlbumList() {
    	thirdsec();
    }
	@Override
	public void changed(ObservableValue<? extends Tab> arg0, Tab arg1, Tab arg2) {
		if (arg2.getText().equals("Photo")) {
	    	login login = getModel().nowloginuser();
	    	collection label = login.nowgroupp();
        	picture nowpic = label.curp();
        	if (nowpic==null) {
        		page.setVisible(false);
        	}else {
        		page.setVisible(true);
	        	int num = label.phindex(nowpic);
				page.setPageCount(label.length());
				page.setCurrentPageIndex(num);
        	}
		}
	}
    public void doHelp() {
		asisst(phelp);
	}
    public void deleteclicked() {
    	login member = getModel().nowloginuser();
    	collection combine = member.nowgroupp();
    	picture onpic =combine.curp();
    	int number = tlist.getSelectionModel().getSelectedIndex();
    	onpic.removeta(number);
    	tlist.refresh();
	}
    public void createClicked() {
    	String tagn = tname.getText().trim();
    	String tagv = tval.getText().trim();
    	if (tagn.length()>0 && tagv.length()>0) {
	    	login login = getModel().nowloginuser();
	    	collection group = login.nowgroupp();
	    	picture onpic = group.curp();
	    	boolean truth = onpic.maketa(tagn, tagv);
			if (truth) {
                tlist.refresh();
                tval.setText("");
                tname.setText("");
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("wrong");
                alert.setHeaderText("not finished");
                alert.setContentText("same pair.enter again.");
                alert.showAndWait();
            }
    	}else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("wrong");
            alert.setHeaderText("not done");
            alert.setContentText("tag name or value empty, enter again");
            alert.showAndWait();
    	}
    	
    }
}