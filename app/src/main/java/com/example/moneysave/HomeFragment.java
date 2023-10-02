package com.example.moneysave;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.moneysave.Adapters.GastosAdapter;
import com.example.moneysave.Models.Gasto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class HomeFragment extends Fragment {

    private FloatingActionButton buttonCreateItem;
    private int colorBlue, colorWhite;
    private Date dateSelected;
    private TextView textViewDay;
    private RadioGroup radioGroup;
    private ImageButton arrow_left, arrow_right;
    private View selectionIndicatorDay, selectionIndicatorWeek, selectionIndicatorMonth;
    private RelativeLayout boxIngresos, boxGastos;

    private void formatDateDay(Date dateInitial){
        SimpleDateFormat sdfDay = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale.getDefault());
        String formattedDateDay = sdfDay.format(dateInitial);
        textViewDay.setText(formattedDateDay);
    }
    private void formatDateWeek(Date dateInitial){
        long sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000L; // 7 días en milisegundos
        SimpleDateFormat sdfWeek = new SimpleDateFormat("d 'de' MMM 'de' yyyy", Locale.getDefault());
        String formattedDateWeekLast = sdfWeek.format(dateInitial);
        String formattedDateWeekStart = sdfWeek.format(dateInitial.getTime() - sevenDaysInMillis);
        String generateDate = formattedDateWeekStart + " - " + formattedDateWeekLast;
        textViewDay.setText(generateDate);
    }

    private void formatDateMonth(Date dateInitial){
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM 'de' yyyy", Locale.getDefault());
        String formattedDateMonth = sdfMonth.format(dateInitial);
        textViewDay.setText(formattedDateMonth);
    }
    public void selectorRadioGroup(View rootView){

        radioGroup = rootView.findViewById(R.id.radioGroup);
        textViewDay = rootView.findViewById(R.id.textViewDay);
        selectionIndicatorDay = rootView.findViewById(R.id.selectionIndicatorDay);
        selectionIndicatorWeek = rootView.findViewById(R.id.selectionIndicatorWeek);
        selectionIndicatorMonth = rootView.findViewById(R.id.selectionIndicatorMonth);

        colorBlue = ContextCompat.getColor(getContext(), R.color.blue);
        colorWhite = ContextCompat.getColor(getContext(), R.color.white);

        //Initial Value
        dateSelected = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale.getDefault());

        String formattedDate = sdf.format(dateSelected);
        textViewDay.setText(formattedDate);

        selectionIndicatorDay.setBackgroundColor(colorBlue);
        selectionIndicatorWeek.setBackgroundColor(colorWhite);
        selectionIndicatorMonth.setBackgroundColor(colorWhite);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            textViewDay = rootView.findViewById(R.id.textViewDay);
            dateSelected = new Date();
            selectionIndicatorDay = rootView.findViewById(R.id.selectionIndicatorDay);
            selectionIndicatorWeek = rootView.findViewById(R.id.selectionIndicatorWeek);
            selectionIndicatorMonth = rootView.findViewById(R.id.selectionIndicatorMonth);
            colorBlue = ContextCompat.getColor(getContext(), R.color.blue);
            colorWhite = ContextCompat.getColor(getContext(), R.color.white);
            if(checkedId == R.id.radioDay){
                formatDateDay(dateSelected);
                selectionIndicatorDay.setBackgroundColor(colorBlue);
                selectionIndicatorWeek.setBackgroundColor(colorWhite);
                selectionIndicatorMonth.setBackgroundColor(colorWhite);
            }
            if(checkedId == R.id.radioWeek){
                formatDateWeek(dateSelected);
                selectionIndicatorDay.setBackgroundColor(colorWhite);
                selectionIndicatorWeek.setBackgroundColor(colorBlue);
                selectionIndicatorMonth.setBackgroundColor(colorWhite);
            }
            if(checkedId == R.id.radioMonth){
                formatDateMonth(dateSelected);
                selectionIndicatorDay.setBackgroundColor(colorWhite);
                selectionIndicatorWeek.setBackgroundColor(colorWhite);
                selectionIndicatorMonth.setBackgroundColor(colorBlue);
            }
        });
    }

    private void selectorArrows(View rootView){
        arrow_left = rootView.findViewById(R.id.arrow_leftButton);
        arrow_right = rootView.findViewById(R.id.arrow_rightButton);

        arrow_left.setOnClickListener((value) -> {
            RadioButton radioDay = rootView.findViewById(R.id.radioDay);
            RadioButton radioWeek = rootView.findViewById(R.id.radioWeek);
            RadioButton radioMonth = rootView.findViewById(R.id.radioMonth);
            if(radioDay.isChecked()){
                long oneDaysInMillis = 1 * 24 * 60 * 60 * 1000L; // 1 día en milisegundos
                dateSelected.setTime(dateSelected.getTime() - oneDaysInMillis);
                formatDateDay(dateSelected);
            }
            if(radioWeek.isChecked()){
                long oneDaysInMillis = 8 * 24 * 60 * 60 * 1000L; // 8 días en milisegundos
                dateSelected.setTime(dateSelected.getTime() - oneDaysInMillis);
                formatDateWeek(dateSelected);
            }
            if(radioMonth.isChecked()){
                long oneDaysInMillis = 32 * 24 * 60 * 60 * 1000L; // 32 días en milisegundos
                dateSelected.setTime(dateSelected.getTime() - oneDaysInMillis);
                formatDateMonth(dateSelected);
            }
        });

        arrow_right.setOnClickListener((value) -> {
            RadioButton radioDay = rootView.findViewById(R.id.radioDay);
            RadioButton radioWeek = rootView.findViewById(R.id.radioWeek);
            RadioButton radioMonth = rootView.findViewById(R.id.radioMonth);
            if(radioDay.isChecked()){
                long oneDaysInMillis = 1 * 24 * 60 * 60 * 1000L; // 1 día en milisegundos
                dateSelected.setTime(dateSelected.getTime() + oneDaysInMillis);
                formatDateDay(dateSelected);
            }
            if(radioWeek.isChecked()){
                long oneDaysInMillis = 7 * 24 * 60 * 60 * 1000L; // 7 días en milisegundos
                dateSelected.setTime(dateSelected.getTime() + oneDaysInMillis);
                formatDateWeek(dateSelected);
            }
            if(radioMonth.isChecked()){
                long oneDaysInMillis = 32 * 24 * 60 * 60 * 1000L; // 32 días en milisegundos
                dateSelected.setTime(dateSelected.getTime() + oneDaysInMillis);
                formatDateMonth(dateSelected);
            }
        });

    }

    private void selectorBoxIngGast(View rootView){
        Context context = getContext();
        boxIngresos = rootView.findViewById(R.id.boxIngresos);
        boxGastos = rootView.findViewById(R.id.boxGastos);

        boxIngresos.setOnClickListener((value) -> {
            Toast.makeText(context, "Filter by income", Toast.LENGTH_SHORT).show();
        });

        boxGastos.setOnClickListener((value) -> {
            Toast.makeText(context, "Filter by expenses", Toast.LENGTH_SHORT).show();
        });
    }

    private void selectorCreateItem(View rootView){
        Context context = getContext();
        buttonCreateItem = rootView.findViewById(R.id.buttonCreateItem);

        buttonCreateItem.setOnClickListener((value) -> {
            //Toast.makeText(context, "CREATEEE ITEM", Toast.LENGTH_SHORT).show();

            // Cuando se hace clic en el botón, crea un Intent para abrir el Activity
            Intent intent = new Intent(getActivity(), Create_Item.class);

            // Inicia el Activity
            startActivity(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //CONFIGURACION DEL SELECTOR DE RADIO
        selectorRadioGroup(rootView);

        //CONFIGURACION DE LOS SELECTORES FLECHAS
        selectorArrows(rootView);

        //CONFIGURACION DE LOS BOX DE INGRESOS Y GASTOS
        selectorBoxIngGast(rootView);

        //CONFIGURACION DEL CREATE NEW ITEM
        selectorCreateItem(rootView);

        RecyclerView recycleItems = rootView.findViewById(R.id.listaCategoriasGasto);

        // Main configuration
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycleItems.setLayoutManager(layoutManager);

        // Adapter configuration
        List<Gasto> listGastosStatic = getGastosEstaticos();

        RecyclerView.Adapter adapterGastos = new GastosAdapter(getContext(), listGastosStatic);

        recycleItems.setAdapter(adapterGastos);

        return rootView;
    }

    public List<Gasto> getGastosEstaticos(){
        List<Gasto> listaGastos = new ArrayList<>();

        // Agregar gastos estáticos a la lista
        listaGastos.add(new Gasto("Comida", 50.0));
        listaGastos.add(new Gasto("Transporte", 30.0));
        listaGastos.add(new Gasto("Entretenimiento", 20.0));
        listaGastos.add(new Gasto("Compras", 75.0));
        listaGastos.add(new Gasto("Salud", 40.0));

        return listaGastos;
    }
}