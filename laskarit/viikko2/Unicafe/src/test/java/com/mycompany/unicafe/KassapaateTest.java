package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti rahaaOn;
    Maksukortti rahaaVahan;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        rahaaOn = new Maksukortti(1000);
        rahaaVahan = new Maksukortti(200);
        
    }
    
    @Test
    public void kassanAlkusaldoOikein(){
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanAlkuMyyntiOikein(){
        assertEquals(0,kassa.maukkaitaLounaitaMyyty()+kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateinenToimiiJosYliEdullinen(){
        int vaihtoraha=kassa.syoEdullisesti(500);
        assertEquals(100240,kassa.kassassaRahaa());
        assertEquals(260,vaihtoraha);
    }
    
    @Test
    public void kateinenToimiiJosTasanEdullinen(){
        assertEquals(0,kassa.syoEdullisesti(240));
        assertEquals(100240,kassa.kassassaRahaa());
    }
    
    @Test
    public void kateinenToimiiJosAlleEdullinen(){
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000,kassa.kassassaRahaa());
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateinenToimiiJosYliMaukas(){
        int vaihtoraha=kassa.syoMaukkaasti(500);
        assertEquals(100400,kassa.kassassaRahaa());
        assertEquals(100,vaihtoraha);
    }
    
    @Test
    public void kateinenToimiiJosTasanMaukas(){
        assertEquals(0,kassa.syoMaukkaasti(400));
        assertEquals(100400,kassa.kassassaRahaa());
    }
    
    @Test
    public void kateinenToimiiJosAlleMaukas(){
        assertEquals(200, kassa.syoMaukkaasti(200));
        assertEquals(100000,kassa.kassassaRahaa());
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenLounaidenMaaraKasvaaKateisella(){
        kassa.syoEdullisesti(500);
        kassa.syoEdullisesti(240);
        assertEquals(2,kassa.edullisiaLounaitaMyyty());
        kassa.syoMaukkaasti(500);
        assertEquals(1,kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoOnnistuuEdullinen(){
        assertTrue(kassa.syoEdullisesti(rahaaOn));
        assertEquals(760,rahaaOn.saldo());
    }
    
    @Test
    public void korttiostoKasvattaaMyytyjaLounaitaEdullinen(){
        kassa.syoEdullisesti(rahaaOn);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        kassa.syoEdullisesti(rahaaOn);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiMyyLounastaJosSaldoEiRiitaEdullinen(){
        assertFalse(kassa.syoEdullisesti(rahaaVahan));
        assertEquals(200,rahaaVahan.saldo());
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoOnnistuuMaukas(){
        assertTrue(kassa.syoMaukkaasti(rahaaOn));
        assertEquals(600,rahaaOn.saldo());
    }
    
    @Test
    public void korttiostoKasvattaaMyytyjaLounaitaMaukas(){
        kassa.syoMaukkaasti(rahaaOn);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        kassa.syoMaukkaasti(rahaaOn);
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiMyyLounastaJosSaldoEiRiitaMaukas(){
        assertFalse(kassa.syoMaukkaasti(rahaaVahan));
        assertEquals(200,rahaaVahan.saldo());
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiMuutaKassanRahamaaraa(){
        kassa.syoEdullisesti(rahaaOn);
        kassa.syoMaukkaasti(rahaaOn);
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaToimii(){
        kassa.lataaRahaaKortille(rahaaOn, 100);
        assertEquals(1100, rahaaOn.saldo());
        assertEquals(100100,kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaEiLataaNegatiivista(){
        kassa.lataaRahaaKortille(rahaaOn, -100);
        assertEquals(1000,rahaaOn.saldo());
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    

}
