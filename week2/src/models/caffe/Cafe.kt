package models.caffe

import helper.DaysOfWeek
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
        DaysOfWeek.Monday to mondayReceipts,
        DaysOfWeek.Tuesday to tuesdayReceipts,
        DaysOfWeek.Wednesday to wednesdayReceipts,
        DaysOfWeek.Thursday to thursdayReceipts,
        DaysOfWeek.Friday to fridayReceipts,
        DaysOfWeek.Saturday to saturdayReceipts,
        DaysOfWeek.Sunday to sundayReceipts
    )

    // maybe as employees check in, you can add them to the list of working employees!
    private val employees = mutableSetOf<Employee>()
    private val customers = mutableSetOf<Person>()

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = mutableSetOf<Sponsorship>()

    fun checkInEmployee(employee: Employee) {
        employee.clockIn()
        employees.add(employee)
    }

    fun checkOutEmployee(employee: Employee) {
        employee.clockOut()
        employees.remove(employee)
    }

    fun showNumberOfReceiptsForDay(day: DaysOfWeek) {
        val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!
        println("On $day you made ${receiptForDay.size} transactions!")
    }

    //Verificar si es empleado o no para obtener descuento
    fun getReceipt(items: List<MenuItem>, customerId: String): Receipt {
        return Receipt(
            menuItems = items.toMutableList(),
            customerId = customerId,
            applyEmployeeDiscount = verifyIfIdBelongsToEmployee(customerId)
        )
    }

    private fun verifyIfIdBelongsToEmployee(customerId: String): Boolean {
        return (employees + customers).find { it.id == customerId } is Employee
    }

    fun addSponsorship(catId: String, personId: String) {
        // TODO add the sponsorship
    }

    fun getWorkingEmployees(): Set<Employee> = employees

    fun getAdoptedCats(): Set<Cat> {

    }

    fun getSponsoredCats(): Set<Cat> {
        return setOf()
    }

    fun getMostPopularCats(): Set<Cat> {
        return setOf()
    }

    fun getTopSellingItems(): Set<Pair<String, Int>> {
        val allItemsMenu = mutableListOf<MenuItem>()
        receiptsByDay.values.forEach { receiptsList ->
            receiptsList.forEach { receipt ->
                receipt.menuItems.forEach {
                    allItemsMenu.add(it)
                }
            }
        }

        val menuItemsMap = mutableSetOf<Pair<String, Int>>()
        allItemsMenu.groupBy { it.name }.forEach { itemsMenu ->
            menuItemsMap.add(Pair(itemsMenu.key, itemsMenu.value.size))
        }

        return menuItemsMap.sortedByDescending { it.second }.toSet()
    }

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }
}