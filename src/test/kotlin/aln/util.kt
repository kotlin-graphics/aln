package aln

import kool.ShortBuffer
import kool.Stack
import org.lwjgl.stb.STBVorbis.*
import org.lwjgl.stb.STBVorbisInfo
import org.lwjgl.system.MemoryUtil.NULL
import java.io.File
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ShortBuffer
import java.nio.channels.FileChannel


fun String.toByteBuffer(): ByteBuffer {
    val url = ClassLoader.getSystemResource(this)
    val file = File(url.toURI())
    return FileInputStream(file).use { stream ->
        stream.channel.use {
            it.map(FileChannel.MapMode.READ_ONLY, 0, it.size())
        }
    }
}

fun readVorbis(resource: String): ShortBuffer = Stack {
    val info = STBVorbisInfo.callocStack(it)
    val buffer = resource.toByteBuffer()
    val error = it.callocInt(1)
    val decoder = stb_vorbis_open_memory(buffer, error, null)
    if (decoder == NULL)
        throw RuntimeException("Failed to open Ogg Vorbis file. Error: " + error[0])

    stb_vorbis_get_info(decoder, info)

    val channels = info.channels()

    return ShortBuffer(stb_vorbis_stream_length_in_samples(decoder) * channels).apply {
        stb_vorbis_get_samples_short_interleaved(decoder, channels, this)
        stb_vorbis_close(decoder)
    }
}