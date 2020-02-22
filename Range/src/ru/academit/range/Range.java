package ru.academit.range;

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

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return from < number && number < to;
    }

    public Range rangeIntersection(Range range) {
        if (this.getFrom() < range.getTo() && this.getTo() > range.getFrom()) {
            return new Range(Math.max(this.getFrom(), range.getFrom()), Math.min(this.getTo(), range.getTo()));
        }
        return null;
    }

    public Range[] unionRanges(Range range) {
        if (this.isInside(range.getFrom()) || range.isInside(this.getFrom())) {
            return new Range[]
                    {new Range(Math.min(this.getFrom(), range.getFrom()), Math.max(this.getTo(), range.getTo()))};
        }
        return new Range[]{this, range};
    }

    public Range[] differenceRanges(Range range) {
        if (this.isInside(range.getFrom()) || this.isInside(range.getTo())) {
            if (this.getFrom() > range.getFrom()) {
                return new Range[]{new Range(this.getFrom(), range.getTo())};
            }

            if (this.getFrom() < range.getFrom() && this.getTo() > range.getTo()) {
                return new Range[]{new Range(range.getTo(), this.getTo()),
                        new Range(range.getTo(), this.getTo())};
            }

            if (this.getFrom() < range.getFrom() && this.getTo() < range.getTo()) {
                return new Range[]{new Range(this.getFrom(), range.getFrom())};
            }
        }
        return null;
    }


}
