package com.example.daniel.filtros;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Button btnPicture;
    private ImageView mostrarFoto;
    private Bitmap bmp;
    private ListView list;
    private String [] filtros={"Filtro negativo","Escala de grises","Contraste","Modificacion por canal","Brillo",
            "Correccion gama","Rotar", "Imagen Espejo", "deteccion de bordes"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPicture =(Button)findViewById(R.id.button);
        mostrarFoto =(ImageView)findViewById(R.id.imageView);
        list = (ListView) findViewById(R.id.ListView);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,filtros);
        list.setAdapter(adapter);
        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l)
            {
                if (i == 0 || i == 1 || i == 7)
                    filtros(bmp);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        bmp = (Bitmap) data.getExtras().get("data");
        mostrarFoto.setImageBitmap(bmp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Metodo para enviar un bitmap a otro activity
    public void filtros(Bitmap bitmap) {
        Intent intent = new Intent(this, FiltroNegativo.class);
        intent.putExtra("bitMap",bitmap);
        startActivity(intent);
    }

}
