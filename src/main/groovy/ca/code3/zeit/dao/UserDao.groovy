package ca.code3.zeit.dao

import ca.code3.zeit.*
import org.hibernate.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*

@Repository
class UserDao {
    @Autowired SessionFactory sessionFactory

    @Transactional(readOnly=true)
    User findByEmail (String testEmail) {
        sessionFactory.currentSession
            .createQuery("from User where email = :email")
            .setString("email", testEmail)
            .uniqueResult()
    }

    @Transactional
    User save (User aUser) {
        sessionFactory.currentSession.saveOrUpdate(aUser)
        aUser
    }
}

