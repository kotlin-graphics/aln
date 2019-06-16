package aln.identifiers

import aln.AlFormat
import kool.*
import org.lwjgl.openal.AL10
import java.nio.Buffer
import java.nio.IntBuffer

inline class AlBuffer(val name: Int) {

    val isValid: Boolean
        get() = AL10.alIsBuffer(name)

    val isInvalid: Boolean
        get() = !AL10.alIsBuffer(name)

    fun delete() = AL10.alDeleteBuffers(name)

    // --- [ alGetBufferi ] ---

    val frequency: Int
        get() = Stack.intAddress { AL10.nalGetBufferi(name, AL10.AL_FREQUENCY, it) }

    val size: Int
        get() = Stack.intAddress { AL10.nalGetBufferi(name, AL10.AL_SIZE, it) }

    val bits: Int
        get() = Stack.intAddress { AL10.nalGetBufferi(name, AL10.AL_BITS, it) }

    val channels: Int
        get() = Stack.intAddress { AL10.nalGetBufferi(name, AL10.AL_CHANNELS, it) }


    fun data(format: AlFormat, data: Buffer, frequency: Int) = AL10.nalBufferData(name, format.i, data.adr, data.rem, frequency)

    companion object {
        fun gen(): AlBuffer = AlBuffer(AL10.alGenBuffers())
    }
}

fun AlBuffers(size: Int) = AlBuffers(IntBuffer(size))

inline class AlBuffers(val names: IntBuffer) {

    val rem: Int
        get() = names.rem

    val adr: Adr
        get() = names.adr

    fun gen() = AL10.nalGenBuffers(rem, adr)
    fun delete() = AL10.nalDeleteBuffers(rem, adr)

    companion object {
        fun gen(size: Int) = AlBuffers(size).apply { gen() }
    }
}
