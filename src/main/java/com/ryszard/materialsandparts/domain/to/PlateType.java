package com.ryszard.materialsandparts.domain.to;

import java.util.StringJoiner;

public class PlateType {
    private Long plateTypeId;

    private String plateTypeName;

    public PlateType() {
    }

    public PlateType(String plateTypeName) {
        this.plateTypeName = plateTypeName;
    }

    public PlateType(Long plateTypeId, String plateTypeName) {
        this.plateTypeId = plateTypeId;
        this.plateTypeName = plateTypeName;
    }

    public Long getPlateTypeId() {
        return plateTypeId;
    }

    public void setPlateTypeId(Long plateTypeId) {
        this.plateTypeId = plateTypeId;
    }

    public String getPlateTypeName() {
        return plateTypeName;
    }

    public void setPlateTypeName(String plateTypeName) {
        this.plateTypeName = plateTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlateType)) return false;

        PlateType plateType = (PlateType) o;

        if (plateTypeId != null ? !plateTypeId.equals(plateType.plateTypeId) : plateType.plateTypeId != null)
            return false;
        return plateTypeName != null ? plateTypeName.equals(plateType.plateTypeName) : plateType.plateTypeName == null;
    }

    @Override
    public int hashCode() {
        int result = plateTypeId != null ? plateTypeId.hashCode() : 0;
        result = 31 * result + (plateTypeName != null ? plateTypeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlateType.class.getSimpleName() + "[", "]")
                .add("plateTypeId=" + plateTypeId)
                .add("plateTypeName='" + plateTypeName + "'")
                .toString();
    }
}
