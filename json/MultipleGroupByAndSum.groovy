/**
* Refer: http://stackoverflow.com/questions/44026268/group-by-several-parameters-in-groovy-sql-like
**/
def str = """[
	{
		"Country": "Norway",
		"Dept": "2",
		"Hours": 08.00
	},
	{
		"Country": "Norway",
		"Dept": "2",
		"Hours": 08.00
	},
	{
		"Country": "Sweden",
		"Dept": "1",
		"Hours": 08.00
	},
	{
		"Country": "Sweden",
		"Dept": "2",
		"Hours": 08.00
	}
]"""
 
def objJson = new groovy.json.JsonSlurper().parseText(str)
 
def list = objJson.groupBy({it.Country}, {it.Dept})
                  .collectEntries { k, v -> [k, v.collect{ [Hours: it.value.Hours.sum(), Dept: it.value.Dept[0], Country: it.value.Country[0]] }]}
                  .collect{ it.value }
                  .flatten()
 
print new groovy.json.JsonBuilder(list).toPrettyString()
