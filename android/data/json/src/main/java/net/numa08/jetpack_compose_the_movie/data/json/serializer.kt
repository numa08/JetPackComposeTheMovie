package net.numa08.jetpack_compose_the_movie.data.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NASerializer : KSerializer<String?> {
    override fun deserialize(decoder: Decoder): String? {
        val value = decoder.decodeString()
        if (value == "N/A") {
            return null
        }
        return value
    }

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("N/A String", PrimitiveKind.STRING)

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: String?) {
    }
}