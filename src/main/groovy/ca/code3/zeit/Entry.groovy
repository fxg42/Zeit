package ca.code3.zeit

class Entry {
    int id, rev /* for Hibernate */
    User user
    Project project
    String comment
    Date from
    Date till
}
