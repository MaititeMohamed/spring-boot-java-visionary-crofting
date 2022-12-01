package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.entity.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {CommandService.class})
@ExtendWith(SpringExtension.class)
class CommandServiceTest {

    @MockBean
    private CommandService commandService;

    private Command command;

    @Test
    void addNewCommand ( ) {
    }

    @Test
    void getCommand ( ) {
    }

    @Test
    void deleteCommand ( ) {
    }

    @Test
    void updateCommand ( ) {
    }

    @Test
    void testUpdateCommand ( ) {
    }

    @Test
    void createCommand ( ) {
    }
}