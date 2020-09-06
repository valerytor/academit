package ru.academit.vkap.ru.academit.vkap.testarraylisthome;

import java.io.IOException;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;

public class ArrayListHomeTest {
    public static void main(String[] args) {
        try {
            ArrayListHome arrayListString = new ArrayListHome<>("");
            ArrayList<String> stringArrayList = new ArrayList<String>();
            stringArrayList = arrayListString.readFile("/home/valent/Java_project/Git_project/academit/ru.academit/ArrayListHome/src/ru/academit/vkap/ru/academit/vkap/testarraylisthome/textFile.txt");
            System.out.println("Array of string: " + stringArrayList);

            ArrayListHome arrayListInt = new ArrayListHome<>(0);
            ArrayList<Integer> intArrayList = new ArrayList<Integer>(arrayListInt.readFile("/home/valent/Java_project/Git_project/academit/ru.academit/ArrayListHome/src/ru/academit/vkap/ru/academit/vkap/testarraylisthome/integerFile.txt"));
            System.out.println("Array of integer: " + intArrayList);

            System.out.println("Delete even numbers:");
            intArrayList = deleteEvenNumbers(intArrayList);
            System.out.println(intArrayList);

            System.out.println("Remove repeat value");
            intArrayList = getWithoutRepeatList(intArrayList);
            System.out.println(intArrayList);
        } catch (IOException ioe) {
            System.out.println("File read error: " + ioe.getMessage());
        } catch (GenericSignatureFormatError fe) {
            System.out.println("Error of using generic format: " + fe.getMessage());
        }
    }

    private static ArrayList<Integer> deleteEvenNumbers(ArrayList<Integer> arrayList) {
        ArrayList<Integer> tempArrayList = new ArrayList<>(arrayList);
        //tempArrayList.addAll(arrayList);
        for (int i = 0; i < tempArrayList.size(); i++) {
            if (tempArrayList.get(i) % 2 == 0) {
                tempArrayList.remove(i);
            }
        }

        tempArrayList.trimToSize();

        return tempArrayList;
    }

    private static ArrayList<Integer> getWithoutRepeatList(ArrayList<Integer> arrayList) {
        ArrayList<Integer> tempArrayList = new ArrayList<>(arrayList);
        for (int i = 0; i < tempArrayList.size(); i++) {
            int j = i + 1;

            while (j < tempArrayList.size()) {
                if (tempArrayList.get(i).equals(tempArrayList.get(j))) {
                    tempArrayList.remove(j);

                    continue;
                }

                j++;
            }
        }

        return tempArrayList;
    }
}
