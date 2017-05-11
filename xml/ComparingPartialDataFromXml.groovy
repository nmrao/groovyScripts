/**
* this script asserts only given data against user input and shows the result
*
* Refef:http://stackoverflow.com/questions/42661315/assert-multiple-values-in-an-xml-response-received-on-querying-db-in-soap-ui/42664867#42664867
**/

//define your expected data as map
def expectedData = ['TASK1':'Value1', 'TASK2':'Value1']

def jdbcResponse = """<Results>
    <ResultSet fetchSize="10">
     <Row rowNumber="1">
         <T1>TEXT1</T1>
         <T2>TASK1</T2>
         <T3>Value1</T3>
         <T4>xyz</T4>
      </Row>
       <Row rowNumber="2">
         <T1>TEXT2</T1>
         <T2>TASK2</T2>
         <T3>Value1a</T3>
         <T4>ABC</T4>
      </Row>
     </ResultSet>
</Results>"""

//Parse and get rows
def rows = new XmlSlurper().parseText(jdbcResponse).ResultSet.Row

def getRowData = { data, elementName, elementValue ->
  data.'**'.findAll { it.name() == elementName && it == elementValue }*.parent()
}

def assertionErrors = new StringBuffer()

//Loop thur expectedData and capture the errors
expectedData.each { key, value->
  def matchingRow = getRowData(rows, 'T2', key)[0]
  value == matchingRow.T3.text() ?: assertionErrors.append("Assertion failed for rowNumber ${matchingRow.@rowNumber}\n")
}

//Check and show the result
if (assertionErrors) {
  throw new Error(assertionErrors.toString())
} else {
  println 'Assertions passed'
}
