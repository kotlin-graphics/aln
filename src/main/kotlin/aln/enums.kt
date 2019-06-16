package aln

import org.lwjgl.openal.*

/*
 * OpenAL Helpers
 *
 * Copyright (c) 2011 by Chris Robinson <chris.kcat@gmail.com>
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

/* This file contains routines to help with some menial OpenAL-related tasks,
 * such as opening a device and setting up a context, closing the device and
 * destroying its context, converting between frame counts and byte lengths,
 * finding an appropriate buffer format, and getting readable strings for
 * channel configs and sample types. */

inline class AlFormat(val i: Int){
    companion object {
        val FORMAT_MONO8 = AlFormat(AL10.AL_FORMAT_MONO8)
        val FORMAT_MONO16 = AlFormat(AL10.AL_FORMAT_MONO16)
        val FORMAT_STEREO8 = AlFormat(AL10.AL_FORMAT_STEREO8)
        val FORMAT_STEREO16 = AlFormat(AL10.AL_FORMAT_STEREO16)
    }
}

inline class AlcStringQuery(val i: Int) {
    companion object {
        val DEFAULT_DEVICE_SPECIFIER = AlcStringQuery(ALC10.ALC_DEFAULT_DEVICE_SPECIFIER)
        val DEVICE_SPECIFIER = AlcStringQuery(ALC10.ALC_DEVICE_SPECIFIER)
        val EXTENSIONS = AlcStringQuery(ALC10.ALC_EXTENSIONS)
        // ALC11
        val DEFAULT_ALL_DEVICES_SPECIFIER = AlcStringQuery(ALC11.ALC_DEFAULT_ALL_DEVICES_SPECIFIER)
        val ALL_DEVICES_SPECIFIER = AlcStringQuery(ALC11.ALC_ALL_DEVICES_SPECIFIER)
        val CAPTURE_DEVICE_SPECIFIER = AlcStringQuery(ALC11.ALC_CAPTURE_DEVICE_SPECIFIER)
        val CAPTURE_DEFAULT_DEVICE_SPECIFIER = AlcStringQuery(ALC11.ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER)
    }
}

inline class AlcError(val i: Int) {
    companion object {
        val NO_ERROR = AlcError(ALC10.ALC_NO_ERROR)
        val INVALID_DEVICE = AlcError(ALC10.ALC_INVALID_DEVICE)
        val INVALID_CONTEXT = AlcError(ALC10.ALC_INVALID_CONTEXT)
        val INVALID_ENUM = AlcError(ALC10.ALC_INVALID_ENUM)
        val INVALID_VALUE = AlcError(ALC10.ALC_INVALID_VALUE)
        val OUT_OF_MEMORY = AlcError(ALC10.ALC_OUT_OF_MEMORY)
    }
}

inline class AlError(val i: Int) {
    companion object {
        val NO_ERROR = AlError(AL10.AL_NO_ERROR)
        val INVALID_NAME = AlError(AL10.AL_INVALID_NAME)
        val INVALID_ENUM = AlError(AL10.AL_INVALID_ENUM)
        val INVALID_VALUE = AlError(AL10.AL_INVALID_VALUE)
        val INVALID_OPERATION = AlError(AL10.AL_INVALID_OPERATION)
        val OUT_OF_MEMORY = AlError(ALC10.ALC_OUT_OF_MEMORY)
    }
}

inline class AlStringQuery(val i: Int) {
    companion object {
        val VERSION = AlStringQuery(AL10.AL_VERSION)
        val RENDERER = AlStringQuery(AL10.AL_RENDERER)
        val VENDOR = AlStringQuery(AL10.AL_VENDOR)
        val EXTENSIONS = AlStringQuery(AL10.AL_EXTENSIONS)
    }
}

inline class DistanceModel(val i: Int) {
    companion object {
        val NONE = DistanceModel(AL10.AL_NONE)
        val INVERSE_DISTANCE = DistanceModel(AL10.AL_INVERSE_DISTANCE)
        val INVERSE_DISTANCE_CLAMPED = DistanceModel(AL10.AL_INVERSE_DISTANCE_CLAMPED)
        val LINEAR_DISTANCE = DistanceModel(AL11.AL_LINEAR_DISTANCE)
        val LINEAR_DISTANCE_CLAMPED = DistanceModel(AL11.AL_LINEAR_DISTANCE_CLAMPED)
        val EXPONENT_DISTANCE = DistanceModel(AL11.AL_EXPONENT_DISTANCE)
        val EXPONENT_DISTANCE_CLAMPED = DistanceModel(AL11.AL_EXPONENT_DISTANCE_CLAMPED)
    }
}

inline class SourceParam(val i: Int) {
    companion object {
        // Float
        val CONE_INNER_ANGLE = SourceParam(AL10.AL_CONE_INNER_ANGLE)
        val CONE_OUTER_ANGLE = SourceParam(AL10.AL_CONE_OUTER_ANGLE)
        val PITCH = SourceParam(AL10.AL_PITCH)
        val CONE_OUTER_GAIN = SourceParam(AL10.AL_CONE_OUTER_GAIN)
        val GAIN = SourceParam(AL10.AL_GAIN)
        val REFERENCE_DISTANCE = SourceParam(AL10.AL_REFERENCE_DISTANCE)
        val ROLLOFF_FACTOR = SourceParam(AL10.AL_ROLLOFF_FACTOR)
        val MAX_DISTANCE = SourceParam(AL10.AL_MAX_DISTANCE)
        // Vec3
        val DIRECTION = SourceParam(AL10.AL_DIRECTION)
        val POSITION = SourceParam(AL10.AL_POSITION)
        val VELOCITY = SourceParam(AL10.AL_VELOCITY)
        // Boolean
        val LOOPING = SourceParam(AL10.AL_LOOPING)
        // AlBuffer
        val BUFFER = SourceParam(AL10.AL_BUFFER)
        // Source State
        val SOURCE_STATE = SourceParam(AL10.AL_SOURCE_STATE)
        // Source Type
        val SOURCE_TYPE = SourceParam(AL10.AL_SOURCE_TYPE)
    }
}

inline class SourceState(val i: Int) {
    companion object {
        val INITIAL = SourceState(AL10.AL_INITIAL)
        val PLAYING = SourceState(AL10.AL_PLAYING)
        val PAUSED = SourceState(AL10.AL_PAUSED)
        val STOPPED = SourceState(AL10.AL_STOPPED)
    }
}

inline class SourceType(val i: Int) {
    companion object {
        val UNDETERMINED = SourceType(AL11.AL_UNDETERMINED)
        val STATIC = SourceType(AL11.AL_STATIC)
        val STREAMING = SourceType(AL11.AL_STREAMING)
    }
}

inline class GetBuffer(val i: Int) {
    companion object {
        val FREQUENCY = GetBuffer(AL11.AL_FREQUENCY)
        val SIZE = GetBuffer(AL11.AL_SIZE)
        val BITS = GetBuffer(AL11.AL_BITS)
        val CHANNELS = GetBuffer(AL11.AL_CHANNELS)
    }
}

inline class ContextAttribute(val i: Int) {
    companion object {
        val FREQUENCY = ContextAttribute(ALC10.ALC_FREQUENCY)
        val REFRESH = ContextAttribute(ALC10.ALC_REFRESH)
        val SYNC = ContextAttribute(ALC10.ALC_SYNC)
        val MONO_SOURCES = ContextAttribute(ALC11.ALC_MONO_SOURCES)
        val STEREO_SOURCES = ContextAttribute(ALC11.ALC_STEREO_SOURCES)
        val MAX_AUXILIARY_SENDS = ContextAttribute(EXTEfx.ALC_MAX_AUXILIARY_SENDS)
    }
}