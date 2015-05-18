/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author calleskanshed
 */
public class SavedData {
    
    public SavedData() {

    }

    public void save(String date, String data) {
        Properties saveFile = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("config.properties");

            saveFile.setProperty(date, data);

            saveFile.store(output, null);

        } catch (Exception ex) {

        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(SavedData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String getData(String date) {
        Properties saveFile = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            saveFile.load(input);

        } catch (Exception ex) {

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(SavedData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return saveFile.getProperty(date);
        }
    }

    public String getAllData() {
        Properties saveFile = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                return "";
            }
            saveFile.load(input);

            Enumeration<?> e = saveFile.propertyNames();

            while (e.hasMoreElements()) {
                String date = (String) e.nextElement();
                String value = saveFile.getProperty(date);
                System.out.println("" + date + " : "+ value);
                return date + ":" + value;
            }

        } catch (Exception ex) {

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(SavedData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "done";
    }
}
