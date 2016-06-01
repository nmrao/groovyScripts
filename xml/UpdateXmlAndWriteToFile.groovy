/**
* below is the groovy script which read and parses xml
* and updates its value and then writes back to file
**/
import groovy.xml.*
//Sample xml string
def xml = '''<?xml version="1.0" encoding="utf-8"?>
<cars>
  <car>
    <name>Jetta</name>
    <madeBy>Volkswagen</madeBy>
  </car>
  <car>
    <name>Polo GT</name>
    <madeBy>Volkswagen</madeBy>
  </car>
  <car>
    <name>i30</name>
    <madeBy>Typo</madeBy>
  </car>
</cars>'''
//parse the xml string and create object
def cars = new XmlSlurper().parseText(xml)
//find the car whose name is i30 and update its madeBy element value to Hyndai
cars.'**'.find { if (it.name == 'i30') it.madeBy = 'Hyndai'}
//Serializes and Writes cars object into given file
new File('c:/Temp/test1.xml').write(XmlUtil.serialize(cars))


/**
* Output looks like below:
<?xml version="1.0" encoding="UTF-8"?><cars>
  <car>
    <name>Jetta</name>
    <madeBy>Volkswagen</madeBy>
  </car>
  <car>
    <name>Polo GT</name>
    <madeBy>Volkswagen</madeBy>
  </car>
  <car>
    <name>i30</name>
    <madeBy>Hyndai</madeBy>
  </car>
</cars>
*/

