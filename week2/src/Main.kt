import helper.CafeController
import helper.DaysOfWeek
import models.caffe.Cafe
import models.people.Employee

fun main() {

    val cafeController = CafeController() // print out the data here using CafeController functions
    val date = Employee("Manu", "Flores", "fmanuela499@gmail.com","393383", 900.0, "333333", "")
    date.clockIn()

    date.clockOut()

    date.workedHours

    val cafe = Cafe()
    cafe.showNumberOfReceiptsForDay(DaysOfWeek.Monday)
    cafe.getTopSellingItems()
}