package models.people

import models.animals.Cat
import models.caffe.Sponsorship

class Patron(
    id: String,
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String,
    val sponsoredCats: MutableSet<Sponsorship>,
    cats: MutableSet<Cat> = mutableSetOf()
) : Person(id = id,firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber, cats = cats) {
    override fun toString(): String {
        return "Patron's full name: $firstName $lastName, Contact number:$phoneNumber, email: $email"
    }
}