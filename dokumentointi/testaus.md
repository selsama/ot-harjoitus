# Testausdokumentti

Ohjelmaa on testattu käyttöjärjestelmätasolla manuaalisesti ja yksikkö- ja integraatiotestein JUnitilla.

## Yksikkö- ja integraatiotestaus

Yksikkötasolla ohjelman testaus on laajaa, ja testiluokkia on useita. Integraatiotason testit löytyvät GameHandler:in ja PointHandler:in testeistä vähän sieltä täältä. PointHandlerin testauksessa hyödynnetään luokkia TestHighScoreDao ja TestExceptionHighScoreDao. Sovelluksen käyttämän SQLHighScoreDao-luokan testaamiseen on käytetty erillistä tietokantaa.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta testauksen rivikatttavuus on 92% ja haaraumakattavuus 90%.

![testikattavuus](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kuvat/testikattavuus.png)

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti. Sovellus on haettu ja testattu [käyttöohjeen](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kayttoohje.md) kuvaamalla tavalla Linux-ympäristössä. Sovellusta on testattu sekä siten, että tietokanta on ollut olemassa että siten että ei, jolloin sovellus on luonut ne. Toiminnallisuudet on käyty läpi myös virheellisillä syötteillä, kuten painamalla useita nappuloita samaan aikaan. Sovellus sallii myös tuloksen tallentamisen ilman nimeä, koska tätä ei pidetty huonona asiana.

##Sovellukseen jääneet laatuongelmat

Mikäli konfiguraatiotiedostoa ei ole määritelty oikein, sovellus tulostaa komentoriville "check config.properties". Sovellus kuitenkin sallii käytön jatkamisen tämän jälkeen, ja alkaa suoltaa stack tracea komentoriville. Tulin ongelmasta tietoiseksi vasta hyvin myöhäisessä vaiheessa, enkä keksinyt kätevää ja helppoa tapaa päästä siitä eroon. Lisäksi en ollut varma, olisiko se edes tarpeellista, kun käyttöohje kuitenkin opastaa sovelluksen konfiguroinnin.
