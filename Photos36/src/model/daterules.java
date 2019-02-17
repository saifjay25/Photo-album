package model;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class daterules implements Serializable {
	private static final long serialVersionUID = 1;
    private LocalDate first = null;
    private LocalDate last = null;
    public LocalDate gfirstd() {
		return first;
	}
    public void sfirstd(LocalDate startDate) {
		this.first = startDate;
	}
    public LocalDate glastd() {
		return last;
	}
    public void slastd(LocalDate endDate) {
		this.last = endDate;
	}
}