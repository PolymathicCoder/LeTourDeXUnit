package com.polymathiccoder.talk.xunit.misc;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

interface FastTests {
}
interface SlowTests {
}

@RunWith(Categories.class)
@IncludeCategory(SlowTests.class)
@ExcludeCategory(FastTests.class)
@SuiteClasses({SlowTest.class, SlowAndFastTest.class, FastTest.class})
public class SlowTestSuite { // NOPMD
}