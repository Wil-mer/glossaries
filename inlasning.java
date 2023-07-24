
/**
Klassen inlasning läser in data från textfiler och hanterar glosor.
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class inlasning {
  static String[] glosor;

  /**
   * 
   * Metoden svar läser in svarsdata från en textfil och returnerar en
   * tvådimensionell strängmatris.
   * 
   * @return En tvådimensionell strängmatris med svarsdata, eller null om sprakVal
   *         inte är "Engelska" eller om filen inte hittades.
   */
  public static String[][] svar(String sprakVal) {
    if (sprakVal.equals("Engelska")) {
      try {
        File svarFil = new File("engelska_svar.txt");
        Scanner myScanner = new Scanner(svarFil);
        int antal_Rader = 0;
        int kolonner = 0;

        while (myScanner.hasNextLine()) {
          String line = myScanner.nextLine();
          String[] data = line.split(",");
          antal_Rader++;
          kolonner = Math.max(kolonner, data.length);
        }

        myScanner = new Scanner(svarFil);
        String[][] svar = new String[antal_Rader][kolonner];
        int rad = 0;

        while (myScanner.hasNextLine()) {
          String line = myScanner.nextLine();
          String[] data = line.split(",");

          for (int i = 0; i < data.length; i++) {
            svar[rad][i] = data[i];
          }
          rad++;
        }

        myScanner.close();
        return svar;
      } catch (FileNotFoundException e) {
        System.out.println("Ett fel inträffade.");
        e.printStackTrace();
        String[][] svar = new String[0][0];
        return svar;
      }
    }
    else System.out.println("Detta språk finns ej");
    return null;
  }

  /**
   * 
   * Metoden glosor läser in glosdata från en textfil och returnerar en
   * strängarray.
   * 
   * @return En strängarray med glosdata, eller null om sprakVal inte är
   *         "Engelska" eller om filen inte hittades.
   */
  public static String[] glosor(String sprakVal) {
    if (sprakVal.equals("Engelska")) {
      try {
        File glosFil = new File("engelska_glosor.txt");
        Scanner myScanner = new Scanner(glosFil);
        String data = myScanner.nextLine();
        myScanner.close();
        glosor = data.split(",");
        return glosor;
      } catch (FileNotFoundException e) {
        System.out.println("Ett fel inträffade.");
        e.printStackTrace();
        return glosor;
      }
    }
    else
    return null;
  }
}