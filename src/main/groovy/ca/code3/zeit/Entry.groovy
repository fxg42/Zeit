package ca.code3.zeit

class Entry {
    int id, rev /* for Hibernate */
    User user
    Project project
    String comment
    Date recordedOn
    int duration

    /* always copy then remove time component */
    void setRecordedOn (Date recordedOn) {
        this.recordedOn = new Date(recordedOn.time)
        this.recordedOn.clearTime()
    }
}
