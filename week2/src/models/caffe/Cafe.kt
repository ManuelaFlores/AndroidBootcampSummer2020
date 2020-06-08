package models.caffe

import models.animals.Cat
import models.people.Employee
import models.people.Person
import repository.*

/**
 * @class Cafe is in charge to manage all logic related to
 * @property receiptsByDay is in charge to save all week's receipts
 *
 */
class Cafe {

    private val receiptsByDay = mutableMapOf(
        "Monday" to mondayReceipts,
        "Tuesday" to tuesdayReceipts,
        "Wednesday" to wednesdayReceipts,
        "Thursday" to thursdayReceipts,
        "Friday" to fridayReceipts,
        "Saturday" to saturdayReceipts,
        "Sunday" to sundayReceipts
    )

    // maybe as employees check in, you can add them to the list of working employees!
    private val employees = mutableSetOf<Employee>()
    private val customers = mutableSetOf<Person>()

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = mutableSetOf<Sponsorship>()

    // TODO Add logic for checking in and checking out!
    fun checkInEmployee(employee: Employee) {

    }

    fun checkOutEmployee(employee: Employee) {

    }

    fun showNumberOfReceiptsForDay(day: String) {
        val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!

        println("On $day you made ${receiptsByDay.size} transactions!")
    }

    fun getReceipt(items: List<MenuItem>, customerId: String): Receipt {
        // TODO return a receipt! Also make sure to check if customer is also an employee

        return Receipt(mutableListOf<MenuItem>(), "")
    }

    fun addSponsorship(catId: String, personId: String) {
        // TODO add the sponsorship
    }

    fun getWorkingEmployees(): Set<Employee> = employees

    fun getAdoptedCats(): Set<Cat> {
        return setOf()
    }

    fun getSponsoredCats(): Set<Cat> {
        return setOf()
    }

    fun getMostPopularCats(): Set<Cat> {
        return setOf()
    }

    fun getTopSellingItems(): Set<MenuItem> {
        return setOf()
    }

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }
}