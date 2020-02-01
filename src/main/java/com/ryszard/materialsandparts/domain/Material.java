package com.ryszard.materialsandparts.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class Material {

    private Long materialId;
    private String title;
    private String vCode;
    private String type;
    private int thickness;
    private int length;
    private int width;
    private String color;
    private String description;
    private double price;
    private double coefficient;

    public Material(){

    }

    public Material(Long materialId, String title, String vCode, String type, int thickness, int length, int width,
                    String color, String description, double price, double coefficient) {
        this.materialId = materialId;
        this.title = title;
        this.vCode = vCode;
        this.type = type;
        this.thickness = thickness;
        this.length = length;
        this.width = width;
        this.color = color;
        this.description = description;
        this.price = price;
        this.coefficient = coefficient;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
