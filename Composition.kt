/**
 * Created by andrj148 on 5/26/16.
 */

abstract class MenuComponent() {
    open var name = ""
    open var description = ""
    open var price = 0.0
    open var isVegetarian = false


    open fun add(menuComponent: MenuComponent) { }

    open fun remove(menuComponent: MenuComponent) { }

    open fun getChild(index: Int): MenuComponent {
        return this
    }

    open fun print() {
        println("$name + $description")
    }
}

class MenuItem(override var name: String,
               override var description: String,
               override var isVegetarian: Boolean,
               override var price: Double): MenuComponent() {

    override fun print() {
        super.print()
        println(" $name is $price for $description")
        if (isVegetarian) {
            println("Is Vegetarian")
        }
    }
}

class Menu(override var name: String,
           override var description: String): MenuComponent() {
    var menuComponents: kotlin.collections.MutableList<MenuComponent> = mutableListOf()

    override fun add(menuComponent: MenuComponent) {
        super.add(menuComponent)
        println("${menuComponent.name} has been added to menu $name")
        menuComponents.add(menuComponent)
    }

    override fun remove(menuComponent: MenuComponent) {
        super.remove(menuComponent)
        println("${menuComponent.name} has been removed from menu $name")
        menuComponents.remove(menuComponent)
    }

    override fun getChild(index: Int): MenuComponent {
        return  menuComponents[index]
    }

    override fun print() {
        super.print()

        val iterator: Iterator<MenuComponent> = menuComponents.iterator()
        while (iterator.hasNext()) {
            val selectedMenuComponent= iterator.next()
            selectedMenuComponent.print()
        }
    }
}

data class Waitress(val allMenus: kotlin.collections.MutableList<Menu>) {
    fun printMenu() {
        println("Waitress finna print")
        val iterator: Iterator<MenuComponent> = allMenus.iterator()
        while (iterator.hasNext()) {
            val selectedMenuComponent= iterator.next()
            selectedMenuComponent.print()
        }
    }
}

//-------------------------------
/*
val pancakeMenu = Menu("PANCAKE MENU", "Breakfast")
val dinnerMenu = Menu("DINER MENU", "Dinner")
val cafeMenu = Menu("CAFE MENU", "Dinner")
val dessertMenu = Menu("DESSERT MENU", "Dessert")

val allMenus = mutableListOf<Menu>(pancakeMenu, dinnerMenu, cafeMenu, dessertMenu)

val pasta = MenuItem("Pasta", "Spaghetti with Marinara Sauce", true, 6.89)
dinnerMenu.add(pasta)

val applePie = MenuItem("Apple Pie", "warm apple Pie with vanilla ice cream", true, 5.25)
dessertMenu.add(applePie)

val waitress = Waitress(allMenus)
waitress.printMenu()
        */