package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class tagrules implements Serializable {
	private static final long serialVersionUID = 1;
    private ObservableList<pictags> listTags;
    private ArrayList<pictags> listOfTagsToKeep;
    public void washing(boolean yes) {
		if (!(yes)) {
			listTags= FXCollections.observableList(listOfTagsToKeep);
			listOfTagsToKeep = null;
		}else {
			listOfTagsToKeep = new ArrayList<>(listTags);
			listTags = null;
			
		}
	}
    public tagrules() {
	    listTags = FXCollections.observableArrayList();
	}
    public ObservableList<pictags> getTags() {
		return listTags;
	}
    public boolean addTag(String tagName, String tagValue) {
    	pictags t = new pictags(tagName, tagValue);
        return asisster.addOrRetrieveOrderedList(listTags, t);
    }
}