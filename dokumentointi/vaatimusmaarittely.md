# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on matopeli, jossa madon häntä muodostaa jatkuvan kiinteän esteen ruudulle. Omaan häntään tai seinään törmääminen lopettaa pelin. Sovellus pitää kirjaa huippupisteistä SQL-tietokannan avulla.

## Käyttöliittymäluonnos

Sovelluksessa on valikko(aloitus)näkymä, pelinäkymä, peli ohi -näkymä,  asetusnäkymä ja parhaat tulokset -näkymä, joiden välillä voi liikkua klikkaamalla nappuloita.

![luonnos](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kuvat/kayttoliittymaluonnos.jpeg)

## Toiminnallisuus

### Valikko:

- käyttäjä voi aloittaa uuden pelin, muuttaa asetuksia tai tarkastella parhaita tuloksia

### Peli

- peli lähtee käyntiin, kun pelaaja painaa nuolinäppäintä/P
- matoa ohjataan nuolinäppäimillä
- mato liikkuu, kun peli on käynnissä 
- pelin voi pausettaa näppäimellä P
- peliruutua ympäröivät seinät, johon törmääminen madolla lopettaa pelin
- mato jättää jälkeensä häntää, joka pysyy paikallaan, ja johon törmääminen lopettaa pelin 
- hengissä pysyminen kerryttää pisteitä, jotka näkyvät ruudulla

### Peli ohi

Pelin loputtua näkymä vaihtuu. Käyttäjä näkee ilmoituksen pelin loppumisesta, omat pisteensä ja nappulat uuden pelin aloittamiseen ja valikkoon palaamiseen. Jos pisteet riittävät parhaiden tulosten listalle, käyttäjä näkee ilmoituksen siitä ja hänellä on mahdollisuus syöttää nimensä tallentaakseen pisteensä.

### Asetukset

- käyttäjä voi valita madon värin
- käyttäjä voi valita vaikeustason ( = kuinka nopeasti mato liikkuu)

### Parhaat tulokset

Näkymä näyttää käyttäjälle listan parhaista tuloksista paremmuusjärjestyksessä.

## Toiminnallisuus - jatkokehitysideoita

- madon ohjausnäppäimien vaihtaminen
- useampi pelaaja, voittaja se joka selviää hengissä pisimpään
- useampia tasoja, joissa ylimääräisiä esteitä
- käyttäjällä mahdollisuus luoda uusia tasoja
