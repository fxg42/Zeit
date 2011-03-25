package ca.code3.zeit

import org.junit.*
import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*

class TestActivite {
    def client, projet, individu

    @Before void set_up () {
        client = new Client("nom du client")
        projet = new Projet(client, "nom du projet")
        individu = new Individu("fxg@code3.ca", "nom de l'individu")
    }

    @Test void it_should_hold_all_information () {
        new Activite(individu, projet, "contenu du commentaire", new Date(), new Duree(60, Duree.Unite.MINUTE))
        new Activite(individu, projet, new Date(), new Duree(60, Duree.Unite.MINUTE))
    }

    @Test(expected = IllegalArgumentException) void comments_shouldnt_be_longer_than_140_chars () {
        def longComment = "a"*141
        new Activite(individu, projet, longComment, new Date(), new Duree(60, Duree.Unite.MINUTE))
    }

}
