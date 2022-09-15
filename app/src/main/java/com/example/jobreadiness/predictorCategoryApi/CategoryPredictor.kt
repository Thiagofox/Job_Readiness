package com.example.jobreadiness.predictorCategoryApi

import com.google.gson.annotations.SerializedName

class CategoryPredictor {

//    @SerializedName("domain_id")
//    var domainId: String = ""
//    @SerializedName("domain_name")
//    var domainname: String = ""
    @SerializedName("category_id")
    var categoryid: String = ""
//    @SerializedName("category_name")
//    var categoryname: String = ""


}





// https://api.mercadolibre.com/sites/MLB/domain_discovery/search?limit=1&q=games
/*
[
    {
        "domain_id": "MLB-GAME_CONSOLES_VIDEO_GAMES_AND_ARCADE_MACHINES",
        "domain_name": "Consoles de videogames, videogames e máquinas de fliperama",
        "category_id": "MLB11207",
        "category_name": "Outros",
        "attributes": []
    }
]
*/