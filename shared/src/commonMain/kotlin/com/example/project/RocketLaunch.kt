package com.example.project

import io.exoquery.annotation.ExoEntity
import io.exoquery.annotation.ExoField
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@ExoEntity("Launch")
@Serializable
data class RocketLaunch(
    // In the database we represent it as flightNumber, in the json as flight_number.
    // ExoQuery understands both SerialName and ExoField but the latter overrides the former.
    @ExoField("flightNumber")
    @SerialName("flight_number")
    val flightNumber: Int,
    @ExoField("missionName")
    @SerialName("name")
    val missionName: String,
    val details: String?,
    @ExoField("launchSuccess")
    @SerialName("success")
    val launchSuccess: Boolean?,
    @ExoField("launchDateUTC")
    @SerialName("date_utc")
    val launchDateUTC: String,
    val links: Links
) {
    @Transient
    var launchYear = launchDateUTC.toInstant().toLocalDateTime(TimeZone.UTC).year
}

// ExoQuery knows how to automatically flatten these out.
@Serializable
data class Links(
    val patch: Patch?,
    @ExoField("articleUrl")
    val article: String?
)

// ExoQuery knows how to automatically flatten these out.
@Serializable
data class Patch(
    @ExoField("patchUrlSmall")
    val small: String?,
    @ExoField("patchUrlLarge")
    val large: String?
)
