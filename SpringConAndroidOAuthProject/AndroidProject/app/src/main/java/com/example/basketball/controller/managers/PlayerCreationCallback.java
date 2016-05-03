package com.example.basketball.controller.managers;

import com.example.basketball.model.Player;

import java.util.List;

public interface PlayerCreationCallback {
    void onSuccess(Player playerList);

    void onFailure(Throwable t);
}
