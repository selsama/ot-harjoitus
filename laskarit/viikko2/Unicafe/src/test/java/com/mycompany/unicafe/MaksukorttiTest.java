package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(100);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void alkusaldoOikein(){
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void toStringIlmoittaaSaldonOikein(){
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(200);
        assertEquals(300,kortti.saldo());
    }
    
    @Test
    public void otaRahaaVähentääSaldoaOikein(){
        kortti.otaRahaa(50);
        assertEquals(50, kortti.saldo());
    }
    
    @Test
    public void otaRahaaKertooRiittivatkoRahat(){
        assertEquals(true, kortti.otaRahaa(50));
        assertEquals(false, kortti.otaRahaa(200));
        assertEquals(true, kortti.otaRahaa(50));
    }
}
