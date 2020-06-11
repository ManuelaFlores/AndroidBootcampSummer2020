import helper.CafeController
import models.people.Employee
import models.people.Patron
import repository.*

fun main() {

    val cafeController = CafeController() // print out the data here using CafeController functions
    val newEmployee = Employee("Manu", "Flores", "fmanuela499@gmail.com","969999567", 900.0, "333333", "")
    newEmployee.clockIn()
    newEmployee.workedHours

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

    //The new customer wants to adopt a cat:

    //First, we need to review our non-adopted cats

    //Get non-adopted cats:
    val nonAdoptedCats = cafeController.getUnadoptedCats()
    //She wants a random cat:
    val catAdoptedId = nonAdoptedCats.random().id
    cafeController.adoptCat(catAdoptedId, newCustomer)
    println()

    //Verifying the number of adoptions per shelter:
    println("Number of adoptions per shelter:")
    println(cafeController.getNumberOfAdoptionsPerShelter())

    //



}