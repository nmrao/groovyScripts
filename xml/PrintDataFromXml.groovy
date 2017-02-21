/**
* Refer : http://stackoverflow.com/questions/42358677/parsing-not-producing-output-expected/42360204#42360204
**/
def xml="""<tptc:People xmlns:tptc="http://www.metadata.calcum-inc.com/stats/">
    <tptc:Identifier>456-99088</tptc:Identifier>
    <tptc:Name>The Grand Supper</tptc:Name>
     <tptc:Person>
     <tptc:PersonRole>
         <tptc:PersonRoleCode>COMMANDER</tptc:PersonRoleCode>
        <tptc:PersonRoleDescription>
      IN CHARGE OF   ARMY    
    </tptc:PersonRoleDescription>
    </tptc:PersonRole>
    <tptc:PersonRole>
        <tptc:PersonRoleCode>CONDUCTOR</tptc:PersonRoleCode>
        <tptc:PersonRoleDescription>
      DIRECTS SYMPHONY
   </tptc:PersonRoleDescription>
    </tptc:PersonRole>
    <tptc:PersonName>
        <tptc:PersonFirstName>Jackie</tptc:PersonFirstName>
        <tptc:PersonMiddleName>Wang</tptc:PersonMiddleName>
        <tptc:PersonLastName>Mang</tptc:PersonLastName>
    </tptc:PersonName>
    <tptc:PersonAddress>
        <tptc:PersonAddressLine1Text>String</tptc:PersonAddressLine1Text>
        <tptc:PersonAddressLine2Text>String</tptc:PersonAddressLine2Text>
        <tptc:PersonCityName>String</tptc:PersonCityName>
        <tptc:PersonStateCode>String</tptc:PersonStateCode>
        <tptc:PersonZip5Code>String</tptc:PersonZip5Code>
        <tptc:PersonZip4Code>String</tptc:PersonZip4Code>
    </tptc:PersonAddress>
    <tptc:PersonTelephone>
        <tptc:PersonAreaCode>String</tptc:PersonAreaCode>
        <tptc:PersonTelephoneNumber>String</tptc:PersonTelephoneNumber>
        <tptc:TelephoneTypeCode>String</tptc:TelephoneTypeCode>
    </tptc:PersonTelephone>
    <tptc:PersonTelephone>
        <tptc:PersonAreaCode>String</tptc:PersonAreaCode>
        <tptc:PersonTelephoneNumber>String</tptc:PersonTelephoneNumber>
        <tptc:TelephoneTypeCode>String</tptc:TelephoneTypeCode>
    </tptc:PersonTelephone>
   </tptc:Person>
   <tptc:Person>
    <tptc:PersonRole>
        <tptc:PersonRoleCode>First</tptc:PersonRoleCode>
        <tptc:PersonRoleDescription>Best Best</tptc:PersonRoleDescription>
    </tptc:PersonRole>
    <tptc:PersonRole>
        <tptc:PersonRoleCode>None</tptc:PersonRoleCode>
        <tptc:PersonRoleDescription>Nonetity</tptc:PersonRoleDescription>
    </tptc:PersonRole>
      <tptc:PersonName>
        <tptc:PersonFirstName>String</tptc:PersonFirstName>
        <tptc:PersonMiddleName>String</tptc:PersonMiddleName>
        <tptc:PersonLastName>String</tptc:PersonLastName>
     </tptc:PersonName>
      <tptc:PersonAddress>
        <tptc:PersonAddressLine1Text>String</tptc:PersonAddressLine1Text>
        <tptc:PersonAddressLine2Text>String</tptc:PersonAddressLine2Text>
        <tptc:PersonCityName>String</tptc:PersonCityName>
        <tptc:PersonStateCode>String</tptc:PersonStateCode>
        <tptc:PersonZip5Code>String</tptc:PersonZip5Code>
        <tptc:PersonZip4Code>String</tptc:PersonZip4Code>
      </tptc:PersonAddress>
       <tptc:PersonTelephone>
        <tptc:PersonAreaCode>String</tptc:PersonAreaCode>
        <tptc:PersonTelephoneNumber>
          String
       </tptc:PersonTelephoneNumber>
        <tptc:TelephoneTypeCode>String
      </tptc:TelephoneTypeCode>
       </tptc:PersonTelephone>
      <tptc:PersonTelephone>
        <tptc:PersonAreaCode>String</tptc:PersonAreaCode>
        <tptc:PersonTelephoneNumber>
             String
      </tptc:PersonTelephoneNumber>
        <tptc:TelephoneTypeCode>
           String
      </tptc:TelephoneTypeCode>
      </tptc:PersonTelephone>
     </tptc:Person>
   </tptc:People>"""

def parsedXml = new XmlSlurper().parseText(xml)
def ppl = parsedXml.'**'.findAll{ it.name() == 'Person'}
def result = []
ppl.each { peop ->
  peop.'**'.findAll{ it.name() == 'PersonRole'}.each { role ->
    def detail = []
    detail << role.PersonRoleCode
    detail << role.PersonRoleDescription.text().trim()
    result << detail
  }
}
println result.toString()
