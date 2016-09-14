import java.util.List;

public class Naive {

  public int[] NaiveSearch(String[] dosya, int sLenght, String[][] cbLine, String[] Txt) {
    int[] ctrarray = new int[dosya.length];
    boolean control;
    for (int i = 0; i < dosya.length; i++)
    // this for loop is running until it reaches final lenght.
    {
      int cntr = 0;

      for (int j = 0; j <= (dosya[i].length() - sLenght); j++)
      // in each line we simply extract the line string number and searched string number to find
      // the exact string in searched line to ensure we looked over every string in that line.
      {
        int k;
        for (k = 0; k < sLenght; k++)// the loop goes until it finds the sting that we are searching
                                     // for in that line.
        {
          control = cbLine[i][k + j].equals(Txt[k]);
          if (control == false)
          // if there is a mismatch in that line the loop breakes and increases the k value to look
          // over other strings.
          {
            break;
          }
        }
        if (k == sLenght)
        // if it matches it holds the counts of the string value in each line.
        {
          cntr++;
          ctrarray[i] = cntr;
        }
      }

    }

    return ctrarray;

  }
}
