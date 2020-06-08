package models.caffe

import helper.DaysOfWeek
import models.animals.Cat
import models.people.Employee
import models.people.Patron
import models.people.Person
import models.shelter.Shelter
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
    //TODO: regresarlo a como estaba
    private val employees = employers

    //Can contain persons or patrons
    private val customers = customersList

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = mutableSetOf<Sponsorship>()

    fun addNewCustomer(person: Person) {
        customers.add(person)
    }

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
        val sponsorship = Sponsorship(catId = catId, patronId = personId)
        customers.filterIsInstance<Patron>().find { it.id == personId }?.sponsoredCats?.add(sponsorship)
    }

    fun getWorkingEmployees(): Set<Employee> = employees

    fun getAdoptedCats(): Set<Cat> {
        return (employees + customers).filter { it.cats.size != 0 }.flatMap {
            it.cats
        }.toSet()
    }

    //TODO: POR PROBAR
    fun getSponsoredCats(catsInShelter: MutableMap<Shelter, MutableSet<Cat>>): Set<Cat> {
        val sponsoredCats = mutableSetOf<Cat>()
        val sponsoredCatsIdList = customers.filterIsInstance<Patron>().flatMap { patron ->
            patron.sponsoredCats.map { sponsorship ->
                sponsorship.catId
            }
        }

        sponsoredCatsIdList.forEach { catId ->
            catsInShelter.values.forEach { cats ->
                val foundedCat = cats.find {
                    it.id == catId
                }
                foundedCat?.let { sponsoredCats.add(it) }
            }
        }
        return sponsoredCats
    }

    fun getMostPopularCats(catsInShelter: MutableMap<Shelter, MutableSet<Cat>>): Set<Cat> {
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