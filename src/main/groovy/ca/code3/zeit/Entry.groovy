package ca.code3.zeit

import javax.validation.*
import javax.validation.constraints.*
import org.hibernate.validator.constraints.*

@ScriptAssert(lang="groovy", script="_this.tags.every{it}")
class Entry {
    int id, rev /* for Hibernate */

    @NotNull @Valid User user
    @NotNull @Length(max=140) String comment
    @NotNull @Past Date recordedOn
    @Min(1L) int duration
    @NotNull Set<String> tags = []

    /* return a copy */
    Date getRecordedOn () {
        new Date(recordedOn.time)
    }
    /* always copy then remove time component */
    void setRecordedOn (Date recordedOn) {
        if (! recordedOn) throw new IllegalArgumentException("recordedOn may not be null")
        this.recordedOn = new Date(recordedOn.time)
        this.recordedOn.clearTime()
    }
}
