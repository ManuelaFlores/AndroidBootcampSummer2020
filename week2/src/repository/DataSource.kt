package repository

import helper.Gender
import models.animals.BreedCats
import models.animals.Cat
import models.caffe.MenuItem
import models.caffe.Receipt
import models.caffe.Sponsorship
import models.people.Employee
import models.people.Patron
import models.people.Person

val bagel = MenuItem(id = "123", price = 9.50, name = "Bagel")
val cappuccino = MenuItem(id = "124", price = 2.20, name = "Cappuccino")
val orangeJuice = MenuItem(id = "125", price = 3.20, name = "Orange Juice")
val icedAmericano = MenuItem(id = "126", price = 2.00, name = "Iced Americano")
val croissant = MenuItem("126", price = 1.30, name = "Croissant")
val carrotCake = MenuItem("127", price = 2.50, name = "Carrot Cake")
val espresso = MenuItem("128", price = 3.0, name = "Espresso")


val mondayReceipts = mutableSetOf(
    Receipt(customerId = "1", menuItems = mutableListOf(cappuccino, croissant, croissant)),
    Receipt(customerId = "2", menuItems = mutableListOf(orangeJuice, carrotCake, orangeJuice, croissant)),
    Receipt(customerId = "3", menuItems = mutableListOf(cappuccino, cappuccino, icedAmericano)),
    Receipt(customerId = "4", menuItems = mutableListOf(icedAmericano)),
    Receipt(customerId = "5", menuItems = mutableListOf(orangeJuice, bagel))
)

val tuesdayReceipts = mutableSetOf(
    Receipt(customerId = "7", menuItems = mutableListOf(espresso)),
    Receipt(customerId = "8", menuItems = mutableListOf(orangeJuice, croissant)),
    Receipt(customerId = "9", menuItems = mutableListOf(cappuccino, carrotCake)),
    Receipt(customerId = "10", menuItems = mutableListOf(icedAmericano, icedAmericano)),
    Receipt(customerId = "11", menuItems = mutableListOf(orangeJuice, bagel, cappuccino))
)

val wednesdayReceipts = mutableSetOf(
    Receipt(customerId = "12", menuItems = mutableListOf(orangeJuice, orangeJuice)),
    Receipt(customerId = "13", menuItems = mutableListOf(cappuccino, croissant)),
    Receipt(customerId = "14", menuItems = mutableListOf(cappuccino, carrotCake)),
    Receipt(customerId = "15", menuItems = mutableListOf(icedAmericano, icedAmericano, icedAmericano)),
    Receipt(customerId = "16", menuItems = mutableListOf(icedAmericano, icedAmericano, icedAmericano)),
    Receipt(customerId = "17", menuItems = mutableListOf(icedAmericano, cappuccino, icedAmericano)),
    Receipt(customerId = "18", menuItems = mutableListOf(cappuccino, cappuccino, cappuccino))
)

val thursdayReceipts = mutableSetOf(
    Receipt(customerId = "19", menuItems = mutableListOf(orangeJuice, orangeJuice)),
    Receipt(customerId = "20", menuItems = mutableListOf(cappuccino, croissant)),
    Receipt(customerId = "21", menuItems = mutableListOf(cappuccino, carrotCake)),
    Receipt(customerId = "22", menuItems = mutableListOf(icedAmericano, icedAmericano, icedAmericano)),
    Receipt(customerId = "23", menuItems = mutableListOf(icedAmericano, icedAmericano, icedAmericano)),
    Receipt(customerId = "24", menuItems = mutableListOf(icedAmericano, cappuccino, icedAmericano)),
    Receipt(customerId = "25", menuItems = mutableListOf(cappuccino, cappuccino, cappuccino))
)

val fridayReceipts = mutableSetOf(
    Receipt(customerId = "26", menuItems = mutableListOf(orangeJuice, orangeJuice)),
    Receipt(customerId = "27", menuItems = mutableListOf(cappuccino, croissant)),
    Receipt(customerId = "28", menuItems = mutableListOf(cappuccino, carrotCake))
)

val saturdayReceipts = mutableSetOf(
    Receipt(customerId = "26", menuItems = mutableListOf(orangeJuice, orangeJuice)),
    Receipt(customerId = "27", menuItems = mutableListOf(espresso, espresso, espresso)),
    Receipt(customerId = "27", menuItems = mutableListOf(carrotCake, orangeJuice)),
    Receipt(customerId = "27", menuItems = mutableListOf(orangeJuice, croissant)),
    Receipt(customerId = "27", menuItems = mutableListOf(icedAmericano, croissant)),
    Receipt(customerId = "28", menuItems = mutableListOf(cappuccino, carrotCake))
)

val sundayReceipts = mutableSetOf(
    Receipt(customerId = "26", menuItems = mutableListOf(orangeJuice, orangeJuice, carrotCake)),
    Receipt(customerId = "27", menuItems = mutableListOf(espresso, croissant, bagel)),
    Receipt(customerId = "27", menuItems = mutableListOf(icedAmericano, croissant, orangeJuice)),
    Receipt(customerId = "28", menuItems = mutableListOf(cappuccino, bagel))
)

val employers = mutableSetOf(
    Employee(
        firstName = "Ana",
        lastName = "Flowers",
        email = "ana.flowers@gmail.com",
        phoneNumber = "959777954",
        salary = 1500.0,
        socialSecurityNumber = "333666",
        hireDate = ""
    ),
    Employee(
        firstName = "Jose",
        lastName = "Rojas",
        email = "jose120394@gmail.com",
        phoneNumber = "997567432",
        salary = 1500.0,
        socialSecurityNumber = "444555",
        hireDate = "",
        cats = mutableSetOf(
            Cat(
                id = "1234",
                shelterId = "987",
                name = "Michi",
                breed = BreedCats.TOYGER,
                gender = Gender.MALE
            )
        )
    )
)

val customersList: MutableSet<Person> = mutableSetOf<Person>(
    Person(
        id = "12345",
        firstName = "Tom",
        lastName = "Portman",
        phoneNumber = "993800622",
        email = "tom.portman@gmail.com",
        cats = mutableSetOf(
            Cat(
                id = "1235",
                shelterId = "988",
                name = "Coco",
                breed = BreedCats.BIRMAN,
                gender = Gender.MALE
            ),
            Cat(
                id = "1236",
                shelterId = "989",
                name = "Silver",
                breed = BreedCats.CHESHIRE,
                gender = Gender.FEMALE
            ),
            Cat(
                id = "1234",
                shelterId = "987",
                name = "Zoe",
                breed = BreedCats.TOYGER,
                gender = Gender.MALE
            )
        )
    ),
    Patron(
        id = "12346",
        firstName = "Bill",
        lastName = "Osma",
        email = "bill.o@gmail.com",
        phoneNumber = "9796666843",
        sponsoredCats = mutableSetOf(
            Sponsorship(patronId = "4444", catId = "986"),
            Sponsorship(patronId = "4444", catId = "985"),
            Sponsorship(patronId = "4444", catId = "984")
        )
    )
)





