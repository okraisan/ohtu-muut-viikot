package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private VarastoRajapinta varasto;
    private PankkiRajapinta pankki;
    private Ostoskori ostoskori;
    private ViitegeneraattoriRajapinta viitegeneraattori;
    private String kaupanTili;
    
    @Autowired
    public Kauppa(VarastoRajapinta v, PankkiRajapinta p, ViitegeneraattoriRajapinta vg) {
      varasto = v;
      pankki = p;
      viitegeneraattori = vg;
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
