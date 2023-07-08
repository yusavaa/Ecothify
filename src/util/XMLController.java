package util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class XMLController {

    public static void saveToXML(ArrayList<?> objectList, String fileName) {
        XStream xStream = new XStream(new StaxDriver());
        String sxml = xStream.toXML(objectList);
        FileOutputStream fileOutput;
        try {

            byte[] bytes = sxml.getBytes("UTF-8");
            fileOutput = new FileOutputStream("src/database/" + fileName);
            fileOutput.write(bytes);
            fileOutput.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<?> loadFroamXML(ArrayList<?> objectList, String fileName) {
        XStream xStream = new XStream(new StaxDriver());
        xStream.addPermission(AnyTypePermission.ANY); // Fix forbidden class exception
        FileInputStream fileInput;
        try {

            fileInput = new FileInputStream("src/database/" + fileName);

            String sxml = "";
            int isi;
            char c;
            while ((isi = fileInput.read()) != -1) {
                c = (char) isi;
                sxml += c;
            }

            objectList = (ArrayList<?>) xStream.fromXML(sxml);
            fileInput.close();
            return objectList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return objectList;
        }
    }

}
