fun main() {
    val name = "Wahid Bawa"
    val yearOfBirth = 2001
    var currentYear = 2021
    var age: Int
    
    age = currentYear - yearOfBirth
    println("$age")
    currentYear++
    println("$name was born in $yearOfBirth and is $age years old." + "Next year he will be ${currentYear - yearOfBirth} years old") 
}
