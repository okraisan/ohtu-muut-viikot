package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
  
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
      // luodaan ensin mock-oliot
      pankki = mock(Pankki.class);

      viite = mock(Viitegeneraattori.class);
      // määritellään että viitegeneraattori palauttaa viitten 42
      when(viite.uusi()).thenReturn(42);

      varasto = mock(Varasto.class);
      // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
      when(varasto.saldo(1)).thenReturn(10); 
      when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
      // määritellään että tuote numero 2 on gröna kulor jonka hinta on 3 ja saldo 20
      when(varasto.saldo(2)).thenReturn(20); 
      when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "gröna kulor", 3));
      // määritellään että tuote numero 3 on leipä jonka hinta on 1 ja saldo 0
      when(varasto.saldo(3)).thenReturn(0); 
      when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "leipä", 1));
      
      k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
    }
    
    @Test
    public void pankinMetodiaTilisiirtoKutsutaanKahdenEriTuotteenSummalla() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli gröna kulor
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 8);   
    }
    
    @Test
    public void pankinMetodiaTilisiirtoKutsutaanKahdenSamanTuotteenSummalla() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);   
    }
    @Test
    public void pankinMetodiaTilisiirtoKutsutaanSummallaJossaEiOleLoppuOlevaaTuotetta() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
    }
    
    @Test
    public void kauppaNollaantuuKunKutsutaanAloitaAsiointi() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki, times(1)).tilisiirto("pekka", 42, "12345", "33333-44455", 5);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki, times(2)).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
    
    @Test
    public void saadaanAinaUusiViite() {
      k.aloitaAsiointi();
      k.lisaaKoriin(1);
      k.tilimaksu("pekka", "12345");
      verify(viite, times(1)).uusi();
      
      k.aloitaAsiointi();
      k.lisaaKoriin(1);
      k.tilimaksu("pekka", "12345");
      verify(viite, times(2)).uusi();
    }
    
    @Test
    public void ostoksenVoiPoistaaKorista() {
      k.aloitaAsiointi();
      k.lisaaKoriin(1);
      k.poistaKorista(1);
      Tuote tuote = varasto.haeTuote(1);
      verify(varasto).palautaVarastoon(tuote);
    }
}

