package ca.code3.zeit

class Duration {
    static enum Unit { MINUTE, HOUR }

    private int qty
    private Unit unit

    Duration (int qty, Unit unit) {
        this.qty = qty
        this.unit = unit
    }
}
