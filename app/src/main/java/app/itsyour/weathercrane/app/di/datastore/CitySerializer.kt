package app.itsyour.weathercrane.app.di.datastore

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

//object CitySerializer : Serializer<City.CurrentWeatherRequest> {
//
//    override fun readFrom(input: InputStream): City.CurrentWeatherRequest {
//        try {
//            return parseFrom(input)
//        } catch (exception: InvalidProtocolBufferException) {
//            throw CorruptionException("Cannot read proto.", exception)
//        }
//    }
//
//    override fun writeTo(t: City.CurrentWeatherRequest, output: OutputStream) = t.writeTo(output)
//}
