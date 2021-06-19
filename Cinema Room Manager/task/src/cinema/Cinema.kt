package cinema

fun main() {
    val room = Communication.askForSeats()
    Communication.startDialog(room)
}