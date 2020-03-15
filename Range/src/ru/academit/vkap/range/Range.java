package ru.academit.vkap.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return from <= number && number <= to;
    }

    public Range getIntersection(Range range) {
        if (this.from < range.to && this.to > range.from) {
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (range.from <= to && from <= range.to) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        if (from < range.from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(range.from, range.to), new Range(from, to)};
    }

    public Range[] getDifference(Range range) {
        if (to <= range.from && from >= range.to) {
            return new Range[]{new Range(from, to)};
        }

        if (from > range.from && to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from),
                    new Range(range.to, to)};
        }

        if (from < range.from && to < range.to) {
            return new Range[]{new Range(from, to)};
        }
        if (from >= range.from && to <= range.to) {
            return new Range[0];
        }

        return new Range[]{new Range(from, to)};
    }

    @Override
    public String toString() {
        return "From:" + from + " To: " + to;
    }
}
