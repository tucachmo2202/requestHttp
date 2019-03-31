package http_database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Configuration {

    public static ArrayList<String> getConfiguration(){
        BufferedReader roll = null;
        ArrayList<String> a = new ArrayList<String>();
        try {
            roll = new BufferedReader(new FileReader("database_config.txt"));
            String str;
            str = roll.readLine();
            a.add(str.split("\\s")[1]);
            str = roll.readLine();
            a.add(str.split("\\s")[1]);
            str = roll.readLine();

            a.add(str.split("\\s")[1]);
            str = roll.readLine();

            a.add(str.split("\\s")[1]);
        }
        catch (IOException e) {
        }finally {

            try {

                if (roll != null)
                    roll.close();


            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return a;
    }

}
