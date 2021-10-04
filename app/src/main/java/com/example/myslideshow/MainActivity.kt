package com.example.myslideshow

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myslideshow.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var player: MediaPlayer
    class MyAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
        private val resources = listOf(
            R.drawable.food_byanbyanmen, R.drawable.food_curry_mukashi_yellow,
            R.drawable.food_curry_sagu_rice, R.drawable.food_jambalaya,
            R.drawable.food_udon_ippoin
        )
        //resourcesの大きさを返す
        override fun getItemCount():Int{
            return resources.size
        }
        override fun createFragment(position: Int): Fragment {
            return ImageFragment.newInstance(resources[position])
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter = MyAdapter(this)
        val handler = Handler()
        timer(period = 5000){
            handler.post{
                binding.pager.currentItem = (binding.pager.currentItem+1)%5
            }
        }
        player = MediaPlayer.create(this,R.raw.guruguru_2)
        player.isLooping = true
    }

    override fun onResume() {
        super.onResume()
        player.start()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }
}