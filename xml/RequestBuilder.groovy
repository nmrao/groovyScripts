/**
* this script is to create a soap request
* user passes some dynamic data from outside
* for more details:
* https://community.smartbear.com/t5/SoapUI-Open-Source/Dynamic-request-creation-with-Groovy-element-Create/m-p/127999#U127999
**/
import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil
def createRequest = {accountNumbers ->
    def nameSpacesMap = [
            soapenv: 'http://schemas.xmlsoap.org/soap/envelope/',
            ns: 'ABC',
    ]
    def builder = new StreamingMarkupBuilder()
    builder.encoding ='utf-8'
    def soapRequest = builder.bind {
        namespaces << nameSpacesMap
        soapenv.Envelope {
            soapenv.Header{}
            soapenv.Body {
                ns.TOPPER{
                    RequestHeader{
                        MessageId("ABC")
                    }
                    accountNumbers.each { actNo ->
                        RequestElement {
                            accountnumber(actNo)
                        }
                    }

                }
            }
        }
    }
    XmlUtil.serialize(soapRequest)
}
def accountdetails = [84144135,84023193]
println createRequest(accountdetails)
