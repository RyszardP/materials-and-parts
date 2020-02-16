package com.ryszard.materialsandparts.domain.vo;

import java.util.StringJoiner;

public class Plate {

    private Long plateId;
    private String plateType;
    private String plateManufacturer;
    private String plateThickness;
    private String plateVCode;
    private String plateSizes;
    private String plateDescription;
    private String platePrice;
    private String plateCoefficient;

    public Plate() {
    }

    public Plate(Long plateId, String plateType, String plateManufacturer, String plateThickness, String plateVCode,
                 String plateSizes, String plateDescription, String platePrice, String plateCoefficient) {
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

    public Long getPlateId() {
        return plateId;
    }

    public void setPlateId(Long plateId) {
        this.plateId = plateId;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getPlateManufacturer() {
        return plateManufacturer;
    }

    public void setPlateManufacturer(String plateManufacturer) {
        this.plateManufacturer = plateManufacturer;
    }

    public String getPlateThickness() {
        return plateThickness;
    }

    public void setPlateThickness(String plateThickness) {
        this.plateThickness = plateThickness;
    }

    public String getPlateVCode() {
        return plateVCode;
    }

    public void setPlateVCode(String plateVCode) {
        this.plateVCode = plateVCode;
    }

    public String getPlateSizes() {
        return plateSizes;
    }

    public void setPlateSizes(String plateSizes) {
        this.plateSizes = plateSizes;
    }

    public String getPlateDescription() {
        return plateDescription;
    }

    public void setPlateDescription(String plateDescription) {
        this.plateDescription = plateDescription;
    }

    public String getPlatePrice() {
        return platePrice;
    }

    public void setPlatePrice(String platePrice) {
        this.platePrice = platePrice;
    }

    public String getPlateCoefficient() {
        return plateCoefficient;
    }

    public void setPlateCoefficient(String plateCoefficient) {
        this.plateCoefficient = plateCoefficient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plate)) return false;

        Plate plate = (Plate) o;

        if (plateId != null ? !plateId.equals(plate.plateId) : plate.plateId != null) return false;
        if (plateType != null ? !plateType.equals(plate.plateType) : plate.plateType != null) return false;
        if (plateManufacturer != null ? !plateManufacturer.equals(plate.plateManufacturer) : plate.plateManufacturer != null)
            return false;
        if (plateThickness != null ? !plateThickness.equals(plate.plateThickness) : plate.plateThickness != null)
            return false;
        if (plateVCode != null ? !plateVCode.equals(plate.plateVCode) : plate.plateVCode != null) return false;
        if (plateSizes != null ? !plateSizes.equals(plate.plateSizes) : plate.plateSizes != null) return false;
        if (plateDescription != null ? !plateDescription.equals(plate.plateDescription) : plate.plateDescription != null)
            return false;
        if (platePrice != null ? !platePrice.equals(plate.platePrice) : plate.platePrice != null) return false;
        return plateCoefficient != null ? plateCoefficient.equals(plate.plateCoefficient) : plate.plateCoefficient == null;
    }

    @Override
    public int hashCode() {
        int result = plateId != null ? plateId.hashCode() : 0;
        result = 31 * result + (plateType != null ? plateType.hashCode() : 0);
        result = 31 * result + (plateManufacturer != null ? plateManufacturer.hashCode() : 0);
        result = 31 * result + (plateThickness != null ? plateThickness.hashCode() : 0);
        result = 31 * result + (plateVCode != null ? plateVCode.hashCode() : 0);
        result = 31 * result + (plateSizes != null ? plateSizes.hashCode() : 0);
        result = 31 * result + (plateDescription != null ? plateDescription.hashCode() : 0);
        result = 31 * result + (platePrice != null ? platePrice.hashCode() : 0);
        result = 31 * result + (plateCoefficient != null ? plateCoefficient.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Plate.class.getSimpleName() + "[", "]")
                .add("plateId=" + plateId)
                .add("plateType='" + plateType + "'")
                .add("plateManufacturer='" + plateManufacturer + "'")
                .add("plateThickness='" + plateThickness + "'")
                .add("plateVCode='" + plateVCode + "'")
                .add("plateSizes='" + plateSizes + "'")
                .add("plateDescription='" + plateDescription + "'")
                .add("platePrice='" + platePrice + "'")
                .add("plateCoefficient='" + plateCoefficient + "'")
                .toString();
    }
}
