/**
* Ref: https://community.smartbear.com/t5/SoapUI-Open-Source/JSONSLURPER-How-to-check-optional-array-available-in-response/m-p/146572#M24633
* http://ideone.com/JdkTNz
*
**/

def json = """{
  "uri": "VehiclesandGadgets",
  "Types": {
    "Vehicle": [{
      "label": "Click to expand",
      "value": {
        "Cars": [{
          "label": "Click to expand",
          "value": {
            "Properties": [{
              "Make": "Honda",
              "Model": "Civic",
              "Color": "Red"
            }],
            "Specifications": [{
              "Engine": "V6",
              "Gear": "Auto",
              "Drive": "AWD"
            }]
          },
          "ov": true
        }]
      },
      "ov": true
    }],
    "Gadget": [{
      "label": "Click to expand",
      "value": {
        "Gaming": [{
          "label": "Click to expand",
          "value": {
            "Properties": [{
              "Type": "Xbox",
              "Model": "One",
              "Color": "Black"
            }]
          },
          "ov": true
        }]
      },
      "ov": true
    }]
  },
  "isFavorite": false
}"""

def jsonRes = new groovy.json.JsonSlurper().parseText(json)
def make = jsonRes.Types?.Vehicle?.value?.Cars?.value?.Properties?.Make
def result = make ? make.flatten()[0] : 'NA'
println result
