/**
* Refer: https://stackoverflow.com/questions/44457739/regular-expression-for-a-fixed-text-followed-by-a-date/44469850#44469850
*
**/

def str ='Price:12-Jun-2017'
def regex = '\\d{2}-[a-zA-Z]{3}-\\d{4}'

(str  =~ /($regex)/).each { match, date ->
  println date
}

println str.find( /($regex)/) {it[0]}
