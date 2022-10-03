<h1 align="center">

<img alt="cgapp logo" src="https://raw.githubusercontent.com/create-go-app/cli/master/.github/images/cgapp_logo%402x.png" width="224px"/><br/>

PetPefeito

</h1>

<p align="center">PetPerfeito is a platform created to help adoption of pets from ONGs. This repo consists of the project's backend.</p>

##  ⚡️ Quick start

First, [download](https://docs.oracle.com/goldengate/1212/gg-winux/GDRAD/java.htm#BGBFJHAB) and install **JRE**. Version `11` or higher is required.

Installation is done by using the [Intellij's Community Edition IDE](https://www.jetbrains.com/idea/download/#section=linux) to download and install the project's dependencies.


That's all you need to know to start! 🎉

###  🐳 Docker steps to quick start

A database is needed to successfully run the project, and that's the motive of the docker-composer's file existance. To build and execute the containers with the DB, navegate to the directory of the docker-compose-yml and run:

```bash
docker compose up
```

> 🔔 Please note: you're not supposed to add or change any configuration related to the DB, it's already done so it communicates with the project in any environment.

##  📖 API Docs

The best way to better explore all the features of the **PetPefeito' backend** is  using the OpenAPI 3.0 that's implemente in the code. For that, run the project and access [this URL](http://localhost:8080/docs/).