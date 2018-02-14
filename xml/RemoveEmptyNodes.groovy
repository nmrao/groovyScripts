/**
* Ref: https://stackoverflow.com/questions/48777710/how-to-remove-empty-nodes-with-groovy
**/

def xmlString = """<Profile>
    <Name>Fin</Name>
    <Aga>20</Aga>
    <Contact>
        <Mobile>1819</Mobile>
        <Telephone/>
    </Contact>
    <Team>
        <Team1/>
        <Team2/>
    </Team>
</Profile>"""

def xml = new XmlParser().parseText(xmlString)
def nodes = xml.'**'.findAll{it.name() && !it.text()}
def removeNode = { node ->
   def parent = node.parent()
   parent.remove(node)
}

nodes.each{removeNode(it)}
groovy.xml.XmlUtil.serialize(xml)
