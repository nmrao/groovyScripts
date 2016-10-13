/**
* Retrieves cdata from below xml and retrieves only attribute Values of HIndex
* Refer below link for more details:
* http://stackoverflow.com/questions/39997997/how-to-get-the-attribute-value-inside-cdata-from-the-response-and-use-it-in-othe/40014055#40014055
**/
def xml = '''<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:xsd="http://www.w3.org/2001/XMLSchema">
<soap:Body>
    <HResponse xmlns="http://demo.org/">
        <HResult>
            <![CDATA[
            <HRS>
                <Header>
                    <Currency>INR</Currency>
                    <SessionId>1313123123123123123</SessionId>
                </Header>
                <HotelDetails>
                    <Stay></Stay>
                    <Hotel HIndex="1701"  PrefContract="" HDIndex="28">
                        <HNm> Demo</HNm>
                        <HImg>demo</HImg>
                    </Hotel>
                   <Hotel HIndex="1702"  PrefContract="" HDIndex="29">
                        <HNm> Demo</HNm>
                        <HImg>demo</HImg>
                    </Hotel>
                    <Hotel HIndex="1703" PrefContract="" HDIndex="30">
                        <HNm> Demo</HNm>
                        <HImg>demo</HImg>
                    </Hotel>
              </HotelDetails>
            </HRS>
          ]]>
        </HResult>
    </HResponse>
</soap:Body>
</soap:Envelope>'''

/**
* Closure to parse and retrieve the data
* retrieves element or attribute
*/
def searchData = { data, item, itemType ->
    def parsedData = new XmlSlurper().parseText(data)
    if ('attribute' == itemType) {
        return parsedData?.'**'.findAll{it.@"$item".text()}*.@"$item"
    }
    parsedData?.'**'.find { it.name() == item} as String
}

//Assert response xml
assert xml, "Response data is empty or null"
 
//Get the CDATA from response which is inside of HResult
def cData = searchData(xml, 'HResult', 'element')
 
//Assert retrieved CDATA is not empty
assert cData, "Data inside of HResult is empty or null"
 
//Get the HIndex list
def hIndexes = searchData(cData, 'HIndex', 'attribute')
 
//Print list of hIndexes
println hIndexes
 
//Print the first in the list
println "First HIndex is : ${hIndexes.first()}"
 
//Print the last in the list
println "Last HIndex is : ${hIndexes.last()}"
