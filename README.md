#JavaOne 2011 Session Demos

##Java EE Behave!!!! - Behavior-Driven Development with Java EE

### GitHub Setup
1. Fork Repo into your github account
2. Run on your local machine:

		git clone git@github.com:<github-username>/JavaOne2011.git
		cd JavaOne2011
		git remote add upstream git@github.com:aaronwalker/JavaOne2011.git

You can now pull/rebase from the upstream repository to pickup any upstream changes

	git pull upstream master

And push now push these changes to your forked repository

	git push origin master

Also you should run the following

    git config --global user.name "Your Name"
    git config --global user.email <your_email>

#### How to Build
Requires maven 3.0+  and Java 6

To build and run the demos:

	cd JavaOne2011
	mvn clean install

To run the web demo run:

    mvn clean install -Dintegration-tests=true

This will download JBoss AS 7.0.2.Final to /tmp/jboss-as7/jboss-as-7.0.2.Final by default and due to a
the JBoss AS7 default config doesn't have JMS support so first time you run the test they will fail.

You need to need to enable the Jboss AS7 preview configuration by:

    cp /tmp/jboss-as7/jboss-as-7.0.2.Final/standalone/configuration/standalone-preview.xml /tmp/jboss-as7/jboss-as-7.0.2.Final/standalone/configuration/standalone.xml

This enables the hornetq JMS messaging subsystem which is required by the test