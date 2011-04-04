package ca.code3.zeit.dao.hibernate

import groovy.time.*
import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class TimeDurationDtoTest {
    
    @Test void it_should_transform_time_durations_to_and_fro () {
        use (TimeCategory) {
            def original = 2.hours
            def dto = TimeDurationDto.from(original)
            def readBack = dto.toTimeDuration()
            assertEquals original.toMilliseconds(), readBack.toMilliseconds()
        }
    }
}
