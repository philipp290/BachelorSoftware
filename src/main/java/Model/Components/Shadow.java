package Model.Components;

public enum Shadow {

    HOCH(0),
    MITTEL(1),
    NIEDRIG(2),
    KEIN(3);

    private final int value;

    Shadow(int v) {
        this.value = v;
    }

    // Getter
    public int getValue() {
        return value;
    }


}
