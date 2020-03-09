package ru.academit.userange;

import ru.academit.range.Range;

public class UseRange {
    public static void main(String[] args) {
        Range range1 = new Range(-1, 7);
        System.out.println("Length of range: " + range1.getLength());

        Range range2 = new Range(-2, 5);
        if (range1.getIntersection(range2) != null) {
            Range range3 = range1.getIntersection(range2);
            System.out.println("Range3. Length: " + range3.getLength());
            System.out.println("Range3. Get point From: " + range3.getFrom());
            System.out.println("Range3. Get point To: " + range3.getTo());
        }

        Range[] array = range1.getUnion(range2);
        System.out.println("Union. Length of array is: " + array.length);
        System.out.println("Union. From: " + array[0].getFrom() + " To: " + array[0].getTo());

        array = range1.getDifference(range2);
        if (array.length > 0) {
            System.out.println("Difference. Length of array is: " + array.length);
            System.out.println("Difference. From: " + array[0].getFrom() + " To: " + array[0].getTo());
            if (array.length == 2) {
                System.out.println("Difference. From: " + array[1].getFrom() + " To: " + array[1].getTo());
            }
        }
    }
}
