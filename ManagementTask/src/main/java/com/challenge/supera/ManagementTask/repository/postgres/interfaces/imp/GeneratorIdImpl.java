package com.challenge.supera.ManagementTask.repository.postgres.interfaces.imp;

import com.challenge.supera.ManagementTask.repository.postgres.interfaces.GeneratorId;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GeneratorIdImpl implements GeneratorId {

    @Override
    public String generatedListId() {
        Random random = new Random();
        int randomInt = 100 + random.nextInt(900);
        return "LISTA_" + randomInt;
    }

    @Override
    public String generatedItemId() {
        Random random = new Random();
        int randomInt = 100 + random.nextInt(900);
        return "ITEM_" + randomInt;
    }
}
