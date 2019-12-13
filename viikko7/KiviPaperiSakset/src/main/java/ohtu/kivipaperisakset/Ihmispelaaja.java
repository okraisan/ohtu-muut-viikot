package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Ihmispelaaja implements Osallistuja {
  
  private static final Scanner scanner = new Scanner(System.in);

  public String annaSiirto() {
    return scanner.nextLine();
  }
  
  public void asetaSiirto(String s) {
    System.out.println(s);
  }

}
