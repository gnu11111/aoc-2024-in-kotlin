package at.gnu.adventofcode.year2024

class Day17(registers: List<String>, program: String) {

    data class State(val a: Long, val b: Long, val c: Long, val ip: Int = 0, val output: List<String> = mutableListOf())

    private val program = program.substringAfter("Program: ").split(",").map(String::toInt)
    private val registers = registers.associate {
        it.substringAfter("Register ").first().uppercase().first() to it.substringAfter(": ").toLong()
    }


    fun part1(): String {
        val initialState = State(registers['A']!!, registers['B']!!, registers['C']!!)
        val state = program.execute(initialState)
        val output = state.output.joinToString(",")
        if (DEBUG) println(output)
        return output
    }

    fun part2(): String {
        val originalProgram = program.joinToString(",")
        var candidates = listOf(0L)
        while (candidates.isNotEmpty()) {
            candidates = buildList {
                for (base in candidates) {
                    val start = base shl 3
                    for (a in start..(start + 7)) {
                        val state = program.execute(State(a, registers['B']!!, registers['C']!!))
                        val output = state.output.joinToString(",")
                        if (originalProgram == output)
                            return a.toString()
                        else if (originalProgram.endsWith(output))
                            add(a)
                    }
                }
            }
        }
        error("A not found!")
    }


    private fun List<Int>.execute(state: State): State {
        var currentState = state
        while (currentState.ip < size) {
            val instruction = this[currentState.ip]
            val operand = this[currentState.ip + 1]
            currentState = when (instruction) {
                0 -> Adv(operand)
                1 -> Blx(operand)
                2 -> Bst(operand)
                3 -> Jnz(operand)
                4 -> Bxc(operand)
                5 -> Out(operand)
                6 -> Bdv(operand)
                7 -> Cdv(operand)
                else -> error("no such operation: $instruction")
            }.execute(currentState)
            if (DEBUG) println(currentState)
        }
        return currentState
    }

    sealed class Instruction(val execute: (State) -> State) {
        companion object {
            fun Int.combo(state: State): Long =
                when (this) {
                    in 0..3 -> this.toLong()
                    4 -> state.a
                    5 -> state.b
                    6 -> state.c
                    else -> error("reserved operand: $this")
                }

            fun debug(message: String) {
                if (DEBUG) println(message)
            }
        }
    }

    class Adv(private val operand: Int) : Instruction({ state ->
        val combo = operand.combo(state)
        val value = (state.a shr combo.toInt())
        debug("Adv($operand) -> A = ${state.a} >> $combo = $value")
        State(value, state.b, state.c, state.ip + 2, state.output)
    })

    class Blx(private val operand: Int) : Instruction({ state ->
        val value = (state.b xor operand.toLong())
        debug("Blx($operand) -> B = ${state.b} xor $operand = $value")
        State(state.a, value, state.c, state.ip + 2, state.output)
    })

    class Bst(private val operand: Int) : Instruction({ state ->
        val combo = operand.combo(state)
        val value = (combo and 0b111)
        debug("Bst($operand) -> B = $combo mod 8 = $value")
        State(state.a, value, state.c, state.ip + 2, state.output)
    })

    class Jnz(private val operand: Int) : Instruction({ state ->
        if (state.a == 0L) {
            debug("Jnz($operand) -> A == 0 -> IP = IP + 2")
            State(0, state.b, state.c, state.ip + 2, state.output)
        } else {
            debug("Jnz($operand) -> A == ${state.a} -> IP = $operand")
            State(state.a, state.b, state.c, operand, state.output)
        }
    })

    class Bxc(private val operand: Int) : Instruction({ state ->
        val value = state.b xor state.c
        debug("Bxc($operand) -> B = ${state.b} xor ${state.c} = $value")
        State(state.a, value, state.c, state.ip + 2, state.output)
    })

    class Out(private val operand: Int) : Instruction({ state ->
        val combo = operand.combo(state)
        val value = combo % 8
        debug("Out($operand) -> $combo mod 8 = $value")
        State(state.a, state.b, state.c, state.ip + 2, state.output + value.toString())
    })

    class Bdv(private val operand: Int) : Instruction({ state ->
        val combo = operand.combo(state)
        val value = (state.a shr combo.toInt())
        debug("Bdv($operand) -> B = ${state.a} >> $combo = $value")
        State(state.a, value, state.c, state.ip + 2, state.output)
    })

    class Cdv(private val operand: Int) : Instruction({ state ->
        val combo = operand.combo(state)
        val value = (state.a shr combo.toInt())
        debug("Cdv($operand) -> C = ${state.a} >> $combo = $value")
        State(state.a, state.b, value, state.ip + 2, state.output)
    })

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
