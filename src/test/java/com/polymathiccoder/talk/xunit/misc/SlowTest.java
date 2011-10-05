package com.polymathiccoder.talk.xunit.misc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(SlowTests.class)
public class SlowTest {
    @Test
    public void waitASecond() throws InterruptedException {
    	Thread.sleep(1000);
    	assertTrue("This is a smoke test!", true); // NOPMD
    }

    @Test
    public void waitAnotherSecond() throws InterruptedException {
    	Thread.sleep(1000);
    	assertTrue("This is a smoke test!", true); // NOPMD
    }
}