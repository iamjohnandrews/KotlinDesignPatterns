/**
 * Created by andrj148 on 5/27/16.
 */

enum class Quarter {
    SoldOut,
    NoQuarter,
    HasQuarter,
    Sold
}

abstract class State() {
    open fun insertQuarter() {
        println("Please wait, we're already giving you a gumball")
    }
    open fun ejectQuarter() {
        println("Sorry, you already turned the crank")
    }
    open fun turnCrank() {
        println("Turning twice doesn't get you another gumball")
    }

    abstract fun dispense()
}

class SoldState(var gumballMachine: GumballMachine): State() {

    override fun dispense() {
        gumballMachine.releaseGumball()

        if (gumballMachine.count > 0) {
            gumballMachine.setNewState(gumballMachine.noQuarterState)
        } else {
            gumballMachine.setNewState(gumballMachine.soldOutState)
        }
    }
}
class NoQuarterState(var gumballMachine: GumballMachine): State() {

    override fun insertQuarter() {
        gumballMachine.setNewState(gumballMachine.hasQuarterState)
    }

    override fun ejectQuarter() {
        println("You have not inserted a quarter")
    }

    override fun turnCrank() {
        println("You turned crank, but there is no quarter")
    }
    override fun dispense() {
        println("You need to pay. Gumballs cost 25 cents")
    }

}
class HasQuarterState(var gumballMachine: GumballMachine): State() {

    override fun insertQuarter() {
        println("You cannot insert another quarter")
    }

    override fun ejectQuarter() {
        println("Quarter returned")
        gumballMachine.setNewState(gumballMachine.soldState)
    }

    override fun turnCrank() {
        println("You turned crank...")
        gumballMachine.setNewState(gumballMachine.soldState)
    }
    override fun dispense() {
        println("No gumball dispensed")
    }

}
class SoldOutState(var gumballMachine: GumballMachine): State() {

    override fun insertQuarter() {
        println("Oops, we're out of gumballs")
    }

    override fun turnCrank() {
        println("Oops, we're out of gumballs")
    }
    override fun dispense() {
        println("Oops, we're out of gumballs")
    }
}
class WinnerState(var gumballMachine: GumballMachine): State() {

    override fun dispense() {
        gumballMachine.releaseGumball()

        if (gumballMachine.count == 0) {
            gumballMachine.setNewState(gumballMachine.noQuarterState)
        } else {
            gumballMachine.releaseGumball()
            println("You're a Winner! You get an extra gumball for free")

            if (gumballMachine.count > 0) {
                gumballMachine.setNewState(gumballMachine.noQuarterState)
            } else {
                println("Oops, we're out of gumballs")
                gumballMachine.setNewState(gumballMachine.soldOutState)
            }
        }
    }
}

class GumballMachine(var count: Int) {
    var state: State
    var soldState = SoldState(this)
    var noQuarterState = NoQuarterState(this)
    var hasQuarterState = HasQuarterState(this)
    var soldOutState = SoldOutState(this)

    init {
        if (count > 0) {
            state = noQuarterState
        } else {
            state = soldOutState
        }
    }

    fun insertQuarter() {
        state.insertQuarter()
    }
    fun ejectQuarter() {
        state.ejectQuarter()
    }
    fun turnCrank() {
        state.turnCrank()
        state.dispense()
    }
    fun setNewState(newState: State) {
        this.state = newState
    }
    fun releaseGumball() {
        println("a gumball is rolling out the slot...")
        if (count != 0) {
            count--
        }
    }
}