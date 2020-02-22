package com.ryszard.materialsandparts.domain.to;

public enum Thickness {
    ONE(1),
    EIGHT(8),
    TEN(10),
    TWELVE(12),
    SIXTEEN(16),
    EIGHTEEN(18),
    NINETEEN(19),
    TWENTY(20),
    TWENTY_TWO(22);


    private int i;

    Thickness(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
