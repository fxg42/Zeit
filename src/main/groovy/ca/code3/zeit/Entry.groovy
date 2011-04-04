package ca.code3.zeit

import groovy.time.*

class Entry {
    int id, rev /* for Hibernate */

    User user
    Project project
    String comment
    Date startDatetime
    TimeDuration duration

    Entry () {} /* for Hibernate */

    Entry (user, project, startDatetime, duration) {
        this(user, project, "", startDatetime, duration)
    }

    Entry (user, project, comment, startDatetime, duration) {
        if (comment.size() > 140) throw new IllegalArgumentException("The comment <${comment}> exceeds the 140 character limit.")
        
        this.user = user
        this.project = project
        this.startDatetime = startDatetime
        this.duration = duration
        this.comment = comment
    }
}
