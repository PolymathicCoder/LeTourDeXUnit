package com.polymathiccoder.talk.xunit.domain; // NOPMD

import static com.polymathiccoder.talk.xunit.domain.RiverCrossing.RiverCrossingErrorMessages.ERROR_ONE_ATE_THE_OTHER;
import static com.polymathiccoder.talk.xunit.domain.RiverCrossing.RiverCrossingErrorMessages.ERROR_BOTH_OF_THE_SAME_KIND;
import static com.polymathiccoder.talk.xunit.domain.RiverCrossing.Thing.canEatOneAnother;
import static com.polymathiccoder.talk.xunit.domain.RiverCrossing.Thing.getTheThird;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assume.assumeTrue;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_ORDER;

import org.junit.Before;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.polymathiccoder.talk.xunit.domain.RiverCrossing.Thing;

@RunWith(Theories.class)
public class RiverCrossingTheory {
	private transient RiverCrossing riverCrossing;

	@Rule
    public transient final ExpectedException exception = ExpectedException.none();

	@DataPoints
	public static Thing[] things = Thing.values();

	@Before
	public void setUp() {
		riverCrossing = new RiverCrossing();
	}

	@Theory
	public void leaveBehind_twoDifferentThingsThatCannotEatEachOther_takenToTheRightBank(final Thing first, final Thing second) { // NOPMD
		assumeTrue(!first.equals(second) && !canEatOneAnother(second, first));
		riverCrossing.leaveBehind(first, second);
		assertReflectionEquals("Some things should not be on the left side", riverCrossing.getRiverBanks().getLeft().toArray(), new Thing[] {first, second}, LENIENT_ORDER);
		assertReflectionEquals("Some things should not be on the right side", riverCrossing.getRiverBanks().getRight().toArray(), new Thing[] {getTheThird(first, second)}, LENIENT_ORDER);
	}

	@Theory
	public void leaveBehind_twoDifferentThingsThatCanEatOneAnother_exceptionThrown(final Thing first, final Thing second) { // NOPMD
		assumeTrue(!first.equals(second) && canEatOneAnother(second, first));
		exception.expect(RuntimeException.class);
		exception.expectMessage(equalTo(ERROR_ONE_ATE_THE_OTHER));
		riverCrossing.leaveBehind(first, second);
	}

	@Theory
	public void leaveBehind_twoThingsofTheSameKindThatCannotEatEachOther_exceptionThrown(final Thing first, final Thing second) { // NOPMD
		assumeTrue(first.equals(second));
		exception.expect(RuntimeException.class);
		exception.expectMessage(equalTo(ERROR_BOTH_OF_THE_SAME_KIND));
		riverCrossing.leaveBehind(first, second);
	}
}
