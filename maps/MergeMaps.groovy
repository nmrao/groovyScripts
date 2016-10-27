/**
* for more details : http://stackoverflow.com/questions/40267591/how-to-merge-two-maps-in-groovy
**/
//Merging of multiple maps with different merge strategies 
//And handled null inside of mergeMethod instead of outside like earlier
def map1 = [a:10, b:2, c:3]
def map2 = [b:3, c:2, d:5]
def map3 = [d:3, a:4, e:9]

//Input map list and Merge strategy closure and handling null
def getMergedMap(list, closure) {
   list.inject([],{ klist, map -> klist.addAll(map.keySet());klist as Set}).inject([:]) { m, k -> m[k] =  closure(list.inject([]){ vlist,map -> vlist << map[k];vlist-null });m }
}

def mapList = [map1, map2, map3]

//Closures for merged value strategy
def minValue = { list -> Collections.min(list) }
def maxValue = { list -> Collections.max(list) }
def totalValue = { list -> (list).sum() }
def defaultValue = { list -> (list).last() }

//Call merge maps with strategies and assert
assert [a:4, b:2, c:2, d:3, e:9] == getMergedMap(mapList, minValue)
assert [a:10, b:3, c:3, d:5, e:9] == getMergedMap(mapList, maxValue)
assert [a:14, b:5, c:5, d:8, e:9] == getMergedMap(mapList, totalValue)
assert [a:4, b:3, c:2, d:3, e:9] == getMergedMap(mapList, defaultValue)
