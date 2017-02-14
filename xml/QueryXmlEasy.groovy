/**
* filter data easily by adding list
* refer for more details - http://stackoverflow.com/questions/42222531/groovy-xmlslurper-searching-sepecific-node/42229427#42229427
**/
​def xml = """<stores>  <bookstore name="Store A">
      <employee>
          <id>546343</id>
          <name>Dustin Brown</name>
      </employee>
      <employee>
          <id>547547</id>
          <name>Lisa Danton</name>
      </employee>
      <book category="cooking">
        <title lang="en">Everyday Italian</title>
        <author>Giada De Laurentiis</author>
        <year>2005</year>
        <price>30.00</price>
      </book>
      <book category="children">
        <title lang="en">Harry Potter</title>
        <author>J K. Rowling</author>
        <year>2005</year>
        <price>29.99</price>
      </book>
      <book category="web">
        <title lang="en">Learning XML</title>
        <author>Erik T. Ray</author>
        <year>2003</year>
        <price>39.95</price>
      </book>
  </bookstore>
  <bookstore name="Store B">
  </bookstore>
</stores>"""

enum Operator {
  EQ('=='), LE('<='), GE('>='), GT('>'), LT('<'), NE('!=')
  def value
  Operator(String value){
    this.value = value
  }

  def getValue(){
    value
  }
}

def getClosure = { list ->
  def sb = new StringBuffer('{ it -> ') 
  list.eachWithIndex { sublist, index ->
   index == 0 ?: sb.append(' && ')
   Operator operator = sublist[1]
   sb.append("it.${sublist[0]}.text() ${operator.value} '${sublist[2]}'")
  }
  def query = sb.append(' }').toString()
  println "Query formed is : ${query}"
  def sh = new GroovyShell()
  sh.evaluate(query)
}

def getBook = { stores, closure ->
  stores.'**'.findAll { closure(it) } ?: 'Could not find matching book'
}

def stores = new XmlSlurper().parseText(xml)
def queryData = [['year','EQ', '2003'], ['price', 'LE', '39.95']]


def result = getBook(stores, getClosure(queryData))
println result

​
