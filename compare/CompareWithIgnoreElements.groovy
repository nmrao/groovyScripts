/** 
* Refer: https://community.smartbear.com/t5/SoapUI-NG/Groovy-XML-Comparison-Dynamic-XML-Attributes-are-not-ignored/m-p/139489#U139489
**/

def expected = """<response>
  <payload>
    <selfcare>
      <subscription id="2306" status="24">
        <pricepoint>
          <charging-method>3</charging-method>
          <duration-code>2</duration-code>
          <rate resource="GBP" tax-rate="0.185">2.37</rate>
        </pricepoint>
        <purchase-date>2017-04-18T14:12:39+05:30</purchase-date>
        <expiry-date>2017-04-25T23:59:59+05:30</expiry-date>
        <package-id>ReservedPackage</package-id>
       <b2b-partner>
          <id>B2B_Google</id>
        </b2b-partner>
        <partner-id>B2B_Google</partner-id>
      </subscription>
    </selfcare>
  </payload>
</response>"""
def actual = """<response>
  <payload>
    <selfcare>
      <subscription id="2310" status="24">
        <pricepoint>
          <charging-method>3</charging-method>
          <duration-code>2</duration-code>
          <rate resource="GBP" tax-rate="0.185">2.37</rate>
        </pricepoint>
        <purchase-date>2017-04-19T14:12:39+06:30</purchase-date>
        <expiry-date>2017-04-26T23:59:59+06:30</expiry-date>
        <package-id>ReservedPackage</package-id>
       <b2b-partner>
          <id>B2B_Google</id>
        </b2b-partner>
        <partner-id>B2B_Google</partner-id>
      </subscription>
    </selfcare>
  </payload>
</response>"""
def ignoreNodes = ['purchase-date', 'expiry-date']
def ignoreAttributes = ['subscription':'id']
def updateIgnoreElements = { data, fixedVal = 'REPLACED' ->
    def parsedData = new XmlSlurper().parseText(data)
    ignoreAttributes.each { k,v -> parsedData.'**'.findAll { it.name() == k}.collect{it.@"$v" = fixedVal} }
    ignoreNodes.each { element ->  parsedData.'**'.findAll { it.name() == element}.collect{it.replaceBody fixedVal} }
    groovy.xml.XmlUtil.serialize(parsedData) 
}

assert updateIgnoreElements(expected) == updateIgnoreElements(actual), 'Both are not matching'
