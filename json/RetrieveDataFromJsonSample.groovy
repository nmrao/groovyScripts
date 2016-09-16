/**
* this script is to extract all id fields with in test property
* for more details: https://community.smartbear.com/t5/SoapUI-NG/How-to-generate-multiple-data-from-json-response-in-data-source/m-p/127075/highlight/false#M29291
**/
import groovy.json.JsonSlurper
​def response = '''{     "summary":[  
      {  
         "id":"26590924327313990",
         "name":"Summary-1",
         "state":"ACTIVE",
         "runTimestamp":1473138585467,
         "scheduledTimestamp":1473138585507,
         "createdTimestamp":1473138585507,
         "test":[  
            {  
               "id":"15473842955298105",
               "name":"test-1",
               "createdTimestamp":1473138585467,
               "runTimestamp":1473138585467,
               "status":{  
                  "Pass":10,
                  "Fail":20
               }
            }
         ]
      },
      {  
         "id":"30493603067815445",
         "name":"Summary-2",
         "state":"ACTIVE",
         "runTimestamp":1473138254517,
         "scheduledTimestamp":1473138254520,
         "createdTimestamp":1473138254520,
         "test":[  
            {  
               "id":"6780837264054677",
               "name":"Test -2 ",
               "createdTimestamp":1473138254517,
               "runTimestamp":1473138254517,
               "status":{  
                  "Pass":1,
                  "Fail":2
               }
            }
         ]
      }
   ]

}'''
def summary = new JsonSlurper().parseText(response).summary
def expectedIds = [15473842955298105, 6780837264054677]
def actualIds = []
summary.each { summaryItem ->
   summaryItem.test.each { testItem -> 
      actualIds << testItem.id 
   }
}
println actualIds
assert actualIds.sort() == expectedIds.sort(), "Test Ids are not matching"
