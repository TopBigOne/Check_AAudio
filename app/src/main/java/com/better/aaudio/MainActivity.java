package com.better.aaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.better.aaudio.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'aaudio' library on application startup.


    private ActivityMainBinding binding;

    private boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initAAudio();


        initEvent();
    }

    private void initAAudio() {

        AAudioApi.init();

        short[] buffer = readFile();

        AAudioApi.writeBuffer(buffer);
    }

    private short[] readFile() {
        //fill buffer
        AssetManager am = getBaseContext().getAssets();
        try {
            InputStream is     = am.open("myfile");
            byte[]      arr    = readBytes(is);
            short[]     shorts = new short[arr.length / 2];
            ByteBuffer.wrap(arr).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
            return shorts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] readBytes(InputStream inputStream) throws IOException {
        byte[]                b  = new byte[1024];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int                   c;
        while ((c = inputStream.read(b)) != -1) {
            os.write(b, 0, c);
        }
        return os.toByteArray();
    }


    private void initEvent() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPlaying) {
                    AAudioApi.stop();

                    binding.button.setText("start");
                    isPlaying = false;
                } else {
                    AAudioApi.start();

                    binding.button.setText("stop");
                    isPlaying = true;
                }

            }
        });

    }


}