package com.bilge.einkaufsliste //identification in appstore

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.app.AlertDialog
import android.widget.EditText
import android.text.InputType
import android.widget.Toast
import android.widget.AdapterView.OnItemLongClickListener

import androidx.appcompat.app.AppCompatActivity

import com.bilge.einkaufsliste.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() { //An acitivity is smth we see / one page = one acitivity
//MainActivity erbt von AppCompatActivity
//Klasse: Sammlung von variablen und Funktionen

    //variablen [noch leer und nicht verknüpft; erst wenn die App gestartet wurde dürfen wir diese Variablen verknüpfen
    private lateinit var binding: ActivityMainBinding //um auf startseite zuzugreifen;variable namens binding vom Typ ActivityMainBinding; private: variable wird nur in dieser Klasse verwendet ; lateinit: variable hat zu diesem Zeitpunkt keinen Wert

    private lateinit var lvTodoList: ListView //die liste wo wir in unserer activity alles anzeigen wollen
    private lateinit var fab: FloatingActionButton //der plus button
    private lateinit var shoppingItems: ArrayList <String> //wo wir alle sachen die wir in unserer Einkaufliste eintragen wollen speichern
    private lateinit var itemAdapter: ArrayAdapter<String> //adapter der die Brücke ist zwischen dieser lister und unserer visuellen Liste ;adapter um elemente der liste mit den elementen aus der listview verbinden

    override fun onCreate(savedInstanceState: Bundle?) { //funktion onCreate existiert immer/ befehl um zu sagen dass alles zwischen {}ausgeführt wird wenn die App startet

        super.onCreate(savedInstanceState)

        //verknüpfung erstellen

        binding = ActivityMainBinding.inflate(layoutInflater) //wertzuweisung von der Variable binding; binding wird mit main Activity/activity_main.xml/hauptansichtder app verbunden
        setContentView(binding.root)

        //verknüpfung erstellen

        lvTodoList = findViewById(R.id.lvTodoList)
        fab = findViewById(R.id.floatingActionButton)
        shoppingItems = ArrayList() //erstellen der textbasierten Liste


        shoppingItems.add("Apfel")
        shoppingItems.add("Banane")

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingItems) //this:mit dieser klasse; adapter nimmt textbasierte Liste und generiert daraus grafische Komponenten
        lvTodoList.adapter = itemAdapter //adapter der grafischen ListView zuordnen


        lvTodoList.onItemLongClickListener = OnItemLongClickListener { arg0, arg1, pos, id ->
            shoppingItems.removeAt(pos)
            itemAdapter.notifyDataSetChanged() //adapter informieren dass etwas verändert wurde!!DAMIT APP NICHT ABSTÜRZT
            Toast.makeText(applicationContext, "Element gelöscht", Toast.LENGTH_SHORT).show()
            true
        }




        fab.setOnClickListener { //wenn auf button geklickt wird
            var builder =AlertDialog.Builder(this)
            builder.setTitle("Hinzufügen")

            //input textfeld

            var input = EditText(this)
            input.hint = "Text eingeben"
            input.inputType = InputType.TYPE_CLASS_TEXT //was für einen input man möchte
            builder.setView(input) //element input hinzufügen

            builder.setPositiveButton("OK") { _, _ ->
                shoppingItems.add(input.text.toString())
            }//dem Builder einen Button hinzufügen



            builder.setNegativeButton("Abbrechen") { _, _ ->
                Toast.makeText(applicationContext, "Abgebrochen", Toast.LENGTH_SHORT).show()
            }






            builder.show();
        }


    }






}