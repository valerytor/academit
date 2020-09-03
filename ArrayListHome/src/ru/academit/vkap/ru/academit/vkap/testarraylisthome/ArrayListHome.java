package ru.academit.vkap.ru.academit.vkap.testarraylisthome;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArrayListHome<T> {
    private T objectVar;

    ArrayListHome(T object) {
        objectVar = object;
    }

    ArrayList<T> fileRead(String pathString) {
        try {
            FileInputStream fis = new FileInputStream(pathString);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String dataLine;
            ArrayList<T> arrayList = new ArrayList<>();
            while ((dataLine = br.readLine()) != null) {
                if (objectVar.getClass().getTypeName().equals("java.lang.Integer")) {
                    arrayList.add((T) Integer.valueOf(dataLine));
                } else {
                    arrayList.add((T) dataLine);
                }
            }
            fis.close();
            isr.close();
            br.close();

            return arrayList;
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
}
