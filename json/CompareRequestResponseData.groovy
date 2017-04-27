/**
* Refer: https://community.smartbear.com/t5/forums/replypage/board-id/SoapUING_forum/message-id/31656
**/
def request = """{
     "storeId" : "0010",
     "registerNumber" : "0012"
  }"""

def response = """{
      "PackageId": "188800120412201723609318",
      "storeId" : "0010",
      "registerNumber" : "0012",
      "lineId": "01",
      "createdTimestamp": "04-19-2017 3:22 PM Wed UTC"
}"""

//Closure to get the required data from json
def getDataMap = { data, list ->
    def json = new groovy.json.JsonSlurper().parseText(data)
    json.inject([:]) {m, key, value -> if(key in list) m[key] = value;m }
}

def desiredKeys = ['storeId', 'registerNumber']

def reqestMap = getDataMap(request, desiredKeys)
def responseMap = getDataMap(response, desiredKeys)

println "Request data : $reqestMap"
println "Response data : $responseMap"
assert reqestMap == responseMap, 'Response data is not matching with request data'
