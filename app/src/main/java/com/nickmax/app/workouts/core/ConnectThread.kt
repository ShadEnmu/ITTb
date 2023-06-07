package com.nickmax.app.workouts.core

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import kotlinx.coroutines.delay
import java.io.IOException
import java.util.*

class ConnectThread(device: BluetoothDevice, val callback: BluetoothServer.BluetoothCallback) :
    Thread() {

    private var socket: BluetoothSocket? = null

    init {
        try {
            socket =
                device.createRfcommSocketToServiceRecord(UUID.fromString(BluetoothServer.DEVICE_UUID))
        } catch (e: IOException) {

        } catch (se: SecurityException) {

        }
    }

    override fun run() {
        try {
            socket?.connect()
            fetchData()
        } catch (e: IOException) {
            Log.d("DevTag", "fetchData: failed")
            closeConnection()
        } catch (se: SecurityException) {
            Log.d("DevTag", "fetchData: failed")
            closeConnection()
        }
    }

    fun fetchData() {
        while (true) {
            try {
                val available = socket?.inputStream?.available()
                val bytes = ByteArray(available!!)
                socket?.inputStream?.read(bytes, 0, available)
                val text = String(bytes)
                callback.post(text)
                sleep(200)
            } catch (se: SecurityException) {
                closeConnection()
            } catch (e: IOException) {
                closeConnection()
            } catch (ie: NumberFormatException) {

            }
        }
    }

    fun closeConnection() {
        sleep(1000)
        try {
            socket?.close()
        } catch (e: IOException) {

        }
    }
}