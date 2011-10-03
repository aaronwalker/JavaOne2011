package com.base2.javaone.demo;

import org.codehaus.cargo.container.configuration.LocalConfiguration;
import org.codehaus.cargo.container.deployable.Deployable;
import org.codehaus.cargo.container.deployable.File;
import org.codehaus.cargo.container.deployable.SAR;
import org.codehaus.cargo.container.jboss.*;
import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.io.StoryFinder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.base2.javaone.demo.ConfigurationProducer.BASE_DIR;
import static org.jbehave.core.io.CodeLocations.codeLocationFromPath;

/**
 * JUnit BDD Story Runner
 *
 * @author aaronwalker
 */
public abstract class CargoBehaviourRunner extends InjectableEmbedder {

    protected static final String CONTAINER_HOME = System.getProperty("jboss.home", "/tmp/jboss-as7/jboss-as-7.0.2.Final");
    protected static final String CONFIGURATION_HOME = CONTAINER_HOME + "/standalone";

    protected JBoss7xInstalledLocalContainer container;

    @Before
    public void startContainer() throws IOException {
        JBoss7xExistingLocalConfiguration configuration = new JBoss7xExistingLocalConfiguration(CONFIGURATION_HOME);
        deploy(configuration);
        container = new JBoss7xInstalledLocalContainer(configuration);
        container.setHome(CONTAINER_HOME);

        try {
            System.out.println("Starting JBoss");
            container.start();
            Thread.sleep(5000);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @After
    public void stopContainer() {
        System.out.println("Stopping JBoss");
        container.stop();
    }

    @Test
    public void run() throws Throwable {
        injectedEmbedder().runStoriesAsPaths(storyPaths());
    }

    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromPath(ConfigurationProducer.STORY_DIR),defaultStoryPath(),"");
    }

    protected String defaultStoryPath() {
        return "**/*.story";
    }

    protected void deploy(LocalConfiguration configuration) throws IOException {
        for (Deployable d : defaultDeployable()) {
            configuration.addDeployable(d);
        }
    }

    protected abstract List<Deployable> defaultDeployable();
}
