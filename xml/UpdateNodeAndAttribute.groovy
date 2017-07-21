/**
* Refer: https://stackoverflow.com/questions/45235508/update-soap-request-node-value-and-attribute-value-using-groovy/45236090#45236090
* Output : http://ideone.com/qMBRuy
**/

def xml = """<root>
<TITLE>Computer Parts</TITLE>
<PART Price="High">
   <ITEM>Motherboard</ITEM>
   <MANUFACTURER>ASUS</MANUFACTURER>
   <MODEL>P3B-F</MODEL>
   <COST>123.00</COST>
</PART>
</root>"""

def pxml = new XmlSlurper().parseText(xml)

def expectedCost = 200.00
def expectedPrice = 'Low'

def cost = pxml.'**'.find{it.name() == 'COST'}
cost.replaceBody(expectedCost)
def part = pxml.'**'.find{it.name() == 'PART'}
part.@Price = expectedPrice
println groovy.xml.XmlUtil.serialize(pxml)
