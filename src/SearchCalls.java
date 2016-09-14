import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Locale;
import javax.swing.JScrollBar;
import java.awt.Color;

public class SearchCalls extends JFrame {

  private JPanel contentPane;
  public static java.util.List<String> dosya;

  public static java.util.List<String> dosyaLowerCase;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        JFrame parent = new JFrame();

        try {
          SearchCalls frame = new SearchCalls();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
        JOptionPane.showMessageDialog(parent,
            "Upload file first and then search" + "\n" + "Alisan DAGDELEN");

      }
    });

  }

  /**
   * Create the frame.
   */
  public SearchCalls() {



    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(500, 900, 650, 450);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JTextPane textPane_3 = new JTextPane();
    textPane_3.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
    textPane_3.setBounds(260, 6, 253, 34);
    contentPane.add(textPane_3);


    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setBounds(215, 76, 297, 335);



    JScrollPane jp1 = new JScrollPane(textArea);
    jp1.setBounds(33, 74, 297, 335);
    jp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    contentPane.add(jp1);

    JTextArea textArea_1 = new JTextArea();
    textArea_1.setEditable(false);
    textArea_1.setBounds(334, 74, 297, 335);



    JScrollPane jp2 = new JScrollPane(textArea_1);
    jp2.setBounds(334, 74, 297, 335);
    jp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    contentPane.add(jp2);



    JButton btnUpload = new JButton("Upload");

    btnUpload.setBounds(25, 11, 117, 29);
    contentPane.add(btnUpload);

    JButton btnNewButton = new JButton("Search");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] txt = new String[200];
        String search = textPane_3.getText().toLowerCase(Locale.ENGLISH);
        int searchLength = search.length();
        for (int i = 0; i < search.length(); i++) {
          txt[i] = search.substring(i, i + 1);


        }
        String[] lowerCaseText = new String[dosya.size()];
        for (int i = 0; i < dosya.size(); i++) {
          lowerCaseText[i] = dosya.get(i).toLowerCase(Locale.ENGLISH);
        }

        /////////////////////////
        String[][] charByLine = new String[lowerCaseText.length][500];
        String myString;
        for (int i = 0; i < lowerCaseText.length; i++) {
          myString = lowerCaseText[i];
          // we assign mystring string to lines array that is getting the line number that is being
          // searched.
          for (int j = 0; j < myString.length(); j++)
          // this loop goes until it reaches the end of the line that is being searched.
          {

            charByLine[i][j] = myString.substring(j, j + 1);// the d serie that we created holds i
                                                            // as line j as characters that is in
                                                            // that line.


          }
        }
        ////////////////////
        Naive n = new Naive();
        long starttimef = System.currentTimeMillis();
        int[] ctA = n.NaiveSearch(lowerCaseText, searchLength, charByLine, txt);
        long stoptimef = System.currentTimeMillis();
        String sonucTextNaive = "";

        Finite fSearch = new Finite();
        long starttimen = System.currentTimeMillis();
        int[] ctf = fSearch.finiteSearch(searchLength, lowerCaseText, search);
        long stoptimen = System.currentTimeMillis();

        sonucTextNaive = " The Naive Algorithm " + "\n";
        for (int i = 0; i < dosya.size(); i++) {
          if (ctA[i] > 0) {
            sonucTextNaive += "Line : " + (i + 1) + " : " + ctA[i] + " occurrence" + "\n";
          } else {

          }
        }
        sonucTextNaive += ("Time for Naive String Matcher: " + (stoptimen - starttimen) + "ms");
        textArea.setText(sonucTextNaive);



        String sonucTextFinite = " The Finite Algorithm " + "\n";
        for (int i = 0; i < dosya.size(); i++) {
          if (ctA[i] > 0) {
            sonucTextFinite += "Line : " + (i + 1) + " : " + ctf[i] + " occurrence" + "\n";
          } else {

          }
        }
        sonucTextFinite += ("Time for Finite Automata Matcher: " + (stoptimef - starttimef) + "ms");
        textArea_1.setText(sonucTextFinite);

      }
    });
    btnNewButton.setBounds(533, 6, 90, 34);
    contentPane.add(btnNewButton);



    btnUpload.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        JFileChooser openFile = new JFileChooser();
        openFile.showOpenDialog(null);
        File xyz = openFile.getSelectedFile();
        // FileReader dosya = new FileReader(xyz);
        try (BufferedReader br = new BufferedReader(new FileReader(xyz))) {

          String yazi = "";
          dosya = java.nio.file.Files.readAllLines(xyz.toPath(), StandardCharsets.UTF_8);


        } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
  }
}
