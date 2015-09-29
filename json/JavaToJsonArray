/** json array example from java object, uses jackson libraries from 
* http://mvnrepository.com/artifact/com.fasterxml.jackson.core
*/
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
class Author {
    String firstName
    String lastName
}

class Book {
    String name
    List<Author> authors
    @JsonProperty("isbn-number")
    String isbn
    Double price
}
def author1 = new Author(firstName: "Behrouz", lastName: "Forouzan")
def book = new Book(name: "DATA COMMUNICATIONS AND NETWORKING", authors: [author1], isbn: "8982328392", price: 439.00)
def books = [book]
ObjectMapper mapper = new ObjectMapper()
mapper.configure(SerializationFeature.INDENT_OUTPUT,true)
println mapper.writeValueAsString(books)
