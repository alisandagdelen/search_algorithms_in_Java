import java.util.List;


public class Finite {

  int numberchar = 256;

  public int[] finiteSearch(int searchlenght, String[] dosya, String searchW) {
    char[] searchChar = new char[searchlenght];
    searchChar = searchW.toCharArray();
    int cntr;
    int[] satir = new int[dosya.length];
    for (int i = 0; i < dosya.length; i++) {
      char[] txtChar = new char[dosya[i].length()];
      txtChar = dosya[i].toCharArray();

      cntr = search(searchChar, txtChar, searchlenght, dosya[i].length());

      satir[i] = cntr;
    }

    return satir;

  }

  public int search(char[] searchChar, char[] textChar, int searchLenght, int lineLenght) {
    int[][] ts = new int[searchLenght + 1][numberchar];
    // we create sd array and assign number of state and the character number for character
    // matching.
    computeTF(searchChar, searchLenght, ts);
    int[] cntrArray = new int[200];
    int cntr = 0;
    int i = 0;
    int state = 0;
    for (i = 0; i < lineLenght; i++)
    // in this for loop it goes until the line lenght that we search for.
    {
      state = ts[state][textChar[i]];
      if (state == searchLenght)
      // if this matching happens that means the given ssearchlenght succesfully reached the
      // accepting state and found what we are searching for.
      {
        cntr++;
        // this is for counter for how many text we found in the line that we are searching for.
      }
    }
    return cntr;
  }


  public void computeTF(char[] searchchar, int _slength, int[][] sd) {
    int x;
    int state;
    for (state = 0; state <= _slength; ++state)
    // this for loop start from the state 0 and goes until given texts length.
    {
      for (x = 0; x < numberchar; ++x)
      // x represents the character number in alphabeth,it searches for right character number.
      {
        sd[state][x] = NextState(searchchar, _slength, state, x);
        // this is for the sd array to get the state number that we are searching for.
      }
    }


  }

  public int NextState(char[] searchchar, int _slength, int _state, int _x) {
    if (_state < _slength && _x == searchchar[_state])
    // if the state number lower than given texts length and x is equal to correct character
    // number,gets into other state.
    {
      return _state + 1;
    }
    int nextState;
    int i;

    for (nextState = _state; nextState > 0; nextState--)
    // for loop goes until it finds the lowest state that is equal to the text that we are searching
    // for.
    {
      if (searchchar[nextState - 1] == _x) {
        for (i = 0; i < nextState - 1; i++) {
          if (searchchar[i] != searchchar[_state - nextState + 1 + i])
            // if this loop couldn`t find the lowest state that is equal to the lowest text
            // component that we are searching for,the loop breakes and returns 0.
            break;
        }
        if (i == nextState - 1)
          // if we find the lowest state that we can get to,it returns that state and goes through
          // that state.
          return nextState;
      }
    }
    return 0;


  }


}
