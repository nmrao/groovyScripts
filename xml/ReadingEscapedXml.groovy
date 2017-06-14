/**
* Refer:https://stackoverflow.com/questions/43792305/unable-to-retrieve-data-while-parsing-soap-message-with-escaped-xml-using-groovy/43794984#43794984
*
**/
String source='''<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <soapenv:Body>
      &lt;ns0:GetListBy_QualificationResponse xmlns:ns0=&quot;urn:WS_CTM_People_ICEVA&quot;&gt;
         &lt;ns0:getListValues&gt;
            &lt;ns0:Person_ID&gt;PPL000000301739&lt;/ns0:Person_ID&gt;
            &lt;ns0:Submitter&gt;soehler&lt;/ns0:Submitter&gt;
            &lt;ns0:Profile_Status&gt;Enabled&lt;/ns0:Profile_Status&gt;
            &lt;ns0:Locale2&gt;en_US&lt;/ns0:Locale2&gt;
            &lt;ns0:VIP&gt;No&lt;/ns0:VIP&gt;
            &lt;ns0:Client_Sensitivity&gt;Standard&lt;/ns0:Client_Sensitivity&gt;
         &lt;/ns0:getListValues&gt;
      &lt;/ns0:GetListBy_QualificationResponse&gt;
   </soapenv:Body>
</soapenv:Envelope>'''

//map the unescape characters
def map = ['&lt;' : '<', '&gt;' : '>', '&quot;' : '"', '&apos;':'\'', '&amp;':'&']
//Replace them in source string
map.collect {k,v -> source = source.replaceAll(k,v)}
//Now parse it
def root = new XmlSlurper().parseText(source)
//Get the submitter
def submitter = root.'**'.find { it.name() == 'Submitter' }
println submitter
