package ar.unlam.nohaapp

class Datasource {
    fun loadEventos(): List<Evento> {
        return mutableListOf(
            Evento("Desayuno", false, "Buffet"),
            Evento("Almuerzo", false, "Buffet"),
            Evento("Merienda", false, "Buffet"),
            Evento("Cena", false, "Buffet"),

            Evento("Bingo", false, "Salón"),
            Evento("Karaoke", false, "Salón"),
            Evento("Stand Up", false, "Salón"),

            Evento("Zumba", false, "Gimnasio"),
            Evento("Crossfit", false, "Gimnasio"),

            Evento("Pileta libre", false, "Natatorio"),
            Evento("Clases junior", false, "Natatorio"),
            Evento("Clases teens", false, "Natatorio"),
            Evento("Clases adultos", false, "Natatorio")
        )
    }

    fun loadLugares(): HashMap<String, List<Evento>> {
        loadEventos()

        val lugaresList = HashMap<String, List<Evento>>()
        val buffetList: MutableList<Evento> = mutableListOf()
        val salonList: MutableList<Evento> = mutableListOf()
        val gimnasioList: MutableList<Evento> = mutableListOf()
        val natatorioList: MutableList<Evento> = mutableListOf()

        for (i in loadEventos().indices) {
            var evento = loadEventos()[i]
            if (evento.lugar == "Buffet") {
                buffetList.add(evento)
                continue
            }
            if (evento.lugar == "Salón") {
                salonList.add(evento)
                continue
            }
            if (evento.lugar == "Gimnasio") {
                gimnasioList.add(evento)
                continue
            }
            if (evento.lugar == "Natatorio") {
                natatorioList.add(evento)
                continue
            }
        }

        lugaresList["Buffet"] = buffetList
        lugaresList["Salón"] = salonList
        lugaresList["Gimnasio"] = gimnasioList
        lugaresList["Natatorio"] = natatorioList

        return lugaresList
    }
}