package ca.code3.zeit

import org.junit.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

class LoadTest {
    static base_url
    def http = new HttpClientWrapper()

    @BeforeClass static void set_up_once () {
        base_url = System.getProperty('zeit.url') ?: "http://localhost:8080/zeit"
        if (base_url.endsWith('/')) base_url = base_url[0..-2]
    }

    @Test void it_should_call_the_script () {
        http.get("${base_url}/api/load", [
            success: { response ->
                assertTrue true
            },
            error: { response ->
                fail()
            }
        ])    
    }
}

