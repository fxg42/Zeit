package ca.code3.zeit

import org.junit.*
import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*

class EntryTest {
    def client, projet, user

    @Before void set_up () {
        client = new Client("nom du client")
        projet = new Project(client, "nom du projet")
        user = new User("fxg@code3.ca", "nom de l'user")
    }

    @Test void it_should_hold_all_information () {
        new Entry(user, projet, "contenu du commentaire", new Date(), new Duration(60, Duration.Unit.MINUTE))
        new Entry(user, projet, new Date(), new Duration(60, Duration.Unit.MINUTE))
    }

    @Test(expected = IllegalArgumentException) void comments_shouldnt_be_longer_than_140_chars () {
        def longComment = "a"*141
        new Entry(user, projet, longComment, new Date(), new Duration(60, Duration.Unit.MINUTE))
    }

}
