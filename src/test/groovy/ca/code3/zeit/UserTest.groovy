package ca.code3.zeit

import javax.validation.*

import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class UserTest {

    static validator

    @BeforeClass static void set_up_once () {
        def factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    @Test void it_should_validate_user_entity () {
        def violations = validator.validate(new User(email: "test@test.test", name: "test"))
        assertEquals 0, violations.size()

        violations = validator.validate(new User(email: null, name: null))
        assertEquals 2, violations.size()

        violations = validator.validate(new User(email: "", name: ""))
        assertEquals 2, violations.size()

        violations = validator.validate(new User(email: "a", name: "test"))
        assertEquals 1, violations.size()
    }
}

