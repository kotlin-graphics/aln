package aln

import aln.identifiers.AlBuffer
import aln.identifiers.AlSource
import glm_.func.cos
import glm_.func.sin
import glm_.glm
import glm_.vec2.Vec2
import glm_.vec3.Vec3
import io.kotlintest.specs.StringSpec
import kool.IntBuffer
import kool.lib.isEmpty
import kool.set
import org.lwjgl.openal.ALC10.ALC_TRUE
import org.lwjgl.openal.SOFTHRTF.*
import kotlin.system.exitProcess

/*
 * OpenAL HRTF Example
 *
 * Copyright (c) 2015 by Chris Robinson <chris.kcat@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

// This file contains an example for selecting an HRTF.

class HRTF : StringSpec() {


}

fun hrtf(soundName: String) = hrtf(null, soundName)

fun hrtf(hrtfName: String?, soundName: String) {

//    ALuint source, buffer
//    ALdouble angle
//    ALenum state

    // Print out usage if no arguments were specified
//    if(argc < 2)
//    {
//        fprintf(stderr, "Usage: %s [-device <name>] [-hrtf <name>] <soundfile>\n", argv[0]);
//        return 1;
//    }

    // Initialize OpenAL, and check for HRTF support.
    if (!al.init())
        exitProcess(1)

    val device = alc.currentContext.device
    if (!device.isExtensionPresent("ALC_SOFT_HRTF")) {
        System.err.println("Error: ALC_SOFT_HRTF not supported")
        al.close()
        exitProcess(1)
    }

    // Check for the AL_EXT_STEREO_ANGLES extension to be able to also rotate stereo sources.
    val hasAngleExt = al isExtensionPresent "AL_EXT_STEREO_ANGLES"
    println("AL_EXT_STEREO_ANGLES${if (hasAngleExt) "" else " not"} found")

    // Check for user-preferred HRTF
//    if (strcmp(argv[0], "-hrtf") == 0) {
//        hrtfname = argv[1]
//        soundname = argv[2]
//    } else {
//        hrtfname = NULL
//        soundname = argv[0]
//    }

    // Enumerate available HRTFs, and reset the device using one.
    val numHrtf = device.NUM_HRTF_SPECIFIERS_SOFT
    if (numHrtf == 0)
        println("No HRTFs found")
    else {
        val attr = IntBuffer(5)
        var index = -1

        println("Available HRTFs:")
        for (i in 0 until numHrtf) {
            val name = device.getStringSOFT(ALC_HRTF_SPECIFIER_SOFT, i)
            println("    $i: $name")

            // Check if this is the HRTF the user requested.
            if (hrtfName?.equals(name) == true)
                index = i
        }

        var i = 0
        attr[i++] = ALC_HRTF_SOFT
        attr[i++] = ALC_TRUE
        if (index == -1) {
            if (hrtfName != null)
                println("HRTF \"$hrtfName\" not found")
            println("Using default HRTF...")
        } else {
            println("Selecting HRTF $index...")
            attr[i++] = ALC_HRTF_ID_SOFT
            attr[i++] = index
        }
        attr[i] = 0

        if (!device.resetSOFT(attr))
            println("Failed to reset device: ${device.error}")
    }

    // Check if HRTF is enabled, and show which is being used.
    val hrtfState = device getBoolean ALC_HRTF_SOFT
    if (!hrtfState)
        println("HRTF not enabled!")
    else {
        val name = device getString ALC_HRTF_SPECIFIER_SOFT
        println("HRTF enabled, using $name")
    }

    // Load the sound into a buffer.
    val buffer = AlBuffer.gen()
    val (vorbis, sampleRate) = readVorbis(soundName)
    if (vorbis.isEmpty()) {
        al.close()
        exitProcess(1)
    }
    buffer.data(AlFormat.MONO16, vorbis, sampleRate)

    // Create the source to play the sound with.
    val source = AlSource.gen().apply {
        relative = true
        position = Vec3(0f, 0f, -1f)
        this.buffer = buffer
    }
    assert(al.error == AlError.NONE) { "Failed to setup sound source" }

    // Play the sound until it finishes.
    var angle = 0.0
    source.play()
    do {
        Thread.sleep(10)

        // Rotate the source around the listener by about 1/4 cycle per second, and keep it within -pi...+pi.
        angle += 0.01 * glm.PIf * 0.5
        if (angle > glm.PIf)
            angle -= glm.PIf * 2.0

        /* This only rotates mono sounds. */
        source.position = Vec3(angle.sin, 0f, -(angle.cos))

        if (hasAngleExt)
        /* This rotates stereo sounds with the AL_EXT_STEREO_ANGLES
         * extension. Angles are specified counter-clockwise in radians.             */
            source.stereoAngles = Vec2(glm.PIf / 6 - angle, -glm.PIf / 6.0 - angle)

        val state = source.state

    } while (al.error == AlError.NONE && state == SourceState.PLAYING)

    /* All done. Delete resources, and close down SDL_sound and OpenAL. */
    source.delete()
    buffer.delete()

    al.close()
}