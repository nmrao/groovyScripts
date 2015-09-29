/** this is the same example as JavaToJson.groovy, but without third party library dependencies */
import groovy.json.JsonBuilder
def json = new JsonBuilder()
json {
    method 'GET'
    path 'news'
    "synchronization-type" 2
}
println json.toPrettyString()
