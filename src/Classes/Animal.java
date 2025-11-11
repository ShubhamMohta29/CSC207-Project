package Classes;

public class Animal {
    private String name;
    private String taxonomy;
    private String habitat;
    private String prey;
    private String mostDistinctiveFeature; // or slogan
    private double lifespan;
    private String diet;
    private String lifestyle;
    private double weight;
    private double height;
    private String group;

    public static double parseAverageLifespanYears(String lifespanStr) {
        if (lifespanStr == null || lifespanStr.isEmpty()) {
            return 0;
        }

        lifespanStr = lifespanStr.replaceAll("years?", "").trim();

        String[] parts = lifespanStr.split("-");

        if (parts.length == 2) {
            double low = Double.parseDouble(parts[0].trim());
            double high = Double.parseDouble(parts[1].trim());
            return (low + high) / 2.0;
        } else if (parts.length == 1) {
            return Double.parseDouble(parts[0].trim());
        }

        return 0;
    }


    public static double parseAverageHeightCm(String heightStr) {
        if (heightStr == null || heightStr.isEmpty()) {
            return 0;
        }

        String cmPart = heightStr.split("\\(")[0];
        cmPart = cmPart.replaceAll("cm", "").trim();
        String[] parts = cmPart.split("-");

        if (parts.length == 2) {
            double low = Double.parseDouble(parts[0].trim());
            double high = Double.parseDouble(parts[1].trim());
            return (low + high) / 2.0;
        } else if (parts.length == 1) {
            return Double.parseDouble(parts[0].trim());
        }

        return 0;
    }


    public static double parseAverageWeightKg(String weightStr) {
        if (weightStr == null || weightStr.isEmpty()) {
            return 0;
        }

        String kgPart = weightStr.split("\\(")[0];

        kgPart = kgPart.replaceAll("kg", "").trim();
        String[] parts = kgPart.split("-");
        if (parts.length == 2) {
            double low = Double.parseDouble(parts[0].trim());
            double high = Double.parseDouble(parts[1].trim());
            return (low + high) / 2.0; // average
        } else if (parts.length == 1) {
            return Double.parseDouble(parts[0].trim());
        }

        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getPrey() {
        return prey;
    }

    public void setPrey(String prey) {
        this.prey = prey;
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
        this.lifespan = parseAverageLifespanYears(lifespan);
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
        this.weight = parseAverageWeightKg(weight);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = parseAverageHeightCm(height);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
