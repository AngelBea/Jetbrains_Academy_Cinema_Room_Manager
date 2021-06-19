package cinema

import java.lang.Exception
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.round

class Room(val rows: Int, val seats: Int) {
    private val totalIncome by lazy { calculateIncome() }
    private val fullSeats = Array(rows) { Array(seats) { SEAT_INDICATOR_FREE }.toMutableList() }
    private val totalSeats = rows * seats
    private var seatsPurchased = 0
    private var currentIncome = 0
    private var percentage = 0.00

    private fun calculateIncome(): Int {
        return if (totalSeats <= 60) {
            totalSeats * 10
        } else {
            val halfRows = rows / 2
            (halfRows * seats * 10) + (rows - halfRows) * seats * 8
        }
    }

    fun getMap() {
        CINEMA_STRING.let(::println)
        (1..seats).joinToString(" ", "  ").let(::println)
        fullSeats.forEachIndexed { index, chars -> "${index.plus(1)} ${chars.joinToString(" ")}".let(::println) }
    }

    fun printTotalIncome() {
        println(TOTAL_INCOME_STRING)
        println("\$$totalIncome")
    }

    fun getIncome(): Int {
        return totalIncome
    }

    fun acquireSeat(row : Int, seat : Int){
        var correctTicket = false

        while (!correctTicket){
            if(row <= rows && seat <= seats){
                if(fullSeats[row.minus(1)][seat.minus(1)] == SEAT_INDICATOR_FREE){
                    fullSeats[row.minus(1)][seat.minus(1)] = SEAT_INDICATOR_BUSY
                    correctTicket = true
                }else{
                    ALREADY_PURCHASED.let(::println)
                    Communication.askForAcquireASeat(this)
                }
            }else{
                WRONG_INPUT_ERROR.let(::println)
                Communication.askForAcquireASeat(this)
            }
        }

        if (totalSeats <= EXPENSIVE_SEATS) {
            "$TICKET_PRICE_STRING${INCOME_FIRST_HALF}".let(::println)
            seatsPurchased++
            currentIncome += INCOME_FIRST_HALF
            percentage = seatsPurchased.toDouble() / totalSeats.toDouble() * 100
        } else{
            val halfRows = rows / 2
            "$TICKET_PRICE_STRING${if (row <= halfRows) {               
                seatsPurchased++
                currentIncome += INCOME_FIRST_HALF
                percentage = seatsPurchased.toDouble() / totalSeats.toDouble() * 100
                INCOME_FIRST_HALF
            } else{                
                seatsPurchased++
                currentIncome += INCOME_SECOND_HALF
                percentage = seatsPurchased.toDouble() / totalSeats.toDouble() * 100
                INCOME_SECOND_HALF
            } }".let(::println)
        }
    }

    fun getStats(){
        PURCHASED_STRING.plus("$seatsPurchased").let(::println)
        PERCENTAGE_STRING.plus("${"%.2f".format(percentage)}%").let(::println)
        CURRENT_INCOME_STRING.plus("\$$currentIncome").let(::println)
        TOTAL_INCOME_STRING.plus("\$$totalIncome").let(::println)
    }
}