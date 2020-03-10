package com.ryszard.materialsandparts.domain.to;

import java.util.StringJoiner;

public class Plate {

    private int plateId;
    private int plateType;
    private int plateManufacturer;
    private int plateThickness;
    private int plateVCode;
    private int plateSizes;
    private String plateDescription;
    private double platePrice;
    private double plateCoefficient;

    public Plate() {
    }

    public Plate(int plateType, int plateManufacturer, int plateThickness, int plateVCode, int plateSizes,
                 String plateDescription, double platePrice, double plateCoefficient) {
        this.plateType = plateType;
        this.plateManufacturer = plateManufacturer;
        this.plateThickness = plateThickness;
        this.plateVCode = plateVCode;
        this.plateSizes = plateSizes;
        this.plateDescription = plateDescription;
        this.platePrice = platePrice;
        this.plateCoefficient = plateCoefficient;
    }

    public Plate(int plateId, int plateType, int plateManufacturer, int plateThickness, int plateVCode,
                 int plateSizes, String plateDescription, double platePrice, double plateCoefficient) {
        this.plateId = plateId;
        this.plateType = plateType;
        this.plateManufacturer = plateManufacturer;
        this.plateThickness = plateThickness;
        this.plateVCode = plateVCode;
        this.plateSizes = plateSizes;
        this.plateDescription = plateDescription;
        this.platePrice = platePrice;
        this.plateCoefficient = plateCoefficient;
    }

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    public int getPlateType() {
        return plateType;
    }

    public void setPlateType(int plateType) {
        this.plateType = plateType;
    }

    public int getPlateManufacturer() {
        return plateManufacturer;
    }

    public void setPlateManufacturer(int plateManufacturer) {
        this.plateManufacturer = plateManufacturer;
    }

    public int getPlateThickness() {
        return plateThickness;
    }

    public void setPlateThickness(int plateThickness) {
        this.plateThickness = plateThickness;
    }

    public int getPlateVCode() {
        return plateVCode;
    }

    public void setPlateVCode(int plateVCode) {
        this.plateVCode = plateVCode;
    }

    public int getPlateSizes() {
        return plateSizes;
    }

    public void setPlateSizes(int plateSizes) {
        this.plateSizes = plateSizes;
    }

    public String getPlateDescription() {
        return plateDescription;
    }

    public void setPlateDescription(String plateDescription) {
        this.plateDescription = plateDescription;
    }

    public double getPlatePrice() {
        return platePrice;
    }

    public void setPlatePrice(double platePrice) {
        this.platePrice = platePrice;
    }

    public double getPlateCoefficient() {
        return plateCoefficient;
    }

    public void setPlateCoefficient(double plateCoefficient) {
        this.plateCoefficient = plateCoefficient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plate)) return false;

        Plate plate = (Plate) o;

        if (plateId != plate.plateId) return false;
        if (plateType != plate.plateType) return false;
        if (plateManufacturer != plate.plateManufacturer) return false;
        if (plateThickness != plate.plateThickness) return false;
        if (plateVCode != plate.plateVCode) return false;
        if (plateSizes != plate.plateSizes) return false;
        if (Double.compare(plate.platePrice, platePrice) != 0) return false;
        if (Double.compare(plate.plateCoefficient, plateCoefficient) != 0) return false;
        return plateDescription != null ? plateDescription.equals(plate.plateDescription) : plate.plateDescription == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = plateId;
        result = 31 * result + plateType;
        result = 31 * result + plateManufacturer;
        result = 31 * result + plateThickness;
        result = 31 * result + plateVCode;
        result = 31 * result + plateSizes;
        result = 31 * result + (plateDescription != null ? plateDescription.hashCode() : 0);
        temp = Double.doubleToLongBits(platePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(plateCoefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Plate.class.getSimpleName() + "[", "]")
                .add("plateId=" + plateId)
                .add("plateType=" + plateType)
                .add("plateManufacturer=" + plateManufacturer)
                .add("plateThickness=" + plateThickness)
                .add("plateVCode=" + plateVCode)
                .add("plateSizes=" + plateSizes)
                .add("plateDescription='" + plateDescription + "'")
                .add("platePrice='" + platePrice + "'")
                .add("plateCoefficient='" + plateCoefficient + "'")
                .toString();
    }
}
