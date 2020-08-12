package ru.academit.vkap.ru.academit.vkap.testarraylisthome;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome<T> {
    public static void main(String[] args) {
        ArrayListHome arrayListString = new ArrayListHome<String>();
        ArrayList<String> stringArrayList = new ArrayList<String>();
        stringArrayList = arrayListString.fileRead("/home/valent/Java_project/Git_project/academit/ru.academit/ArrayListHome/src/ru/academit/vkap/ru/academit/vkap/testarraylisthome/textFile.txt");
        System.out.println("Array of string: " + stringArrayList);

        ArrayListHome arrayListInt = new ArrayListHome<Integer>();
        ArrayList<Integer> intArrayList = new ArrayList<Integer>();
        intArrayList = arrayListInt.fileRead("/home/valent/Java_project/Git_project/academit/ru.academit/ArrayListHome/src/ru/academit/vkap/ru/academit/vkap/testarraylisthome/integerFile.txt");
        System.out.println("Array of int: " + intArrayList);

        System.out.println("Delete even numbers:");
        arrayListInt.deleteEvenNumber(intArrayList);
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
                arrayList.add((T) dataLine);
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
                //arrayList.remove(i);
                String st="wds";
            }
        }
        arrayList.trimToSize();
    }
}
