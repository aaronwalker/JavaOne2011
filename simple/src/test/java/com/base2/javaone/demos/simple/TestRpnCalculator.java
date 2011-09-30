package com.base2.javaone.demos.simple;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.ANSI_CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.XML;

/**
 * JBehave JUnit runner
 *
 * @author aaronwalker
 */
public class TestRpnCalculator extends JUnitStories {

    public TestRpnCalculator() {
        configuredEmbedder()
                .embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true)
                .useThreads(1)
                .useStoryTimeoutInSecs(60);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryControls(new StoryControls()
                        .doDryRun(false)
                        .doSkipScenariosAfterFailure(false))
                .useStepPatternParser(new RegexPrefixCapturingPatternParser("%"))
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(this.getClass()))
                        .withDefaultFormats()
                        .withFormats(ANSI_CONSOLE, HTML, XML)
                        .withFailureTrace(true)
                        .withFailureTraceCompression(true));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
                new RPNCalculatorSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(RpnCalculator.class), "**/*.story", "");
     }
}
