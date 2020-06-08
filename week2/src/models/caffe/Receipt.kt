package models.caffe

import models.animals.Cat

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

    var subTotal = 0.0
    var tip: Double = 0.0
    var tax = 0.0

    fun calculateTotalPriceOfMenuItems(): Double {
        menuItems.forEach {
            subTotal += it.price
        }
        return subTotal
    }

    fun calculateTaxOfMenuItems() {
        tax = subTotal * 0.1
    }

    fun addTip(tip: Double) {
        this.tip = tip
    }

    private fun calculateTotal(isEmployee: Boolean = false): Double {
        applyEmployeeDiscount = isEmployee
        return if (applyEmployeeDiscount && adoptedCats.isEmpty()) {
            ((subTotal + tax) * 0.85) + tip
        } else {
            subTotal + tax + tip
        }
    }


}