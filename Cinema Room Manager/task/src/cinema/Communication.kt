package cinema

class Communication {
    companion object{
        fun askForSeats() : Room{
            MESSAGE_ASK_ROWS.let(::println)
            INPUT_SYMBOL.let(::print)
            val rows = readLine()!!.toInt()

            MESSAGE_ASK_SEATS.let(::println)
            INPUT_SYMBOL.let(::print)
            val seats = readLine()!!.toInt()

            val room = Room(rows, seats)
            room.getMap()

            return room
        }

        fun askForAcquireASeat(room: Room){
            MESSAGE_ASK_ROW.let(::println)
            INPUT_SYMBOL.let(::print)
            val row = readLine()!!.toInt()

            MESSAGE_ASK_SEAT.let(::println)
            INPUT_SYMBOL.let(::print)
            val seat = readLine()!!.toInt()

            room.acquireSeat(row, seat)
            room.getMap()
        }

        fun startDialog(room : Room){
            var option = 0
            do{
                OPTION_1.let(::println)
                OPTION_2.let(::println)
                OPTION_3.let(::println)
                OPTION_0.let(::println)
                INPUT_SYMBOL.let(::print)

                option = readLine()!!.toInt()

                when(option){
                    1 -> room.getMap()
                    2 -> askForAcquireASeat(room)
                    3 -> room.getStats()
                }
            }while (option > 0)
        }
    }
}