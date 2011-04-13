package ca.code3.zeit

import org.apache.http.client.methods.*
import org.apache.http.entity.*
import org.apache.http.impl.client.*

class HttpClientWrapper {

    def get (url, Closure fn) {
        request(new HttpGet(url), [success: fn])
    }
    def get (url, Map callbacks) {
        request(new HttpGet(url), callbacks)
    }
    
    def put (url, Closure fn) {
        request(new HttpPut(url), [success: fn])
    }
    def put (url, Map callbacks) {
        request(new HttpPut(url), callbacks)
    }
    
    def post (url, Closure fn) {
        request(new HttpPost(url), [success: fn])
    }
    def post (url, Map callbacks) {
        request(new HttpPost(url), callbacks)
    }
    
    def delete (url, Closure fn) {
        request(new HttpDelete(url), [success: fn])
    }
    def delete (url, Map callbacks) {
        request(new HttpDelete(url), callbacks)
    }
    
    private request (request, Map callbacks) {
        def httpclient = new DefaultHttpClient()
        def retval
        try {
            if (callbacks.before) callbacks.before(request)

            def response = httpclient.execute(request)
            def statusCode = response.statusLine.statusCode

            if (callbacks[statusCode]) {
                retval = callbacks[statusCode](response)
            } else if (200 <= statusCode && statusCode < 400 && callbacks.success) {
                retval = callbacks.success(response)
            } else if (statusCode >= 400 && callbacks.error) {
                retval = callbacks.error(response)
            } else {
                throw new RuntimeException("Unhandled HTTP status code <${response.statusLine}> on request <${request.requestLine}>")
            }
        } finally {
            httpclient.getConnectionManager().shutdown()
        }
        retval
    }
}

