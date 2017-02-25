/**
* Generates a future date based on the contion mentioned below
* Refer:http://stackoverflow.com/questions/42441823/how-to-create-a-dynamic-date-generator-in-groovy/42448462#42448462
*
**/
import groovy.time.TimeCategory

def dateFormat = 'yyyy-MM-dd'

def getNumberInRange = { min, max -> new Random().nextInt(max + 1 - min) + min }

def isTodayBeforeMay = { Calendar.MONTH < 5 }

def isTodayAfterJune = { Calendar.MONTH > 6 }

//Get the number of days between today and given date
def getDifferenceDays = { targetDate, closure ->
    def strDate = closure (targetDate)
    def futureDate = new Date().parse(dateFormat, strDate)
    TimeCategory.minus(futureDate, new Date()).days
}

//Get the offset between today and max date i.e.,31 august
def getOffSetDays = { date ->
    //Need to change the date range if needed.
    //As per OP, May to August is mentioned below
    def max = getDifferenceDays(date) { "${it[Calendar.YEAR]}-08-31" }
    def min = getDifferenceDays(date) { "${it[Calendar.YEAR]}-05-01" }
    getNumberInRange(min, max)
}


def now = new Date()
def nextYearNow = now.updated(year: now[Calendar.YEAR] + 1)

def selected
def finalDate

println "Today : $now"
println "Next year same date : $nextYearNow"

if (isTodayBeforeMay()) {
    selected = now    
} else if (isTodayAfterJune()) {
    selected = nextYearNow
} else {
    //It is not mentioned what should happened for the mentioned period by OP
    throw new Error("Not implemented for the days between 1st May to 30th June")
}

def offset = getOffSetDays(selected)

//Add the offset days to selected date
use(TimeCategory) {
    finalDate = now + offset.days
}
println "Final future date is : $finalDate"
println "Final future date is(formatted) : ${finalDate.format(dateFormat)}"

//set the date at test case level property
//context.testCase.setPropertyValue('NEXT_DATE', finalDate.format(dateFormat))
