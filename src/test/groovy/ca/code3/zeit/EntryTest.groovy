package ca.code3.zeit

import groovy.time.*
import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class EntryTest {
    def user

    @Before void set_up () {
        user = new User(email:"test@test.test", name:"test")
    }

    @Test void it_should_hold_all_information () {
        use (TimeCategory) {
            new Entry(user:user, comment:"comment...", recordedOn:new Date(), duration:30, tags: ["tag-a", "tag-b", "tag-c"])
        }
    }
}
