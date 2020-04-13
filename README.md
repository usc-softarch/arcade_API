# arcade_console

This project contains a tentative console-based front-end for ARCADE (https://bitbucket.org/joshuaga/arcade). It also contains a middleware layer to standardize the interfaces offered by ARCADE components.

## Repository Information

This repository has had its history overwritten several times for housekeeping due to its size. Do not attempt to use old commits; use either the latest commit available, or a stable release (once they are made available).

## Compiling the project

To compile this project, you will need Maven (https://maven.apache.org/) and a JDK version 11+.

First, you will need to provide Github authentication credentials to Maven (https://help.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-apache-maven-for-use-with-github-packages). Make sure the access token has the correct permissions (read:packages), and that the correct configuration file is being written to (Maven will sometimes use multiple settings.xml files depending on the context). Then, run `mvn clean` to make sure everything is set up correctly for the first time. Finally, run `mvn package -Dmaven.test.skip=true` to generate the jar packages. It is recommended that packaging generally be done without tests, and that testing be done on a case-by-case basis, as a full round of tests may take a while.

## Running the project

To use, run arcade_console_fat-jar-with-dependencies.jar from the root directory, as follows:

`java -jar ./target/arcade_console_fat-jar-with-dependencies.jar`

This will initiate the execution wizard. Make sure to answer the menu prompts carefully, as the menus do not have extensive validation and exception handling yet.

The default subject_systems for testing are available as a Git-LFS repository on https://github.com/MarceloLaser/arcade_console_test_resources. This repository may take a very long time to download, so it is recommended that a local copy (downloaded from a more efficient server) be used instead. This submodule also includes the mallet and code-maat distributions that have been tested with ARCADE. Newer distributions are not guaranteed to work.

## Documentation

Javadoc generation is currently disabled, but can be enabled and is tied to the package phase. It will be placed inside the target directory along with the jar packages. The javadoc documentation is currently incomplete, and some of it is out of date. However, as a general rule, the documentation should be present for all the most important elements. If it is not, or if it is out-of-date/insufficient, please contact me at marcelo (dot) laser (at) gmail so that I may update it.
