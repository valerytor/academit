package ru.academit.vkap.ru.academit.vkap.testarraylisthome;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArrayListHome<T> {
    T objectVar;

    ArrayListHome(T object) {
        objectVar = object;
    }

    public static void main(String[] args) {
        ArrayListHome arrayListString = new ArrayListHome<String>(new String());
        ArrayList<String> stringArrayList = new ArrayList<String>();
        stringArrayList = arrayListString.fileRead("/home/valent/Java_project/Git_project/academit/ru.academit/ArrayListHome/src/ru/academit/vkap/ru/academit/vkap/testarraylisthome/textFile.txt");
        System.out.println("Array of string: " + stringArrayList);

        ArrayListHome arrayListInt = new ArrayListHome<Integer>(0);
        ArrayList<Integer> intArrayList = new ArrayList<Integer>();
        intArrayList = arrayListInt.fileRead("/home/valent/Java_project/Git_project/academit/ru.academit/ArrayListHome/src/ru/academit/vkap/ru/academit/vkap/testarraylisthome/integerFile.txt");

        System.out.println("Delete even numbers:");
        arrayListInt.deleteEvenNumber(intArrayList);
        System.out.println(intArrayList);


        System.out.printf("Remove repeat value");
        arrayListInt.repeatDelete(intArrayList);
        System.out.println(intArrayList);
    }

    private ArrayList<T> fileRead(String pathString) {
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

    private void deleteEvenNumber(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
            }
        }
        arrayList.trimToSize();
    }

    private void repeatDelete(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            int j = i + 1;
            while (j < arrayList.size()) {
                if (arrayList.get(i) == arrayList.get(j)) {
                    arrayList.remove(j);

                    continue;
                }
                j++;
            }
        }
    }
}
