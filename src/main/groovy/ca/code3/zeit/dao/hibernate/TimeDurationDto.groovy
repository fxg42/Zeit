package ca.code3.zeit.dao.hibernate

import groovy.time.*

class TimeDurationDto implements Serializable {
    int days, hours, minutes, seconds, millis
    
    static TimeDurationDto from (TimeDuration duration) {
        new TimeDurationDto(
            days    : duration.days,
            hours   : duration.hours,
            minutes : duration.minutes,
            seconds : duration.seconds,
            millis  : duration.millis,
        )
    }

    TimeDuration toTimeDuration () {
        new TimeDuration(days, hours, minutes, seconds, millis)
    }
}
