## Käyttöohje

### Konfigurointi ja ohjelman käynnistäminen

Lataa kohdasta (loppupalautuksen)[https://github.com/selsama/ot-harjoitus/releases/tag/Loppupalautus] *snakegame.jar* ja *config.properties* ja sijoita ne samaan kansioon. Suorita ohjema hakemistossa komennolla

    java -jar snakegame.jar
  
Oletuksena ohjelma luo sijaintiinsa tietokannan parhaiden tulosten tallentamiseen. Jos haluat muokata tietokannan sijaintia/nimeä, muuta tekstieditorilla tiedoston *config.properties* kohta *./scores* mieleiseksesi.

### Päävalikko

Sovellus käynnistyy valikkonäkymään, jossa on kolme nappulaa: 

- *new game*: aloittaa uuden pelin
- *settings*: muuta asetuksia
- *highscores*: katso parhaat tulokset 

### Peli-ikkuna ja pelaaminen

Käärme aloittaa keskeltä ruutua (värillinen neliö). Käärmeen liikkumasuunta valitaan nuolinäppäimiä painamalla ja se liikkuu itsestään. Tavoitteena on olla törmäämättä seinään tai käärmeen häntään mahdollisimman pitkään.

Peli lähtee käyntiin valitsemalla aloitussuunta nuolinäppäimellä tai painamalla *P*, jolloin käärme lähtee ylös. Pelin voi keskeyttää (pause) ja jatkaa painamalla P. Vasemmassa yläkulmassa näet senhetkisen pistetilanteen.

### Peli ohi

Kun käärme törmää, on peli ohi. Sovellus näyttää ikkunan, jossa näkyvät saamasi pisteet ja kaksi nappulaa: *new game* aloittaa uuden pelin ja *back to menu* palaa päävalikkoon. Mikäli pisteesi riittivät parhaiden tulosten listalle, näet lisäksi tekstin *"new high score!"* ja tekstikentän, johon voit syöttää nimesi. Painamalla *submit* tulos tallennetaan.

### Asetukset

Asetusnäkymässä, johon pääsee päävalikossa, voit valita käärmeen värin ja pelin vaikeustason. Peli on oletuksena vaikeudella *easy* (=helppo). Takaisin valikkoon pääsee nappulaa *back to menu* painamalla.

### Parhaat tulokset

Parhaat tulokset - näkymä näyttää kymmenen parasta sijoitusta: nimen ja pisteet.

### Lopettaminen

Peli suljetaan klikkaamalla oikean yläkulman ruksia.
