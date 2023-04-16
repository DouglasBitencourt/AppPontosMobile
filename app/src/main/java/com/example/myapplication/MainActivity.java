package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMatricula, editTextNome, editTextDepartamento, editTextCargo;
    private Button button_create_fields;
    private LinearLayout linearLayoutRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializar as views do layout
        editTextMatricula = findViewById(R.id.editTextMatricula);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDepartamento = findViewById(R.id.editTextDepartamento);
        editTextCargo = findViewById(R.id.editTextCargo);
        button_create_fields = findViewById(R.id.button_create_fields);
        linearLayoutRegistros = findViewById(R.id.linearLayoutRegistros);

        button_create_fields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerarCampos();
            }
        });
    }

    private void gerarCampos() {
        linearLayoutRegistros.removeAllViews(); // remover todos os campos anteriores, caso existam

        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);

        // verificar se está dentro do horário de trabalho
        if (hora >= 8 && hora < 17) {

            // verificar se é horário de almoço
            if (hora == 12) {
                adicionarCampo();
            } else {
                int numeroCampos = hora - 8;
                if (hora > 12) {
                    numeroCampos--; // descontar o horário de almoço
                }
                for (int i = 0; i < numeroCampos; i++) {
                    adicionarCampo();
                }
            }

        } else if (hora >= 17 && hora <= 23) {
            for (int i = 0; i < 8; i++) {
                adicionarCampo();
            }
        }
    }

    private void adicionarCampo() {
        EditText campoEditText = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 16, 0, 0);
        campoEditText.setLayoutParams(layoutParams);
        campoEditText.setHint("Registro");
        linearLayoutRegistros.addView(campoEditText);
    }
}
