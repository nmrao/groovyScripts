/**
* Refer:https://stackoverflow.com/questions/38935530/iterate-arraylist-over-xml-groovy/38939125#38939125
*
**/

import groovy.xml.*
def books='''
    <response version-api="2.0">
        <value>
            <a>2</a>
            <b>
                <e>4</e> 
            </b>
            <g>123</g>
       </value>
    </response>'''
def elementsToBeRemoved = ['g'] 
def xml=new XmlParser().parseText(books) 
//Get all the nodes to be removed
def nodes = xml.'**'.findAll{ elementsToBeRemoved.contains(it.name())} 
//Remove all the nodes
nodes.each{it.parent().remove(it)} 
//Serialize to string
XmlUtil.serialize(xml).toString()
