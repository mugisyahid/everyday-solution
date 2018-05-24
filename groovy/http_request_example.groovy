/**
* https://mvnrepository.com/artifact/org.codehaus.groovy.modules.http-builder/http-builder
* compile group: 'org.codehaus.groovy.modules.http-builder', name: 'http-builder', version: '0.7.1'
*/
private def postRequest(String url, String payload) {
	def client = new RESTClient(url)
	def resp = client.post(body: payload, requestContentType: 'text/xml')
	return resp
}