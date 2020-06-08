import helper.CafeController
import models.people.Employee
import models.people.Patron
import repository.*

fun main() {

    val cafeController = CafeController() // print out the data here using CafeController functions
    val date = Employee("Manu", "Flores", "fmanuela499@gmail.com","969999567", 900.0, "333333", "")
    date.clockIn()

    date.workedHours

    val newCustomer = Patron(
        id = "8908",
        firstName = "Jenny",
        lastName = "Rocket",
        email = "jenny.10@gmail.com",
        phoneNumber = "9786756452",
        sponsoredCats = mutableSetOf()
    )

    //The new customer buys some products:
    cafeController.addNewCustomer(newCustomer)

    cafeController.sellItems(listOf(bagel, cappuccino),newCustomer.id,2.0)
}