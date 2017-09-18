package app.oneapp.eddy.myapp.com.oneapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class ActivityListBusiness extends AppCompatActivity {

    ArrayList<Empresa> listaEmpresas;
    RecyclerView recyclerEmpresas;

    empresaSQLiteHelper  conn;
    //empresaSQLiteHelper Empresa = new empresaSQLiteHelper(ActivityListBusiness.this, "dbEmpresas", null, 1);
    //SQLiteDatabase db = Empresa.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_business);


        conn = new empresaSQLiteHelper(ActivityListBusiness.this, "dbEmpresas", null, 1);

        listaEmpresas = new ArrayList<>();

        recyclerEmpresas = (RecyclerView) findViewById(R.id.recyclerid);
        recyclerEmpresas.setLayoutManager(new LinearLayoutManager(this));

        consultarListaEmpresas();

        //Log.i("INFO", String.valueOf(listaEmpresas.get(0)));
        //llenarEmpresas();

       // AdaptadorEmpresas adapter = new AdaptadorEmpresas((listaEmpresas));
        //recyclerEmpresas.setAdapter(adapter);

        AdaptadorEmpresas adapter = new AdaptadorEmpresas((listaEmpresas));
        recyclerEmpresas.setAdapter(adapter);
    }

    //Metodo para realizar la consulta a la db
    private void consultarListaEmpresas() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Empresa empresa = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Adaptador.tabla_empresa, null);

        while(cursor.moveToNext()){

            empresa = new Empresa();
            empresa.setNombre(cursor.getString(0));
            empresa.setMechardising(cursor.getString(1));
            empresa.setEmisora(cursor.getString(2));
            empresa.setCodigo(cursor.getInt(3));

            listaEmpresas.add(empresa);
        }

    }

    /*
    public void llenarEmpresas(){

        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));


    }*/
}
