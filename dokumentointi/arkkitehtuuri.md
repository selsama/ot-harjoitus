## Arkkitehtuurikuvaus

### Rakenne

Ohjelman pakkausrakenne on seuraava:

![pakkauskaavio](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/pakkauskaavio.png)

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

![luokkakaavio](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/luokkakaavio.png)

### Päätoiminnallisuudet

Seuraavassa kuvataan sovelluksen toimintalogiikka sekvenssikaavioina muutaman päätoiminnallisuuden osalta.

##### Käärmeen liikkuminen

*SnakeUi* kutsuu luokan *GameHandler* metodia *moveSnake()*. Tämä kutsuu ensin SnakeHead-luokan metodia *move()*, joka siirtää käärmeen päätä käärmeen kulkusuuntaan. Sitten se kutsuu *SnakeHead*in metodia *leaveTail()*, joka luo uuden *SnakeTail*-instanssin oikeaan kohtaan ja palauttaa sen *GameHandlerille*. *GameHandler* lisää hännän listalle. Sitten se kutsuu *SnakeTail*-luokan metodia *getShape()* ja palauttaa saamansa *Rectangle*-olion *SnakeUi*lle piirrettäväksi.

![snakeUP](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/Snake%20moving%20(UP).png)

