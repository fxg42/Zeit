package ca.code3.zeit

import groovy.time.*
import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class EntryTest {
    def client, project, user

    @Before void set_up () {
        client = new Client(name:"client name")
        project = new Project(client:client, mnemonic:"TEST")
        user = new User(email:"test@test.test", name:"test")
    }

    @Test void it_should_hold_all_information () {
        use (TimeCategory) {
            new Entry(user:user, project:project, comment:"comment...", recordedOn:new Date(), duration:30)
        }
    }
}
