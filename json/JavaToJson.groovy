/** this script converts java object to json object, uses jackson libraries
* also shows customed json element using JsonProperty
*/
class Synchronize {

    String method = null
    String path = null
    @com.fasterxml.jackson.annotation.JsonProperty("synchronization-type")
    int synchronizationType
}

def synch = new Synchronize()
synch.method = "GET"
synch.path = "news"
synch.synchronizationType = 2
com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper()
println(mapper.writeValueAsString(synch))
