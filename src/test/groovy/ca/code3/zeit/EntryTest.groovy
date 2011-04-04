package ca.code3.zeit

import groovy.time.*
import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class EntryTest {
    def client, projet, user

    @Before void set_up () {
        client = new Client(name:"nom du client")
        projet = new Project(client:client, mnemonic:"TEST")
        user = new User(email:"test@test.test", name:"test")
    }

    @Test void it_should_hold_all_information () {
        use (TimeCategory) {
            new Entry(user, projet, "contenu du commentaire", new Date(), 1.hour)
            new Entry(user, projet, new Date(), 30.minutes)
        }
    }

    @Test(expected = IllegalArgumentException) void comments_shouldnt_be_longer_than_140_chars () {
        use (TimeCategory) {
            def longComment = "a"*141
            new Entry(user, projet, longComment, new Date(), 2.hours)
        }
    }

}
