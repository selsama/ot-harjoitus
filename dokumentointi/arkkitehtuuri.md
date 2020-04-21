### Luokkakaavio:

Käyttäjän syötteiden vastaanottamisesta ja JavaFX:n hallinnoinnista vastaa luokka SnakeUi. Luokka GameHandler hallinnoi pelin elementtejä, joita ovat seinät ja muut estees (obstacle) sekä käärme, joka koostuu päästä (SnakeHead) ja useista häntäpaloista (SnakeTail). Kukin elementti toteuttaa rajapinnan Crashable, jonka avulla GameHandler tarkkailee käärmeen törmäämistä. GameHandler myös huolehtii pisteenlaskusta.

![kaavio](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/kaavio.png)

### Sovelluslogiikka:

#### Käärmeen liikkuminen

SnakeUi kutsuu luokan GameHandler metodia moveSnake(), joka kutsuu SnakeHead-luokan metodia move(), joka siirtää käärmeen päätä käärmeen kulkusuuntaan. Tämän jälkeen GameHandlerin metodi kutsuu SnakeHead:in metodia leaveTail(), joka luo uuden SnakeTail-instanssin oikeaan kohtaan ja palauttaa sen GameHandlerille. GameHandler lisää hännän listalle. Sitten se kutsuu SnakeTail-luokan metodia getShape() ja palauttaa saamansa Rectangle-olion SnakeUi:lle piirrettäväksi.

![snakeUP](https://github.com/selsama/ot-harjoitus/blob/master/dokumentointi/Snake%20moving%20(UP).png)

