<h1 align="center">

<img alt="cgapp logo" src="https://raw.githubusercontent.com/create-go-app/cli/master/.github/images/cgapp_logo%402x.png" width="224px"/><br/>

PetPefeito

</h1>

<p align="center">PetPerfeito is a platform created to help adoption of pets from ONGs. This repo consists of the project's backend.</p>

##  ⚡️ Quick start

First, [download](https://docs.oracle.com/goldengate/1212/gg-winux/GDRAD/java.htm#BGBFJHAB) and install **JRE**. Version `11` or higher is required.

Dependencies download and installation is done by using the [Intellij's Community Edition IDE](https://www.jetbrains.com/idea/download/#section=linux). 🎉

###  🐳 Docker steps to quick start

A database is needed to successfully run the project, and that's the motive of the docker-composer's file existance. To build and execute the containers with the DB, navegate to the directory of the docker-compose-yml and run:

```bash
docker compose up
```

> 🔔 Please note: you're not supposed to add or change any configuration related to the DB, it's already done so it communicates with the project in any environment.

##  📖 API Docs

The best way to better explore all the features of the **PetPefeito' backend** is  using the OpenAPI 3.0 that's implemente in the code. For that, run the project and access [this URL](http://localhost:8080/docs/).

## Questions for Interest form:

Cachorro

As respostas devem ser um valor entre 0~10, podendo ser estipulado pelos devs do front-end
- Exemplos: 
- Tem experiência com cachorros? a) Sim (10 pontos) b) Não (0 pontos)
- Em que faixa está sua renda? a) 0~2k (0 pontos)  b) 2k~5k (3 pontos)  c) 5k~10k (6 pontos)  d) 10k+ (10 pontos)


1. Tem experiência com cachorros? 
   (Peso: 3)

2. Mora em casa ou apartamento?
   (Peso: 2)

3. Já possui os materiais necessários para receber o cachorro? (coleira, casinha, etc)
   (Peso: 1)

4. Existem outros animais na casa?
   (Peso: 2)

5. Tem filhos?
   (Peso: 2)

6. Qual o clima do local onde reside? (quente, frio, meio-termo)
   (Peso: 1)

7. Possui emprego?
   (Peso: 3)

8. Em que faixa está sua renda? (0~2k, 2k~5k, 5k~10k, 10k+)
   (Peso: 2)