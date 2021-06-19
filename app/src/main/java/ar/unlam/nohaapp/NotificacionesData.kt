package ar.unlam.nohaapp

internal object NotificacionesData {
    val data: HashMap<String, List<String>>
        get() {
            val eventosList = HashMap<String, List<String>>()

            val buffet: MutableList<String> = ArrayList()
            buffet.add("Desayuno")
            buffet.add("Almuerzo")
            buffet.add("Merienda")
            buffet.add("Cena")

            val salon: MutableList<String> = ArrayList()
            salon.add("Bingo")
            salon.add("Karaoke")
            salon.add("Stand Up")

            val gimnasio: MutableList<String> = ArrayList()
            gimnasio.add("Zumba")
            gimnasio.add("Crossfit")

            val natatorio: MutableList<String> = ArrayList()
            natatorio.add("Pileta libre")
            natatorio.add("Clases Junior")
            natatorio.add("Clases Teens")
            natatorio.add("Clases Adultos")

            eventosList["Buffet"] = buffet
            eventosList["Salon"] = salon
            eventosList["Gimnasio"] = gimnasio
            eventosList["Natatorio"] = natatorio
            return eventosList
        }
}