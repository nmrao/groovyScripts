/** this script converts java object to json object, uses jackson libraries
* * http://mvnrepository.com/artifact/com.fasterxml.jackson.core
* also shows customed json element using JsonProperty
*/
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

class Synchronize {
    String method
    String path
    @JsonProperty("synchronization-type")
    int synchronizationType
}

def synch = new Synchronize(method:'GET', path:'news', synchronizationType:2)
ObjectMapper mapper = new ObjectMapper()
mapper.configure(SerializationFeature.INDENT_OUTPUT,true)
println(mapper.writeValueAsString(synch))
