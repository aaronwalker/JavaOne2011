package com.base2.javaone.demo;

import org.codehaus.cargo.container.deployable.Deployable;
import org.codehaus.cargo.container.deployable.WAR;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.weld.UsingWeld;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.junit.weld.WeldAnnotatedEmbedderRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer Story Runner
 *
 * @author aaronwalker
 */
@RunWith(WeldAnnotatedEmbedderRunner.class)
@Configure()
@UsingWeld
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = false, ignoreFailureInView = true)
public class TestCustomerBehavior extends CargoBehaviourRunner {

    private static final String WAR_FILE = ConfigurationProducer.BASE_DIR + "demo-web/target/demo-web.war";

    @Override
    protected List<Deployable> defaultDeployable() {
        List<Deployable> deployables = new ArrayList<Deployable>();
        deployables.add(new WAR(WAR_FILE));
        return deployables;
    }

    protected String defaultStoryPath() {
        return "customer.story";
    }

}
