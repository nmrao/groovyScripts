/**
* Refer:
*
**/

​def response='''<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Header>
      <MessageID xmlns="http://www.w3.org">uuid: ...</MessageID>
   </S:Header>
   <S:Body>
      <ns3:ResponseElement xmlns:ns2="http://www.example.com/services/common/example/xsd" xmlns:ns3="http://www.example.com/example/xsd">
         <Info>
            <Data1>
               <Site1>OneValue</Site1>
            </Data1>
            <OtherData>
               <Code1>01234</Code1>
               <Time1>2017-05-28</Time1>
               <SecondCode1>ThirdValue</SecondCode1>
               <Number1>000</Number1>
            </OtherData>
         </Info>
         <Info>
            <Data2>
               <Site2>OneValue</Site2>
            </Data2>
            <OtherData>
               <Code2>56789</Code2>
               <Time2>2017-07-30</Time2>
               <SecondCode2>ThirdValue</SecondCode2>
               <Number2>111</Number2>
            </OtherData>
         </Info>
      </ns3:ResponseElement>
   </S:Body>
</S:Envelope>
'''
def data = new XmlSlurper().parseText(response)
def infos = data.'**'.findAll{it.name()=='Info'}
def result = infos.collect{
              element -> element.children().breadthFirst()*.name()
             .findAll { !element."$it".children().size() }
             .collectEntries{[(element.'*'."$it".name()): element.'*'."$it"] }
         }
println result
