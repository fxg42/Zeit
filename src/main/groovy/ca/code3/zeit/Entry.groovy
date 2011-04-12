package ca.code3.zeit

class Entry {
    int id, rev /* for Hibernate */

    User user
    String comment
    Date recordedOn
    int duration
    Set tags = []

    /* return a copy */
    Date getRecordedOn () {
        new Date(recordedOn.time)
    }
    /* always copy then remove time component */
    void setRecordedOn (Date recordedOn) {
        this.recordedOn = new Date(recordedOn.time)
        this.recordedOn.clearTime()
    }
}
