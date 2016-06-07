/**
 * Created by andrj148 on 5/26/16.
 */
//Without Hook
abstract class CaffeineBeverage() {

    fun prepareRecipe() {
        boilWater()
        brew()
        pourInCup()
        addCondiments()
    }

    abstract fun brew()
    abstract fun addCondiments()

    fun boilWater() {
        println("boil water")
    }
    fun pourInCup() {
        println("Pour into cup")
    }
}

class Tea(): CaffeineBeverage() {
    override fun brew() {
        println("Steep bag of tea")
    }

    override fun addCondiments() {
        println("add lemon and honey")
    }
}

class Coffee(): CaffeineBeverage() {
    override fun brew() {
        println("Drip coffee through filter")
    }

    override fun addCondiments() {
        println("add sugar and milk")
    }
}

/* With Hook ------------------------------------
A hook is a method that is declared in the abstract class, but only given an empty or default implementation.
This gives subclasses the ability to “hook into” the algorithm at various points, if they wish; a subclass is also free to ignore the hook.
Use abstract methods when your subclass MUST provide an implementation of the method or step in the algorithm.
Use hooks when that part of the algorithm is optional. With hooks, a subclass may choose to implement that hook, but it doesn’t have to.
*/

abstract class CaffeineBeverageWithHook() {

    fun prepareRecipe() {
        boilWater()
        brew()
        pourInCup()
        if (customerWantsCondiments()) {
            addCondiments()
        }
    }

    abstract fun brew()
    abstract fun addCondiments()

    fun boilWater() {
        println("boil water")
    }
    fun pourInCup() {
        println("Pour into cup")
    }

    open fun customerWantsCondiments(): Boolean {
        return true
    }
}

class TeaWithHook(val wantsCondiments: Boolean): CaffeineBeverageWithHook() {
    override fun brew() {
        println("Steep bag of tea")
    }

    override fun addCondiments() {
        println("add lemon and honey")
    }

    override fun customerWantsCondiments(): Boolean {
        return wantsCondiments
    }
}

class CoffeeWithHook(val wantsCondiments: Boolean): CaffeineBeverageWithHook() {
    override fun brew() {
        println("Drip coffee through filter")
    }

    override fun addCondiments() {
        println("add sugar and milk")
    }
    override fun customerWantsCondiments(): Boolean {
        return wantsCondiments
    }
}
