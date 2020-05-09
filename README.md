# Ohjelmistotekniikka, harjoitustyö: SnakeGame

Sovellus on matopeli. Matoa käännetään nuolinäppäimillä ja se liikkuu itsestään. Peli loppuu, kun mato törmää seinään tai omaan häntäänsä. Pelin voi pausettaa/jatkaa painamalla P. Madon värin ja vaikeustason voi valita asetuksista.

## Dokumentaatio

[Käyttöohje](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/testaus.md)

[Työaikakirjanpito](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Projekti

[SnakeGame](https://github.com/selsama/ot-harjoitus/tree/master/SnakeGame)

## Releaset

[Loppupalautus](https://github.com/selsama/ot-harjoitus/releases/tag/Loppupalautus)

[Kaikki releaset](https://github.com/selsama/ot-harjoitus/releases)

## Komentorivikomennot

Koodin suoritus: 

    mvn compile exec:java -Dexec.mainClass=snakegame.Main
    
Tai voit generoida jar-tiedoston komennolla 

    mvn package

ja suorittaa *target*-hakemistoon generoidun tiedoston *SnakeGame-1.0-SNAPSHOT.jar*

### Testaus

Testien suorittaminen: 

    mvn test

Testikattavuusraportin luonti: 

    mvn test jacoco:report

Raporttia voi tarkastella avaamalla selaimella tiedoston *target/site/jacoco/index.html*

Checkstyle-tarkistus:

    mvn jxr:jxr checkstyle:checkstyle
    
Tulokset löytyvät tiedostosta */target/site/checkstyle.html.*

### Javadoc

Javadocin generointi:

    mvn javadoc:javadoc
    
Javadoc saattaa vaatia JAVA_HOME:n määrittämisen:

    JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/ mvn clean javadoc:javadoc
    
Javadoc löytyy kansiosta */target/site/apidocs*

