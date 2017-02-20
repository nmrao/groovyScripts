/**
* This create a new xml file from different xmls
* Refer for more details:
* https://community.smartbear.com/t5/SoapUI-Open-Source/Groovy-XML-Script-for-Loops/m-p/136678#U136678
**/
def students = """<Students>
    <name> st name 1</name>
    <name> st name 2</name>
    <name> st name 3</name>
    <name> st name 4</name>
    <name> st name 5</name>
    <name> st name 6</name>
    <name> st name 7</name>
    <name> st name 8</name>
    <name> st name 9</name>
  <name> st name 10</name>
    <name> st name 11</name>
    <name> st name 12</name>
    <name> st name 13</name>
    <name> st name 14</name>
    <name> st name 15</name>
    <name> st name 16</name>
    <name> st name 17</name>
    <name> st name 18</name>
    <name> st name 19</name>
  <name> st name 20</name>
    <name> st name 21</name>
    <name> st name 22</name>
    <name> st name 23</name>
    <name> st name 24</name>
    <name> st name 25</name>
    <name> st name 26</name>
    <name> st name 27</name>
    <name> st name 28</name>
    <name> st name 29</name>
  <name> st name 30</name>
  <name> st name 31</name>
    <name> st name 32</name>
    <name> st name 33</name>
    <name> st name 34</name>
    <name> st name 35</name>
    <name> st name 36</name>
    <name> st name 37</name>
    <name> st name 38</name>
    <name> st name 39</name>
  <name> st name 40</name>
  <name> st name 41</name>
    <name> st name 42</name>
    <name> st name 43</name>
    <name> st name 44</name>
    <name> st name 45</name>
    <name> st name 46</name>
    <name> st name 47</name>
    <name> st name 48</name>
    <name> st name 49</name>
<name> st name 50</name>
</Students>"""

def teachers = """<Teachers>
    <name> Teacher name 1</name>
    <name> Teacher name 2</name>
    <name> Teacher name 3</name>
</Teachers>"""


def getData  = { xml, elementName ->
	def parseXml = new XmlSlurper().parseText(xml)
	parseXml.'**'.findAll { it.name() == elementName }
}

def teacherNodes = getData( teachers, 'name')
def studentNodes = getData( students, 'name')

def sublist (data, start, end) {
	start == 0 ? data.take(end) : data.drop(start).take(end-start)
}

def incrementBy = 20
def startStudents = 0
def endStundents = incrementBy 
def builder = new groovy.xml.StreamingMarkupBuilder()
builder.encoding = 'UTF-8'
def xml = builder.bind {
    mkp.xmlDeclaration() 
    Assignments {
    	teacherNodes.eachWithIndex { teacherNode, index ->    		
    		Section {
    			id (index+1)
    			teacher (teacherNode.text())
    			studentList {
    				sublist(studentNodes, startStudents, endStundents).each { studentNode ->
    					name (studentNode.text())
    				}
    			}    			
    		}
    		startStudents = endStundents
    		endStundents += incrementBy
    	}    	
    }
}

println groovy.xml.XmlUtil.serialize(xml)
