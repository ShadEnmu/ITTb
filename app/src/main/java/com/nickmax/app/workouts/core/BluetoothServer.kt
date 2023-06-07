package com.nickmax.app.workouts.core

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice

class BluetoothServer(private val adapter: BluetoothAdapter) {

    private lateinit var device: BluetoothDevice
    private var connectThread: ConnectThread? = null
    private lateinit var callback: BluetoothCallback

    fun connect(mac: String, callback: BluetoothCallback) {
        device = adapter.getRemoteDevice(mac)
        connectThread = ConnectThread(device, callback)
        connectThread!!.start()
    }

    fun closeConnection() {
        connectThread?.closeConnection()
        connectThread = null
    }

    companion object {
        const val DEVICE_UUID: String = "00001101-0000-1000-8000-00805F9B34FB"
    }

    interface BluetoothCallback {
        fun post(angle: String)
    }
}