/**
* Refer:https://community.smartbear.com/t5/forums/replypage/board-id/SoapUING_forum/message-id/33159
* This groovy script adds cancelledForNonPay node after burglarAlarm
**/
def xml = """<HomeOwnersContext xmlns="http://schemas.datacontract.org/2004/07/Homesite.Homeowners.ContextModel.Api.Context" xmlns:i="http://www.w3.org/2001/XMLSchema-instance"> <DisplayOnlyData/>
<QuoteData>
<animalsBitten>false</animalsBitten>
<burglarAlarm>No</burglarAlarm>
<businessOnPremises>false</businessOnPremises>
<totalNumberOfPeople>2</totalNumberOfPeople>
</QuoteData>
<QuoteHeader>
<Channel>Direct_Web</Channel>
<FormCode>3</FormCode>
</QuoteHeader>
</HomeOwnersContext>"""

def pxml = new XmlSlurper().parseText(xml).declareNamespace('con':'http://schemas.datacontract.org/2004/07/Homesite.Homeowners.ContextModel.Api.Context', 'xsi': 'http://www.w3.org/2001/XMLSchema-instance')
pxml.QuoteData.burglarAlarm + {
  con.cancelledForNonPay (1)
}
println groovy.xml.XmlUtil.serialize(pxml)
