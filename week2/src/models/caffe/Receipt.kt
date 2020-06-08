package models.caffe

// TODO add data, such as ID, list of products, and maybe an optional set of cats adopted/sponsored!
data class Receipt(
    val menuItems: MutableList<MenuItem> = mutableListOf(),
    val customerId: String
) {
    val receiptTotal: Double
        get() {
            return calculateTotal()
        }

    private var subTotal = 0.0

    private fun calculateTotalPriceOfMenuItems(): Double {
        menuItems.forEach {
            subTotal += it.price
        }
        return subTotal
    }

    private fun calculateTaxOfMenuItems() = subTotal * 0.1

    fun addTip(tip: Double) {
        subTotal += tip
    }

    private fun calculateTotal(): Double = calculateTotalPriceOfMenuItems() + calculateTaxOfMenuItems()

}