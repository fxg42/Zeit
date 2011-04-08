package ca.code3.zeit.dao

import ca.code3.zeit.*
import org.springframework.context.support.*

import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class EntryDaoTest {
    def dao, client, project, user, entry

    @Before void set_up () {
        def ctx = new ClassPathXmlApplicationContext('META-INF/applicationContext-test.xml')
        dao = ctx.getBean('domainDao')
        client = new Client(name:"client name")
        project = new Project(client:client, mnemonic:"TEST")
        user = new User(email:"test@test.test", name:"test")
        entry = new Entry(user:user, project:project, comment:"comment...", from:new Date(), till:new Date())
    }
    
    @Test void it_should_save_new_entries () {
        def saved = dao.save(entry)
        assertNotNull saved.id
        assertNotNull saved.rev
    }

    @Test void it_should_find_all_activites_by_user_and_date_range () {
        // def fromDate = Date.parse("yyyy-MM-dd", "2011-03-20")
        // def toDate = Date.parse("yyyy-MM-dd", "2011-03-26")
        // dao.findEntriesByUserEmailAndDatesBetween("test@test.test", fromDate, toDate)
    }
}
