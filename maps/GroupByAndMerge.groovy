/**
*
* Ref: https://stackoverflow.com/questions/45791945/how-to-join-list-of-maps-in-groovy/45792696#45792696
**/
def x = [ [a:1, b:2], [a:1, b:3], [a:2, b:4] ]
def y = ​[ [f:10, b:2, g:7], [f:100, b:3, g:8], [f:20, b:4, g:9] ]
[x,y].transpose().collect { a, b -> a + b }
