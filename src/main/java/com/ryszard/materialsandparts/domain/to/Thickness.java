package com.ryszard.materialsandparts.domain.to;

public class Thickness {


    private Long thicknessId;
    private int size;

    public Thickness(int size) {
        this.size = size;
    }

    public int getThicknessSize() {
        return size;
    }

    Thickness() {
    }



    public Thickness(Long thicknessId, int size) {
        this.thicknessId = thicknessId;
        this.size = size;
    }

    public Long getThicknessId() {
        return thicknessId;
    }

    public void setThicknessId(Long thicknessId) {
        this.thicknessId = thicknessId;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thickness)) return false;

        Thickness thickness = (Thickness) o;

        if (size != thickness.size) return false;
        return thicknessId != null ? thicknessId.equals(thickness.thicknessId) : thickness.thicknessId == null;
    }

    @Override
    public int hashCode() {
        int result = thicknessId != null ? thicknessId.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }
}
