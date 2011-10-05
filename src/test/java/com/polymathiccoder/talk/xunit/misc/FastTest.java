package com.polymathiccoder.talk.xunit.misc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FastTests.class)
public class FastTest {
    @Test
    public void hurryUp() {
        assertTrue("This is a smoke test!", true); // NOPMD
    }

    @Test
    public void moveIt() {
        assertTrue("This is a smoke test!", true); // NOPMD
    }
}
