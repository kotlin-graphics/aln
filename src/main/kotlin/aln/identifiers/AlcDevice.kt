package aln.identifiers

import aln.AlcError
import aln.AlcStringQuery
import aln.ContextAttribute
import aln.alc
import org.lwjgl.openal.ALC10
import org.lwjgl.openal.ALC11
import org.lwjgl.openal.SOFTHRTF
import org.lwjgl.system.MemoryUtil
import java.nio.Buffer
import java.nio.IntBuffer

inline class AlcDevice(val handle: Long) {

    fun createContext(attrList: IntArray? = null): AlcContext =
            AlcContext(Stack {
                ALC10.nalcCreateContext(handle, attrList?.toBuffer(it)?.adr ?: MemoryUtil.NULL)
            })

    fun close(): Boolean = ALC10.alcCloseDevice(handle)

    infix fun isExtensionPresent(extName: CharSequence): Boolean = ALC10.alcIsExtensionPresent(handle, extName)

    infix fun getProcAddress(funcName: CharSequence): Adr = ALC10.alcGetProcAddress(handle, funcName)

    infix fun getEnumValue(enumName: CharSequence): Int = ALC10.alcGetEnumValue(handle, enumName)

    val error: AlcError
        get() = AlcError(ALC10.alcGetError(handle))

    // --- [ alcGetString ] ---

    val defaultDeviceSpecifier: String
        get() = alc.getString(this, AlcStringQuery.DEFAULT_DEVICE_SPECIFIER)

    val deviceSpecifier: String
        get() = alc.getString(this, AlcStringQuery.DEVICE_SPECIFIER)

    val extensions: List<String>
        get() = alc.getString(this, AlcStringQuery.EXTENSIONS).split("\\s+")

    val defaultAllDeviceSpecifier: String
        get() = alc.getString(this, AlcStringQuery.DEFAULT_ALL_DEVICES_SPECIFIER)

    val allDeviceSpecifier: String
        get() = alc.getString(this, AlcStringQuery.ALL_DEVICES_SPECIFIER)

    val captureDeviceSpecifier: String
        get() = alc.getString(this, AlcStringQuery.CAPTURE_DEVICE_SPECIFIER)

    val captureDefaultDeviceSpecifier: String
        get() = alc.getString(this, AlcStringQuery.CAPTURE_DEFAULT_DEVICE_SPECIFIER)


    val majorVersion: Int
        get() = ALC10.alcGetInteger(handle, ALC10.ALC_MAJOR_VERSION)
    val minorVersion: Int
        get() = ALC10.alcGetInteger(handle, ALC10.ALC_MINOR_VERSION)
    val allAttributes: MutableMap<ContextAttribute, Int>
        get() = alc.getAllAttributes(this)
    val captureSamples: Int
        get() = ALC10.alcGetInteger(handle, ALC11.ALC_CAPTURE_SAMPLES)

    fun captureCloseDevice(): Boolean = ALC11.alcCaptureCloseDevice(handle)

    fun captureStart() = ALC11.alcCaptureStart(handle)

    fun captureStop() = ALC11.alcCaptureStop(handle)

    inline fun <R> capture(block: () -> R): R {
        ALC11.alcCaptureStart(handle)
        return block().also {
            ALC11.alcCaptureStop(handle)
        }
    }

    fun captureSamples(buffer: Buffer, samples: Int) = ALC11.nalcCaptureSamples(handle, buffer.adr, samples)

    infix fun captureSamples(size: Int): IntBuffer =
            alc.captureSamples(this, size)

//    fun createCapabilities(): ALCCapabilities = ALC.createCapabilities(handle)

    val isValid: Boolean
        get() = handle != MemoryUtil.NULL

    val isInvalid: Boolean
        get() = handle == MemoryUtil.NULL


    val NUM_HRTF_SPECIFIERS_SOFT: Int
        get() = ALC10.alcGetInteger(handle, SOFTHRTF.ALC_NUM_HRTF_SPECIFIERS_SOFT)

    fun getStringSOFT(paramName: Int, index: Int): String? =
            SOFTHRTF.alcGetStringiSOFT(handle, paramName, index)

    infix fun resetSOFT(attr: IntBuffer): Boolean =
            SOFTHRTF.alcResetDeviceSOFT(handle, attr)

    infix fun getBoolean(token: Int): Boolean =
            ALC10.alcGetInteger(handle, token) != 0

    infix fun getInteger(token: Int): Int =
            ALC10.alcGetInteger(handle, token)

    infix fun getString(token: Int): String =
            ALC10.alcGetString(handle, token)

    companion object {

        fun open(deviceSpecifier: String? = ALC10.alcGetString(MemoryUtil.NULL, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER)): AlcDevice =
                AlcDevice(ALC10.alcOpenDevice(deviceSpecifier))

        fun captureOpenDevice(device: CharSequence?, frequency: Int, format: Int, samples: Int): AlcDevice =
                AlcDevice(ALC11.alcCaptureOpenDevice(device, frequency, format, samples))

        val NULL = AlcDevice(MemoryUtil.NULL)
    }
}