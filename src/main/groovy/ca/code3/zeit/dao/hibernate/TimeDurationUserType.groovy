package ca.code3.zeit.dao.hibernate

import groovy.time.*
import java.sql.*
import org.hibernate.type.*
import org.hibernate.usertype.*

class TimeDurationUserType implements UserType {

    int[] sqlTypes () {
        def integerType = new IntegerType()
        ([integerType.sqlType()]*5) as int[]
    }

    Class returnedClass () {
        TimeDuration.class
    }

    boolean isMutable () {
        false
    }

    Object deepCopy (Object value) {
        new TimeDuration(value.days, value.hours, value.minutes, value.seconds, value.millis)
    }

    Serializable disassemble (Object value) {
        TimeDurationDto.from(value)
    }

    Object assemble (Serializable cached, Object owner) {
        cached.toTimeDuration()
    }

    Object replace (Object original, Object target, Object owner) {
        original
    }

    boolean equals (Object x, Object y) {
        if (x == y) return true
        if (!x || !y) return false
        x.toMilliseconds() == y.toMilliseconds()
    }

    int hashCode (Object x) {
        x.hashCode()
    }

    Object nullSafeGet (ResultSet resultSet, String[] names, Object owner) throws SQLException {
        def days    = resultSet.getInt(names[0]) ?: 0
        def hours   = resultSet.getInt(names[1]) ?: 0
        def minutes = resultSet.getInt(names[2]) ?: 0
        def seconds = resultSet.getInt(names[3]) ?: 0
        def millis  = resultSet.getInt(names[4]) ?: 0
        new TimeDuration(days, hours, minutes, seconds, millis)
    }

    void nullSafeSet (PreparedStatement statement, Object value, int index) {
        def days = value ? value.days : 0
        def hours = value ? value.hours : 0
        def minutes = value ? value.minutes : 0
        def seconds = value ? value.seconds : 0
        def millis = value ? value.millis : 0
        statement.setInt(index, days)
        statement.setInt(index+1, hours)
        statement.setInt(index+2, minutes)
        statement.setInt(index+3, seconds)
        statement.setInt(index+4, millis)
    }
}
