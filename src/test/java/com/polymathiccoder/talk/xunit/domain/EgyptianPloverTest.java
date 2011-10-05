package com.polymathiccoder.talk.xunit.domain; // NOPMD

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EgyptianPloverTest {
	@Mock
	private transient AbstractNileCrocodile nileCrocodile;
	@InjectMocks
	@Spy
	private transient EgyptianPlover egyptianPlover;

	@Before
	public void setUp() {
		reset(nileCrocodile);
	}

	@Test
	public void test_nileCrocodileHasOpenMouthWithLeeches_egyptianPloverPickedLeeches() { // NOPMD
	    stub(nileCrocodile.isMouthOpen()).toReturn(true);

		final boolean ate = egyptianPlover.feed();

		verify(nileCrocodile, times(1)).isMouthOpen();
		verify(egyptianPlover, times(1)).findLeeches();
		verify(egyptianPlover, times(1)).pickLeeches();
		verify(egyptianPlover, times(1)).flyAway();

		assertTrue("The Egyptian Plover didn't eat despite that the Nile Crocodile's mouth is open and that there were leeches in it", ate);
	}

	@Test
	public void test_nileCrocodileHasOpenMouthWithNoLeeches_egyptianPloverPickedLeeches() { // NOPMD
		doThrow(TheyAteAllTheLeechesException.class).when(egyptianPlover).findLeeches();
		stub(nileCrocodile.isMouthOpen()).toReturn(true);

		final boolean ate = egyptianPlover.feed();

		verify(nileCrocodile, times(1)).isMouthOpen();
		verify(egyptianPlover, times(1)).findLeeches();
		verify(egyptianPlover, never()).pickLeeches();
		verify(egyptianPlover, times(1)).flyAway();

		assertFalse("The Egyptian Plover ate despite that the Nile Crocodile's mouth is open and that there were no leeches in it", ate);
	}

	@Test
	public void test_nileCrocodileHasClosedMouth_egyptianPloverDidNotPickLeeches() { // NOPMD
		stub(nileCrocodile.isMouthOpen()).toReturn(false);

		final boolean ate = egyptianPlover.feed();

		verify(nileCrocodile, times(1)).isMouthOpen();
		verify(egyptianPlover, never()).findLeeches();
		verify(egyptianPlover, never()).pickLeeches();
		verify(egyptianPlover, times(1)).flyAway();

		assertFalse("The Egyptian Plover ate despite that the Nile Crocodile's mouth is closed", ate);
	}
}
