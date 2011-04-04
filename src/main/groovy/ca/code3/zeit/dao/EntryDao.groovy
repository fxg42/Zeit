package ca.code3.zeit.dao

import ca.code3.zeit.*
import org.hibernate.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

@Repository
class EntryDao {
    // @Autowired SessionFactory sessionFactory

    List<Entry> findEntriesByUserEmailAndDatesBetween (String email, Date from, Date to) {
        []
    }
}
