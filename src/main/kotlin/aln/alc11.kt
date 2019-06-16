package aln

import aln.identifiers.AlcDevice
import kool.IntBuffer
import kool.adr
import org.lwjgl.openal.ALC11
import java.nio.Buffer
import java.nio.IntBuffer

/** Native bindings to ALC 1.1 functionality. */
interface alc11 {

    // --- [ alcCaptureOpenDevice ] ---

    /**
     * Allows the application to connect to a capture device.
     *
     * <p>The {@code deviceName} argument is a null terminated string that requests a certain device or device configuration. If {@code NULL} is specified, the implementation
     * will provide an implementation specific default.</p>
     *
     * @param deviceName the device or device configuration
     * @param frequency  the audio frequency
     * @param format     the audio format
     * @param samples    the number of sample frames to buffer in the AL
     */
    fun captureOpenDevice(deviceName: CharSequence?, frequency: Int, format: Int, samples: Int): AlcDevice =
            AlcDevice(ALC11.alcCaptureOpenDevice(deviceName, frequency, format, samples))

    // --- [ alcCaptureCloseDevice ] ---

    /**
     * Allows the application to disconnect from a capture device.
     *
     * @param device the capture device to close
     */
    fun captureCloseDevice(device: AlcDevice): Boolean =
            ALC11.alcCaptureCloseDevice(device.handle)

    // --- [ alcCaptureStart ] ---

    /**
     * Starts recording audio on the specific capture device.
     *
     * <p>Once started, the device will record audio to an internal ring buffer, the size of which was specified when opening the device. The application may
     * query the capture device to discover how much data is currently available via the alcGetInteger with the ALC_CAPTURE_SAMPLES token. This will report the
     * number of sample frames currently available.</p>
     *
     * @param device the capture device
     */
    fun captureStart(device: AlcDevice) =
            ALC11.alcCaptureStart(device.handle)

    // --- [ alcCaptureStop ] ---

    /**
     * Halts audio capturing without closing the capture device.
     *
     * <p>The implementation is encouraged to optimize for this case. The amount of audio samples available after restarting a stopped capture device is reset to
     * zero. The application does not need to stop the capture device to read from it.</p>
     *
     * @param device the capture device
     */
    fun captureStop(device: AlcDevice) =
            ALC11.alcCaptureStop(device.handle)

    // --- [ alcCaptureSamples ] ---

    /**
     * Obtains captured audio samples from the AL.
     *
     * <p>The implementation may defer conversion and resampling until this point. Requesting more sample frames than are currently available is an error.</p>
     *
     * @param device  the capture device
     * @param buffer  the buffer that will receive the samples. It must be big enough to contain at least {@code samples} sample frames.
     * @param samples the number of sample frames to obtain
     */
    fun captureSamples(device: AlcDevice, buffer: Buffer, samples: Int) =
            ALC11.nalcCaptureSamples(device.handle, buffer.adr, samples)

    /**
     * Obtains captured audio samples from the AL.
     *
     * <p>The implementation may defer conversion and resampling until this point. Requesting more sample frames than are currently available is an error.</p>
     *
     * @param device  the capture device
     * @param buffer  the buffer that will receive the samples. It must be big enough to contain at least {@code samples} sample frames.
     * @param samples the number of sample frames to obtain
     */
    fun captureSamples(device: AlcDevice, samples: Int): IntBuffer =
            IntBuffer(samples).apply { ALC11.nalcCaptureSamples(device.handle, adr, samples) }
}