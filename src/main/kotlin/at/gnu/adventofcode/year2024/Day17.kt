package at.gnu.adventofcode.year2024

class Day17(registers: List<String>, program: String) {

    data class State(val a: Int, val b: Int, val c: Int, val ip: Int = 0, val output: List<String> = mutableListOf())

    private val program = program.substringAfter("Program: ").split(",").map(String::toInt)
    private val registers = registers.associate {
        it.substringAfter("Register ").first().uppercase().first() to it.substringAfter(": ").toInt()
    }


    fun part1(): String {
        val initialState = State(registers['A']!!, registers['B']!!, registers['C']!!)
//        println(initialState)
        val state = program.execute(initialState)
        val output = state.output.joinToString(",")
        if (DEBUG) println(output)
        return output
    }

    fun part2(): String {
        val output = program.joinToString(",")
        var a = -1
        do {
//            if ((a % 100_000) == 0) println(a)
            val state = program.execute(State(++a, registers['B']!!, registers['C']!!))
//            println("$a -> ${state.output}")
        } while (output != state.output.joinToString(","))
        return a.toString()
    }


    private fun List<Int>.execute(state: State): State {
        var newState = state
        while (newState.ip < size) {
            val instruction = this[newState.ip]
            val operand = this[newState.ip + 1]
            newState = when (instruction) {
                0 -> Adv(newState)(operand)
                1 -> Blx(newState)(operand)
                2 -> Bst(newState)(operand)
                3 -> Jnz(newState)(operand)
                4 -> Bxc(newState)(operand)
                5 -> Out(newState)(operand)
                6 -> Bdv(newState)(operand)
                7 -> Cdv(newState)(operand)
                else -> error("no such operation: $instruction")
            }
            if (DEBUG) println(newState)
        }
        return newState
    }

    sealed class Instruction(private val state: State) {
        abstract operator fun invoke(operand: Int): State

        fun Int.combo(): Int =
            when (this) {
                in 0..3 -> this
                4 -> state.a
                5 -> state.b
                6 -> state.c
                else -> error("reserved operand: $this")
            }

        fun debug(message: String) {
            if (DEBUG) println(message)
        }
    }

    class Adv(private val state: State) : Instruction(state) {
        override fun invoke(operand: Int): State {
            val combo = operand.combo()
            val value = (state.a shr combo)
            debug("Adv($operand) -> A = ${state.a} >> $combo = $value")
            return State(value, state.b, state.c, state.ip + 2, state.output)
        }
    }

    class Blx(private val state: State) : Instruction(state) {
        override fun invoke(operand: Int): State {
            val value = (state.b xor operand)
            debug("Blx($operand) -> B = ${state.b} xor $operand = $value")
            return State(state.a, value, state.c, state.ip + 2, state.output)
        }
    }

    class Bst(private val state: State) : Instruction(state) {
        override fun invoke(operand: Int): State {
            val combo = operand.combo()
            val value = (combo % 8)
            debug("Bst($operand) -> B = $combo mod 8 = $value")
            return State(state.a, value, state.c, state.ip + 2, state.output)
        }
    }

    class Jnz(private val state: State) : Instruction(state) {
        override fun invoke(operand: Int): State {
            return if (state.a == 0) {
                debug("Jnz($operand) -> A == 0 -> IP = IP + 2")
                val state = State(0, state.b, state.c, state.ip + 2, state.output)
//                println(state)
                state
            } else {
                debug("Jnz($operand) -> A == ${state.a} -> IP = $operand")
                val state = State(state.a, state.b, state.c, operand, state.output)
//                println(state)
                state
            }
        }
    }

    class Bxc(private val state: State) : Instruction(state) {
        override fun invoke(operand: Int): State {
            val value = state.b xor state.c
            debug("Bxc($operand) -> B = ${state.b} xor ${state.c} = $value")
            return State(state.a, value, state.c, state.ip + 2, state.output)
        }
    }

    class Out(private val state: State) : Instruction(state) {
        override fun invoke(operand: Int): State {
            val combo = operand.combo()
            val value = combo % 8
            debug("Out($operand) -> $combo mod 8 = $value")
            return State(state.a, state.b, state.c, state.ip + 2, state.output + value.toString())
        }
    }

    class Bdv(private val state: State) : Instruction(state) {
        override fun invoke(operand: Int): State {
            val combo = operand.combo()
            val value = (state.a shr combo)
            debug("Bdv($operand) -> B = ${state.a} >> $combo = $value")
            return State(state.a, value, state.c, state.ip + 2, state.output)
        }
    }

    class Cdv(private val state: State) : Instruction(state) {
        override fun invoke(operand: Int): State {
            val combo = operand.combo()
            val value = (state.a shr combo)
            debug("Cdv($operand) -> C = ${state.a} >> $combo = $value")
            return State(state.a, state.b, value, state.ip + 2, state.output)
        }
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day17.txt"
        const val DEBUG = false
    }
}

fun main() {
    val (registers, program) = Day17::class.java.getResource(Day17.RESOURCE)!!.readText().trim()
        .split("\n\n", "\r\n\r\n").map { it.split("\n", "\r\n") }
    val day17 = Day17(registers, program.first())
    println("Day17::part1 -> ${day17.part1()}")
    println("Day17::part2 -> ${day17.part2()}")
}
