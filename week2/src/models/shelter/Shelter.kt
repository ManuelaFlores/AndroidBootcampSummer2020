package models.shelter

import java.util.*

data class Shelter(
    val shelterId: String = UUID.randomUUID().toString(),
    val name: String,
    val address: String,
    val phoneNumber: String
)