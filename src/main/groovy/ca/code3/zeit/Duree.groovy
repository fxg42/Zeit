package ca.code3.zeit

class Duree {
    static enum Unite { MINUTE, HEURE }

    private int qte
    private Unite unite

    Duree (int qte, Unite unite) {
        this.qte = qte
        this.unite = unite
    }
}
