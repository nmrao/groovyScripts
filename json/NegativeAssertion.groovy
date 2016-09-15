/**
* Below script gets values of 'meid' from json string
* and varifies against a set of values and should not match with any of the values.
* for more details:
* https://community.smartbear.com/t5/SoapUI-NG/Help-with-JSON-Negative-Assertion/m-p/126946#U126946
**/
import groovy.json.JsonSlurper

def str = '''{
  "getCorrectionsResult":{
    "correctionsIdentificationOut":[
      {
        "returnID":1093,
        "meid":673,
        "checkDate":"12/01/2015"
      },
      {
        "returnID":8103,
        "meid":673,
        "checkDate":"02/01/2016"
      }
    ]
  }
}'''

def json = new JsonSlurper().parseText(str)
def existingMeids = json.getCorrectionsResult.correctionsIdentificationOut.meid
def shouldNotBePresent = [671, 672, 980, 994, 993, 995]
assert [] == existingMeids.intersect(shouldNotBePresent), "There are common meids present which is incorrect"

