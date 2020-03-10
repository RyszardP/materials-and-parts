package com.ryszard.materialsandparts.domain.to;

import java.util.StringJoiner;

public class PlateSizes {

    private int plateSizeId;

    private int plateLength;

    private int plateWidth;

    public PlateSizes() {
    }

    public PlateSizes(int plateLength, int plateWidth) {
        this.plateLength = plateLength;
        this.plateWidth = plateWidth;
    }

    public PlateSizes(int plateSizeId, int plateLength, int plateWidth) {
        this.plateSizeId = plateSizeId;
        this.plateLength = plateLength;
        this.plateWidth = plateWidth;
    }

    public int getPlateSizeId() {
        return plateSizeId;
    }

    public void setPlateSizeId(int plateSizeId) {
        this.plateSizeId = plateSizeId;
    }

    public int getPlateLength() {
        return plateLength;
    }

    public void setPlateLength(int plateLength) {
        this.plateLength = plateLength;
    }

    public int getPlateWidth() {
        return plateWidth;
    }

    public void setPlateWidth(int plateWidth) {
        this.plateWidth = plateWidth;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlateSizes.class.getSimpleName() + "[", "]")
                .add("plateSizeId=" + plateSizeId)
                .add("plateLength=" + plateLength)
                .add("plateWidth=" + plateWidth)
                .toString();
    }
}
