package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class login implements Comparable<login>, Serializable {
	private static final long serialVersionUID = 1;
    public static final String administrator = "admin";
    private tagrules tags;
    private String username;
    private ObservableList<collection> listAlbums;
    private daterules dates;
    private ArrayList<collection> listOfAlbumsToKeep;
    private collection currentAlbum;
    public login(String log) {
    	currentAlbum = null;
		username= log;
		listAlbums= FXCollections.observableArrayList();
		tags = new tagrules();
		listOfAlbumsToKeep = null;
		dates = new daterules();
	}
    public collection nowgroupp() {
		return currentAlbum;
	}
    public void setgroup(collection num) {
        this.currentAlbum = num;
    }
    public void rinseup(boolean yes) {
		if (!(yes)) {
			listAlbums = FXCollections.observableList(listOfAlbumsToKeep);
			listOfAlbumsToKeep = null;
			for (collection a:listAlbums) {
				a.rinse(false);
			}
		}else {
			listOfAlbumsToKeep = new ArrayList<>(listAlbums);
			listAlbums = null;
			for (collection a:listOfAlbumsToKeep) {
				a.rinse(true);
			}
		}
		tags.washing(yes);
	}
    public tagrules greps() {
        return tags;
    }
    public void removetcon(int num) {
		ObservableList<pictags> review = tags.getTags();
		if (review.size() > 0 && num >= 0 && num < review.size()) {
			review.remove(num);
		}
	}
    public daterules gtime() {
        return dates;
    }
    public ObservableList<collection> glal() {
		return listAlbums;
	}
    public void changegroup(collection group) {
        IntStream.range(0, listAlbums.size()) .filter(i -> listAlbums.get(i).getAlbumName().equalsIgnoreCase(group.getAlbumName())).findFirst().ifPresent(i -> listAlbums.remove(i));
		listAlbums.add(group);
	}
    public String glogin() {
		return username;
	}
	public collection dgroup(String grouptit) {
		return asisster.delete(listAlbums, grouptit, (t,k)->t.getAlbumName().equalsIgnoreCase(k));
	}
    public collection ggroup(String grouptit) {
		return asisster.get(listAlbums, grouptit, (t,k)->t.getAlbumName().equalsIgnoreCase(k));
	}
    public collection creategroup(String login) {
		collection group = new collection(login);
        boolean find = IntStream.range(0, listAlbums.size()) .anyMatch(i -> listAlbums.get(i).getAlbumName().equalsIgnoreCase(group.getAlbumName()));
		if (find) {
			return null;
		}else {
			listAlbums.add(group);
			return group;
		}
	}
    public void cpic(picture pic, String tgroup) {
		picture newOne = new picture(pic);
		collection targetAlbum = ggroup(tgroup);
		targetAlbum.createp(newOne);
	}
    public void mpic(picture pic, String tcollection) {
		collection targetAlbum = ggroup(tcollection);
		currentAlbum.removepho(pic);
		targetAlbum.createp(pic);
	}
    public List<picture> setime() {
		List<picture> view = new ArrayList<>();
		for (collection a:listAlbums) {
            if (!a.getAlbumName().equalsIgnoreCase(collection.albumdate) && !a.getAlbumName().equalsIgnoreCase(collection.albumtag)) {
                view.addAll(a.looks(dates));
            }
        }
		return view;
	}
    @Override
	public String toString() {
		return username;
	}
    @Override
	public int compareTo(login argument) {
		return username.compareToIgnoreCase(argument.username);
	}
    public List<picture> setag() {
		List<picture> view = new ArrayList<>();
		for (collection a : listAlbums) {
            if (!a.getAlbumName().equalsIgnoreCase(collection.albumdate) && !a.getAlbumName().equalsIgnoreCase(collection.albumtag)) {
                view.addAll(a.lookt(tags));
            }
        }
		return view;
	}
}
