package org.sdecima.cursoandroid.ejercicios.calculodepropinas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.sdecima.cursoandroid.ejercicios.calculodepropinas.R;

public class CalculoDePropina_1 extends Activity {
	
	EditText etMonto;
	EditText etPropina;
	EditText etPersonas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etMonto = (EditText) findViewById(R.id.editTextMonto);
		etPropina = (EditText) findViewById(R.id.editTextPropina);
		etPersonas = (EditText) findViewById(R.id.editTextPersonas);
		
		inicializar();
	}
	
	/**
	 * Inicializo los controles en pantalla a valores por defecto
	 */
	public void inicializar() {
		etMonto.setText("0");
		etPropina.setText("10");
		etPersonas.setText("1");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Lee el valor text del EditText indicado, y lo transforma en un primitivo double
	 */
	public double obtenerValor(EditText et) {
		String strMonto = et.getText().toString();
		double d = Double.parseDouble(strMonto);
		return d;
	}
	
	/**
	 * Lee el valor de un EditText, y valida si se ingresó un número valido y además verifica que no sea negativo.
	 */
	public double validarValor(EditText et, String texto) {
		double valor = -1;
		try {
			valor = obtenerValor(et);
			if(valor < 0) {
				Toast.makeText(this, "Debe ingresar un valor positivo para " + texto, Toast.LENGTH_SHORT).show();
			}
		} catch(NumberFormatException e) {
			Toast.makeText(this, "Debe ingresar " + texto, Toast.LENGTH_SHORT).show();
		}
		return valor;
	}
	
	/**
	 * evento del botón a través de la propiedad onClick
	 */
	public void calcular(View view) {
		// Obtengo el valor del EditText de Monto y muestro mensajes si es inválido
		double dMonto = validarValor(etMonto, "Monto Total");
		// Si es negativo dejo de calcular (ya se mostró mensaje en validarValor)
		if(dMonto < 0)
			return;
		
		// Obtengo el valor del EditText de Propina y muestro mensajes si es inválido
		double dPropina = validarValor(etPropina, "Propina");
		// Si es negativo dejo de calcular (ya se mostró mensaje en validarValor)
		if(dPropina < 0)
			return;
		
		// Obtengo el valor del EditText de Personas y muestro mensajes si es inválido
		double dPersonas = validarValor(etPersonas, "Personas");
		// Si es negativo dejo de calcular (ya se mostró mensaje en validarValor)
		if(dPersonas < 0)
			return;
		
		// Valido que la cantidad de personas nunca sea cero
		if(dPersonas == 0) {
			Toast.makeText(this, "Alguien debe pagar", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// calculo el monto total a pagar, y lo que corresponde por persona
		double montoPropina = (dMonto * dPropina) / 100;
		double totalApagar = dMonto + montoPropina;
		double totalPorPersona = totalApagar / dPersonas;
		
		String mensaje = "Monto Total: " + totalApagar +
				"\nTotal por persona: " + totalPorPersona;
		
		Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
	}
}
