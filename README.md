### Pet-project "TODO"

Project based on Spring Boot framework and used such possibilities as Configuration properties, data source bean configuration via profiles.
For unit test used an embedded H2 database with DDL script for schema creaion and SQL script for test data preparation.

Steps to Start a Project.
- pre-requirements: Started postgres db in docker (schema can be found in src/test/resources/testdb/schema.sql)
- Common network for application and postgress containers (if third launch option preferred, in docker containers)

launch variants:
1. In IDE with --spring.profiles.active=local programm argument
2. As jar file. mvn clean package && java -jar -Dspring.profiles.active=local <your jar-file name>.jar
3. recreate-images.sh - bash script will compile an jar file, then build image and start application in docker container, with "dev" profile, that enables application to connect to postgres db in docker container
