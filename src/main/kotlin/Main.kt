import kotlin.random.Random

fun main() {
    val secretNumber = generateSecretNumber()
    var guess: String? = null
    var numGuesses = 0

    println("I'm thinking of a 4-digit number. Can you guess what it is?")

    while (guess != secretNumber) {
        print("Enter your guess: ")
        guess = readLine()?.trim()

        if (guess != null && guess.length == 4) {
            val (correctDigits, correctPositions) = evaluateGuess(secretNumber, guess)
            println("$correctDigits:$correctPositions")
            numGuesses++
        } else {
            println("Invalid guess. Please enter a 4-digit number.")
        }
    }

    println("Congratulations! You guessed the number in $numGuesses guesses.")
}

// Creates a random 4-digit number and returns it as string
fun generateSecretNumber(): String {
    var digits = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    digits.shuffle()

    return digits.subList(0, 4).joinToString("")
}

// takes the generated secret number and the given 'guess' and checks for correct digits and their position
// returns an int pair which corresponds to the number of correct guessed digits and the correct number of positions
fun evaluateGuess(secretNumber: String, guess: String): Pair<Int, Int> {
    var correctDigits = 0
    var correctPositions = 0

    for (i in 0 until 4) {
        if (guess[i] == secretNumber[i]) {
            correctPositions++
        }

        if (secretNumber.contains(guess[i])) {
            correctDigits++
        }
    }

    return Pair(correctDigits, correctPositions)
}
