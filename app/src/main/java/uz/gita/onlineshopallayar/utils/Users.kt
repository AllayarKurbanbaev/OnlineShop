package uz.gita.onlineshopallayar.utils

object Users {

//    var map: MutableMap<String, Int> = mutableMapOf(
//        "johnd" to 1,
//        "mor_2314" to 2,
//        "kevinryan" to 3,
//        "donero" to 4,
//        "derek" to 5,
//        "david_r" to 6,
//        "snyder" to 7,
//        "hopkins" to 8,
//        "kate_h" to 9,
//        "jimmie_k" to 10
//    )

    fun checking(username: String): Int {
        when (username) {
            "johnd" -> return 1
            "mor_2314" -> return 2
            "kevinryan" -> return 3
            "donero" -> return 4
            "derek" -> return 5
            "david_r" -> return 6
            "snyder" -> return 7
            "hopkins" -> return 8
            "kate_h" -> return 9
            "jimmie_k" -> return 10
            else -> return 0
        }
    }
}