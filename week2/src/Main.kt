import helper.CafeController
import models.people.Employee

fun main() {

    val cafeController = CafeController() // print out the data here using CafeController functions
    val date = Employee("Manu", "Flores", "fmanuela499@gmail.com","393383", 900.0, "333333", "")
    date.clockIn()

    date.clockOut()

    date.workedHours
}