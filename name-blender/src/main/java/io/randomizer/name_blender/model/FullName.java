package io.randomizer.name_blender.model;

import java.util.Comparator;
import java.util.Objects;

public record FullName(WeightedName first, WeightedName middle, WeightedName surname, Integer score) implements Comparable<FullName>, Comparator<FullName> {

    public FullName(WeightedName first, WeightedName middle, WeightedName surname) {
        this(first, middle, surname, first.weight() + middle.weight() + surname.weight());
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", first, middle, surname);
    }

    @Override
    public int compare(FullName o1, FullName o2) {
        return getScore(o2) - getScore(o1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FullName) obj;
        return Objects.equals(this.first, that.first) &&
                Objects.equals(this.middle, that.middle) &&
                Objects.equals(this.surname, that.surname);
    }

    @Override
    public int compareTo(FullName o) {
        int sumOfThis = getScore(this);
        int sumOfComparing = getScore(o);

        return sumOfComparing - sumOfThis;
    }

    public static int getScore(FullName o) {
        return o.first.weight() + o.middle.weight() + o.surname.weight();
    }
}
