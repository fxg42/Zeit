package ca.code3.zeit.dao

import ca.code3.zeit.*
import org.junit.*
import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*

class EntryDaoTest {
    def dao

    @Before void set_up () {
        dao = new EntryDao()
    }
    
    @Test void it_should_find_all_activites_by_user_and_date_range () {
        def fromDate = Date.parse("yyyy-MM-dd", "2011-03-20")
        def toDate = Date.parse("yyyy-MM-dd", "2011-03-26")
        dao.findEntriesByUserEmailAndDatesBetween("fxg@code3.ca", fromDate, toDate)
    }
    
    @Test void it_should_save_new_activites () {
        
    }
}
