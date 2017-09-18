/**
* Ref: https://community.smartbear.com/t5/SoapUI-Pro/How-to-verify-the-presence-of-an-xml-element-node-in-an-xml/m-p/149865#M34187
* Online demo : https://ideone.com/v1mrZ1
* This script check if given element is present or not.
**/

def xmlString = """<ApiRootContext xmlns="http://schemas.datacontract.org/" xmlns:i="http://www.w3.org/2001/XMLSchema-instance">
   <ApiHeader xmlns:a="http://schemas.datacontract.org/20/0/dbfasdjk.Context">
      <a:Duration>2218.3778</a:Duration>
      <a:EndTime>2017-09-15T10:13:26.4970511-04:00</a:EndTime>
      <a:RequestGuid>c0594d5b-4f15-4fc0-9efc-f574492796f8</a:RequestGuid>
      <a:RequestStatus>Eligibility</a:RequestStatus>
      <a:StartTime>2017-09-15T10:13:24.2782836-04:00</a:StartTime>
   </ApiHeader>
   <QuoteContext xmlns:a="http://schemas.datacontract.sfnadkfnjksfl.Api.Context">
      <a:HomeOwners>
         <a:DisplayOnlyData/>
         <a:PolicyData/>
         <a:QuoteData>
            <a:AdditionalInterestList xmlns:b="http://schemas.dadvfladfnkjlad.Context"/>
            <a:approxFairMarketValue>482678.00</a:approxFairMarketValue>
            <a:dateOfBirth>1979-04-20T00:00:00</a:dateOfBirth>
            <a:effectiveDateOfPolicy>2017-09-23T00:00:00</a:effectiveDateOfPolicy>
            <a:firstName>Wendi</a:firstName>
            <a:yearOfConstruction>1978</a:yearOfConstruction>
         </a:QuoteData>
         <a:QuoteHeader>
            <a:Channel>Direct_Web</a:Channel>
            <a:CompleteQuoteFlag>false</a:CompleteQuoteFlag>
            <a:QuoteStatus>dafvnadk</a:QuoteStatus>
            <a:SessionHash>adfnadjfkajdvnkdn</a:SessionHash>
         </a:QuoteHeader>
         <a:QuoteOffers/>
      </a:HomeOwners>
   </QuoteContext>
</ApiRootContext>"""
def xml = new XmlSlurper().parseText(xmlString)
assert xml.'**'.findAll { it.name() == 'approxFairMarketValue'}.size() > 0, 'approxFairMarketValue element not found'
