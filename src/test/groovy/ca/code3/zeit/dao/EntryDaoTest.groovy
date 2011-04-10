package ca.code3.zeit.dao

import ca.code3.zeit.*
import groovy.time.*
import org.springframework.context.support.*

import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class EntryDaoTest {
    def dao, client, project, user, entry

    @Before void set_up () {
        def ctx = new ClassPathXmlApplicationContext('META-INF/applicationContext.xml')
        dao = ctx.getBean('domainDao')
        client = new Client(name:"client name")
        project = new Project(client:client, mnemonic:"TEST")
        user = new User(email:"test@test.test", name:"test")
        entry = new Entry(user:user, project:project, comment:"comment...", recordedOn:new Date(), duration:30)
    }
    
    @Test void it_should_save_new_entries () {
        def saved = dao.save(entry)
        assertNotNull saved.id
        assertNotNull saved.rev
    }

    @Test void it_should_find_all_activites_by_user_and_date_range () {
        use (TimeCategory) {
            def lastSaved = dao.save(entry)
            def results = dao.findEntriesByUserEmailAndDatesBetween(entry.user.email, 1.day.ago, 1.day.from.now)
            assertEquals 1, results.size()
            assertEquals lastSaved.id, results.first().id

            results = dao.findEntriesByUserEmailAndDatesBetween(entry.user.email, new Date(), 1.day.from.now)
            assertEquals 1, results.size()

            results = dao.findEntriesByUserEmailAndDatesBetween(entry.user.email, 1.day.ago, new Date())
            assertEquals 1, results.size()

            results = dao.findEntriesByUserEmailAndDatesBetween(entry.user.email, new Date(), new Date())
            assertEquals 1, results.size()
        }
    }
}
