package com.polymathiccoder.talk.xunit.misc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SlowAndFastTest {
    @Category(SlowTests.class)
    @Test
    public void waitASecond() throws InterruptedException {
    	Thread.sleep(1000);
    	assertTrue("This is a smoke test!", true); // NOPMD
    }

    @Category(FastTests.class)
    @Test
    public void hurryUp() {
        assertTrue("This is a smoke test!", true); // NOPMD
    }
}
