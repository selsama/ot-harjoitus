## Arkkitehtuurikuvaus

### Rakenne

Ohjelman pakkausrakenne on seuraava:

![pakkauskaavio](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kuvat/pakkauskaavio.png)

Pakkaus *snakegame.ui* sisältää JavaFX:llä toteutetun graafisen käyttöliittymän rakentavan koodin, *snakegame.domain* sovelluslogiikan ja *snakegame.dao* tietojen pysyväistallennuksesta vastaavan koodin. Rakenne noudattaa kolmitasoista kerrosarkkitehtuuria.

### Käyttöliittymä

Käyttöliittymä sisältää viisi erilaista näkymää, jotka ovat: 

- Valikko (menu)
- Pelinäkymä (game)
- Peli ohi (gameOver)
- Asetukset (settings)
- Parhaat tulokset (highscores)

Nämä on toteutettu omina [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olioinaan. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen [Stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html) Käyttöliittymä rakennetaan luokassa snakegame.ui.SnakeUi. 

Valikko- ja asetusnäkymä rakennetaan kerran ohjelman avauksen yhteydessä ja sen jälkeen ne vain näytetään metodien *setMenuScene()* ja *setSettingsScene* avulla. Peli ohi-, parhaat tulokset ja itse pelinäkymä rakennetaan ja näytetään kutsuttaessa.

### Sovelluslogiikka

Luokka *GameHandler* hallinnoi pelin elementtejä, joita ovat seinät ja muut esteet (*Obstacle*) sekä käärme, joka koostuu päästä (*SnakeHead*) ja useista häntäpaloista (*SnakeTail*). Kukin elementti toteuttaa rajapinnan *Crashable*, jonka avulla *GameHandler* tarkkailee käärmeen törmäämistä. 

*PointHandler* huolehtii pisteenlaskusta. *PointHandler* myös pääsee käsiksi parhaiden tulosten listaan pakkauksessa *snakegame.dao* sijaitsevan rajapinnan *HighScoreDao* toteuttavan luokan kautta. Luokan toteutus injektoidaan *PointHandler*ille konstruktorikutsun yhteydessä.

![luokkakaavio](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kuvat/luokkakaavio.png)

### Tietojen pysyväistallennus

Pakkauksen *snakegame.dao* luokka *SQLHighScoreDao* tallentaa huippupisteet tietokantaan. Luokka on eristetty sovelluslogiikasta rajapinnan *HighScoreDao* taakse. Tämä noudattaa Data Access Object -suunnittelumallia ja tietojen talletustapaa voidaan tarvittaessa vaihtaa. Testauksessa käytetäänkin luokkia *TestHighScoreDao* ja *ExceptionTestHighScoreDao*, joista ensimmäinen tallentaa keskusmuistiin ja jälkimmäinen heittää poikkeuksia.

Käytetty tietokanta on määritelty tiedostossa konfiguraatiotiedostossa *config.properties*. Sovellus tallettaa tulokset tietokantatauluun, jossa on sarakkeet nimelle ja pisteille.

### Päätoiminnallisuudet

Seuraavassa kuvataan sovelluksen toimintalogiikka sekvenssikaavioina muutaman päätoiminnallisuuden osalta.

##### Käärmeen liikkuminen

*SnakeUi* kutsuu luokan *GameHandler* metodia *moveSnake()*. Tämä kutsuu ensin SnakeHead-luokan metodia *move()*, joka siirtää käärmeen päätä käärmeen kulkusuuntaan. Sitten se kutsuu *SnakeHead*in metodia *leaveTail()*, joka luo uuden *SnakeTail*-instanssin oikeaan kohtaan ja palauttaa sen *GameHandlerille*. *GameHandler* lisää hännän listalle. Sitten se kutsuu *SnakeTail*-luokan metodia *getShape()* ja palauttaa saamansa *Rectangle*-olion *SnakeUi*lle piirrettäväksi.

![snakeUP](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kuvat/Snake%20moving%20(UP).png)

##### Käärmeen kääntäminen

Käyttäjä painaa näppäintä ja käyttöjärjestelmä havaitsee sen. *SnakeUi* kutsuu luokan *GameHandler* metodia *handleKeyPressed(Keycode code)*. Tämä tarkistaa ensin, löytyykö painettu näppäin käärmeen kontrolleista, jotka on tallennettu *HasMap*piin *snakeControls*. Jos näppäin löytyy, metodi hakee kontrollia vastaavan suunnan ja kutsuu saman luokan metodia *turnSnake(Direction dir)*. Tämä metodi kutsuu *SnakeHead*-luokan metodia, joka vastaa kyseistä suuntaa (tässä *turnUp()*). Kyseinen metodin tarkistaa ensin, että käärme ei ole menossa vastakkaiseen suuntaan, ja muuttaa sitten käärmeen liikkumissuunnan.

![pressUp](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kuvat/User%20presses%20button%20(UP).png)

### Rakenteeseen jääneet heikkoudet

*GameHandler* -luokka kasvoi hyvin suureksi, sen olisi voinut pilkkoa osiin, esim. asetusten hallinta olisi luontevaa siirtää omaan luokkaansa. Kyseisen luokan metodi *moveSnake()* ei ihan toteuta Single Responsibility -periaatta ja muutaman muunkin metodin toteutus on 'sotkuinen'.

