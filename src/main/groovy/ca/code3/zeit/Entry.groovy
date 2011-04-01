package ca.code3.zeit

class Entry {
    private user, project, comment, date, duration

    Entry (user, project, date, duration) {
        this(user, project, "", date, duration)
    }

    Entry (user, project, comment, date, duration) {
        if (comment.size() > 140) throw new IllegalArgumentException("The comment <${comment}> exceeds the 140 character limit.")
        
        this.user = user
        this.project = project
        this.date = date
        this.duration = duration
        this.comment = comment
    }
}
