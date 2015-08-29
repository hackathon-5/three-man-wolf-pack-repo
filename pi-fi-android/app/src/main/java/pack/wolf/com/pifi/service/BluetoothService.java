package pack.wolf.com.pifi.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pack.wolf.com.pifi.application.AppConstants;
import pack.wolf.com.pifi.util.ContextUtil;

/**
 * Created by ryanmoore on 8/29/15.
 */
public class BluetoothService  extends Service {

    private static final UUID MY_UUID = UUID.fromString("94f39d29-7d6d-437d-973b-fba39e49d4ee");

    BluetoothAdapter mBluetoothAdapter;
    BluetoothLeAdvertiser mBluetoothLeAdvertiser;


    public static final ParcelUuid PIFI = ParcelUuid.fromString("00031809-0000-1000-8000-00805f9b34fb");


    private void startBlueTooth() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "ble not supported.", Toast.LENGTH_SHORT).show();
            ContextUtil.finish(this);
        }

        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
                // your implementation here
                Log.e("blah", "scan - " + device.getAddress());
            }
        };

        AdvertiseSettings settings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED)
                .setConnectable(false)
                .setTimeout(0)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM)
                .build();

        AdvertiseData data = new AdvertiseData.Builder()
                .setIncludeDeviceName(true)
                .setIncludeTxPowerLevel(true)
                .addServiceUuid(PIFI)

                .addServiceData(PIFI, buildTempPacket())
                .build();


        mBluetoothLeAdvertiser = mBluetoothAdapter.getBluetoothLeAdvertiser();
        mBluetoothLeAdvertiser.startAdvertising(settings, data, mAdvertiseCallback);
    }

    private byte[] buildTempPacket() {
        int value;
        try {
            value = Integer.parseInt("blah.".toString());
        } catch (NumberFormatException e) {
            value = 0;
        }

        return new byte[] {(byte)value, 0x00};
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
//
//        Intent discoverableIntent = new
//                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//        startActivity(discoverableIntent);
//
//        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();
//        enableBluetooth(true);
////
////        mBluetoothAdapter =((BluetoothManager) this.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
////        if (mBluetoothAdapter == null) Log.e("onCreate", "mBluetoothAdapter is NULL!!!!!");
////
////        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
////            Log.d("onCreate", "BLE feature is NOT available");
////        } else {
////            Log.d("onCreate", "BLE feature is available");
////            startAdvertising();
////        }
//        // Initializes Bluetooth adapter.
//        final BluetoothManager bluetoothManager =
//                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        mBluetoothAdapter = bluetoothManager.getAdapter();
//        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice("48:51:B7:83:61:38");
//        BluetoothSocket tmp;
//        try {
//            tmp = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
//        } catch (IOException e) {
//            Log.e("Blah", "IO EXception creating connection.");
//        }

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }


    private AdvertiseCallback mAdvertiseCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            Log.i(AppConstants.LOG_TAG, "LE Advertise Started.");
        }

        @Override
        public void onStartFailure(int errorCode) {
            Log.w(AppConstants.LOG_TAG, "LE Advertise Failed: "+errorCode);
        }
    };


    public static void enableBluetooth(boolean enable){
        BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Log.e("enableBluetooth", "Not switching bluetooth since its not present");
            return;
        }
        if (bluetoothAdapter.isEnabled() == enable) {
            Log.e("enableBluetooth", "BT is enabled");
            if (bluetoothAdapter.isMultipleAdvertisementSupported()) {
                Log.d("Capability", "Multiple Advertisements supported");
            } else {
                Log.d("Capability", "Multiple Advertisements NOT supported!");
            }
            return;
        }
        if (enable) {
            bluetoothAdapter.enable();
        }
        else {
            bluetoothAdapter.disable();
        }
        Log.i("enableBluetooth", "Switched bluetooth to " + enable);
    }

    private void startAdvertising() {
        ParcelUuid mAdvParcelUUID = ParcelUuid.fromString("0000DEFF-0000-1000-8000-00805F9B34FB");

        mBluetoothLeAdvertiser = (BluetoothLeAdvertiser)((BluetoothAdapter)((BluetoothManager)this.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter()).getBluetoothLeAdvertiser();
        if (mBluetoothLeAdvertiser == null)
        {
            Log.e("startAdvertising", "didn't get a bluetooth le advertiser");
            return;
        }

        AdvertiseSettings.Builder mLeAdvSettingsBuilder =
                new AdvertiseSettings.Builder().setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH);
        mLeAdvSettingsBuilder.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_POWER);
        mLeAdvSettingsBuilder.setConnectable(false);
        AdvertiseData.Builder mLeAdvDataBuilder = new AdvertiseData.Builder();

        List<ParcelUuid> myUUIDs = new ArrayList<ParcelUuid>();
        myUUIDs.add(ParcelUuid.fromString("0000FE00-0000-1000-8000-00805F9B34FB"));
        byte mServiceData[] = { (byte)0xff, (byte)0xfe, (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x03, (byte)0x04 };
        mLeAdvDataBuilder.addServiceData(mAdvParcelUUID, mServiceData);

        AdvertiseSettings.Builder advSetBuilder = new AdvertiseSettings.Builder();
        advSetBuilder.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY);
        advSetBuilder.setConnectable(false);
        advSetBuilder.setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM);
        advSetBuilder.setTimeout(10000);
        Log.d("advBuild", "settings:" + advSetBuilder.build());

        AdvertiseData.Builder advDataBuilder = new AdvertiseData.Builder();
        advDataBuilder.setIncludeDeviceName(false);
        advDataBuilder.setIncludeTxPowerLevel(true);
        advDataBuilder.addServiceData(mAdvParcelUUID, mServiceData);
        mBluetoothLeAdvertiser.startAdvertising(mLeAdvSettingsBuilder.build(), mLeAdvDataBuilder.build(), mLeAdvCallback);
    }

    /**
     * Stop Advertisements
     */
    public void stopAdvertisements() {
        if (mBluetoothLeAdvertiser != null) {
            mBluetoothLeAdvertiser.stopAdvertising(mLeAdvCallback);
        }
    }

    private final AdvertiseCallback mLeAdvCallback = new AdvertiseCallback() {
        public void onStartSuccess (AdvertiseSettings settingsInEffect) {
            Log.d("AdvertiseCallback", "onStartSuccess:" + settingsInEffect);
        }

        public void onStartFailure(int errorCode) {
            String description = "";
            if (errorCode == AdvertiseCallback.ADVERTISE_FAILED_FEATURE_UNSUPPORTED) description = "ADVERTISE_FAILED_FEATURE_UNSUPPORTED";
            else if (errorCode == AdvertiseCallback.ADVERTISE_FAILED_TOO_MANY_ADVERTISERS) description = "ADVERTISE_FAILED_TOO_MANY_ADVERTISERS";
            else if (errorCode == AdvertiseCallback.ADVERTISE_FAILED_ALREADY_STARTED) description = "ADVERTISE_FAILED_ALREADY_STARTED";
            else if (errorCode == AdvertiseCallback.ADVERTISE_FAILED_DATA_TOO_LARGE) description = "ADVERTISE_FAILED_DATA_TOO_LARGE";
            else if (errorCode == AdvertiseCallback.ADVERTISE_FAILED_INTERNAL_ERROR) description = "ADVERTISE_FAILED_INTERNAL_ERROR";
            else description = "unknown";
            Log.e("AdvertiseCB", "onFailure error:" + errorCode + " " + description);
        }
    };
}