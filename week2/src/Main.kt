import helper.CafeController
import helper.DaysOfWeek
import models.people.Employee
import models.people.Patron
import repository.*
import java.time.LocalDate
import java.time.LocalDateTime

fun main() {

    //Create a CafeController instance:
    val cafeController = CafeController()

    //Creating a new employee
    val newEmployee = Employee(
        "Manu",
        "Flores",
        "fmanuela499@gmail.com",
        "969999567",
        900.0,
        "333333",
        cafeController.getCurrentDate("", LocalDateTime.now())
    )

    //Register a new employee:
    cafeController.checkingEmployee(newEmployee)
    println()

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

    cafeController.sellItems(listOf(bagel, cappuccino), newCustomer.id, 2.0)
    println()
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
    println()

    //Producing a cafe report:
    //According to produce our cafe report, let's start by brush over the number of receipts per day:
    cafeController.showNumberOfReceiptsForDay(DaysOfWeek.Monday)
    println()

    //Then let's take a review about employees working
    println(cafeController.showWorkingEmployees())

    //Produce a list of the unadopted, unsponsored and sponsored cats staying at the cafe currently
    println("Non-adopted cats: ${cafeController.getUnadoptedCats()}")
    println()
    println("Non-sponsored cats: ${cafeController.getUnSponsoredCats()}")
    println()
    println("Sponsored cats: ${cafeController.getSponsoredCats()}")
    println()

    //Get top selling items:
    println("Top Selling items:")
    println(cafeController.getTopSellingItems())
    println()

    //Get most popular cats:
    println(cafeController.getMostPopularCats())

}