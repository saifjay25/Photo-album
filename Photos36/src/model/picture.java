package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class picture implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String pictype= "png";
    private static final int wsize = 120;
    private static final int hsize= 120;
    private static final String	trail = "thumbnails";
    static {
        new File(trail).mkdir();
    }
    public static String gtxt(String input) {
        return trail + "/" + input + "." + pictype;
    }
    public static picture makepic(String txttile, File directory) {
        if (directory==null) {
            directory = new File(txttile);
        }
        long endchange= directory.lastModified();
        String smalltitle= directory.getName();
        String finger = String.valueOf(UUID.randomUUID());
        boolean truth = makefinger(txttile, gtxt(finger), wsize, hsize, pictype);
        int position= smalltitle.indexOf('.');
        if (position>0) {
            smalltitle = smalltitle.substring(0, position);
        }
        if (truth) {
            return new picture(txttile, finger, smalltitle, endchange);
        }
        return null;
    }
    public String gtxttitle() {
        return fileName;
    }
    public String retrivename() {
        return caption;
    }
    private static boolean makefinger(String txttitle, String thfname, int wsize, int hsize, String tfor) {
        boolean truth = true;
        Image picture = null;
        try {
            picture = new Image(new FileInputStream(txttitle), wsize, hsize, true, true);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (picture==null) {
        	truth = false;
        }else {
        	BufferedImage bpic = SwingFXUtils.fromFXImage(picture, null);
            try {
                FileOutputStream ter = new FileOutputStream(thfname);
                ImageIO.write(bpic, tfor, ter);
                ter.close();
            }
            catch (IOException e) {
                truth = false;
                e.printStackTrace();
            }
        }
        return truth;
    }
    private String caption;
    private long dateOfPhoto;
    private String fileName;
    private String finger;
    private ObservableList<pictags> listTags;
    private ArrayList<pictags> listOfTagsToKeep;
    public void rinsing(boolean keep) {
        if (!(keep)) {
        	listTags = FXCollections.observableList(listOfTagsToKeep);
            listOfTagsToKeep = null;
        }else {
        	listOfTagsToKeep = new ArrayList<>(listTags);
            listTags = null;
        }
    }
    private picture(String txttitle, String pic, String write, long time) {
    	finger= pic;
        fileName= txttitle;
        dateOfPhoto= time;
        listTags= FXCollections.observableArrayList();
        caption = write;
        listOfTagsToKeep = null;
    }
    public ObservableList<pictags> gtreps() {
        return listTags;
    }
    private String gtxt() {
        return gtxt(finger);
    }
    public picture(picture pic) {
        this.fileName = pic.fileName;
        this.finger = String.valueOf(UUID.randomUUID());
        this.dateOfPhoto = pic.dateOfPhoto;
        this.caption = pic.caption;
        listTags= FXCollections.observableArrayList();
        for (pictags t:pic.listTags) {
            listTags.add(new pictags(t));
        }
        try {
            Files.copy(new File(pic.gtxt()).toPath(), new File(gtxt()).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void makename(String title) {
        this.caption = title;
    }
    public long pictime() {
        return dateOfPhoto;
    }
    public String gpictime() {
        return edatet(dateOfPhoto);
    }
    public boolean maketa(String ttitle, String tval) {
        pictags teg = new pictags(ttitle, tval);
        return asisster.addOrRetrieveOrderedList(listTags, teg);
    }
    public void removeta(int num) {
        if (num>=0 && num<listTags.size()) {
            listTags.remove(num);
        }
    }
    public boolean within(daterules time) {
        ZoneId ident = ZoneId.systemDefault();
        LocalDate begin= time.gfirstd();	
        long first= begin.atStartOfDay(ident).toEpochSecond();
        LocalDate last= time.glastd().plusDays(1);		
        long apoc= last.atStartOfDay(ident).toEpochSecond();
        Date day= new Date(this.dateOfPhoto);
        LocalDateTime date = day.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        long right= date.atZone(ident).toEpochSecond();
        return right >= first && right < apoc;
    }
    public BorderPane gpicvi(EventHandler<MouseEvent> grip, String original) {
        Image pic = new Image("File:"+gtxt(), 0, 0, false, false);
        ImageView view = new ImageView(pic);
        view.setFitHeight(hsize);
        view.setFitWidth(wsize);
        view.setOnMouseClicked(grip);
        view.setUserData(this);
        picture dispic = this;
        TextField txtbar = new TextField(retrivename());
        txtbar.setPrefWidth(wsize);
        txtbar.setOnAction(event -> {TextField textField = (TextField) event.getSource();
            String replace = textField.getText().trim();
            if (replace.length()!=0) {
            	dispic.makename(replace);
            } else {
            	textField.setText(dispic.retrivename());
            }
        });
        VBox xx = new VBox(4);
        xx.getChildren().addAll(view, txtbar, new Label(gpictime()));
        BorderPane rap = new BorderPane(xx);
        rap.setStyle(original);
        return rap;
    }
    public static String edatet(long second) {
        LocalDateTime combo = LocalDateTime.ofInstant(Instant.ofEpochMilli(second), ZoneId.systemDefault());
        DateTimeFormatter normalize = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return combo.format(normalize);
    }
    public Node gnpic(EventHandler<MouseEvent> handler) {
    	Image picee = null;
        ImageView pic;
        try {
            picee = new Image(new FileInputStream(gtxttitle()));
        }catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        pic = new ImageView();
        pic.setFitHeight(400);
        pic.setFitWidth(600);
        pic.setSmooth(true);
        pic.setPreserveRatio(true);
        pic.setImage(picee);
        pic.setOnMouseClicked(handler);
        VBox xx = new VBox(2);
        xx.getChildren().addAll(new Label("Caption name: " + retrivename() + ". date of photo is " + gpictime()), pic);
        return xx;
    }
}