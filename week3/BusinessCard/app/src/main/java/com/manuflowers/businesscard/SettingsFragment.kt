package com.manuflowers.businesscard

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.manuflowers.businesscard.utils.Constants.PREFERENCES_MODE
import com.manuflowers.businesscard.utils.Constants.THEME_MODE_KEY
import com.manuflowers.businesscard.utils.ThemeMode
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : DialogFragment(), RadioGroup.OnCheckedChangeListener {

    lateinit var sharedPreferences: SharedPreferences

    override fun onStart() {
        super.onStart()
        /**
         * Set width and height to fragment layout
         * */
        dialog?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Initialize interface
        themeModeRadioGroup.setOnCheckedChangeListener(this)

        sharedPreferences = activity!!.getSharedPreferences(PREFERENCES_MODE, Context.MODE_PRIVATE)

        when (sharedPreferences.getInt(THEME_MODE_KEY, 0)) {
            ThemeMode.LIGHT.ordinal -> lightModeRadioButton.isChecked = true
            ThemeMode.DARK.ordinal -> darkModeRadioButton.isChecked = true
            ThemeMode.SYSTEM.ordinal, ThemeMode.BATTERY.ordinal -> systemModeRadioButton.isChecked = true
            else -> systemModeRadioButton.isChecked = true
        }

        if(isPreAndroid10()) {
            systemModeRadioButton.text = getString(R.string.battery_saver)
        }
    }

    //Apply changes according to checked radio button
    override fun onCheckedChanged(radioGroup: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.lightModeRadioButton -> switchToMode(AppCompatDelegate.MODE_NIGHT_NO, ThemeMode.LIGHT)
            R.id.darkModeRadioButton -> switchToMode(AppCompatDelegate.MODE_NIGHT_YES, ThemeMode.DARK)
            R.id.systemModeRadioButton -> {
                if (isPreAndroid10()) {
                    switchToMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, ThemeMode.BATTERY)
                } else {
                    switchToMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, ThemeMode.SYSTEM)
                }
            }
        }
    }

    //Set mode and save value in shared preference
    private fun switchToMode(mode: Int, themeMode: ThemeMode) {
        AppCompatDelegate.setDefaultNightMode(mode)
        sharedPreferences.edit().putInt(THEME_MODE_KEY, themeMode.ordinal).apply()
    }

    //validate pre android 10 devices
    private fun isPreAndroid10() = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
}