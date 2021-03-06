package com.example.bsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bsql.app.appcontroller;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tambahteman extends AppCompatActivity {

    private TextInputEditText nama, telpon;
    private Button simpanBtn;
    String nm,tlp;
    int success;

    private static String url_insert = "https://20200140077.praktikumtiumy.com/insert.php";
    private static final String TAG = Tambahteman.class.getSimpleName();
    private static final String TAG_SUCCES = "succes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahteman);

        nama = (TextInputEditText) findViewById(R.id.tietNama);
        telpon = (TextInputEditText) findViewById(R.id.tietTelpon);
        simpanBtn = (Button) findViewById(R.id.buttonSave);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpanData();
            }
        });
    }

public void SimpanData()
{
    if (nama.getText().toString().equals("")||telpon.getText().toString().equals("")) {
        Toast.makeText(Tambahteman.this, "Semua harus diisi data", Toast.LENGTH_SHORT).show();
    }
    else
    {
        nm = nama.getText().toString();
        tlp = telpon.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response : " + response.toString());
                try {
                    JSONObject jobj = new JSONObject(response);
                    success = jobj.getInt(TAG_SUCCES);
                    if (success == 1) {
                        Toast.makeText(Tambahteman.this, "Sukses simpan data", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Tambahteman.this, "gagal", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "Error : " + error.getMessage());
            Toast.makeText(Tambahteman.this, "Gagal simpan data", Toast.LENGTH_SHORT).show();
        }

    })
        {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            params.put("nama", nm);
            params.put("telpon", tlp);

            return params;
        }
    };
        requestQueue.add(strReq);
    }
    }
}


