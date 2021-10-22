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
* run `ng serve` in the `frontend` module to serve the angular application and run the @SpringbootApplication main class with the profile `local`
* run the `bootRun` Gradle task after setting the argument `-Pprofile=dev` and the environment variable `SPRING_PROFILES_ACTIVE=local`. This will run the jar the same way it runs on other stages when deploying the app.

## 4) Steps to create a new application using the template
1) Create a new repository and copy the current project in the new repo
2) Rename the `spring-angular-scaffold-project` project name inside the `setting.gradle` file in the root project
3) Rename the base package `springangularscaffold` to the new app package
4) Update the constant values inside the `com.servix.springangularscaffold.config.ProjectConstants` class
