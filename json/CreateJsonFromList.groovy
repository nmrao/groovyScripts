/**
* this script create a json array from a list of map based on the condition
* Refer :http://stackoverflow.com/questions/43881683/groovy-optimize-code-to-find-duplicate-elements/43882831?noredirect=1#comment74801117_43882831
*
**/

def invoiceList = [
  [INVOICE_DATE:20150617, INVOICE_NUMBER:617151,SENDER_COUNTRY:'USA', CLIENT_COUNTRY:'USA'],
  [INVOICE_DATE:20150617, INVOICE_NUMBER:617152,SENDER_COUNTRY:'CAD', CLIENT_COUNTRY:'MEX'],
  [INVOICE_DATE:20150617, INVOICE_NUMBER:617153,SENDER_COUNTRY:'CAD', CLIENT_COUNTRY:'MEX']
]

def getFilteredList = { map ->
    map.collect{ k,v -> invoiceList.countBy{ it."$k" }.findAll{it.value > 1}.collectEntries{[it.key,v] } }
}

//You may change the description in the values of below map
def findEntries = [CLIENT_COUNTRY: 'Multiple Client Countries found', SENDER_COUNTRY: 'Multiple Sender Countries found']
println groovy.json.JsonOutput.toJson(getFilteredList(findEntries))
