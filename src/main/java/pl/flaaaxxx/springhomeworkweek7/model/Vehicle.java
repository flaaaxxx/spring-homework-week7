package pl.flaaaxxx.springhomeworkweek7.model;

import java.util.Date;

public class Vehicle {
    private long id;
    private String mark;
    private String model;
    private String color;
    private Date productionDate;

    public Vehicle() {
    }

    public Vehicle(long id, String mark, String model, String color, Date productionDate) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionDate = productionDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", productionDate=" + productionDate +
                '}';
    }
}
