package ca.code3.zeit.dao

import ca.code3.zeit.*
import org.hibernate.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*

@Repository
class EntryDao {
    @Autowired SessionFactory sessionFactory

    @Transactional
    Entry save (Entry anEntry) {
        sessionFactory.currentSession.saveOrUpdate(anEntry)
        anEntry
    }
}
