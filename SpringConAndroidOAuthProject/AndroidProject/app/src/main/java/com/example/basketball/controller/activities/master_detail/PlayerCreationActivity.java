package com.example.basketball.controller.activities.master_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerCallback;
import com.example.basketball.controller.managers.PlayerCreationCallback;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.controller.managers.UserLoginManager;
import com.example.basketball.controller.services.PlayerService;
import com.example.basketball.model.Player;

import java.util.List;

import retrofit2.Call;

public class PlayerCreationActivity extends AppCompatActivity implements PlayerCreationCallback {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        // Spinner values
        Spinner spinner = (Spinner) findViewById(R.id.player_posicion);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.player_positions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        final EditText playerName = (EditText) findViewById(R.id.player_name);
        final EditText playerBaskets = (EditText) findViewById(R.id.player_baskets);
        final EditText playerRebotes = (EditText) findViewById(R.id.player_rebotes);
        final EditText playerAsistencias = (EditText) findViewById(R.id.player_asistencias);
        final Spinner playerPosicion = (Spinner) findViewById(R.id.player_posicion);



        // Button enviar
        Button button = (Button) findViewById(R.id.button_enviar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Player player = new Player(
                            null,
                            playerName.getText().toString(),
                            Integer.parseInt(playerBaskets.getText().toString()),
                            Integer.parseInt(playerRebotes.getText().toString()),
                            Integer.parseInt(playerAsistencias.getText().toString()),
                            (String) playerPosicion.getSelectedItem()
                    );
                    PlayerManager.getInstance(v.getContext()).createPlayer(player, PlayerCreationActivity.this);
                }catch (Exception e){
                    Toast toast = Toast.makeText(PlayerCreationActivity.this, "No introduzcas texto en campos de números", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


        Button buttonSalir = (Button) findViewById(R.id.button_salir);
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerCreationActivity.this.onBackPressed();
            }
        });
    }


    @Override
    public void onSuccess(Player player) {
        Toast toast = Toast.makeText(this, "Se ha creado el jugador", Toast.LENGTH_LONG);
        toast.show();
    }


    @Override
    public void onFailure(Throwable t) {
        Toast toast = Toast.makeText(this, "Error al crear el juegador", Toast.LENGTH_LONG);
        toast.show();
    }
}
