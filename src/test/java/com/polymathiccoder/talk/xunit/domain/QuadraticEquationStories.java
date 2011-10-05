package com.polymathiccoder.talk.xunit.domain;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_ORDER;

@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyReporterBuilder = QuadraticEquationStories.ReportBuilder.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = false, ignoreFailureInView = false, stepsFactory = true)
@UsingSteps(instances = {QuadraticEquationStories.QuadraticEquationSteps.class})
public class QuadraticEquationStories implements Embeddable {
    private transient Embedder embedder;

    @Override
    public void useEmbedder(final Embedder embedder) {
        this.embedder = embedder;
    }

    @Test
    @Override
    public void run() { // NOPMD
        embedder.runStoriesAsPaths(
        	new StoryFinder().findPaths(
        		CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
                asList("**/*.story"),
                asList("")));
    }

    //Reporters
    public static class ReportBuilder extends StoryReporterBuilder {
        public ReportBuilder() {
            super();
            this.withFormats(org.jbehave.core.reporters.Format.CONSOLE,
                    org.jbehave.core.reporters.Format.TXT,
                    org.jbehave.core.reporters.Format.HTML,
                    org.jbehave.core.reporters.Format.XML);
        }
    }

    //Steps
    public static class QuadraticEquationSteps {
    	private transient QuadraticEquation quadraticEquation;
    	private transient List<Double> actualRoots;

    	@Given("the quadratic equation ax^2 + bx + c = 0, such that a = $a, b = $b, and c = $c")
    	@Aliases(values = {"the quadratic equation $ax^2 + $bx + $c = 0"})
    	public void aQuadratic(final double a, final double b, final double c) { // NOPMD
    	    quadraticEquation = new QuadraticEquation(a, b, c);
    	}

    	@When("the equation is solved")
    	public void getRoots() {
    		actualRoots = quadraticEquation.solve();
    	}

    	@Then("it yields the following real solutions: $expectedRoots")
    	public void getRoot(final List<Double> expectedRoots) {
    	    assertReflectionEquals("The method rerturned the wrong solutions", expectedRoots.toArray(), actualRoots.toArray(), LENIENT_ORDER);
    	}

    	@Then("it yields no real solution")
    	public void getRoot() {
    		assertEquals("The method should not have returned any solution", 0, actualRoots.size());
    	}
    }
}
