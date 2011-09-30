package com.base2.javaone.demos.simple.cdi;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.weld.UsingWeld;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.weld.WeldAnnotatedEmbedderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromPath;

/**
 * CDI Injectable JUnit runner
 *
 * @author aaronwalker
 */
@RunWith(WeldAnnotatedEmbedderRunner.class)
@Configure()
@UsingWeld
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = false, ignoreFailureInView = true)
public class TestRpnCalculator extends InjectableEmbedder {

    @Test
    public void run() throws Throwable {
        injectedEmbedder().runStoriesAsPaths(storyPaths());
    }

    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromPath(ConfigurationProducer.STORY_DIR),"rpncalculator.story","");
    }
}
