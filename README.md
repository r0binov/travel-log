# Travel Log
This repository contains template application (backend & frontend) for "Travel log" test assignment.

## Prepare database (PostgreSQL 12.0)

```
docker run --name travel-log-postgres -e POSTGRES_PASSWORD=thisispassword -p 5432:5432 -d postgres:12.0
```
This command will start PostgreSQL container listening for connection on **localhost:5432**.
Username is **postgres** and password is **thisispassword**.

If for some reason you have to re-initialize Postgres container from the scratch then run:
`docker rm -fv travel-log-postgres` and execute `docker run` command again.


## Running the backend application

From backend project root execute the following command:
```
./gradlew run
```
After that navigate to `http://localhost:8080/api/travel-logs` in your browser
to verify that backend application is running.

## Running the frontend application

First, install angular-cli globally:
```
npm install -g @angular/cli
``` 

Then navigate to frontend project root and install required dependencies:
```
npm install
```

When dependencies are installed run the frontend application:
```
ng serve
```

and navigate to `http://localhost:4200` in your browser

#
**Happy coding!**
