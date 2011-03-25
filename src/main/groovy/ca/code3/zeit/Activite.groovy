package ca.code3.zeit

class Activite {
    private individu, projet, commentaire, date, duree

    Activite (individu, projet, date, duree) {
        this(individu, projet, "", date, duree)
    }

    Activite (individu, projet, commentaire, date, duree) {
        if (commentaire.size() > 140) throw new IllegalArgumentException("Le commentaire <${commentaire}> depasse la limite permise de 140 chars.")
        
        this.individu = individu
        this.projet = projet
        this.date = date
        this.duree = duree
        this.commentaire = commentaire
    }
}
