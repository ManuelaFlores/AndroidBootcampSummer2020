package models.people

class Patron(
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {
    override fun toString(): String {
        return "Patron's full name: $firstName $lastName, Contact number:$phoneNumber, email: $email"
    }
}