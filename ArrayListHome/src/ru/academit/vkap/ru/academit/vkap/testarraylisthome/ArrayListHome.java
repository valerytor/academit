package ru.academit.vkap.ru.academit.vkap.testarraylisthome;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;

class ArrayListHome<T> {
    private T objectVar;

    ArrayListHome(T object) {
        objectVar = object;
    }

    ArrayList<T> readFile(String filePath) throws IOException, GenericSignatureFormatError {
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)
        ) {
            String dataLine;
            ArrayList<T> arrayList = new ArrayList<>();
            boolean isIntClass = objectVar.getClass().getTypeName().equals("java.lang.Integer");
            while ((dataLine = br.readLine()) != null) {
                if (isIntClass) {
                    arrayList.add((T) Integer.valueOf(dataLine));
                } else {
                    arrayList.add((T) dataLine);
                }
            }

            return arrayList;
        }
    }
}