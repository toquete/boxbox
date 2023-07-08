package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import javax.inject.Inject

@Suppress("MaxLineLength")
internal class DefaultConstructorImageRemoteDataSource @Inject constructor() : ConstructorImageRemoteDataSource {

    override suspend fun getConstructorsImages(): List<ConstructorImageResponse> {
        return listOf(
            ConstructorImageResponse(
                id = "alphatauri",
                imageUrl = "https://logodownload.org/wp-content/uploads/2022/03/alphatauri-logo-5.png"
            ),
            ConstructorImageResponse(
                id = "mclaren",
                imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/6/66/McLaren_Racing_logo.svg/640px-McLaren_Racing_logo.svg.png"
            ),
            ConstructorImageResponse(
                id = "alpine",
                imageUrl = "https://logos-world.net/wp-content/uploads/2021/08/Alpine-Logo-2017-present-700x394.png"
            ),
            ConstructorImageResponse(
                id = "red_bull",
                imageUrl = "https://logodownload.org/wp-content/uploads/2014/10/red-bull-logo-2-1.png"
            ),
            ConstructorImageResponse(
                id = "ferrari",
                imageUrl = "https://logodownload.org/wp-content/uploads/2017/05/ferrari-logo-4-1.png"
            ),
            ConstructorImageResponse(
                id = "mercedes",
                imageUrl = "https://logodownload.org/wp-content/uploads/2014/04/mercedes-benz-logo-5.png"
            ),
            ConstructorImageResponse(
                id = "williams",
                imageUrl = "https://logodownload.org/wp-content/uploads/2022/04/williams-racing-logo-55.png"
            ),
            ConstructorImageResponse(
                id = "alfa",
                imageUrl = "https://logodownload.org/wp-content/uploads/2022/03/alfa-romeo-logo-4.png"
            ),
            ConstructorImageResponse(
                id = "haas",
                imageUrl = "https://logodownload.org/wp-content/uploads/2022/03/haas-f1-team-logo-5.png"
            ),
            ConstructorImageResponse(
                id = "aston_martin",
                imageUrl = "https://www.seekpng.com/png/full/143-1437620_aston-martin-logo-png.png"
            )
        )
    }
}
