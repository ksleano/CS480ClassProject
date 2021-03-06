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

/**
 *
 * @author kslea_000
 */
public class CS480ClassDB {

    /**
     * @param args the command line arguments
     */
    private static boolean sameIPDB;
    private static boolean sameIPDDOS;

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

                Log entry = new Log(d1, d2, d3, d4, d5, d6, d7, d8);

                // use i to feed logOfDB.get()
                int i = 0;
                //assume false
                sameIPDB = false;
                sameIPDDOS = false;
                while (i < logOfDB.size()) {

                    if ((entry.getTime() - logOfDB.get(i).getTime()) > 5) {
                        logOfDB.remove(i);
                    }

                    if (logOfDB.get(i).getIpSRC().equals(entry.getIpSRC())) {
                        sameIPDB = true;
                        if ((entry.getTime() - logOfDB.get(i).getTime()) < 2.0) {

//                            // if new log is already in ddos list update counter
                            if (logOfDDOS.isEmpty()) {
                                logOfDDOS.add(entry);
                            }
                            // itterate over DDOS log
                            //if same IP, update the counter
                            for (int k = 0; k < logOfDDOS.size(); k++) {
                                if (logOfDDOS.get(k).getIpSRC().equals(entry.getIpSRC())) {
                                    sameIPDDOS = true;
                                    logOfDDOS.get(k).updateDDOScounter();

                                }
                            }
                            //if unique IP to DDOS list, add it and update counter
                            if (sameIPDDOS == false) {
                                logOfDDOS.add(entry);
                                int j = logOfDDOS.size()-1;
                                logOfDDOS.get(j).updateDDOScounter();
                            }
                            //exit loop
                            i = logOfDB.size();
                        }
                    }
                    i++;
                }

                if (sameIPDB == false) {
                    logOfDB.add(entry);
                }
                //remove entry if expired

            }
            inFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not Found");
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
