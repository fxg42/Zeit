package ca.code3.zeit.dao

import ca.code3.zeit.*
import org.springframework.context.support.*
import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class UserDaoTest {
    def ctx = new ClassPathXmlApplicationContext('META-INF/applicationContext.xml')
    def dao = ctx.getBean('userDao')
    def testUser = new User(email:'test@test.test', name:'test')

    @Before void set_up () {
    }
    
    @Test void it_should_save_users () {
        dao.save(testUser)
        
        def foundUser = dao.findByEmail(testUser.email)
        assertNotNull foundUser
        assertEquals testUser.email, foundUser.email
    }
}

