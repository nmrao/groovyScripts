/**
* this groovy script validates given xml file 
* with the given xsd file
**/
import org.xml.sax.SAXException
import javax.xml.transform.stream.StreamSource
import java.nio.charset.StandardCharsets
import javax.xml.XMLConstants
import javax.xml.validation.SchemaFactory

//Provide valid xml instance & and xsd file paths below
def xml = '/absolute/path/to/xml'
def xsd = '/absolute/path/to/xsd'

//Not required to edit beyond this point

def isFileExists(String fileName) {
    def file = new File(fileName)
    def result = file.exists()
    if (result) {
        println "$fileName exists"
    } else {
        println "$fileName does not exist"
    }
    result
}

def isValid(String xsd, String xml) {
    def result = false
    if (isFileExists(xsd) && isFileExists(xml)) {
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        try {
            def schema = factory.newSchema(new File(xsd))
            def validator = schema.newValidator()
            validator.validate(new StreamSource(new InputStreamReader(new FileInputStream(xml), StandardCharsets.UTF_8)))
            println "$xml is valid."
            result = true
        } catch (SAXException ex) {
            println(xml + " is not valid because \n")
            println(ex.localizedMessage)
        } catch (FileNotFoundException ex) {
            println(ex.localizedMessage)
        } catch (IOException ex) {
            println(ex.localizedMessage)
        } finally {
            return result
        }
    } else {
        println "Can't validate file as one of the file is not found"
    }
    result
}

//Validate xml by calling above method
isValid(xsd, xml)
