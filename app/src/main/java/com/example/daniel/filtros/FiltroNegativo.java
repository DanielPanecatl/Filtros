package com.example.daniel.filtros;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FiltroNegativo extends AppCompatActivity {
    private Spinner spin;
    private Bitmap bitmap;
    private ImageView imagen;
    private Button btnGuardar;
    private String [] filtros={"Seleccione un filtro","Filtro negativo","Escala de grises","Imagen espejo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_negativo);
        Intent intent = getIntent();
        bitmap = intent.getParcelableExtra("bitMap");
        imagen = (ImageView) findViewById(R.id.imageView2);
        imagen.setImageBitmap(bitmap);
        spin = (Spinner)findViewById(R.id.spinner);
        btnGuardar = (Button)findViewById(R.id.guardarImg);

        ArrayAdapter <String> intent2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,filtros);
        spin.setAdapter(intent2);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                if (index==1)
                    imagen.setImageBitmap(filtroNegativo(bitmap));
                else if (index == 2)
                    imagen.setImageBitmap(filtroEscala(bitmap));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
    }

    //metodo para convertir el bitmap enviado por la activity principal
    public static Bitmap filtroNegativo(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap returnBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int colorArray[] = new int[width * height];
        int r, g, b;
        bitmap.getPixels(colorArray, 0, width, 0, 0, width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                r = 255 - Color.red(colorArray[y * width + x]);
                g = 255 - Color.green(colorArray[y * width + x]);
                b = 255 - Color.blue(colorArray[y * width + x]);

                colorArray[y * width + x] = Color.rgb(r, g, b);
                returnBitmap.setPixel(x, y, colorArray[y * width + x]);
            }
        }
        return returnBitmap;
    }

    public Bitmap filtroEscala(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap returnBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int colorArray[] = new int[width * height];
        int r, g, b, gray;
        bitmap.getPixels(colorArray, 0, width, 0, 0, width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                r =Color.red(colorArray[y * width + x]);
                g =Color.green(colorArray[y * width + x]);
                b =Color.blue(colorArray[y * width + x]);
                gray =(r+g+b)/3;
                colorArray[y * width + x] = Color.rgb(gray, gray, gray);
                returnBitmap.setPixel(x, y, colorArray[y * width + x]);
            }
        }
        return returnBitmap;
    }
    //Metodo para guardar la imagen


}
