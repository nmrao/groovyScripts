//Check here for more details:
//https://community.smartbear.com/t5/SoapUI-NG/Datasource-Loop-through-JSON-data/m-p/131899#U131899
def json = """{
   "AccountAggregationCompute_AMBBOS-LPD0F3":    {
      "ID": "AccountAggregationCompute_AMBBOS-LPD0F3",
      "Service": "AccountAggregationCompute",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/AccountAggregationCompute",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "AccountBalancingCompute_AMBBOS-LPD0F3":    {
      "ID": "AccountBalancingCompute_AMBBOS-LPD0F3",
      "Service": "AccountBalancingCompute",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/AccountBalancingCompute",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "AccountBaseRunOffCompute_AMBBOS-LPD0F3":    {
      "ID": "AccountBaseRunOffCompute_AMBBOS-LPD0F3",
      "Service": "AccountBaseRunOffCompute",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/AccountBaseRunOffCompute",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "AccountCompute_AMBBOS-LPD0F3":    {
      "ID": "AccountCompute_AMBBOS-LPD0F3",
      "Service": "AccountCompute",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/AccountCompute",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "AccountNewBusinessCompute_AMBBOS-LPD0F3":    {
      "ID": "AccountNewBusinessCompute_AMBBOS-LPD0F3",
      "Service": "AccountNewBusinessCompute",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/AccountNewBusinessCompute",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "ComputeResults_AMBBOS-LPD0F3":    {
      "ID": "ComputeResults_AMBBOS-LPD0F3",
      "Service": "ComputeResults",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/ComputeResults",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "Hierarchy_AMBBOS-LPD0F3":    {
      "ID": "Hierarchy_AMBBOS-LPD0F3",
      "Service": "Hierarchy",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/Hierarchy",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "Model_AMBBOS-LPD0F3":    {
      "ID": "Model_AMBBOS-LPD0F3",
      "Service": "Model",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/Model",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "PoolCompute_AMBBOS-LPD0F3":    {
      "ID": "PoolCompute_AMBBOS-LPD0F3",
      "Service": "PoolCompute",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/PoolCompute",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "PositionBasket_AMBBOS-LPD0F3":    {
      "ID": "PositionBasket_AMBBOS-LPD0F3",
      "Service": "PositionBasket",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/PositionBasket",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "PositionSource_AMBBOS-LPD0F3":    {
      "ID": "PositionSource_AMBBOS-LPD0F3",
      "Service": "PositionSource",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/PositionSource",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "Position_AMBBOS-LPD0F3":    {
      "ID": "Position_AMBBOS-LPD0F3",
      "Service": "Position",
      "Tags": [],
      "Address": "http://ambbos-lpd0f3:8750/FIS/Risk/BancWare/api/V201701/Position",
      "Port": 8750,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   },
   "consul":    {
      "ID": "consul",
      "Service": "consul",
      "Tags": [],
      "Address": "",
      "Port": 8300,
      "EnableTagOverride": false,
      "CreateIndex": 0,
      "ModifyIndex": 0
   }
}"""

//Extracting Service data
def parsedJson = new groovy.json.JsonSlurper().parseText(json)
parsedJson.each { entry ->
   def data = entry.value
   println data.Service
}
