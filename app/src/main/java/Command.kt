/**
 * Created by andrj148 on 5/25/16.
 */
abstract class  Command {
    abstract fun execute()
}

class Light {
    fun on() {
        println("turn Light ON")
    }
    fun off() {
        println("turn light OFF")
    }
}

class LightOnCommand(val light: Light): Command() {
    override fun execute() {
        light.on()
    }
}

class LightOffCommand(val light: Light): Command() {
    override fun execute() {
        light.off()
    }
}

class SimpleRemoteControl(var powerButton: Command) {
    //    var powerButton = command
    // Properties must be initialized, even an optional property must have an initial value.
    // You cannot use optionals to just declare a property and assign it a value later
    fun setCommand(command: Command) {
        powerButton = command
    }

    fun buttonPressed() {
        powerButton.execute()
    }
}