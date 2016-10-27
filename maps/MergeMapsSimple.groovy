/**
 * this script to merge the maps based on the closure provided by user based on different use case
 * for more details: http://stackoverflow.com/questions/40267591/how-to-merge-two-maps-in-groovy
 **/

 //For sample, taking below 3 maps
def map1 = [a:10, b:2, c:3]
def map2 = [b:3, c:2, d:5]
def map3 = [d:3,a:4,e:9]

//Below method takes list of maps and closure as input and returns merged map
def getMergedMap(list, closure) {
   def keys = [] as Set
   list.each { element -> keys.addAll(element.keySet()) }
   def map = [:]
   keys.each { k ->
       def items = []
       list.each { items.add(it[k]) }
        map[k] =  closure(items)
    }
   map
}

//Create the list of maps
def mapList = [map1, map2, map3]
//Call the above method and pass the closure are need for merging condition, here min of matched key values from multiple maps
def newmap = getMergedMap(mapList) { list -> Collections.min(list - null)  }
println newmap
//Call the above method and pass the closure are need for merging condition, here max of matched key values from multiple maps
newmap = getMergedMap(mapList) { list -> Collections.max(list - null)  } 
println newmap
//Call the above method and pass the closure are need for merging condition, here sum of matched key values from multiple maps
newmap = getMergedMap(mapList) { list -> (list-null).sum()  }
println newmap
