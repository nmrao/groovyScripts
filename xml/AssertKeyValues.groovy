/**
* Refer: https://stackoverflow.com/questions/44556839/groovy-in-soapui-asserting-on-repeating-nodes-located-by-expected-data/44557172#44557172
*
**/
def expected = ['011': '4', '018': '0', '098': '2']
def str = """<root>
              <ns2:Details xmlns:ns2="http://ww">
                 <ns2:Code>011</ns2:Code>
                 <ns2:Result>4</ns2:Result>
              </ns2:Details>
              <ns2:Details xmlns:ns2="http://ww">
                 <ns2:Code>018</ns2:Code>
                 <ns2:Result>0</ns2:Result>
              </ns2:Details>
              <ns2:Details xmlns:ns2="http://ww">
                 <ns2:Code>098</ns2:Code>
                 <ns2:Result>2</ns2:Result>
              </ns2:Details>
</root>"""

def xml = new XmlSlurper().parseText(str)
def actual = xml.'**'.findAll{it.name() == 'Details'}.collectEntries{[(it.Code.text()): it.Result.text()]}
assert expected == actual
