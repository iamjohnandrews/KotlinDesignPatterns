package gof.kotlinattempt2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}

abstract class  Command {
    abstract fun execute()
}

class Light {
    fun on() {
        println("turn Light on")
    }
}

class LightOnCommand(val light: Light): Command() {
    override fun execute() {
        light.on()
    }
}

class SimpleRemoteControl {
    var powerButton: Command

    fun setCommand(command: Command) {
        powerButton = command
    }

    fun buttonPressed() {
        powerButton.execute()
    }
}