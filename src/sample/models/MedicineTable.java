package sample.models;

public class MedicineTable {
    private Integer id;
    private String name;
    private String activeSubstances;
    private Integer amount;
    private Double cost;
    private String symptoms;

    public MedicineTable(Integer id, String name, String activeSubstances, Integer amount, Double cost, String symptoms) {
        this.id = id;
        this.name = name;
        this.activeSubstances = activeSubstances;
        this.amount = amount;
        this.cost = cost;
        this.symptoms = symptoms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActiveSubstances() {
        return activeSubstances;
    }

    public void setActiveSubstances(String activeSubstances) {
        this.activeSubstances = activeSubstances;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
