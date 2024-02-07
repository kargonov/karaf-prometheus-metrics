
### install

    maven:repository-add --default -id host.local.repository.2 -idx 3 --snapshots -up always '~/.m2/'
    bundle:install mvn:com.test.bundle/karaf-bundle/1.1.0-SNAPSHOT
    - or
    bundle:install file:./deploy/camel-test-1.0-SNAPSHOT.jar
