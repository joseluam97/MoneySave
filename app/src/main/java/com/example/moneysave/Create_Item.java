package com.example.moneysave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.RadioButton;

public class Create_Item extends AppCompatActivity {

    private RadioGroup radioGroupTipo;
    private EditText editTextImporte, editTextFecha, editTextComentario;
    private Spinner spinnerCategoria;
    private Button buttonGuardar;
    private RadioButton radioButtonIngreso, radioButtonGasto;
    private int colorLavender, colorWhite;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        // Inicializar vistas
        colorLavender = ContextCompat.getColor(this, R.color.lavender);
        colorWhite = ContextCompat.getColor(this, R.color.white);
        radioButtonIngreso = findViewById(R.id.radioButtonIngreso);
        radioButtonGasto = findViewById(R.id.radioButtonGasto);
        radioGroupTipo = findViewById(R.id.radioGroupTipo);
        editTextImporte = findViewById(R.id.editTextImporte);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        editTextFecha = findViewById(R.id.editTextFecha);
        editTextComentario = findViewById(R.id.editTextComentario);
        buttonGuardar = findViewById(R.id.buttonGuardar);

        // Inicializar el calendario
        calendar = Calendar.getInstance();

        // Formato de fecha
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        // Configurar el adaptador del Spinner con las categorías
        String[] categoríasArray = {"Categoría 1", "Categoría 2", "Categoría 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoríasArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        //Configurar el selector de gastos/ingreso
        radioGroupTipo.setOnCheckedChangeListener((group, checkedId) -> {
            //Configuracion del boton seleccionado
            // Crear un objeto GradientDrawable para personalizar el fondo y los bordes
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE); // Forma rectangular
            drawable.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.radio_button_corner_radius)); // Radio de borde en píxeles
            drawable.setStroke(getResources().getDimensionPixelSize(R.dimen.radio_button_border_width), Color.BLACK); // Borde de 1px en color negro
            drawable.setColor(colorLavender); // Color de fondo

            if(checkedId == R.id.radioButtonIngreso){
                radioButtonGasto.setBackgroundColor(colorWhite);
                radioButtonGasto.setTextColor(Color.BLACK);
                radioButtonIngreso.setBackground(drawable);
                radioButtonIngreso.setTextColor(Color.WHITE);
            }
            if(checkedId == R.id.radioButtonGasto){
                radioButtonGasto.setBackground(drawable);
                radioButtonGasto.setTextColor(Color.WHITE);
                radioButtonIngreso.setBackgroundColor(colorWhite);
                radioButtonIngreso.setTextColor(Color.BLACK);
            }
        });

        // Configurar el selector de fecha
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                editTextFecha.setText(dateFormatter.format(calendar.getTime()));
            }
        };

        editTextFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la fecha actual
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Crea un DatePickerDialog con la fecha actual como fecha máxima
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Create_Item.this,
                        dateSetListener,
                        year,
                        month,
                        day
                );

                // Establece la fecha máxima permitida
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

                // Muestra el diálogo
                datePickerDialog.show();
            }
        });

        // Manejar el clic del botón de guardar
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el tipo seleccionado (Gasto o Ingreso)
                int tipoSeleccionadoId = radioGroupTipo.getCheckedRadioButtonId();
                RadioButton radioButtonTipo = findViewById(tipoSeleccionadoId);
                String tipoSeleccionado = radioButtonTipo.getText().toString();

                // Obtener el importe ingresado
                String importe = editTextImporte.getText().toString();

                // Obtener la categoría seleccionada
                String categoriaSeleccionada = spinnerCategoria.getSelectedItem().toString();

                // Obtener la fecha seleccionada
                String fechaSeleccionada = editTextFecha.getText().toString();

                // Obtener el comentario ingresado
                String comentario = editTextComentario.getText().toString();

                // Realizar acciones con los datos ingresados, por ejemplo, guardar en una base de datos
                // Aquí puedes implementar la lógica para guardar los datos en tu aplicación

                // Ejemplo: Mostrar un mensaje de confirmación
                String mensaje = "Tipo: " + tipoSeleccionado + "\n" +
                        "Importe: " + importe + "\n" +
                        "Categoría: " + categoriaSeleccionada + "\n" +
                        "Fecha: " + fechaSeleccionada + "\n" +
                        "Comentario: " + comentario;

                Toast.makeText(Create_Item.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }
}