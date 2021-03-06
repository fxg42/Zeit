package ca.code3.zeit.dao

import ca.code3.zeit.*
import groovy.time.*
import org.springframework.context.support.*

import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class EntryDaoTest {
    def dao, user1, user2, entry1, entry2, entry3

    @Before void set_up () {
        def ctx = new ClassPathXmlApplicationContext('META-INF/applicationContext.xml')
        dao = ctx.getBean('domainDao')
        user1 = new User(email:"test@test.test", name:"test")
        user2 = new User(email: "other@other.email", name: "other")
        entry1 = new Entry(user:user1, comment:"comment...", recordedOn:new Date(), duration:30, tags: ["tag-a", "tag-b", "tag-c"])
        entry2 = new Entry(user:user1, comment:"comment...", recordedOn:new Date(), duration:30, tags: ["tag-a", "tag-b"])
        entry3 = new Entry(user:user2, comment:"comment...", recordedOn:new Date(), duration:30, tags: ["other"])
        dao.save(entry3)
    }
    
    @Test void it_should_save_new_entries () {
        def saved = dao.save(entry1)
        assertNotNull saved.id
        assertNotNull saved.rev
    }

    @Test void it_should_find_all_activites_by_user_and_date_range () {
        use (TimeCategory) {
            def lastSaved = dao.save(entry1)
            def results = dao.findEntriesByUserEmailAndDatesBetween(entry1.user.email, 1.day.ago, 1.day.from.now)
            assertEquals 1, results.size()
            assertEquals lastSaved.id, results.first().id

            results = dao.findEntriesByUserEmailAndDatesBetween(entry1.user.email, new Date(), 1.day.from.now)
            assertEquals 1, results.size()

            results = dao.findEntriesByUserEmailAndDatesBetween(entry1.user.email, 1.day.ago, new Date())
            assertEquals 1, results.size()

            results = dao.findEntriesByUserEmailAndDatesBetween(entry1.user.email, new Date(), new Date())
            assertEquals 1, results.size()
        }
    }

    @Test void it_should_return_empty_if_unknown_email_is_given () {
        def results = dao.findEntriesByUserEmailAndDatesBetween("unkown.email", new Date(), new Date())
        assertEquals 0, results.size()
    }

    @Test void it_should_return_empty_when_out_of_range () {
        def lastSaved = dao.save(entry1)
        use (TimeCategory) {
            def results = dao.findEntriesByUserEmailAndDatesBetween(entry1.user.email, 2.days.ago, 1.day.ago)
            assertEquals 0, results.size()
        }
    }

    @Test void it_should_find_entries_by_tag () {
        dao.save(entry1)
        dao.save(entry2)

        def fromDate = new Date()
        def toDate = new Date()

        def results = dao.findEntriesByTagsAndDatesBetween(["tag-a"], fromDate, toDate)
        assertEquals 2, results.size()

        results = dao.findEntriesByTagsAndDatesBetween(["tag-c"], fromDate, toDate)
        assertEquals 1, results.size()

        results = dao.findEntriesByTagsAndDatesBetween(["tag-a", "tag-b"], fromDate, toDate)
        assertEquals 2, results.size()

        results = dao.findEntriesByTagsAndDatesBetween(["tag-a", "tag-c"], fromDate, toDate)
        assertEquals 1, results.size()

        results = dao.findEntriesByTagsAndDatesBetween(["tag-that-does-not-exist"], fromDate, toDate)
        assertEquals 0, results.size()

        results = dao.findEntriesByTagsAndDatesBetween(["tag-a", "tag-that-does-not-exist"], fromDate, toDate)
        assertEquals 0, results.size()

        results = dao.findEntriesByTagsAndDatesBetween([], fromDate, toDate)
        assertEquals 0, results.size()
    }
}
