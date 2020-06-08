package models.animals

import helper.Gender
import models.caffe.Sponsorship
import java.util.*

data class Cat(
    val id: String = UUID.randomUUID().toString(),
    val shelterId: String,
    val name: String,
    val breed: BreedCats,
    val gender: Gender,
    val sponsorships: MutableSet<Sponsorship>

) {
    fun addSponsorShip(sponsorship: Sponsorship) {
        sponsorships.add(sponsorship)
    }
}

enum class BreedCats {
    CHESHIRE, TABBY_TOMCAT, TOYGER, MARMALADE_TABBY, BIRMAN, MIXED_BREED
}