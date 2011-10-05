package com.polymathiccoder.talk.xunit.domain; // NOPMD

import static ch.lambdaj.collection.LambdaCollections.with;
import static com.google.common.base.Preconditions.checkArgument;
import static com.polymathiccoder.talk.xunit.domain.RiverCrossing.RiverCrossingErrorMessages.ERROR_BOTH_OF_THE_SAME_KIND;
import static com.polymathiccoder.talk.xunit.domain.RiverCrossing.RiverCrossingErrorMessages.ERROR_ONE_ATE_THE_OTHER;
import static com.polymathiccoder.talk.xunit.domain.RiverCrossing.Thing.canEatOneAnother;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import java.util.ArrayList;

import org.apache.commons.lang3.tuple.Pair;

public class RiverCrossing {
	private transient final Pair<ArrayList<Thing>, ArrayList<Thing>> riverBanks;

	public RiverCrossing() {
		riverBanks = Pair.of(new ArrayList<Thing>(asList(Thing.values())), new ArrayList<Thing>());
	}

	public Pair<ArrayList<Thing>, ArrayList<Thing>> getRiverBanks() {
		return riverBanks;
	}

	public void leaveBehind(final Thing first, final Thing second) {
		checkArgument(!first.equals(second), ERROR_BOTH_OF_THE_SAME_KIND);

		if (canEatOneAnother(first, second)) {
			throw new RuntimeException(ERROR_ONE_ATE_THE_OTHER); // NOPMD
		} else {
			final Thing toTake = Thing.getTheThird(first, second);
			riverBanks.getLeft().remove(toTake);
			riverBanks.getRight().add(toTake);
		}
	}

	public enum Thing {
		WOLF, GOAT, CABBAGE;

		public static boolean canEatOneAnother(final Thing first, final Thing second) {
			boolean canEat = false; // NOPMD
			if (first == WOLF) {
				if (second == GOAT) {
					canEat = true;
				}
			} else if (first == GOAT) {
				if (second == CABBAGE || second == WOLF) {
					canEat = true;
				}
			} else if (first == CABBAGE && second == GOAT) {
				canEat = true;
			}

			return canEat;
		}

		@SuppressWarnings("unchecked")
		public static Thing getTheThird(final Thing first, final Thing second) {
			return with(asList(Thing.values())).first(allOf(not(first), not(second)));
		}
	}

	public final class RiverCrossingErrorMessages {
	    public static final String ERROR_BOTH_OF_THE_SAME_KIND = "BIG FAIL: You only have one of a kind!"; // NOPMD
	    public static final String ERROR_ONE_ATE_THE_OTHER = "BIG FAIL: One ate the other!"; // NOPMD

	    private RiverCrossingErrorMessages() {
	        throw new UnsupportedOperationException();
	    }
	}
}
