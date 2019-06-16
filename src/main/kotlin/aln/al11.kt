package aln

import org.lwjgl.openal.AL11

interface al11 {

    // --- [ alListener3i ] ---

//    /**
//     * Sets the 3 dimensional integer values of a listener parameter.
//     *
//     * @param paramName the parameter to modify
//     * @param value1    the first value
//     * @param value2    the second value
//     * @param value3    the third value
//     */
//    @NativeType("ALvoid")
//    public static void alListener3i(@NativeType("ALenum") int paramName, @NativeType("ALint") int value1, @NativeType("ALint") int value2, @NativeType("ALint") int value3) {
//        long __functionAddress = AL.getICD().alListener3i;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        invokeV(paramName, value1, value2, value3, __functionAddress);
//    }

    // --- [ alGetListeneriv ] ---

//    /**
//     * Returns the integer values of the specified listener parameter.
//     *
//     * @param param  the parameter to query
//     * @param values the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alGetListeneriv(@NativeType("ALenum") int param, @NativeType("ALint *") IntBuffer values) {
//        if (CHECKS) {
//            check(values, 1);
//        }
//        nalGetListeneriv(param, memAddress(values));
//    }

    // --- [ alSource3i ] ---

//    /**
//     * Sets the 3 dimensional integer values of a source parameter.
//     *
//     * @param source    the source to modify
//     * @param paramName the parameter to modify
//     * @param value1    the first value
//     * @param value2    the second value
//     * @param value3    the third value
//     */
//    @NativeType("ALvoid")
//    public static void alSource3i(@NativeType("ALuint") int source, @NativeType("ALenum") int paramName, @NativeType("ALint") int value1, @NativeType("ALint") int value2, @NativeType("ALint") int value3) {
//        long __functionAddress = AL.getICD().alSource3i;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        invokeV(source, paramName, value1, value2, value3, __functionAddress);
//    }

    // --- [ alListeneriv ] ---

//    /**
//     * Pointer version.
//     *
//     * @param listener the parameter to modify
//     * @param value    the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alListeneriv(@NativeType("ALenum") int listener, @NativeType("ALint const *") IntBuffer value) {
//        if (CHECKS) {
//            check(value, 1);
//        }
//        nalListeneriv(listener, memAddress(value));
//    }

    // --- [ alSourceiv ] ---

//    /**
//     * Pointer version.
//     *
//     * @param source    the source to modify
//     * @param paramName the parameter to modify
//     * @param value     the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alSourceiv(@NativeType("ALuint") int source, @NativeType("ALenum") int paramName, @NativeType("ALint const *") IntBuffer value) {
//        if (CHECKS) {
//            check(value, 1);
//        }
//        nalSourceiv(source, paramName, memAddress(value));
//    }

    // --- [ alBufferf ] ---

//    /**
//     * Sets the float value of a buffer parameter.
//     *
//     * @param buffer    the buffer to modify
//     * @param paramName the parameter to modify
//     * @param value     the value
//     */
//    @NativeType("ALvoid")
//    public static void alBufferf(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALfloat") float value) {
//        long __functionAddress = AL.getICD().alBufferf;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        invokeV(buffer, paramName, value, __functionAddress);
//    }

    // --- [ alBuffer3f ] ---

//    /**
//     * Sets the dimensional value of a buffer parameter.
//     *
//     * @param buffer    the buffer to modify
//     * @param paramName the parameter to modify
//     * @param value1    the first value
//     * @param value2    the second value
//     * @param value3    the third value
//     */
//    @NativeType("ALvoid")
//    public static void alBuffer3f(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALfloat") float value1, @NativeType("ALfloat") float value2, @NativeType("ALfloat") float value3) {
//        long __functionAddress = AL.getICD().alBuffer3f;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        invokeV(buffer, paramName, value1, value2, value3, __functionAddress);
//    }

    // --- [ alBufferfv ] ---

//    /**
//     * the pointer version of {@link #alBufferf Bufferf}
//     *
//     * @param buffer    the buffer to modify
//     * @param paramName the parameter to modify
//     * @param value     the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alBufferfv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALfloat const *") FloatBuffer value) {
//        if (CHECKS) {
//            check(value, 1);
//        }
//        nalBufferfv(buffer, paramName, memAddress(value));
//    }

    // --- [ alBufferi ] ---

//    /**
//     * Sets the integer value of a buffer parameter.
//     *
//     * @param buffer    the buffer to modify
//     * @param paramName the parameter to modify
//     * @param value     the value
//     */
//    @NativeType("ALvoid")
//    public static void alBufferi(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALint") int value) {
//        long __functionAddress = AL.getICD().alBufferi;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        invokeV(buffer, paramName, value, __functionAddress);
//    }

    // --- [ alBuffer3i ] ---

//    /**
//     * Sets the integer 3 dimensional value of a buffer parameter.
//     *
//     * @param buffer    the buffer to modify
//     * @param paramName the parameter to modify
//     * @param value1    the first value
//     * @param value2    the second value
//     * @param value3    the third value
//     */
//    @NativeType("ALvoid")
//    public static void alBuffer3i(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALint") int value1, @NativeType("ALint") int value2, @NativeType("ALint") int value3) {
//        long __functionAddress = AL.getICD().alBuffer3i;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        invokeV(buffer, paramName, value1, value2, value3, __functionAddress);
//    }

    // --- [ alBufferiv ] ---

//    /**
//     * the pointer version of {@link #alBufferi Bufferi}
//     *
//     * @param buffer    the buffer to modify
//     * @param paramName the parameter to modify
//     * @param value     the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alBufferiv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALint const *") IntBuffer value) {
//        if (CHECKS) {
//            check(value, 1);
//        }
//        nalBufferiv(buffer, paramName, memAddress(value));
//    }

    // --- [ alGetBufferiv ] ---

//    /**
//     * Returns the integer values of the specified buffer parameter.
//     *
//     * @param buffer the buffer to query
//     * @param param  the parameter to query
//     * @param values the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alGetBufferiv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int param, @NativeType("ALint *") IntBuffer values) {
//        if (CHECKS) {
//            check(values, 1);
//        }
//        nalGetBufferiv(buffer, param, memAddress(values));
//    }

    // --- [ alGetBufferfv ] ---

//    /**
//     * Returns the float values of the specified buffer parameter.
//     *
//     * @param buffer the buffer to query
//     * @param param  the parameter to query
//     * @param values the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alGetBufferfv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int param, @NativeType("ALfloat *") FloatBuffer values) {
//        if (CHECKS) {
//            check(values, 1);
//        }
//        nalGetBufferfv(buffer, param, memAddress(values));
//    }

    // --- [ alSpeedOfSound ] ---

    /**
     * Sets the speed of sound.
     *
     * @param value the speed of sound
     */
    infix fun speedOfSound(value: Float) = AL11.alSpeedOfSound(value)
}