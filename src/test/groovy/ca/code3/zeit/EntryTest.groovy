package ca.code3.zeit

import groovy.time.*
import javax.validation.*

import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class EntryTest {
    static validator
    def validUser, testEntry

    @BeforeClass static void set_up_once () {
        def factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    @Before void set_up () {
        validUser = new User(email:"test@test.test", name:"test")
        testEntry = new Entry(user: validUser, comment: "the comment.", recordedOn: new Date(), duration: 30, tags: ["tag-a", "tag-b"])
    }

    @Test void it_should_validate_its_user () {
        testEntry.user = new User(email: null, name: null)
        def violations = validator.validate(testEntry)
        assertEquals 2, violations.size()

        testEntry.user = null
        violations = validator.validate(testEntry)
        assertEquals 1, violations.size()
    }

    @Test void it_should_validate_comment_length () {
        testEntry.comment = null
        def violations = validator.validate(testEntry)
        assertEquals 1, violations.size()

        testEntry.comment = ""
        violations = validator.validate(testEntry)
        assertEquals 0, violations.size()

        testEntry.comment = "a"*140
        violations = validator.validate(testEntry)
        assertEquals 0, violations.size()

        testEntry.comment = "a"*141
        violations = validator.validate(testEntry)
        assertEquals 1, violations.size()
    }

    @Test void dates_should_not_be_in_the_future () {
        testEntry.recordedOn = new Date()
        def violations = validator.validate(testEntry)
        assertEquals 0, violations.size()
        
        use (TimeCategory) {
            testEntry.recordedOn = 1.day.ago
            violations = validator.validate(testEntry)
            assertEquals 0, violations.size()

            testEntry.recordedOn = 1.day.from.now
            violations = validator.validate(testEntry)
            assertEquals 1, violations.size()
        }
    }
    
    @Test(expected=IllegalArgumentException)
    void it_should_not_allow_null_dates () {
        testEntry.recordedOn = null
    }

    @Test void duration_should_not_be_less_than_1 () {
        testEntry.duration = 1
        def violations = validator.validate(testEntry)
        assertEquals 0, violations.size()

        [0, -1].each { invalidValue ->
            testEntry.duration = invalidValue
            violations = validator.validate(testEntry)
            assertEquals 1, violations.size()
        }
    }

    @Test void tag_set_should_not_be_null () {
        testEntry.tags = null
        def violations = validator.validate(testEntry)
        assertEquals 1, violations.size()
    }

    @Test void tags_should_not_be_blank () {
        testEntry.tags = ["not-blank", "not-blank"]
        def violations = validator.validate(testEntry)
        assertEquals 0, violations.size()

        testEntry.tags = ["", "not-blank"]
        violations = validator.validate(testEntry)
        assertEquals 1, violations.size()

        testEntry.tags = [null, "not-blank"]
        violations = validator.validate(testEntry)
        assertEquals 1, violations.size()
    }
}
