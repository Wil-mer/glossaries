/**
Klassen Main hanterar glosövningen genom att läsa in data och utföra gissningar.
*/
import java.util.Scanner;

public class glosboken {
public static Scanner scn = new Scanner(System.in);
public static String[] languages = { "Engelska", "Tyska" };

  /**
   * 
   * Huvudmetoden som körs när programmet startar.
   * 
   */
  public static void main(String[] args) {
    boolean pgrmOn = true;
    while (pgrmOn == true) {
      System.out.println("Glosövning\n");
      String choice = languageChoice();
      if (choice.equals("Q")) {
          pgrmOn = false;
          break;
      }
      inlasning inlast = new inlasning();
      String[] glossaries = inlast.glosor(choice);
      String[][] answers = inlast.svar(choice);
      int score = 0;
      String guess = query(glossaries, answers);
      if (guess.equals("Q")) {
          pgrmOn = false;
          break;
      }
      System.out.println("Du klarade: " + score + " poäng!");
    }
  }

  /**
   * 
   * Metoden sprakVal hanterar användarens val av språk.
   * 
   * @return Det valda språket som en sträng.
   */
  public static String languageChoice() {
    System.out.println("Göt ditt språkval");
    for (int i = 0; i < languages.length; i++) {
        System.out.println((i + 1) + ":" + languages[i]);
    }
    String temp = "";
    String choice = "";
    boolean loop = true;
    while (loop == true) {
      temp = scn.nextLine();
      if (temp.equals("Q"))
        return temp;
      else if (Integer.parseInt(temp) == 1) {
        choice = "Engelska";
        loop = false;
      } else if (Integer.parseInt(temp) == 2) {
        choice = "Tyska";
        loop = false;
      } else {
        System.out.println("Fel inmatning, försök igen");
      }
    }
    System.out.println(choice + " valt");
    clearScreen();
    return choice;
  }

  public static String query(String[] glossaries, String[][] answers){
    for (int i = 0; i < glossaries.length; i++) {
      System.out.println(glossaries[i]);
      String guess = scn.nextLine();
      if (guess.equals("Q")) {
        return guess;
      }
      String checked_answer = rattning(i, guess, answers);
      System.out.println(checked_answer);
      if (checked_answer.equals("Korrekt!"))
        return checked_answer;
    }
    return null;
  }

  /**
   * 
   * Metoden rattning utför rättning av en gissning baserat på de korrekta svaren.
   * 
   * @param i        Index för den aktuella glosan.
   * @param gissning Den angivna gissningen.
   * @param svar     En tvådimensionell strängmatris med de korrekta svaren.
   * @return En sträng som indikerar om gissningen är korrekt eller inte.
   */
  public static String rattning(int i, String gissning, String[][] svar) {
    String rattat_svar = "";

    // temp_svar blir raden med de korrekta synonymerna
    String[] temp_svar = svar[i];
    char[] temp_gissning = gissning.toCharArray();
    int antal_ratt = 0;

    // Går igenom varje korrekt synonym och jämför den med gissningen
    for (int k = 0; k < temp_svar.length; k++) {
      // Först kolla så inte svaret är null, matrisen innehåller null element.
      if (temp_svar[k] != null) {
        // Om gissningen är lika med en av synonymerna
        if (gissning.equals(temp_svar[k])) {
          rattat_svar = "Korrekt!";
          return rattat_svar;
        }
        /*
         * Om längden på gissningen är dessamma som synonymen
         * räknas antalet bokstäver som är korrekta
         */
        if (gissning.length() == temp_svar[k].length()) {
          // gå igenom synonymsträngen
          for (int j = 0; j < temp_svar[k].length(); j++) {
            // Om karatären i gissningen på index j är lika med karaktären i svaret index j
            if (temp_gissning[j] == temp_svar[k].charAt(j)) {
              antal_ratt++;
            }
          }
        }
        // Kolla om MAJORITETEN av bokstäverna är korrekta
        if (gissning.length() == temp_svar[k].length() && antal_ratt >= (temp_svar[k].length() / 2)) {
          rattat_svar = "Nästan korrekt! Du klarade "+antal_ratt+" av "+temp_svar[k].length()+ " bokstäver";
          return rattat_svar;
        } else
          rattat_svar = "Inkorrekt!";
      }
    }
    return rattat_svar;
  }

  /**
   * 
   * Metoden clearScreen rensar konsolfönstret.
   */
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}