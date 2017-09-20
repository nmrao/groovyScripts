/**
* Ref: https://community.smartbear.com/t5/SoapUI-Open-Source/converting-datetime-to-unix-timestamp/m-p/150163#M25227
* Demo: https://ideone.com/T3oyru
* converts date time string to milli seconds
**/
def dateString = '2017-09-08 14:00:00+0200'
def dateFormat = 'yyyy-MM-dd HH:mm:ssZ'
def millis = Date.parse(dateFormat, dateString).time
println millis
