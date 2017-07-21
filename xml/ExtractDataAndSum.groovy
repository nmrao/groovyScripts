/**
* Refer: https://community.smartbear.com/t5/SoapUI-NG/Transfering-values-from-a-response-to-a-property/m-p/146321#M33279
*
**/
def xml = """<Response>
  <PropertyDef>
 <Property1>
  <e>
   <Name>Prop1-1</Name>
   <ID>1</ID>
  </e>
  <e> 
   <Name>Prop1-2</Name>
   <ID>2</ID>
  </e>
 </Property1>
 <Property2>
  <e>
   <Name>Prop2-1</Name>
   <ID>11</ID>
  </e>
  <e> 
   <Name>Prop2-2</Name>
   <ID>22</ID>
  </e>
 </Property2>
  </PropertyDef>
  <Propertyvalues>
   <e>
  <value1>3.0</value1>
      <references>
        <prop1>1</prop1>
        <prop2>11</prop2>
      </references>
      <value2>5</value2>
 </e>
 <e>
  <value1>4.0</value1>
      <references>
        <prop1>1</prop1>
        <prop2>22</prop2>
      </references>
      <value2>4</value2>
 </e>
 <e>
  <value1>6.0</value1>
      <references>
        <prop1>2</prop1>
        <prop2>11</prop2>
      </references>
      <value2>1</value2>
 </e>
  </Propertyvalues>
 
</Response>"""

//Define the following ; edit if required.
def expectedProp2Value = 11
def expectedKeyName = 'prop2'
def keyNameToSum = 'value1'

def pxml = new XmlSlurper().parseText(xml)
//Find all matching element e; 
def alles = pxml.'**'.findAll{it.name() ==  expectedKeyName && it == expectedProp2Value }*.parent()*.parent()
//Sum the values
def result = alles.collect{ it."$keyNameToSum".text() as float }.sum()
println "Sum of the values : $result"
