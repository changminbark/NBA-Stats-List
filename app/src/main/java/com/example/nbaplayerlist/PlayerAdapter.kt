package com.example.nbaplayerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter (private val playerNameList: List<String>, private val playerAgeList: List<String>, private val playerPTSList: List<String>): RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerName: TextView
        val playerAge: TextView
        val playerPTS: TextView

        init {
            // Find our RecyclerView item's TextView for future use
            playerName = view.findViewById(R.id.playerName)
            playerAge = view.findViewById(R.id.playerAge)
            playerPTS = view.findViewById(R.id.playerPoints)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerNameList.size + playerAgeList.size + playerPTSList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playerNameString: String = playerNameList.get(position)
        // Set item views based on your views and data model
        val nameTextView = holder.playerName
        nameTextView.setText(playerNameString)

        val playerAgeString: String = playerAgeList.get(position)
        // Set item views based on your views and data model
        val ageTextView = holder.playerAge
        ageTextView.setText(playerAgeString)

        val playerPTSString: String = playerPTSList.get(position)
        // Set item views based on your views and data model
        val ptsTextView = holder.playerPTS
        ptsTextView.setText(playerPTSString)
    }
}