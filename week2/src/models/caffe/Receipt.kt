package models.caffe

// TODO add data, such as ID, list of products, and maybe an optional set of cats adopted/sponsored!
data class Receipt (
    val menuItems: MutableList<MenuItem> = mutableListOf(),
    val customerId: String
) {
    val receiptTotal : Double
    get() {
        //TODO: hacer los calculos para extraer el precio de todos los menu items
       return 0.0
    }
}