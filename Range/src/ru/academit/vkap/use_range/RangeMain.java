package ru.academit.vkap.use_range;

import ru.academit.vkap.range.Range;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(5, 7);
        System.out.println("Length of range: " + range1.getLength());

        Range range2 = new Range(5, 7);
        Range intersection = range1.getIntersection(range2);
        if (intersection != null) {
            System.out.println("Intersection.Length: " + intersection.getLength());
            System.out.println("Intersection. Range: " + intersection);
        } else {
            System.out.println("Intersection: null");
        }

        Range[] union = range1.getUnion(range2);
        System.out.println("Union. Length of array is: " + union.length);
        System.out.println("Union. Range1: " + union[0]);
        if (union.length == 2) {
            System.out.println("Union. Range2: " + union[1]);
        }

        Range[] difference = range1.getDifference(range2);
        System.out.println("Difference. Length of array is: " + difference.length);
        if (difference.length > 0) {
            System.out.println("Difference. Range1: " + difference[0]);
            if (difference.length == 2) {
                System.out.println("Difference. Range2: " + difference[1]);
            }
        } else {
            System.out.println("Difference. Range: null");
        }
    }
}
