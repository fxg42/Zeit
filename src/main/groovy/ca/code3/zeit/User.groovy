package ca.code3.zeit

import org.hibernate.validator.constraints.*

class User {
    int id, rev /* for Hibernate */

    @NotBlank @Email String email
    @NotBlank String name
}
