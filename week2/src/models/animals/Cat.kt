package models.animals

import models.caffe.Sponsorship
import java.util.*

data class Cat(
    val id: String = UUID.randomUUID().toString(),
    val shelterId: String,
    val name: String,
    val breed: BreedCats,
    val gender: Gender,
    val sponsorships: MutableSet<Sponsorship>

)

enum class BreedCats {
    CHESHIRE,
}

enum class Gender {
    FEMALE, MALE
}