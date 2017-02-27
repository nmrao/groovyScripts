/**
* converts string to map by tokenizing
**/
def description = """index:17, mac:AAA, ip:BBB, port:0058, requestId:ce6598b2-fe8b-463d-bdf3-01ec35055f7a, tempImageKey:ba416127-14e3-4c7b-8f1f-5b4d633102e5"""
def result = description.split(',')*.trim()*.tokenize(':').collectEntries()
assert result instanceof Map
println result
