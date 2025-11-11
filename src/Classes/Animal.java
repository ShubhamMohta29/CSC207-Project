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
    AnimalConverter conv =  new AnimalConverter();

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
}
