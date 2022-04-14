package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel :ViewModel(){
    // hold the timer constants
    companion object {

        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L

    }

    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime
    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>() //对内是可修改的
    val score: LiveData<Int>
        get() = _score

    // Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    private val timer: CountDownTimer

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }


    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }else{
            resetList()
        }
    }
    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    /** Method for the game completed event **/
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    init {
        _word.value = ""
        _score.value = 0

        // Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) { //匿名对象，该对象继承自CountDownTimer

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }
        }

        timer.start()

        resetList()
        nextWord()
        Log.i("GameViewModel","GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        //Cancel the timer
        timer.cancel()
        Log.i("GameViewModel","GameViewModel destroyed!")
    }
}