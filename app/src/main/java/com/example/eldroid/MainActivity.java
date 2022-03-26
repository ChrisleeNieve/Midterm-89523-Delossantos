package com.example.eldroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText prodId, prodName, prodDesc, prodPrice, prodQty;
    Button create, retrieve, update, delete,retrieveid;
    prodDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prodId = (EditText) findViewById(R.id.prod_id);
        prodName = findViewById(R.id.prod_name);
        prodDesc = findViewById(R.id.prod_description);
        prodPrice = findViewById(R.id.prod_price);
        prodQty = findViewById(R.id.product_quantity);

        create = findViewById(R.id.create_btn);
        retrieve = findViewById(R.id.retrieve_btn);
        retrieveid = (Button) findViewById(R.id.retrieveID_btn);
        update = findViewById(R.id.update_btn);
        delete = findViewById(R.id.delete_btn);


        DB = new prodDatabase(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = prodId.getText().toString();
                String nameTXT = prodName.getText().toString();
                String dscTXT = prodDesc.getText().toString();
                String priceTXT = prodPrice.getText().toString();
                String qtyTXT = prodQty.getText().toString();

                if (idTXT.isEmpty() && !nameTXT.isEmpty() && !dscTXT.isEmpty() && !priceTXT.isEmpty() && !qtyTXT.isEmpty()) {
                    prodId.setError("Product ID required");
                    prodId.requestFocus();
                    return;
                }
                if (nameTXT.isEmpty() && !idTXT.isEmpty() && !dscTXT.isEmpty() && !priceTXT.isEmpty() && !qtyTXT.isEmpty()) {
                    prodName.setError("Product name required");
                    prodName.requestFocus();
                    return;
                }
                if (dscTXT.isEmpty() && !idTXT.isEmpty() && !nameTXT.isEmpty() && !priceTXT.isEmpty() && !qtyTXT.isEmpty()) {
                    prodDesc.setError("Product description required");
                    prodDesc.requestFocus();
                    return;
                }
                if (priceTXT.isEmpty() && !idTXT.isEmpty() && !nameTXT.isEmpty() && !dscTXT.isEmpty() && !qtyTXT.isEmpty()) {
                    prodPrice.setError("Product price required");
                    prodPrice.requestFocus();
                    return;
                }
                if (qtyTXT.isEmpty() && !idTXT.isEmpty() && !nameTXT.isEmpty() && !dscTXT.isEmpty() && !priceTXT.isEmpty()) {
                    prodQty.setError("Product quantity required");
                    prodQty.requestFocus();
                    return;
                }

                if (idTXT.isEmpty() || nameTXT.isEmpty() || dscTXT.isEmpty() || priceTXT.isEmpty() || qtyTXT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Some fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkinsertdata = DB.insertuserdata(idTXT, nameTXT, dscTXT, priceTXT, qtyTXT);
                    if (checkinsertdata == true) {
                        Toast.makeText(MainActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                        prodId.getText().clear();
                        prodName.getText().clear();
                        prodDesc.getText().clear();
                        prodPrice.getText().clear();
                        prodQty.getText().clear();
                    } else
                        Toast.makeText(MainActivity.this, "Product already exists", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = prodId.getText().toString();
                String nameTXT = prodName.getText().toString();
                String dscTXT = prodDesc.getText().toString();
                String priceTXT = prodPrice.getText().toString();
                String qtyTXT = prodQty.getText().toString();

                if (idTXT.isEmpty() && !nameTXT.isEmpty() && !dscTXT.isEmpty() && !priceTXT.isEmpty() && !qtyTXT.isEmpty()) {
                    prodId.setError("Product ID required");
                    prodId.requestFocus();
                    return;
                }
                if (nameTXT.isEmpty() && !idTXT.isEmpty() && !dscTXT.isEmpty() && !priceTXT.isEmpty() && !qtyTXT.isEmpty()) {
                    prodName.setError("Product name required");
                    prodName.requestFocus();
                    return;
                }
                if (dscTXT.isEmpty() && !idTXT.isEmpty() && !nameTXT.isEmpty() && !priceTXT.isEmpty() && !qtyTXT.isEmpty()) {
                    prodDesc.setError("Product description required");
                    prodDesc.requestFocus();
                    return;
                }
                if (priceTXT.isEmpty() && !idTXT.isEmpty() && !nameTXT.isEmpty() && !dscTXT.isEmpty() && !qtyTXT.isEmpty()) {
                    prodPrice.setError("Product price required");
                    prodPrice.requestFocus();
                    return;
                }
                if (qtyTXT.isEmpty() && !idTXT.isEmpty() && !nameTXT.isEmpty() && !dscTXT.isEmpty() && !priceTXT.isEmpty()) {
                    prodQty.setError("Product quantity required");
                    prodQty.requestFocus();
                    return;
                }

                if (idTXT.isEmpty() || nameTXT.isEmpty() || dscTXT.isEmpty() || priceTXT.isEmpty() || qtyTXT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Some fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkupdatedata = DB.updateuserdata(idTXT, nameTXT, dscTXT, priceTXT, qtyTXT);
                    if (checkupdatedata == true) {
                        Toast.makeText(MainActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
                        prodId.getText().clear();
                        prodName.getText().clear();
                        prodDesc.getText().clear();
                        prodPrice.getText().clear();
                        prodQty.getText().clear();
                    } else
                        Toast.makeText(MainActivity.this, "Invalid product ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Product/s Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + "\n");
                    buffer.append("Description: " + res.getString(2) + "\n");
                    buffer.append("Price: " + res.getString(3) + "\n");
                    buffer.append("Quantity: " + res.getString(4) + "\n\n");
                }

                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Products");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        retrieveid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = prodId.getText().toString();
                SQLiteDatabase simpledb = getApplicationContext().openOrCreateDatabase("Productdata.db",Context.MODE_PRIVATE,null);
                Cursor c = simpledb.rawQuery("Select * from ProductInventory where id ='"+n+"'",null);
                if (c.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "No Product/s Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("ID: " + c.getString(0) + "\n");
                    buffer.append("Name: " + c.getString(1) + "\n");
                    buffer.append("Description: " + c.getString(2) + "\n");
                    buffer.append("Price: " + c.getString(3) + "\n");
                    buffer.append("Quantity: " + c.getString(4) + "\n\n");

                }
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Products");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = prodId.getText().toString();
                Boolean checkudeletedata = DB.deletedata(idTXT);

                if (idTXT.isEmpty()) {
                    prodId.setError("Product ID is required!");
                    prodId.requestFocus();
                    return;
                }

                if (checkudeletedata == true) {
                    Toast.makeText(MainActivity.this, "Product Deleted", Toast.LENGTH_SHORT).show();
                    prodId.getText().clear();
                } else
                    Toast.makeText(MainActivity.this, "Invalid product ID", Toast.LENGTH_SHORT).show();
            }
        });


    }
}