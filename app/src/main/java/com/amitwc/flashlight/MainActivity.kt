package com.amitwc.flashlight

import android.graphics.Color
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var flashLight:ImageView
    private var isActive=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor=Color.WHITE

        flashLight=findViewById(R.id.flash_light)

        lightState(isActive)

        flashLight.setOnClickListener{
            if(isActive==false){
                flashLight.setImageResource(R.drawable.baseline_highlight_24)

                isActive=true
                lightState(isActive)
            }
            else{
                flashLight.setImageResource(R.drawable.outline_highlight_24)

                isActive=false
                lightState(isActive)
            }
        }

    }

    private fun lightState(state: Boolean) {

        val cameraManager:CameraManager=getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId:String?=null
        try{
            cameraId=cameraManager.cameraIdList[0]

            cameraManager.setTorchMode(cameraId,state)

        }
        catch (e:Exception){
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
        }

    }
}