fun main() {
    var fizzbuzz = ""
    for (i in 1..100) {
        if (i % 3 == 0 && i % 5 == 0) {
            fizzbuzz += "fizzbuzz, "
        } else if (i % 3 == 0) {
           fizzbuzz += "fizz, " 
        } else if (i % 5 == 0) {
            fizzbuzz += "buzz, "
        } else {
            fizzbuzz += "$i, "
        }
    }

    println(fizzbuzz)
}
