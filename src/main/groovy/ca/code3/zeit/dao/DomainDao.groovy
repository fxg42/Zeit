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
    Collection<Entry> findEntriesByUserEmailAndDatesBetween (String testEmail, Date lowerBound, Date upperBound) {
        sessionFactory.currentSession
            .createQuery("from Entry e where e.user.email = :testEmail and :lowerBound <= e.from and e.till <= :upperBound")
            .setString("testEmail", testEmail)
            .setDate("lowerBound", lowerBound)
            .setDate("upperBound", upperBound)
            .list()
    }

    @Transactional
    def save (aDomainObject) {
        sessionFactory.currentSession.saveOrUpdate(aDomainObject)
        aDomainObject
    }
}

