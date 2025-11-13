package Classes;

import org.json.JSONArray;
import org.json.JSONObject;


//Important Fields
public class Animal {
    private String name;
    private java.util.Map<String, String> taxonomy;
    private String habitat;
    private String[] location;
    private String[] prey;
    private String mostDistinctiveFeature; // or slogan
    private double lifespan;
    private String diet;
    private String lifestyle;
    private double weight;
    private double height;
    private String group;
    private final AnimalConverter conv = new AnimalConverter();

    public Animal(String s){
        //Remove this comement
        JSONArray obj = new JSONArray(s);
        JSONObject animal = obj.optJSONObject(0);
        JSONObject characteristics = animal.optJSONObject("characteristics");
        setName(animal.optString("name"));
        JSONObject taxonomyObj = animal.optJSONObject("taxonomy");

        java.util.Map<String, String> taxonomyMap = new java.util.HashMap<>();
        for (String key : taxonomyObj.keySet()) {
            taxonomyMap.put(key, taxonomyObj.optString(key));
        }
        setTaxonomy(taxonomyMap);
        setHabitat(characteristics.optString("habitat"));

        JSONArray locations = animal.optJSONArray("locations");
        String[] tempLoc = new String[locations.length()];
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
        else{
            setHeight(characteristics.optString("height"));
        }
        setGroup(characteristics.optString("group"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public java.util.Map<String, String> getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(java.util.Map<String, String> taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String[] getPrey() {
        return prey;
    }


    public void setPrey(String preyString) {
        String[] parts = preyString.split(",\\s*");
        this.prey = parts;
    }

    public String getMostDistinctiveFeature() {
        return mostDistinctiveFeature;
    }

    public void setMostDistinctiveFeature(String mostDistinctiveFeature) {
        this.mostDistinctiveFeature = mostDistinctiveFeature;
    }

    public double getLifespan() {
        return lifespan;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = conv.parseAverageLifespanYears(lifespan);
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(String lifestyle) {
        this.lifestyle = lifestyle;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = conv.parseAverageWeightKg(weight);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = conv.parseAverageHeightCm(height);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n"
                + "Taxonomy: " + getTaxonomy() + "\n"
                + "Habitat: " + getLocation() + "\n"
                + "Prey: " + convertedToString(getPrey()) + "\n"
                + "Most Distinctive Feature: " + getMostDistinctiveFeature() + "\n"
                + "Lifespan: " + getLifespan() + "\n"
                + "Diet: " + getDiet() + "\n"
                + "Lifestyle: " + getLifestyle() + "\n"
                + "Weight: " + getWeight() + "\n"
                + "Height: " + getHeight() + "\n"
                + "Group: " + getGroup();
    }

    private String convertedToString(String[] preys) {
        String out = "";
        for (String x : preys) {
            out += x + ", ";
        }
        out.trim();
        out = out.substring(0, out.length() - 2);
        return out;
    }
}
