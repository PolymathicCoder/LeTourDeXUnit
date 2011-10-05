package com.polymathiccoder.talk.xunit.domain;

public class EgyptianPlover {
	private transient AbstractNileCrocodile nileCrocodile;

	public boolean feed() {
		boolean ate = true;
		if (nileCrocodile.isMouthOpen()) {
			try {
				findLeeches();
				pickLeeches();
			} catch(TheyAteAllTheLeechesException theyAteAllTheLeechesException) { // NOPMD
				ate = false;
			}
		} else {
			ate = false;
		}
		flyAway();
		return ate;
	}

	public void findLeeches() throws TheyAteAllTheLeechesException { // NOPMD
	}

	public void pickLeeches() { // NOPMD
	}

	public void flyAway() { // NOPMD
	}
}

abstract class AbstractNileCrocodile {
	public abstract boolean isMouthOpen();
}

class TheyAteAllTheLeechesException extends RuntimeException { // NOPMD
	private static final long serialVersionUID = 1L;
}
