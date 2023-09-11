package io.randomizer.name_blender.model;

public record WeightedName(String name, Integer weight, Integer syllableCount) {

    @Override
    public String toString() {
        return name;
    }

    public String nameAndCount() {
        return name + " " + syllableCount;
    }
}
