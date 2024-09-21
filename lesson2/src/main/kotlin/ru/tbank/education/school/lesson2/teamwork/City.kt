package ru.tbank.education.school.lesson2.teamwork

// Данные по номерам домов : Город -> Округ -> Район -> Улица -> Номера домов
val data: Map<String, Map<String, Map<String, Map<String, Set<Int>>>>> = mapOf(
    "Москва" to mapOf(
        "CAO" to mapOf(
            "Войковский" to mapOf(
                "Зои и Александра" to setOf(1, 2, 3, 4, 6, 8),
            ),
            "Красноармейский" to mapOf(
                "Космонавт Волков" to setOf(1, 3, 5, 6, 7),
                "Ракета" to setOf(2, 4, 8),
                "Тупик" to setOf(4)
            ),
        ),
        "ЗAO" to mapOf(
            "Звенигородскй" to mapOf(
                "Шоссе" to setOf(3, 4, 5, 6, 7),
                "Славянский бульвар" to setOf(1),
            ),
            "Минский" to mapOf(
                "Шоссе" to setOf(4, 1, 3, 6),
                "Генерала дорохова" to setOf(1, 2, 3, 4, 6, 8),
            )),
        "ЦАО" to mapOf(
            "Тверской" to mapOf(
                "Тверская" to setOf(2, 4, 5, 6, 7),
                "Лубянка" to setOf(3, 2, 1, 4, 5, 6),
                "Красная площадь" to setOf(1, 2, 3, 4, 6, 8),
                "Пушкинская" to setOf(1, 2, 3, 4, 6, 8),
                "Красная площадь" to setOf(1, 2, 3, 4, 6, 8),
            ),),
        "ВАО" to mapOf(
            "Митино" to mapOf(
                "Шоссе" to setOf(3, 4, 5, 6, 7),
                "Ворота" to setOf(2),
            ))
    ),
    "Питер" to mapOf("КАД" to mapOf(
            "Центральный" to mapOf(
                "Невский" to setOf(1, 2, 3, 4, 6, 7, 8),
            ),
            "Красноармейский" to mapOf(
                "Рубенштейн" to setOf(1, 3, 5, 6, 7),
                "Жуковского" to setOf(2, 4, 8),
                "Некрасовка" to setOf(4)
            ),
        ),
    )
)

fun main() {
    data.forEach { (city, districts) ->
        districts.forEach { (district, neighborhoods) ->
            // Проверяем, больше ли количество улиц, чем количество районов в округе
            if (neighborhoods.size > districts.size) {
                neighborhoods.forEach { (neighborhood, streets) ->
                    streets.forEach { (street, houseNumbers) ->
                        val maxHouseNumber = houseNumbers.maxOrNull()
                        if (maxHouseNumber != null) {
                            val missingHouses = (1..maxHouseNumber).filter { it !in houseNumbers }

                            if (missingHouses.size > houseNumbers.size) {
                                println("Город: $city, Округ: $district, Район: $neighborhood, Улица: $street")
                                println("Пропущенные дома: $missingHouses")
                                println("Существующие дома: $houseNumbers\n")
                            }
                        }
                    }
                }
            }
        }
    }
}
    //только в тех районах где улиц больше чем районов в округе
    //кольчество пропущеных домов больше чем существующих

