package Classes;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents an animal with various characteristics.
 * Parses animal data from JSON format.
 */
public final class Animal {
    private final AnimalConverter conv = new AnimalConverter();
    private String name;
    private java.util.Map<String, String> taxonomy;
    private String habitat;
    private String[] location;
    private String[] prey;
    private String mostDistinctiveFeature;
    private double lifespan;
    private String diet;
    private String lifestyle;
    private double weight;
    private double height;
    private String group;
    private String type;

    /**
     * Constructs an Animal from JSON string data.
     *
     * @param str the JSON string containing animal data
     */
    public Animal(final String str) {
        if (str == null || str.trim().isEmpty() || "[]".equals(str)) {
            throw new IllegalArgumentException(
                    "Cannot create Animal from null, empty, or empty array string");
        }

        final JSONArray obj = new JSONArray(str);
        final JSONObject animal = obj.optJSONObject(0);
        final JSONObject characteristics = animal.optJSONObject("characteristics");
        setName(animal.optString("name"));
        final JSONObject taxonomyObj = animal.optJSONObject("taxonomy");

        final java.util.Map<String, String> taxonomyMap = new java.util.HashMap<>();
        for (final String key : taxonomyObj.keySet()) {
            taxonomyMap.put(key, taxonomyObj.optString(key));
        }
        setTaxonomy(taxonomyMap);
        setHabitat(characteristics.optString("habitat"));

        final JSONArray locations = animal.optJSONArray("locations");
        final String[] tempLoc = new String[locations.length()];
        for (int i = 0; i < locations.length(); i++) {
            tempLoc[i] = locations.get(i).toString();
        }
        setLocation(tempLoc);

        setPrey(characteristics.optString("prey"));
        setMostDistinctiveFeature(characteristics.optString("most_distinctive_feature"));
        setLifespan(characteristics.optString("lifespan"));
        setDiet(characteristics.optString("diet"));
        setLifestyle(characteristics.optString("lifestyle"));
        setWeight(characteristics.optString("weight"));
        if (characteristics.optString("height").equals("")) {
            if (characteristics.optString("length").equals("")) {
                setHeight("0cm");
            }
            setHeight(characteristics.optString("length"));
        }
        else {
            setHeight(characteristics.optString("height"));
        }
        setGroup(characteristics.optString("group"));
        setType(characteristics.optString("type"));
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(final String[] location) {
        this.location = location;
    }

    /**
     * Gets the taxonomy information as a formatted string.
     *
     * @return formatted taxonomy string
     */
    public String getTaxonomy() {
        final String unknown = "Unknown";
        final String comma = ", ";
        final String kingdom = taxonomy.getOrDefault("kingdom", unknown);
        final String phylum = taxonomy.getOrDefault("phylum", unknown);
        final String clazz = taxonomy.getOrDefault("class", unknown);
        final String order = taxonomy.getOrDefault("order", unknown);
        final String family = taxonomy.getOrDefault("family", unknown);
        final String genus = taxonomy.getOrDefault("genus", unknown);
        final String scientificName = taxonomy.getOrDefault("scientific_name", unknown);

        return "Kingdom: " + kingdom + comma
                + "Phylum: " + phylum + comma
                + "Class: " + clazz + comma
                + "Order: " + order + comma
                + "Family: " + family + comma
                + "Genus: " + genus + comma
                + "Scientific Name: " + scientificName;
    }

    /**
     * Gets the scientific name from taxonomy.
     *
     * @return the scientific name
     */
    public String getScientificName() {
        return taxonomy.getOrDefault("scientific_name", "Unknown");
    }

    public void setTaxonomy(final java.util.Map<String, String> taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(final String habitat) {
        this.habitat = habitat;
    }

    public String[] getPrey() {
        return prey;
    }

    /**
     * Sets the prey from a comma-separated string.
     *
     * @param preyString comma-separated prey types
     */
    public void setPrey(final String preyString) {
        final String[] parts = preyString.split(",\\s*");
        this.prey = parts;
    }

    public String getMostDistinctiveFeature() {
        return mostDistinctiveFeature;
    }

    public void setMostDistinctiveFeature(final String mostDistinctiveFeature) {
        this.mostDistinctiveFeature = mostDistinctiveFeature;
    }

    public double getLifespan() {
        return lifespan;
    }

    public void setLifespan(final String lifespan) {
        this.lifespan = conv.parseAverageLifespanYears(lifespan);
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(final String diet) {
        this.diet = diet;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(final String lifestyle) {
        this.lifestyle = lifestyle;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final String weight) {
        this.weight = conv.parseAverageWeightKg(weight);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final String height) {
        this.height = conv.parseAverageHeightCm(height);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n"
                + "Taxonomy: " + getTaxonomy() + "\n"
                + "Habitat: " + convertedToString(getLocation()) + "\n"
                + "Prey: " + convertedToString(getPrey()) + "\n"
                + "Most Distinctive Feature: " + getMostDistinctiveFeature() + "\n"
                + "Lifespan: " + getLifespan() + "\n"
                + "Diet: " + getDiet() + "\n"
                + "Lifestyle: " + getLifestyle() + "\n"
                + "Weight: " + getWeight() + "\n"
                + "Height: " + getHeight() + "\n"
                + "Group: " + getGroup();
    }

    private String convertedToString(final String[] preys) {
        String out = "";
        for (final String x : preys) {
            out += x + ", ";
        }
        out.trim();
        out = out.substring(0, out.length() - 2);
        return out;
    }
}
