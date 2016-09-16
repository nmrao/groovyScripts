/**
* This script takes inputs of REST Xml Response, JDBC Response
* Compares the required fiedls
* both are xmls not in the same format, so need bring them in the same format to be able to compare
* so, created object model using class
* also needed to implement comparable in order sort the Holdings in the REST Response and Rows in the Jdbc Response
* for each response type, extract the data that is required to compare and build object list
* finally compare
* for more details:
* http://stackoverflow.com/questions/39515010/sorting-xmls-element-list-using-groovy
**/
import groovy.transform.Canonical
/**
* Holds the data from the responses
**/
@Canonical
class Model implements Comparable {
	String unitName
	String unitCode
	float value
	int compareTo(other) {
        int i = unitName <=> other.unitName
        if (i != 0) return i
        i = unitCode <=> other.unitCode
        if (i != 0) return i
        value <=> other.value
    }

    static Model buildData(name, code, value) {
    	new Model(unitName : name, unitCode: code, value : value.toString() as Float)
    }
}

def restResponse = '''<ResponseList>
  <Response>
    <ClientResponseList>
      <ClientResponse>
        <ClientIdentificationinfo FirstName = 'Tania' SurName = 'Gupta' Title = 'Miss'/>
      </ClientResponse>
      <AccountInfo>
        <Holding Unitname = 'A'>
           <FundIdentifier unitcode = 'a' value = '123.05'/>
        </Holding>
        <Holding Unitname = 'B'>
           <FundIdentifier unitcode = 'b' value = '123.08'/>
        </Holding>
      </AccountInfo>
    </ClientResponseList>
  </Response>
</ResponseList>'''


/**
* Processing REST response
**/

def responseList = new XmlSlurper().parseText(restResponse)
def holdings  = responseList.Response.ClientResponseList.AccountInfo.Holding
//Loop thru the Holdings,  build the list and sort
List<Model> restModels = []

holdings.each { holding ->
	restModels << Model.buildData(holding.@Unitname, holding.FundIdentifier.@unitcode, holding.FundIdentifier.@value)
}

Collections.sort(restModels)
// ------------------ processing REST response ends ------------------

/**
* Processing Jdbc Response
**/

def jdbcResponse = '''<Resultset>
  <Row number="1">
    <UnitName>A</UnitName>
    <Unitcode>a</Unitcode>
    <Value>123.60</Value>
  </Row>
  <Row number="2">
    <UnitName>B</UnitName>
    <Unitcode>b</Unitcode>
    <Value>128.80</Value>
</Row>
</Resultset>'''


def resultSet = new XmlSlurper().parseText(jdbcResponse)
def rows = resultSet.Row

//Loop thru the Rows,  build the list and sort
List<Model> jdbcModels = []

rows.each { row ->
	jdbcModels << Model.buildData(row.UnitName, row.Unitcode, row.Value)
}

Collections.sort(jdbcModels)
// ------------------ processing JDBC response ends ------------------
//Now time to compare both the object models

assert restModels == jdbcModels, 'Both rest and jdbc responses are not matching'
