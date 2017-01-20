/**
* Compares JDBC test step response with a SOAP test step request
* Refer: http://stackoverflow.com/questions/41726812/how-do-i-compare-a-jdbc-response-to-an-xml-response-where-number-of-nodes-vary-a/41746806#41726812
* or https://groovyconsole.appspot.com/script/5182287540912128
**/
def jdbcResponse = """<Results>   <ResultSet fetchSize="0">
	<Row rowNumber="1">
		<UPGRADETYPE>1</UPGRADETYPE>
		<SOURCEDESC>Desc 1</SOURCEDESC>
		<STARTDATE>2015-01-01</STARTDATE>
		<ENDDATE>2017-12-31</ENDDATE>
		<SOURCECODE>ABC123</SOURCECODE>
	</Row>
	<Row rowNumber="2">
		<UPGRADETYPE>2</UPGRADETYPE>
		<SOURCEDESC>Desc 2</SOURCEDESC>
		<STARTDATE>2015-01-01</STARTDATE>
		<ENDDATE>2017-12-131</ENDDATE>
		<SOURCECODE>XYZ9987</SOURCECODE>
	</Row>
</ResultSet>
</Results>"""
 
def xmlResponse = """<soap:Envelope xmlns:soap="http://sample.org">
<soap:Body>
	<TXLife xmlns="http://sample.org">
	<TXLifeResponse>
	<TransRefGUID>123456</TransRefGUID>
	<TransType tc="999"/>
	<TransSubType tc="9909"/>
	<BusinessService DataRep="VIEW"/>
	<TransExeDate>2017-01-19-05:00</TransExeDate>
	<TransExeTime>09:19:30.668-05:00</TransExeTime>
	<StartRecord>1</StartRecord>
	<TransResult>
	<ResultCode tc="1"/>
	<RecordsFound>2</RecordsFound>
	</TransResult>
	<OLifE>
	<Holding id="Holding_B1234567">
	</Holding>
	<Campaign id="Campaign_B1234567_1" AppliesToCoverageID="Coverage_B1234567_1">
		<CampaignSysKey>1</CampaignSysKey>
		<CampaignName>Desc 1</CampaignName>
		<StartDate>2015-01-01</StartDate>
		<EndDate>2017-12-31</EndDate>
		<CampaignCode>ABC123</CampaignCode>
	</Campaign>
	<Campaign id="Campaign_B1234567_2" AppliesToCoverageID="Coverage_B1234567_2">
		<CampaignSysKey>2</CampaignSysKey>
		<CampaignName>Desc 2</CampaignName>
		<StartDate>2015-01-01</StartDate>
		<EndDate>2017-12-31</EndDate>
		<CampaignCode>XYZ987</CampaignCode>
	</Campaign>
	</OLifE>
	</TXLifeResponse>
	</TXLife>
	</soap:Body>
</soap:Envelope>"""

@groovy.transform.Canonical
class Model {
	String campaignSysKey
	String campaignName
	String startDate
	String endDate
	String campaignCode
	 
	static def buildJdbcData(row) {
		def obj = new Model()
		row.with {
		obj.campaignSysKey = UPGRADETYPE
		obj.campaignName = SOURCEDESC
		obj.startDate = STARTDATE
		obj.endDate = ENDDATE
		obj.campaignCode = SOURCECODE
		}
		obj
	}
	 
	static def buildXMLData(cmpgn) {
		def obj = new Model()
		obj.campaignSysKey = cmpgn.CampaignSysKey as String
		obj.campaignName = cmpgn.CampaignName as String
		obj.startDate = cmpgn.StartDate as String
		obj.endDate = cmpgn.EndDate as String
		obj.campaignCode = cmpgn.CampaignCode as String
		obj
	}
}
 
 
//def jdbcResponse = context.expand('${Validation#ResponseAsXml}')
//def xmlResponse = context.expand('${OfferHistoryRequest#Response}')
 
def results = new XmlSlurper().parseText(jdbcResponse)
def jdbcDataObjects = []
results.ResultSet.Row.each { row ->
	jdbcDataObjects.add(Model.buildJdbcData(row))
}
 
def parsedXml = new XmlSlurper().parseText(xmlResponse)
def campaigns = parsedXml.'**'.findAll{it.name() == 'Campaign'}
def xmlDataObjects = []
campaigns.each { cmpgn ->
	xmlDataObjects.add(Model.buildXMLData(cmpgn))
}
 
println "${jdbcDataObjects.size()}"
println "${xmlDataObjects.size()}"
 
 
if (jdbcDataObjects.size() != xmlDataObjects.size()) {
	println("Jdbc resultset size is : ${jdbcDataObjects.size()} and XML result size is : ${xmlDataObjects.size()}")
 
}
assert jdbcDataObjects.size() == xmlDataObjects.size(), "Both responses have not matched element count"
println jdbcDataObjects
 
println xmlDataObjects
if (jdbcDataObjects != xmlDataObjects){
	println "Both responses do not match"
	for(int index=0;index<jdbcDataObjects.size();index++){
		assert jdbcDataObjects[index] == xmlDataObjects[index], "Responses @ ${index+1} position do not match"
	}
}
