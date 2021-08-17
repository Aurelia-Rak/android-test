package com.evaneos.data.factory

import com.evaneos.data.model.Destination
import com.evaneos.data.model.DestinationDetails

internal object DataFactory {

    fun createDestinationList(): List<Destination> {
        return listOf(
            Destination(
                id = 217,
                name = "Barbade",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1027399_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 5
            ),
            Destination(
                id = 50,
                name = "Arménie",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1544481_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 6,
                name = "Allemagne",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1027397_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 5
            ),
            Destination(
                id = 306,
                name = "Bali",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1581674_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 13,
                name = "Autriche",
                pictureUrl = "https://static1.evcdn.net/images/reduction/354894_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 5
            ),
            Destination(
                id = 147,
                name = "Antilles",
                pictureUrl = "https://static1.evcdn.net/images/reduction/397848_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 373,
                name = "Basse-Californie",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1596154_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 73,
                name = "Afrique du Sud",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1506493_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 5
            ),
            Destination(
                id = 98,
                name = "Australie",
                pictureUrl = "https://static1.evcdn.net/images/reduction/635304_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 426,
                name = "Amazonie Brésilienne",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1595441_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 377,
                name = "Bajio",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1596170_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 74,
                name = "Azerbaïdjan",
                pictureUrl = "https://static1.evcdn.net/images/reduction/611704_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 5
            ),
            Destination(
                id = 115,
                name = "Antarctique",
                pictureUrl = "https://static1.evcdn.net/images/reduction/210925_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 110,
                name = "Bangladesh",
                pictureUrl = "https://static1.evcdn.net/images/reduction/356979_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 29,
                name = "Algérie",
                pictureUrl = "https://static1.evcdn.net/images/reduction/1230836_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 5
            ),
            Destination(
                id = 75,
                name = "Argentine",
                pictureUrl = "https://static1.evcdn.net/images/reduction/904030_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 5
            ),
            Destination(
                id = 173,
                name = "Açores",
                pictureUrl = "https://static1.evcdn.net/images/reduction/356685_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 170,
                name = "Albanie",
                pictureUrl = "https://static1.evcdn.net/images/reduction/413980_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 287,
                name = "Angleterre",
                pictureUrl = "https://static1.evcdn.net/images/reduction/609757_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 4
            ),
            Destination(
                id = 107,
                name = "Bahamas",
                pictureUrl = "https://static1.evcdn.net/images/reduction/39034_w-800_h-800_q-70_m-crop.jpg",
                tag = "Incontournable",
                rating = 5
            ),
        )
    }

    fun getDestinationDetails(id: Long): DestinationDetails {
        val destinations = createDestinationList()

        for (destination in destinations) {
            if (destination.id == id) {
                val urlSuffix = destination.name
                    .replace(" ", "-")
                    .replace("é", "e")
                    .replace("ï", "i")
                    .replace("ç", "c")
                val url = "https://evaneos.fr/$urlSuffix"

                return DestinationDetails(id = destination.id, name = destination.name, url = url)
            }
        }

        throw IllegalStateException("Could not find details for destination: id = $id")
    }
}