package models.caffe

import java.util.*

data class MenuItem(
    val id: String = UUID.randomUUID().toString(),
    val price: Double,
    val name: String
)