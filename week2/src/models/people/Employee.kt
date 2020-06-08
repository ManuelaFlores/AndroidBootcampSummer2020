package models.people

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Employee(
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String,
    val salary: Double,
    val socialSecurityNumber: String,
    private val hireDate: String
    //private var workedHours: Double? = null
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    private lateinit var entryTime: String
    private lateinit var checkOutTime: String

    var workedHours: Double? = null

    override fun toString(): String {
        return "Full name: $firstName $lastName hired on: $hireDate, you can contact him/her by writing to: $email, or calling to : $phoneNumber"
    }

    fun clockIn() {
        val currentDate = LocalDateTime.now()
        entryTime = getCurrentDate(currentDate)
        println(getCurrentDate("Hi :) You're starting to work at:", currentDate))
    }

    fun clockOut() {
        val currentDate = LocalDateTime.now()
        checkOutTime = getCurrentDate(currentDate)
        println(getCurrentDate("You're finishing work at:", currentDate))
        calculateWorkedHours()
    }

    private fun calculateWorkedHours() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val oldDate = dateFormat.parse(entryTime)
            val lastDate = dateFormat.parse(checkOutTime)
            val dateDifference = lastDate.time - oldDate.time
            val seconds = dateDifference / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            workedHours = hours.toDouble()
        } catch (e: ParseException) {
            println(e.localizedMessage)
        }
    }

    private fun getCurrentDate(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return localDateTime.format(formatter)
    }

    private fun getCurrentDate(initialMessage: String, localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        return "$initialMessage ${localDateTime.format(formatter)}"
    }
}