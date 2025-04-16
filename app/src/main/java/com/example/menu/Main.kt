

fun main() {
    val menu = arrayOf(
        "Ice cream" to 120,
        "Chocolate cupcake" to 200,
        "Napoleon cake" to 500,
        "Pancake" to 250,
        "Milkshake" to 160
    )

    var totalPrice = 0

    println("Welcome to our delivery menu!")

    while (true) {
        println("\nMenu:")
        menu.forEachIndexed { index, (dish, price) ->
            println("${index + 1}. ${dish ?: "Unnamed Dish"} - $price UAH")
        }
        println("0. Finish order")

        print("Please select a menu item (1-${menu.size}) or 0 to exit: ")
        val choiceInput = readlnOrNull()
        val choice = choiceInput?.toIntOrNull() ?: -1

        if (choice == 0) {
            break
        }

        try {
            if (choice in 1..menu.size) {
                val selectedDish = menu[choice - 1]
                totalPrice += selectedDish.second
                println("You selected: ${selectedDish.first ?: "Unnamed Dish"} - ${selectedDish.second} UAH")
            } else {
                throw InvalidMenuChoiceException(choice)
            }
        } catch (e: InvalidMenuChoiceException) {
            println("Error: ${e.message}")
        }
    }

    println("\nYour order is complete.")
    println("Total price: $totalPrice UAH")

    val discount = when {
        totalPrice >= 500 -> 0.15
        totalPrice >= 200 -> 0.10
        else -> 0.0
    }

    val finalPrice = totalPrice * (1 - discount)
    println("Applied discount: ${"%.0f".format(discount * 100)}%")
    println("Final price with discount: ${"%.2f".format(finalPrice)} UAH")
}

class InvalidMenuChoiceException(choice: Int) : Exception("Invalid menu item selection: $choice")

