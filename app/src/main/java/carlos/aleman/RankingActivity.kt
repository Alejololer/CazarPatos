package carlos.aleman

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import carlos.aleman.database.RankingPlayerDBHelper

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        OperacionesSqLite()
        GrabarRankingSQLite()
        LeerRankingsSQLite()
    }

    fun OperacionesSqLite(){
        RankingPlayerDBHelper(this).deleteAllRanking()
        RankingPlayerDBHelper(this).insertRankingByQuery(Player("Jugador9",10))
        val patosCazados = RankingPlayerDBHelper(this).readDucksHuntedByPlayer("Jugador9")
        RankingPlayerDBHelper(this).updateRanking(Player("Jugador9",5))
        RankingPlayerDBHelper(this).deleteRanking("Jugador9")
        RankingPlayerDBHelper(this).insertRanking(Player("Jugador9",7))
        val players = RankingPlayerDBHelper(this).readAllRankingByQuery()
    }
    fun GrabarRankingSQLite(){
        val jugadores = arrayListOf(
            Player("Carlos.Aleman", 10),
            Player("Jugador2", 6),
            Player("Jugador3", 3),
            Player("Jugador4", 9)
        )
        jugadores.sortByDescending { it.huntedDucks }
        for(jugador in jugadores){
            RankingPlayerDBHelper(this).insertRanking(jugador)
        }
    }
    fun LeerRankingsSQLite(){
        val jugadoresSQLite = RankingPlayerDBHelper(this).readAllRanking()
        val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewRanking)
        recyclerViewRanking.layoutManager = LinearLayoutManager(this)
        recyclerViewRanking.adapter = RankingAdapter(jugadoresSQLite)
        recyclerViewRanking.setHasFixedSize(true)
    }

}
