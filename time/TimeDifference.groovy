/**
* Refer:https://stackoverflow.com/questions/567659/calculate-elapsed-time-in-java-groovy/2757551#2757551
*
**/
import groovy.time.TimeCategory 
import groovy.time.TimeDuration

TimeDuration td = TimeCategory.minus( stop, start )
println td
