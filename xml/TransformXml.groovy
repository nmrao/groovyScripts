/**
* this script transforms an xml from one format to another
* for more details, visit:
* http://stackoverflow.com/questions/39499392/in-groovy-how-can-you-efficiently-transform-xml-without-using-any-of-the-sax-par/39502332#39502332
**/
import groovy.xml.XmlUtil
def xml = '''<response version-api="2.0">
    <value>
        <ErrorCodes>1, 2, 3, 4</ErrorCodes>
    </value>
</response>'''
def newXml = new XmlSlurper().parseText(xml)
//Get the current Error codes into a list
def codes = newXml.value.ErrorCodes.toString().split(',')*.trim()
//remove the existing ErrorCodes node
newXml.value.ErrorCodes.replaceNode {}
//Create the transformed xml by adding the list of ErrorCodes
newXml.value.appendNode {
    codes.each {
        ErrorCodes(it)
    }
}
println XmlUtil.serialize(newXml)
