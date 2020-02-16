package com.ryszard.materialsandparts.domain.vo;

import java.util.StringJoiner;

public class PlateVCode {

    private Long plateVCodeId;

    private String plateVCodeTitle;

    private String plateVCodeDescription;

    public PlateVCode() {
    }

    public PlateVCode(Long plateVCodeId, String plateVCodeTitle, String plateVCodeDescription) {
        this.plateVCodeId = plateVCodeId;
        this.plateVCodeTitle = plateVCodeTitle;
        this.plateVCodeDescription = plateVCodeDescription;
    }

    public Long getPlateVCodeId() {
        return plateVCodeId;
    }

    public void setPlateVCodeId(Long plateVCodeId) {
        this.plateVCodeId = plateVCodeId;
    }

    public String getPlateVCodeTitle() {
        return plateVCodeTitle;
    }

    public void setPlateVCodeTitle(String plateVCodeTitle) {
        this.plateVCodeTitle = plateVCodeTitle;
    }

    public String getPlateVCodeDescription() {
        return plateVCodeDescription;
    }

    public void setPlateVCodeDescription(String plateVCodeDescription) {
        this.plateVCodeDescription = plateVCodeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlateVCode)) return false;

        PlateVCode that = (PlateVCode) o;

        if (plateVCodeId != null ? !plateVCodeId.equals(that.plateVCodeId) : that.plateVCodeId != null) return false;
        if (plateVCodeTitle != null ? !plateVCodeTitle.equals(that.plateVCodeTitle) : that.plateVCodeTitle != null)
            return false;
        return plateVCodeDescription != null ? plateVCodeDescription.equals(that.plateVCodeDescription) : that.plateVCodeDescription == null;
    }

    @Override
    public int hashCode() {
        int result = plateVCodeId != null ? plateVCodeId.hashCode() : 0;
        result = 31 * result + (plateVCodeTitle != null ? plateVCodeTitle.hashCode() : 0);
        result = 31 * result + (plateVCodeDescription != null ? plateVCodeDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlateVCode.class.getSimpleName() + "[", "]")
                .add("plateVCodeId=" + plateVCodeId)
                .add("plateVCodeTitle='" + plateVCodeTitle + "'")
                .add("plateVCodeDescription='" + plateVCodeDescription + "'")
                .toString();
    }
}
