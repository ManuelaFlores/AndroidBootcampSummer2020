package helper

import models.animals.Cat
import models.caffe.Cafe
import models.caffe.MenuItem
import models.people.Person
import models.shelter.Shelter
import repository.catsInRefugeOfCats
import repository.catsRefuge
import repository.houseOfKittens
import repository.houseOfKittensCats
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class CafeController {

    // cafe related things
    private val cafe = Cafe()

    // shelter related things
    private val shelters = mutableSetOf(houseOfKittens, catsRefuge)
    private val shelterToCat = mutableMapOf(
        houseOfKittens to houseOfKittensCats,
        catsRefuge to catsInRefugeOfCats
    )

    fun addNewCustomer(person: Person) {
        cafe.addNewCustomer(person)
    }

    fun adoptCat(catId: String, person: Person) {
        // check if cats exist, and retrieve its entry!
        val catInShelter = shelterToCat.entries.firstOrNull { (_, catsInShelter) ->
            catsInShelter.any { it.id == catId }
        }

        // you can adopt that cat!
        if (catInShelter != null) {
            val cat = catInShelter.value.first { cat -> cat.id == catId } // find the cat for that ID again

            // remove the cat from the shelter
            catInShelter.value.remove(cat)

            // add the cat to the person
            person.cats.add(cat)
            println("Congratulations!! Now you adopted a cat :)  Come back again!")
        }
    }

    fun sellItems(items: List<MenuItem>, customerId: String, tip: Double) {
        val receipt = cafe.getReceipt(items, customerId)
        receipt.apply {
            addTip(tip)
            calculateTotalPriceOfMenuItems()
            calculateTaxOfMenuItems()
        }


        println("------Pawffe Receipt: ------")
        println(getCurrentDate(localDateTime = LocalDateTime.now()))
        println("Here's the detail of your consume:")
        println("Subtotal: $ ${receipt.subTotal}")
        println("Tax: $ ${receipt.tax}")
        println("Tip: $ ${receipt.tip}")
        println("You consumed : $ ${receipt.receiptTotal}")
        if (receipt.adoptedCats.isNotEmpty()) println("Thanks for adopt a cat :) ")
        if (receipt.sponsoredCats.isNotEmpty()) println("Thanks for sponsored a cat :) ")
    }

    /**
     * First gets a list of all adopters, then queries shelters.
     *
     * */
    fun getNumberOfAdoptionsPerShelter(): Map<String, Int> {
        val allAdopters = cafe.getAdoptedCats()
        val shelterToCatsKeys = shelterToCat.keys

        return shelterToCatsKeys.map { shelter ->
            shelter.name to allAdopters.count { cat ->
                shelter.shelterId == cat.shelterId
            }
        }.toMap()
    }

    fun getUnadoptedCats(): Set<Cat> {
        val mutableSet = mutableSetOf<Cat>()
        shelterToCat.forEach {
            mutableSet.addAll(it.value)
        }
        return mutableSet
    }

    fun getUnSponsoredCats() = cafe.getUnSponsoredCats(shelterToCat)

    fun getSponsoredCats() = cafe.getSponsoredCats(shelterToCat)

    fun showNumberOfReceiptsForDay(day: DaysOfWeek) {
        cafe.showNumberOfReceiptsForDay(day)
    }

    fun getTopSellingItems() = cafe.getTopSellingItems()

    fun showWorkingEmployees() = cafe.getWorkingEmployees()

    private fun getCurrentDate(initialMessage: String = "", localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        return "$initialMessage ${localDateTime.format(formatter)}"
    }
}