package ca.code3.zeit.service

import ca.code3.zeit.*
import org.junit.*
import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*

class ZeitServiceTest {
    def service

    @Before void set_up () {
        service = new ZeitService()
    }
    
    @Test void it_should_find_all_activites_by_user_and_date_range () {
        def fromDate = Date.parse("yyyy-MM-dd", "2011-03-20")
        def toDate = Date.parse("yyyy-MM-dd", "2011-03-26")
        service.findEntriesByUserEmailAndDatesBetween("fxg@code3.ca", fromDate, toDate)
    }
    
    @Test void it_should_save_new_activites () {
        
    }
}
