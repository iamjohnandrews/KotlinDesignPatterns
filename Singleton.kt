/**
 * Created by andrj148 on 6/6/16.
 */

// https://medium.com/@adinugroho/singleton-in-kotlin-502f80fd8a63#.sdmtato3w
object LifeSingleton {

    fun answerToLifeTheUniverseAndEverything() = someImportantValue

    val someImportantValue: Int
        get() = 42

}

/*
println("WTF")
        val singletonObject = LifeSingleton.answerToLifeTheUniverseAndEverything()
        println("The answer To Life The Universe And Everything = $singletonObject")
 */