package ca.code3.zeit.dao

import ca.code3.zeit.*
import org.hibernate.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*

@Repository
class DomainDao {
    @Autowired SessionFactory sessionFactory

    @Transactional(readOnly=true)
    User findUserByEmail (String testEmail) {
        sessionFactory.currentSession
            .createQuery("from User u where u.email = :testEmail")
            .setString("testEmail", testEmail)
            .uniqueResult()
    }

    @Transactional(readOnly=true)
    Collection<Entry> findEntriesByUserEmailAndDatesBetween (String testEmail, Date fromDate, Date toDate) {
        sessionFactory.currentSession
            .createQuery("from Entry e where e.user.email = :testEmail and :lowerBound <= e.recordedOn and e.recordedOn <= :upperBound")
            .setString("testEmail", testEmail)
            .setDate("lowerBound", toCalendarDate(fromDate))
            .setDate("upperBound", toCalendarDate(toDate))
            .list()
    }

    @Transactional
    def save (aDomainObject) {
        sessionFactory.currentSession.saveOrUpdate(aDomainObject)
        aDomainObject
    }

    private toCalendarDate (Date date) {
        def retval = new Date(date.time)
        retval.clearTime()
        retval
    }
}

