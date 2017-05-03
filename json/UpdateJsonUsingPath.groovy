import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

def cats = ['a', 'b']
def jsonString = '{"parent":{"child":"oldValue"}}'
def newValue = 'newValue'
def stringPath = 'parent.child'
 
def json = new JsonSlurper().parseText(jsonString)
def jsonbuilder = new JsonBuilder(json)
def map = cats.inject([]){l, item -> def map = [:];map['id'] = item; l << map; l}
Eval.xy(jsonbuilder, map, "x.content." + stringPath + ' = y')
println jsonbuilder.toString()
