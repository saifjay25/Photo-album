package model;

import java.io.Serializable;

/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class pictags implements Comparable<pictags>, Serializable {
	private static final long serialVersionUID = 1;
    private String tagValue;
    private String tagName;
    @Override
    public int compareTo(pictags latch) {
    	int truth = tagName.compareToIgnoreCase(latch.tagName);
    	return (truth!=0) ? truth : tagValue.compareToIgnoreCase(latch.tagValue);
    }
    public pictags(pictags leech) {
        tagName = leech.tagName;
        tagValue = leech.tagValue;
    }
    public pictags(String label, String represent) {
        tagName =label;
        tagValue = represent;
    }
    public String toString() {
    	return tagName + "=" + tagValue;
    }
}