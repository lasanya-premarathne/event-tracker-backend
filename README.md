# How to run

### To run the springboot application
1. mvn clean install
2. mvn spring-boot:run

### To run the database
1. docker ps -> to check running containers
2. docker exec -it postgres psql -U lasanyaiit -> postgres is the container name; lasanyaiit is the username
#### once the above command is success
3. \l -> to check existing databases
4. \c event-tracking-system -> to connect to the database; event-tracking-system is the database name