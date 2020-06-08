package models.caffe

import models.animals.Cat

// TODO add data, such as ID, list of products, and maybe an optional set of cats adopted/sponsored!
data class Receipt(
    val menuItems: MutableList<MenuItem> = mutableListOf(),
    val customerId: String,
    var applyEmployeeDiscount: Boolean = false,
    val adoptedCats: MutableSet<Cat> = mutableSetOf(),
    val sponsoredCats: MutableSet<Sponsorship> = mutableSetOf()
) {
    val receiptTotal: Double
        get() {
            return calculateTotal()
        }

    private var subTotal = 0.0
    private var tip: Double = 0.0

    private fun calculateTotalPriceOfMenuItems(): Double {
        menuItems.forEach {
            subTotal += it.price
        }
        return subTotal
    }

    private fun calculateTaxOfMenuItems() = subTotal * 0.1

    fun addTip(tip: Double) {
        this.tip = tip
    }

    private fun calculateTotal(isEmployee: Boolean = false): Double {
        applyEmployeeDiscount = isEmployee
        return if (applyEmployeeDiscount && adoptedCats.isEmpty()) {
            ((calculateTotalPriceOfMenuItems() + calculateTaxOfMenuItems()) * 0.85) + tip
        } else {
            calculateTotalPriceOfMenuItems() + calculateTaxOfMenuItems() + tip
        }
    }


}