package com.toquete.boxbox.data.circuitimages.source.remote

import com.toquete.boxbox.core.network.model.CircuitImageResponse
import javax.inject.Inject

internal class DefaultCircuitImageRemoteDataSource @Inject constructor() : CircuitImageRemoteDataSource {

    @Suppress("MaxLineLength")
    override suspend fun getCircuitImages(): List<CircuitImageResponse> {
        return listOf(
            CircuitImageResponse(
                id = "rodriguez",
                imageUrl = "https://media.formula1.com/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Mexico%20carbon.png"
            ),
            CircuitImageResponse(
                id = "silverstone",
                imageUrl = "https://media.formula1.com/image/upload/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Great%20Britain%20carbon"
            ),
            CircuitImageResponse(
                id = "albert_park",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677245032/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Australia%20carbon.png"
            ),
            CircuitImageResponse(
                id = "spa",
                imageUrl = "https://media.formula1.com/image/upload/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Belgium%20carbon.png"
            ),
            CircuitImageResponse(
                id = "monza",
                imageUrl = "https://media.formula1.com/image/upload/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Italy%20carbon.png"
            ),
            CircuitImageResponse(
                id = "catalunya",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1680529432/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Spain%20carbon.png"
            ),
            CircuitImageResponse(
                id = "interlagos",
                imageUrl = "https://media.formula1.com/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Brazil%20carbon.png"
            ),
            CircuitImageResponse(
                id = "villeneuve",
                imageUrl = "https://media.formula1.com/image/upload/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Canada%20carbon.png"
            ),
            CircuitImageResponse(
                id = "red_bull_ring",
                imageUrl = "https://media.formula1.com/image/upload/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Austria%20carbon.png"
            ),
            CircuitImageResponse(
                id = "americas",
                imageUrl = "https://media.formula1.com/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/United%20States%20carbon.png"
            ),
            CircuitImageResponse(
                id = "yas_marina",
                imageUrl = "https://media.formula1.com/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Abu%20Dhabi%20carbon.png"
            ),
            CircuitImageResponse(
                id = "losail",
                imageUrl = "https://media.formula1.com/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Qatar%20carbon.png"
            ),
            CircuitImageResponse(
                id = "baku",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677245030/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Azerbaijan%20carbon.png"
            ),
            CircuitImageResponse(
                id = "jeddah",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677245030/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Saudi%20Arabia%20carbon.png"
            ),
            CircuitImageResponse(
                id = "vegas",
                imageUrl = "https://media.formula1.com/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Las%20Vegas%20carbon.png"
            ),
            CircuitImageResponse(
                id = "miami",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677245035/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Miami%20carbon.png"
            ),
            CircuitImageResponse(
                id = "hungaroring",
                imageUrl = "https://media.formula1.com/image/upload/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Hungar%20carbon.png"
            ),
            CircuitImageResponse(
                id = "marina_bay",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1683639275/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Singapore%20carbon.png"
            ),
            CircuitImageResponse(
                id = "monaco",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677245032/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Monte%20Carlo%20carbon.png"
            ),
            CircuitImageResponse(
                id = "suzuka",
                imageUrl = "https://media.formula1.com/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Japan%20carbon.png"
            ),
            CircuitImageResponse(
                id = "zandvoort",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677245033/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Netherlands%20carbon.png"
            ),
            CircuitImageResponse(
                id = "bahrain",
                imageUrl = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677245035/content/dam/fom-website/2018-redesign-assets/Track%20icons%204x3/Bahrain%20carbon.png"
            )
        )
    }
}
