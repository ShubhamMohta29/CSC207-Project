package classes.retrieveInfo;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public final class animal {
    private final String name;
    private final Map<String, String> taxonomy;
    private final String habitat;
    private final String[] locations;
    private final String[] prey;
    private final String mostDistinctiveFeature;
    private final double lifespanYears;
    private final String diet;
    private final String lifestyle;
    private final double weightKg;
    private final double heightCm;
    private final String group;
    private final String type;
    private final String na = "N/A";

    public animal(
            String name,
            Map<String, String> taxonomy,
            String habitat,
            String[] locations,
            String[] prey,
            String mostDistinctiveFeature,
            double lifespanYears,
            String diet,
            String lifestyle,
            double weightKg,
            double heightCm,
            String group,
            String type
    ) {
        this.name = name;
        this.taxonomy = taxonomy;
        this.habitat = habitat;
        this.locations = Objects.requireNonNullElseGet(locations, () -> new String[0]);
        this.prey = Objects.requireNonNullElseGet(prey, () -> new String[0]);
        this.mostDistinctiveFeature = mostDistinctiveFeature;
        this.lifespanYears = lifespanYears;
        this.diet = diet;
        this.lifestyle = lifestyle;
        this.weightKg = weightKg;
        this.heightCm = heightCm;
        this.group = group;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getTaxonomy() {
        return taxonomy;
    }

    public String getHabitat() {
        return habitat;
    }

    public String[] getLocation() {
        return locations.clone();
    }

    public String[] getPrey() {
        return prey.clone();
    }

    public String getMostDistinctiveFeature() {
        return mostDistinctiveFeature;
    }

    public double getLifespan() {
        return lifespanYears;
    }

    public String getDiet() {
        return diet;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public double getWeight() {
        return weightKg;
    }

    public double getHeight() {
        return heightCm;
    }

    public String getGroup() {
        return group;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner("\n");
        sj.add("Name: " + safe(name));
        sj.add("Taxonomy: " + formatTaxonomy());
        sj.add("Habitat: " + safe(habitat));
        sj.add("Locations: " + joinArray(locations));
        sj.add("Prey: " + joinArray(prey));
        sj.add("Most Distinctive Feature: " + safe(mostDistinctiveFeature));
        sj.add(String.format("Lifespan (yrs): %.2f", lifespanYears));
        sj.add("Diet: " + safe(diet));
        sj.add("Lifestyle: " + safe(lifestyle));
        sj.add(String.format("Weight (kg): %.2f", weightKg));
        sj.add(String.format("Height (cm): %.2f", heightCm));
        sj.add("Group: " + safe(group));
        sj.add("Type: " + safe(type));
        return sj.toString();
    }

    private String safe(String parameter) {
        String result = parameter;
        if (parameter == null || parameter.isEmpty()) {
            result = na;
        }
        return result;
    }

    private String joinArray(String[] arr) {
        String result = na;

        if (arr != null && arr.length > 0) {
            final StringJoiner joiner = new StringJoiner(", ");
            for (String x : arr) {
                joiner.add(x);
            }
            result = joiner.toString();
        }
        return result;
    }

    private String formatTaxonomy() {
        String result = na;

        if (taxonomy != null && !taxonomy.isEmpty()) {
            final StringJoiner joiner = new StringJoiner(", ");
            for (Map.Entry<String, String> entry : taxonomy.entrySet()) {
                joiner.add(capitalize(entry.getKey()) + ": " + safe(entry.getValue()));
            }
            result = joiner.toString();
        }

        return result;
    }

    private String capitalize(String str) {
        String result = str;

        if (str != null && !str.isEmpty()) {
            result = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }

        return result;
    }
}
