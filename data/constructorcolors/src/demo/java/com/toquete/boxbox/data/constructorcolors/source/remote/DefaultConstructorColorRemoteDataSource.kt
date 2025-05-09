package com.toquete.boxbox.data.constructorcolors.source.remote

import com.toquete.boxbox.core.network.model.ConstructorColorResponse

internal class DefaultConstructorColorRemoteDataSource : ConstructorColorRemoteDataSource {

    override suspend fun getConstructorsColors(): List<ConstructorColorResponse> {
        return listOf(
            ConstructorColorResponse(
                id = "alfa",
                accentColor = "#c92d4b",
                backgroundColor = "#c92d4b"
            ),
            ConstructorColorResponse(
                id = "alpine",
                accentColor = "#2193d1",
                backgroundColor = "#2193d1"
            ),
            ConstructorColorResponse(
                id = "ferrari",
                accentColor = "#f91335",
                backgroundColor = "#f91335"
            ),
            ConstructorColorResponse(
                id = "aston_martin",
                accentColor = "#358c75",
                backgroundColor = "#FFFFFF"
            ),
            ConstructorColorResponse(
                id = "red_bull",
                accentColor = "#3571c6",
                backgroundColor = "#3571c6"
            ),
            ConstructorColorResponse(
                id = "mclaren",
                accentColor = "#f5801f",
                backgroundColor = "#f5801f"
            ),
            ConstructorColorResponse(
                id = "alphatauri",
                accentColor = "#5e8fa9",
                backgroundColor = "#5e8fa9"
            ),
            ConstructorColorResponse(
                id = "mercedes",
                accentColor = "#6bd3be",
                backgroundColor = "#000000"
            ),
            ConstructorColorResponse(
                id = "haas",
                accentColor = "#b6babd",
                backgroundColor = "#b6babd"
            ),
            ConstructorColorResponse(
                id = "williams",
                accentColor = "#36bddc",
                backgroundColor = "#36bddc"
            )
        )
    }
}
