package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class collection implements Comparable<collection>, Serializable {
	private static final long serialVersionUID = 1L;
	public static String albumdate= " Date Search Result";
    public static String albumtag	= " Tag Search Result";
    private String alnoreomove;
    private  SimpleStringProperty tilast;
    private  SimpleStringProperty altitle;
    private  SimpleIntegerProperty phnum;
    private  SimpleStringProperty tibegin;
    public String getAlbumName() {
        return altitle.get();
    }
    public void setalb(String title) {
        this.altitle.set(title);
    }
    public void setPhoc(int num) {
        this.phnum.set(num);
    }
    public int getPhotoCount() {
        return phnum.get();
    }
    public String getStartTime() {
        return  tibegin.get();
    }
    public void firsttime(String first) {
        this. tibegin.set(first);
    }
    public void lasttime(String last) {
        this.tilast.set(last);
    }
    public String getEndTime() {
        return tilast.get();
    }
    public collection(String alti) {
		altitle = new SimpleStringProperty(alti);
	    phnum= new SimpleIntegerProperty(0);
	    tilast = new SimpleStringProperty("none");
	    tibegin= new SimpleStringProperty("none");
	    nowpho 	= -1;
	    photoList = new ArrayList<>();
	}
    public void rinse(boolean keep) {
		if (keep) {
			alnoreomove = getAlbumName();
			tibegin = null;
			altitle= null;
			tilast= null;
			phnum= null;
		}else {
			altitle = new SimpleStringProperty(alnoreomove);
		    phnum= new SimpleIntegerProperty();
		    tilast= new SimpleStringProperty();
		    tibegin = new SimpleStringProperty();
		    alnoreomove = null;
		}
		for (picture p: photoList) {
			p.rinsing(keep);
		}
	}
    private int nowpho;
    private List<picture> photoList;
    public collection(String alti, List<picture> phol) {
		this(alti);
	    for (picture p : phol) {
		    picture born = new picture(p);
		    photoList.add(born);
	    }
		if (photoList.size()>0) {
			nowpho = 0;
		}
	}
    public int curnum(int get) {
		nowpho = get;
		return restart();
	}
    private int restart() {
		if (photoList.size()==0) {
		    nowpho 	= -1;
		}else {
			if (nowpho < 0) {
				nowpho = 0;
			}else if (nowpho > photoList.size() - 1) {
				nowpho  = photoList.size() - 1;
			}
		}
		return nowpho;
	}
    public int getn() {
		return 	restart();
	}
    public void createp(picture pic) {
    	photoList.add(pic);
    	nowpho = photoList.size() - 1;
	}
    public int phindex(picture pic) {
        return IntStream.range(0, photoList.size()).filter(i -> pic == photoList.get(i)).findFirst().orElse(-1);
    }
    public picture altopic(int num) {
    	if (num < photoList.size() && num>=0) {
    		return photoList.get(num);
    	}
    	return null;
    }
    public int createphoal(picture nowpic, picture create) {
    	int num;
        if (nowpic != null) {
            num = IntStream.range(0, photoList.size()) .filter(i -> nowpic == photoList.get(i)) .findFirst() .orElse(-1);
        } else num = photoList.size();
		photoList.add(num, create);
    	return num;
    }
    public List<picture> retrieveplist() {
    	return photoList;
    }
    public int length() {
    	return photoList.size();
    }
    public picture curp() {
    	restart();
        return nowpho >= 0 ? photoList.get(nowpho) : null;
    }
    public int removepho(picture pic) {
    	int num= -1;
    	int i=0;
    	for (i=0; i<photoList.size(); i++) {
    		if (photoList.get(i)==pic) {
    			num = i;
    			photoList.remove(num);
    			break;
    		}
    	}
    	return num;
    }
    @Override
	public int compareTo(collection group) {
		return getAlbumName().compareToIgnoreCase(group.getAlbumName());
	}
    @Override
	public String toString() {
		return getAlbumName();
	}
    public List<picture> lookt(tagrules tname) {
		BiPredicate<picture,tagrules> pre = (p, c)->asisster.search(p.gtreps(), c.getTags(), (t1,t2)->t1.compareTo(t2)==0);
		return asisster.filter(photoList, tname, pre);
	}
    public void timer() {
		if (photoList.size()!=0) {
			boolean begin = true;
			long hi= 0;
			int counter= 0;
			long low= 0;
			for (picture p: photoList) {
				if (begin) {
					counter = 1;
					low	= p.pictime();
					hi	= p.pictime();
					begin = false;
				}else {
					counter++;
					long rr = p.pictime();
					if (rr < low) {
						low = rr;
					}
					if (rr > hi) {
						hi = rr;
					}
				}
			}
			setPhoc(counter);
			lasttime(picture.edatet(hi));
		    firsttime(picture.edatet(low));
		}else {
			setPhoc(0);
			lasttime("none");
		    firsttime("none");
			
		}
	}
    public List<picture> looks(daterules time) {
		BiPredicate<picture,daterules> bre = picture::within;
		return asisster.filter(photoList,time, bre);
	}
}