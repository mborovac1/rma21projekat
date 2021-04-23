package ba.etf.rma21.projekat.data.models

fun pitanja(): List<Pitanje> {
    return listOf(
            Pitanje("RMA-Pitanje1", "Koji programski jezik koristimo na ovom predmetu?",
                    listOf("Kotlin", "Java", "C#"), 0),
            Pitanje("RMA-Pitanje2", "Koja ključna riječ se u Kotlinu koristi za tip " +
                    "podataka sa jedinstvenom implementacijom (Singleton pattern)?",
                    listOf("fun", "object", "inline"), 1),
            Pitanje("RMA-Pitanje3", "Kako se kreiraju ad-hoc funkcije u Kotlinu?",
                    listOf("Korištenjem operator funkcija", "Ad-hoc funkcije nisu moguće u Kotlinu",
                            "Korištenjem lambda funkcija"), 2),
            Pitanje("RMA-Pitanje4", "Na koji način možemo funkciji proslijediti bilo " +
                    "koliko parametara funkciji?", listOf("Korištenjem vararg", "Korištenjem " +
                    "infix funkcije", "Nije moguće"), 0),
            Pitanje("RMA-Pitanje5", "Ako želimo da vrijednost variable može biti " +
                    "null, na kraju tipa dodajemo znak", listOf("!", "/", "?"), 2),

            Pitanje("DM-Pitanje1", "RSA kriptosistem je", listOf("protokol razmjene " +
                    "ključeva", "protokol šifriranja s javnim ključem", "protokol šifriranja sa " +
                    "javnim ključem"), 1),
            Pitanje("DM-Pitanje2", "Šta je neophodno uraditi za rješavanje " +
                    "kvadratnih kongruencija kod kojih je modul složen broj?",
                    listOf("Rastaviti modul na proste faktore", "Primijeniti Henselovu lemu",
                            "Osloboditi se prostih faktora s eksponentima većima od 1"), 0),
            Pitanje("DM-Pitanje3", "Neki prirodan broj je djeljiv s 4 ako i samo ako " +
                    "mu je ", listOf("prva cifra djeljiva s 4", "suma cifara djeljiva s 4", "broj" +
                    " formiran od posljednje dvije cifre djeljiv s 4"), 2),
            Pitanje("DM-Pitanje4", "Kongruencija spada u", listOf("relacije " +
                    "ekvivalencije", "antirefleksivne relacije", "funkcijske relacije"), 0),
            Pitanje("DM-Pitanje5", "Ako su a i b kongruentni po modulu m, tada je",
                    listOf("a + b djeljivo sa m", "i a i b djeljivo sa m",
                            "a - b djeljivo sa m"), 2),

            Pitanje("IM-Pitanje1", "Koliko je 1 + 1?",
                    listOf("3", "5", "2"), 2),
            Pitanje("IM-Pitanje2", "Koliko je 3 - 1?",
                    listOf("4", "2", "1"), 1),
            Pitanje("IM-Pitanje3", "Koliko je 3 * 7?",
                    listOf("21", "10", "14"), 0),
            Pitanje("IM-Pitanje4", "Koliko je 13 * 17",
                    listOf("251", "221", "191"), 1),
            Pitanje("IM-Pitanje5", "Koliko je 4581 / 9",
                    listOf("509", "462", "701"), 0),

            Pitanje("AFJ-Pitanje1", "Šta je alfabet?",
                    listOf("Jedinstveni nedjeljivi element", "Konačan neprazni skup simbola",
                            "Skup svih stanja automata"), 1),
            Pitanje("AFJ-Pitanje2", "Stablo izračunavanja se koristi za",
                    listOf("provjeravanje automata", "računanje dužine riječi",
                            "reverziranje riječi"), 0),
            Pitanje("AFJ-Pitanje3", "Koji automat može preći iz jednog u drugo stanje" +
                    " a da pri tome ne pročita ništa?", listOf("DKA", "NKA", "epsilon-NKA"), 2),
            Pitanje("AFJ-Pitanje4", "Koja je oznaka za praznu riječ?", listOf("w",
                    "epsilon", "x"), 1),
            Pitanje("AFJ-Pitanje5", "Šta je simbol?",
                    listOf("Konačan neprazan skup simbola", "Jedinstveni nedjeljivi element",
                            "Stanje automata"), 1)
    )
}
