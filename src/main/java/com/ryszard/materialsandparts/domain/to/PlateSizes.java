package com.ryszard.materialsandparts.domain.to;

import java.util.StringJoiner;

public class PlateSizes {

    private Long plateSizeId;

    private Long plateLength;

    private Long plateWidth;

    public PlateSizes() {
    }

    public PlateSizes(Long plateSizeId, Long plateLength, Long plateWidth) {
        this.plateSizeId = plateSizeId;
        this.plateLength = plateLength;
        this.plateWidth = plateWidth;
    }

    public Long getPlateSizeId() {
        return plateSizeId;
    }

    public void setPlateSizeId(Long plateSizeId) {
        this.plateSizeId = plateSizeId;
    }

    public Long getPlateLength() {
        return plateLength;
    }

    public void setPlateLength(Long plateLength) {
        this.plateLength = plateLength;
    }

    public Long getPlateWidth() {
        return plateWidth;
    }

    public void setPlateWidth(Long plateWidth) {
        this.plateWidth = plateWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlateSizes)) return false;

        PlateSizes that = (PlateSizes) o;

        if (plateSizeId != null ? !plateSizeId.equals(that.plateSizeId) : that.plateSizeId != null) return false;
        if (plateLength != null ? !plateLength.equals(that.plateLength) : that.plateLength != null) return false;
        return plateWidth != null ? plateWidth.equals(that.plateWidth) : that.plateWidth == null;
    }

    @Override
    public int hashCode() {
        int result = plateSizeId != null ? plateSizeId.hashCode() : 0;
        result = 31 * result + (plateLength != null ? plateLength.hashCode() : 0);
        result = 31 * result + (plateWidth != null ? plateWidth.hashCode() : 0);
        return result;
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
