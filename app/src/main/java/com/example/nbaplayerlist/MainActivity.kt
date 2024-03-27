package com.example.nbaplayerlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.nbaplayerlist.databinding.ActivityMainBinding
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var playerList: MutableList<String>
    private lateinit var rvPlayers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvPlayers = findViewById(R.id.player_list)
        playerList = mutableListOf()

        retrievePlayerStats()
    }

    private fun retrievePlayerStats() {

        val client = AsyncHttpClient()
        client["https://nba-stats-db.herokuapp.com/api/playerdata/season/2023", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Stats", "response successful$json")
                val playerResultsArray = json.jsonObject.getJSONArray("results")
                Log.d("array", playerResultsArray.toString())
                val playerNamesList = mutableListOf<String>()
                val playerAgesList = mutableListOf<String>()
                val playerPTSList = mutableListOf<String>()

                for (i in 0 until playerResultsArray.length()) {
                    playerNamesList.add(playerResultsArray.getJSONObject(i).getString("player_name"))
                }
                for (i in 0 until playerResultsArray.length()) {
                    playerAgesList.add(playerResultsArray.getJSONObject(i).getString("age"))
                }
                for (i in 0 until playerResultsArray.length()) {
                    playerPTSList.add(playerResultsArray.getJSONObject(i).getString("PTS"))
                }

                val adapter = PlayerAdapter(playerNamesList, playerAgesList, playerPTSList)
                rvPlayers.adapter = adapter
                rvPlayers.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Stats", errorResponse)
            }
        }]
    }
}