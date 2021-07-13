package com.example.seoultoilet_cool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    // 런타임에서 권한이 필요한 퍼미션 목록
    var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    // 퍼미션 승인 요청 시 사용하는 요청 코드
    var REQUEST_PERMISSION_CODE = 1

    // 기본 맵 줌 레벨
    val DEFAULT_ZOOM_LEVEL = 17f

    // 현재 위치를 가져올 수 없는 경우 서울 시청의 위치로 지도를 보여주기 위해 서울시청의 위치를 변수로 선언
    // LatLng 클래스는 위도와 경도를 가지는 클래스
    val DEFAULT_LOCATION = LatLng(37.5662952, 126.97794509999994)

    // 구글 맵 객체를 참조할 멤버 변수
    var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mapview에 onCreate 함수 호출
        var mapView = findViewById<MapView>(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        // 앱이 실행될 때 런타임에서 위치 서비스 관련 권한 체크
        if(hasPermissions()){
            //권한이 있는 경우 맵 초기와
            initMap()
        }else{
            //권한 요청
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION_CODE)
        }

        //현재 위치 버튼 클릭 이벤트 리스너 설정
        var mlb = findViewById<FloatingActionButton>(R.id.myLocationButton)
        mlb.setOnClickListener{ onMyLocationButtonClick() }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // 맵 초기화
        initMap()
    }

    fun hasPermissions(): Boolean {
        //퍼미션 목록 중 하나라도 권한이 없으면 false 반환
        for(permission in PERMISSIONS){
            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }
}

@SuppressLint("MissingPermission")
fun initMap(){
    //맵뷰에서 구글 맵을 불러오는 함수. 콜백함수에서 구글맵 객체가 전달됨.
    var mapView = findViewById<MapView>(R.id.mapView)
    mapView.getMapAsync{
        // 구글 맵 멤버 변수에 구글맵 객체 저장
        googleMap = it
        it.uiSettings.isMyLocationButtonEnabled = false
        // 위치 사용 권한이 있는 경우

    }
}