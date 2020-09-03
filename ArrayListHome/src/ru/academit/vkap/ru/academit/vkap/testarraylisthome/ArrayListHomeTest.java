package ru.academit.vkap.ru.academit.vkap.testarraylisthome;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHomeTest {
    public static void main(String[] args) {
        ArrayListHomeTest arrayListHomeTest = new ArrayListHomeTest();

        ArrayListHome arrayListString = new ArrayListHome<String>(new String());
        ArrayList<String> stringArrayList = new ArrayList<String>();
        stringArrayList = arrayListString.fileRead("/home/valent/Java_project/Git_project/academit/ru.academit/ArrayListHome/src/ru/academit/vkap/ru/academit/vkap/testarraylisthome/textFile.txt");
        System.out.println("Array of string: " + stringArrayList);

        ArrayListHome arrayListInt = new ArrayListHome<Integer>(0);
        ArrayList<Integer> intArrayList = new ArrayList<Integer>();
        intArrayList = arrayListInt.fileRead("/home/valent/Java_project/Git_project/academit/ru.academit/ArrayListHome/src/ru/academit/vkap/ru/academit/vkap/testarraylisthome/integerFile.txt");

        System.out.println("Delete even numbers:");
        deleteEvenNumber(intArrayList);
        System.out.println(intArrayList);


        System.out.printf("Remove repeat value");
        arrayListInt.repeatDelete(intArrayList);
        System.out.println(intArrayList);
    }

    private ArrayList<Integer> deleteEvenNumber(ArrayList<Integer> arrayList) {
        ArrayList<Integer> tempArrayList = new ArrayList<>();
        tempArrayList = Arrays.copyOf(arrayList, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
            }
        }
        arrayList.trimToSize();
    }

    public ArrayList<Integer> repeatDelete(ArrayList<Integer> arrayList) {
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
