/** this script converts java object to json object, uses jackson libraries
* also shows customed json element using JsonProperty
*/
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
class Synchronize {
    String method
    String path
    @JsonProperty("synchronization-type")
    int synchronizationType
}

def synch = new Synchronize()
synch.method = 'GET'
synch.path = 'news'
synch.synchronizationType = 2
ObjectMapper mapper = new ObjectMapper()
println(mapper.writeValueAsString(synch))
