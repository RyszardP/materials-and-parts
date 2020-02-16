package com.ryszard.materialsandparts.domain.vo;

import java.util.StringJoiner;

public class Manufacturer {

    private Long manufacturerId;

    private String manufacturerTitle;

    public Manufacturer() {
    }

    public Manufacturer(Long manufacturerId, String manufacturerTitle) {
        this.manufacturerId = manufacturerId;
        this.manufacturerTitle = manufacturerTitle;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerTitle() {
        return manufacturerTitle;
    }

    public void setManufacturerTitle(String manufacturerTitle) {
        this.manufacturerTitle = manufacturerTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manufacturer)) return false;

        Manufacturer that = (Manufacturer) o;

        if (manufacturerId != null ? !manufacturerId.equals(that.manufacturerId) : that.manufacturerId != null)
            return false;
        return manufacturerTitle != null ? manufacturerTitle.equals(that.manufacturerTitle) : that.manufacturerTitle == null;
    }

    @Override
    public int hashCode() {
        int result = manufacturerId != null ? manufacturerId.hashCode() : 0;
        result = 31 * result + (manufacturerTitle != null ? manufacturerTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Manufacturer.class.getSimpleName() + "[", "]")
                .add("manufacturerId=" + manufacturerId)
                .add("manufacturerTitle='" + manufacturerTitle + "'")
                .toString();
    }


}
