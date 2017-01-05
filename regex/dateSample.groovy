/**
* this script to test if an xml contains matching pattern of a date
* for more details, refer: https://community.smartbear.com/t5/forums/replypage/board-id/SoapUI_OS/message-id/22797
**/
def xml = """<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"  xmlns:ns2="http://example.com/1/">
   <S:Body>
      <ns2:InsertDate>2017-01-03T17:48:45</ns2:InsertDate>
   </S:Body>
</S:Envelope>"""

def pattern = "(InsertDate>\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}<\\/)"
assert xml =~ pattern, "Response does not have matching pattern"
