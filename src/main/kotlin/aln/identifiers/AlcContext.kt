package aln.identifiers

import org.lwjgl.openal.ALC10
import org.lwjgl.system.MemoryUtil.NULL

inline class AlcContext(val L: Long) {

    fun makeCurrent() = ALC10.alcMakeContextCurrent(L)
    fun unmakeCurrent() = ALC10.alcMakeContextCurrent(NULL)

    fun process() = ALC10.alcProcessContext(L)

    fun suspend() = ALC10.alcSuspendContext(L)

    fun destroy() = ALC10.alcDestroyContext(L)

    val device: AlcDevice
        get() = AlcDevice(ALC10.alcGetContextsDevice(L))

    val isValid: Boolean
        get() = L != NULL

    val isInvalid: Boolean
        get() = L == NULL

    companion object {

        val current: AlcContext
            get() = AlcContext(ALC10.alcGetCurrentContext())
    }
}