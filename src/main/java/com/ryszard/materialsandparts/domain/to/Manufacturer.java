package com.ryszard.materialsandparts.domain.to;

import java.util.StringJoiner;

public class Manufacturer {

    private int manufacturerId;

    private String manufacturerTitle;

    public Manufacturer() {
    }

    public Manufacturer(String manufacturerTitle) {
        this.manufacturerTitle = manufacturerTitle;
    }

    public Manufacturer(int manufacturerId, String manufacturerTitle) {

        this.manufacturerTitle = manufacturerTitle;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
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

        if (manufacturerId != that.manufacturerId) return false;
        return manufacturerTitle != null ? manufacturerTitle.equals(that.manufacturerTitle) : that.manufacturerTitle == null;
    }

    @Override
    public int hashCode() {
        int result = manufacturerId;
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
