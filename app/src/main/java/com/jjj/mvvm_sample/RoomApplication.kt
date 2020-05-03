package com.jjj.mvvm_sample

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * App 실행시 가장 먼저 호출되는 Application() 을 상속받은 클래스
 *  - 가장 먼저 실행되어 Stetho 설정
 *
 * Manifest 파일의 <Application> 태그 내에 android:name 등록 필요
 */
class RoomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}