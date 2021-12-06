# Spring-angular-scaffold

## 1) Requirements ##
* Gradle 7.2
* Java 16
* MySql server
* Minio server

     ```
     minio.exe server C:\minio-data\
     ```
  1) optionally you can create a windows startup process for minio using [nssm](https://nssm.cc/download)
  2) you can follow this [tutorial for minio](https://forum.duplicati.com/t/setting-up-self-hosted-minio-on-windows-10-with-automatically-renewing-ssl-certificate/645)
  3) the minio server can be accessed on port `:9000` using the default credential if they were not changed `minioadmin:minioadmin`
  
## 2) Profiling
**Frontend**: dev, staging, production

**Backend**: local, staging, production

## 3) How to run the application

Locally there are 2 ways of running the app:

* run `ng serve` in the `frontend` module to serve the angular application and run the @SpringbootApplication main class
  with the profile `local`. For a faster startup, comment out the `frontend` module dependency from
  the `backend-server` 's `build.gradle`
* run the `bootRun` Gradle task after setting the Arguments: `-Pprofile=dev` and the Environment
  variables: `SPRING_PROFILES_ACTIVE=local`. This will run the jar the same way it runs on other stages when deploying
  the app.

## 4) Steps to create a new application using the template

1) Create a new repository and copy the current project in the new repo
2) Rename the `spring-angular-scaffold-project` project name inside the `setting.gradle` file in the root project
3) Rename the base package `springangularscaffold` to the new app package
4) Update the constant values inside the `com.servix.springangularscaffold.config.ProjectConstants` class

## 5) Deploy to heroku

1) Create a new app on heroku. Connect the new app to your repo.
2) Get the ClearDB (MySQL database) and Papertrail (log viewer) addons. The free MySQL database will have a maximum of
   5MB storage capacity.
3) After adding the DB addon, you can get the credentials in Heroku->Settings->Config Vars->CLEARDB_DATABASE_URL. Here
   you can get all you need from the URL.
4) In the Config Vars you need to add:
    - `GRADLE_TASK` `build -Pprofile=staging` to trigger a build with the desired profile
    - `SPRING_PROFILES_ACTIVE` `staging` to set the correct spring profile
5) You can set up automatic deployment or manually deploy from a certain branch.
6) For a free amazon s3 file storage you can check out scaleway.com.