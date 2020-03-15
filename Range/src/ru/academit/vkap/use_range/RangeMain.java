package ru.academit.vkap.use_range;

import ru.academit.vkap.range.Range;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(1, 7);
        System.out.println("Length of range: " + range1.getLength());

        Range range2 = new Range(2, 5);
        if (range1.getIntersection(range2) != null) {
            Range intersection = range1.getIntersection(range2);
            System.out.println("Intersection.Length: " + intersection.getLength());
            System.out.println("Intersection.Get point From: " + intersection.getFrom());
            System.out.println("Intersection.Get point To: " + intersection.getTo());
        }

        Range[] unionResult = range1.getUnion(range2);
        System.out.println("Union. Length of array is: " + unionResult.length);
        System.out.println("Union. Range1: " + unionResult[0].toString());
        if (unionResult.length == 2) {
            System.out.println("Union. Range2: " + unionResult[1].toString());
        }
        Range[] differenceResult = range1.getDifference(range2);
        System.out.println("Difference. Length of array is: " + differenceResult.length);
        if (differenceResult.length > 0) {
            System.out.println("Difference. Range1: " + differenceResult[0].toString());
            if (differenceResult.length == 2) {
                System.out.println("Difference. Range2: " + differenceResult[1].toString());
            }
        }
    }
}
