package com.base2.javaone.demos.simple.cdi;

import org.jbehave.core.annotations.weld.WeldConfiguration;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;

import javax.enterprise.inject.Produces;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

/**
 * Jbehave CDI configuration producer
 *
 * @author aaronwalker
 */
public class ConfigurationProducer {

    public static final String STORY_DIR = "simple-cdi/src/main/stories";

    @Produces
    @WeldConfiguration
    public Configuration configure() {
        return new MostUsefulConfiguration()
                .useStoryControls(new StoryControls()
                        .doDryRun(false)
                        .doSkipScenariosAfterFailure(false))
                .useStepPatternParser(new RegexPrefixCapturingPatternParser("%"))
                .useStoryLoader(new LoadFromRelativeFile(CodeLocations.codeLocationFromPath(STORY_DIR)))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(this.getClass()))
                        .withDefaultFormats()
                        .withFormats(ANSI_CONSOLE, HTML, XML)
                        .withFailureTrace(true)
                        .withFailureTraceCompression(true));
    }
}
