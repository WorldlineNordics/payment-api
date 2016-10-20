# To build / package startkit

## VERSION
Set the version to use in (according to version conventions): 
gradle.properties

## BUILD 
Install gradle: http://gradle.org

Build (just build the jars test and zip, sort of like "beta"):

    gradlew clean build

Release (just like build but also copies the built artifacts to "release"):

    gradlew packageRelease

This creates all needed artifacts (under build/libs):
+ PaymentPageAPI-X.Y.jar
+ PaymentPageAPI-X.Y-javadoc.jar
+ PaymentPageAPI-X.Y-sources.jar
+ PaymentPage_StartKit_DigitalRiver-X.Y.zip

From command line this is just to verify that the release is assebled correctly. 

## RELEASE
Jenkins builds a release (.zip) on each checkin

Version is combined from gradle.properties version and jenkins build number

Jenkins published built zip on artifactory/libs-release-local

Build versions are communicated to qa

Once QA is happy with the release. They mark corresponding jenkins build with QA-approved (promote feature)

During after rrc the "good" release is promoted again (if rrc says "go") to "RRC go"

Run jenkins job PaymentPageAPI-publish. This pushes the zip to artifactory/api-release

CM takes it from here (this can also be automated)
