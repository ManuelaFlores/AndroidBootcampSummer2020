package models.caffe

import java.util.*

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val price: Double,
    val name: String
)