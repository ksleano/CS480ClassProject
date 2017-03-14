/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs480.pkgclass.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kslea_000
 */
public class CS480ClassDB {

    /**
     * @param args the command line arguments
     */
    private static boolean sameIP;

    public static void main(String[] args) {
        // TODO code application logic here

        ArrayList<Log> logOfDB = new ArrayList<>();
        ArrayList<Log> logOfDDOS = new ArrayList<>();

        try {
            File file = new File("test.txt");
            Scanner inFile = new Scanner(file);

            String d1, d2, d3, d4, d5, d6, d7, d8;

            while (inFile.hasNext()) {
                // put everyrhing in an array
                d1 = inFile.next();
                d2 = inFile.next();
                d3 = inFile.next();
                d4 = inFile.next();
                d5 = inFile.next();
                d6 = inFile.next();
                d7 = inFile.next();
                d8 = inFile.next();
                // hold the entries

                Log en = new Log(d1, d2, d3, d4, d5, d6, d7, d8);

                // use i to feed logOfDB.get()
                int i = 0;
                //assume false
                sameIP = false;
                while (i < logOfDB.size()) {
                    // get instance of same ip in the log file
                    if (logOfDB.get(i).getIpSRC().equals(en.getIpSRC())) {
                        sameIP = true;
                        if ((en.getTime() - logOfDB.get(i).getTime()) < 2.0) {

//                            // if new log is already in ddos list update counter
                            if ( logOfDDOS.isEmpty()) 
                            {
                                logOfDDOS.add(en);
                            }
                            for (int k = 0; k < logOfDDOS.size(); k++) {
                                if (logOfDDOS.get(k).getIpSRC().equals(en.getIpSRC())) {
                                    logOfDDOS.get(k).updateDDOScounter();

                                } else {   //else add to DDOS log
                                    logOfDDOS.add(en);
                                }
                            }

                            //exit loop
                            i = logOfDB.size();
                        }
                    }
                    i++;
                }

                if (sameIP == false) {
                    logOfDB.add(en);
                }

            }
            inFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("fdsfasfas");
        }
        // to get info pass the index to get
        //System.out.println(logOfDB.get(0).print());
        int p = 0;
        System.out.println("logOfDDOS");

        while (p < logOfDDOS.size()) {
            System.out.println(logOfDDOS.get(p).print());
            p++;
        }
        int q = 0;
        System.out.println("logOfDB");
        while (q < logOfDB.size()) {
            System.out.println(logOfDB.get(q).print());
            q++;
        }

    }

}
