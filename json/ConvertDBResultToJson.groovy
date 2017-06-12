/**
* Refer : https://community.smartbear.com/t5/SoapUI-NG/How-to-Create-a-JSON-Array-from-Results-at-JDBC-Test-Step-in/m-p/143917#M32529
*
**/

def xml = """<Results>
      <ResultSet fetchSize="10">
            <Row rowNumber="1">
                   <ORDERID>H001</ORDERID>
                   <LINEID>L001</LINEID>
                   <HOLDID>1</HOLDID>
                   <HOLDNAME>HOLDA</HOLDNAME>
                   <RELEASED_FLAG>N</RELEASED_FLAG>
            </Row>
            <Row rowNumber="2">
                   <ORDERID>H001</ORDERID>
                   <LINEID>L001</LINEID>
                   <HOLDID>2</HOLDID>
                   <HOLDNAME>HOLDB</HOLDNAME>
                   <RELEASED_FLAG>N</RELEASED_FLAG>
            </Row>
            <Row rowNumber="3">
                   <ORDERID>H001</ORDERID>
                   <LINEID>3</LINEID>
                   <HOLDID>3</HOLDID>
                   <HOLDNAME>HOLDC</HOLDNAME>
                   <RELEASED_FLAG>N</RELEASED_FLAG>
            </Row>
      </ResultSet>
</Results>"""

def parsed = new XmlSlurper().parseText(xml)
def map = ['requestPayload' :  parsed.'**'.findAll{it.name() == 'Row'}.collect{element -> element.children().breadthFirst()*.name()
             .findAll { !element."$it".children().size() }
		   .collectEntries{[(element."$it".name()): element."$it".text()] }
}]

println new groovy.json.JsonBuilder(map).toPrettyString()
