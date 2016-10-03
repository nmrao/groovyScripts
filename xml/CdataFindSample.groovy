/**
* This script searches for a certain element of xml string
*
*
**/

/**
* Closure to search for certain element data
* input parameters
* xml data, element name to search
**/
def searchData = { data, element ->
   def parsedData = new XmlSlurper().parseText(data)
   parsedData.'**'.find { it.name() == element} as String
}
//Sample xml response data, which also contains cdata
def response = '''<html>
   <head>
      <link href="/ddd/ddddd.css" media="screen" rel="stylesheet"/>
   </head>
   <body>
      <table id="temporalObjects">
         <caption>Data</caption>
         <tr>
            <th>DOCUMENT</th>
            <th>xxxxxx</th>
            <th>ffffff</th>
            <th>dsfdfdfd</th>
            <th>dfdfdf</th>
            <th>fdfdfd</th>
            <th>dsdsdsds</th>
            <th>vcxvdvd</th>
            <th>gssfsfs</th>
            <th>geegegge</th>
            <th>ewewewew</th>
         </tr>
         <tr>
            <td>
               <pre><![CDATA[<?xml version="1.0" encoding="UTF-8"?><xxxWrapper:yyyMMessage xmlns:xxxWrapper="xxxWrapper" xmlns:xxxContract="xxxContract" xmlns:xxxInstrument="xxxInstrument">
    <MessageId>.....</MessageId>
    <MessageType>xxxx</MessageType>
    <MessageSender>jjjj</MessageSender>
    <MessageSendingDateTime>2016-09-20T10:02:51.869</MessageSendingDateTime>
    <MessageSchemaVersion>1.0.0</MessageSchemaVersion>
    <Event>
        <EventId>2</EventId>
        <EventType>Changed Entity</EventType>
        <Object>
            <ObjectId>....</ObjectId>
            <ObjectVersionNumber>1.0.0</ObjectVersionNumber>
            <ObjectType>.....</ObjectType>
            <ObjectOwner>...</ObjectOwner>
            <ObjectXSDName>xxx.xsd</ObjectXSDName>
            <xxContractPayload>
                <xxxContract:InstrumentCore>
                    <xxxInstrument:AsOfDate>2016-07-15T12:22:40Z</xxxInstrument:AsOfDate>
                    <xxxInstrument:InstrumentRUID>16133645</xxxInstrument:InstrumentRUID>
                    <xxxInstrument:InstrumentName>Starbucks Corporation Dividend</xxxInstrument:InstrumentName>
                    <xxxInstrument:IssueStatus>Active</xxxInstrument:IssueStatus>
                </xxxContract:InstrumentCore>
            </xxContractPayload>
        </Object>
    </Event>
</xxxWrapper:yyyMMessage>]]></pre>

</td>
            <td>16133645</td>
            <td>2016-09-21T08:40:55.083Z</td>
            <td>2999-12-31T00:00:00Z</td>
            <td>7534ba96-d0de-4a61-a003-7b12289c28f5-0-83</td>
            <td>ld-20160920-100251850-27</td>
            <td>2016-09-21T08:42:40.189Z</td>
            <td>ld</td>
            <td>xxx_OWNER</td>
            <td>49c1fa7209e4c5c1e0e57c98d618f2ca883dd5813be2d2c6793a179f1716386f</td>
            <td>Y</td>
         </tr>
      </table>
   </body>
</html>'''
//Get the cdata part which is inside element "pre"
def cData = searchData(response,'pre')
//Get the instrumentId from cdata
def instrumentId = searchData(cData, 'InstrumentRUID')
//Check if that is matching.
assert '16133645' == instrumentId, "InstrumentId is not matching"
