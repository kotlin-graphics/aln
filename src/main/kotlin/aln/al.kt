package aln

import aln.identifiers.AlcContext
import aln.identifiers.AlcDevice
import aln.identifiers.AlSource
import glm_.bool
import glm_.vec3.Vec3
import gln.vec3Address
import kool.Stack
import org.lwjgl.openal.AL10

object al : al10, al11 {

    /* InitAL opens a device and sets up a context using default attributes, making
     * the program ready to call OpenAL functions. */
    fun init(args: Array<String>? = null): Boolean {

        var device = AlcDevice.NULL

        // Open and initialize a device
        if (args?.isNotEmpty() == true && args[0] == "-device") {
            device = AlcDevice.open(args[1])
            if (device.isInvalid)
                System.err.println("Failed to open \"${args[1]}\", trying default")
        }
        if (device.isInvalid)
            device = AlcDevice.open(null)
        if (device.isInvalid) {
            System.err.println("Could not open a device!")
            return false
        }

        val ctx = device.createContext(null)
        if (ctx.isInvalid || !ctx.makeCurrent()) {
            if (ctx.isValid)
                ctx.destroy()
            device.close()
            System.err.println("Could not set a context!")
            return false
        }

        var name: String? = null
        if (device isExtensionPresent "ALC_ENUMERATE_ALL_EXT")
            name = device.allDeviceSpecifier
        if (name == null || device.error != AlcError.NONE)
            name = device.deviceSpecifier
        println("Opened \"$name\"")

        return true
    }

    /* CloseAL closes the device belonging to the current context, and destroys the context. */
    fun close() {

        val ctx = AlcContext.current
        if (ctx.isInvalid) return

        val device = ctx.device

        ctx.unmakeCurrent()
        ctx.destroy()
        device.close()
    }

    // --- [ alGet* ] ---

    inline fun <reified N : Number> get(paramName: Int): N = when (N::class) {
        Boolean::class -> AL10.alGetBoolean(paramName)
        Int::class -> AL10.alGetInteger(paramName)
        Float::class -> AL10.alGetFloat(paramName)
        Double::class -> AL10.alGetDouble(paramName)
        else -> error("invalid")
    } as N

    // --- [ alGetSource* ] ---

    inline fun <reified T> getSource(source: AlSource, param: SourceParam): T = Stack { s ->
        when (T::class) {
            Float::class -> AL10.alGetSourcef(source.name, param.i)
            Int::class -> AL10.alGetSourcei(source.name, param.i)
            Boolean::class -> AL10.alGetSourcei(source.name, param.i).bool
            Vec3::class -> s.vec3Address { AL10.nalGetSourcef(source.name, param.i, it) }
            else -> error("[al::getSource] Invalid type")
        } as T
    }
}