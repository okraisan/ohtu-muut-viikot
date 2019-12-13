package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Peli {
  
  private Osallistuja osallistuja1;
  private Osallistuja osallistuja2;
  
  public Peli(Osallistuja osallistuja1, Osallistuja osallistuja2) {
      this.osallistuja1 = osallistuja1;
      this.osallistuja2 = osallistuja2;
  }

  public void pelaa() {
      Tuomari tuomari = new Tuomari();
    
      while (true) {
          System.out.print("Ensimmäisen pelaajan siirto: ");
          String ekanSiirto = osallistuja1.annaSiirto();
        
          System.out.println("Toisen pelaajan siirto: ");
          String tokanSiirto = osallistuja2.annaSiirto();
          
          if (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            osallistuja2.asetaSiirto(ekanSiirto);
            osallistuja1.asetaSiirto(tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
          } else {
            break;
          }
      }

      System.out.println();
      System.out.println("Kiitos!");
      System.out.println(tuomari);
  }

  private static boolean onkoOkSiirto(String siirto) {
    return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
  }
}
