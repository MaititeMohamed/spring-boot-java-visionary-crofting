package com.youcode.visionarycrofting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.youcode.visionarycrofting.classes.PasserCommande;
import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.repository.ClientRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ClientService.class})
@ExtendWith(SpringExtension.class)
class ClientServiceTest {
    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @MockBean
    private CommandService commandService;

    /**
     * Method under test: {@link ClientService#getClients()}
     */
    @Test
    void testGetClients() {
        ArrayList<Client> clientList = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(clientList);
        List<Client> actualClients = clientService.getClients();
        assertSame(clientList, actualClients);
        assertTrue(actualClients.isEmpty());
        verify(clientRepository).findAll();
    }

    /**
     * Method under test: {@link ClientService#getClients()}
     */
    @Test
    void testGetClients2() {
        when(clientRepository.findAll()).thenThrow(new IllegalStateException());
        assertThrows(IllegalStateException.class, () -> clientService.getClients());
        verify(clientRepository).findAll();
    }

    /**
     * Method under test: {@link ClientService#getOneById(Long)}
     */
    @Test
    void testGetOneById() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Client> actualOneById = clientService.getOneById(123L);
        assertSame(ofResult, actualOneById);
        assertTrue(actualOneById.isPresent());
        verify(clientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#getOneById(Long)}
     */
    @Test
    void testGetOneById2() {
        when(clientRepository.findById((Long) any())).thenThrow(new IllegalStateException());
        assertThrows(IllegalStateException.class, () -> clientService.getOneById(123L));
        verify(clientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#addClient(Client)}
     */
    @Test
    void testAddClient() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client1);
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findClientByEmail((String) any())).thenReturn(ofResult);

        Client client2 = new Client();
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        assertThrows(IllegalStateException.class, () -> clientService.addClient(client2));
        verify(clientRepository).findClientByEmail((String) any());
    }

    /**
     * Method under test: {@link ClientService#addClient(Client)}
     */
    @Test
    void testAddClient2() {
        Client client = new Client();
        client.setAddress("42 Main St");
        ArrayList<Command> commandList = new ArrayList<>();
        client.setCommandList(commandList);
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findClientByEmail((String) any())).thenReturn(Optional.empty());

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        clientService.addClient(client1);
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findClientByEmail((String) any());
        assertEquals("42 Main St", client1.getAddress());
        assertEquals("4105551212", client1.getPhone());
        assertEquals("iloveyou", client1.getPassword());
        assertEquals("Name", client1.getName());
        assertEquals(123L, client1.getId().longValue());
        assertEquals("jane.doe@example.org", client1.getEmail());
        assertEquals(commandList, client1.getCommandList());
    }

    /**
     * Method under test: {@link ClientService#addClient(Client)}
     */
    @Test
    void testAddClient3() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client1);
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findClientByEmail((String) any())).thenReturn(ofResult);
        Client client2 = mock(Client.class);
        when(client2.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(client2).setAddress((String) any());
        doNothing().when(client2).setCommandList((List<Command>) any());
        doNothing().when(client2).setEmail((String) any());
        doNothing().when(client2).setId((Long) any());
        doNothing().when(client2).setName((String) any());
        doNothing().when(client2).setPassword((String) any());
        doNothing().when(client2).setPhone((String) any());
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        assertThrows(IllegalStateException.class, () -> clientService.addClient(client2));
        verify(clientRepository).findClientByEmail((String) any());
        verify(client2).getEmail();
        verify(client2).setAddress((String) any());
        verify(client2).setCommandList((List<Command>) any());
        verify(client2).setEmail((String) any());
        verify(client2).setId((Long) any());
        verify(client2).setName((String) any());
        verify(client2).setPassword((String) any());
        verify(client2).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#deleteClient(Long)}
     */
    @Test
    void testDeleteClient() {
        doNothing().when(clientRepository).deleteById((Long) any());
        when(clientRepository.existsById((Long) any())).thenReturn(true);
        clientService.deleteClient(123L);
        verify(clientRepository).existsById((Long) any());
        verify(clientRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#deleteClient(Long)}
     */
    @Test
    void testDeleteClient2() {
        doThrow(new IllegalStateException()).when(clientRepository).deleteById((Long) any());
        when(clientRepository.existsById((Long) any())).thenReturn(true);
        assertThrows(IllegalStateException.class, () -> clientService.deleteClient(123L));
        verify(clientRepository).existsById((Long) any());
        verify(clientRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#deleteClient(Long)}
     */
    @Test
    void testDeleteClient3() {
        doNothing().when(clientRepository).deleteById((Long) any());
        when(clientRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> clientService.deleteClient(123L));
        verify(clientRepository).existsById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#deleteClient(Long)}
     */
    @Test
    void testDeleteClient4() {
        when(clientRepository.existsById((Long) any())).thenThrow(new IllegalStateException());
        assertThrows(IllegalStateException.class, () -> clientService.deleteClient(123L));
        verify(clientRepository).existsById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Client client2 = new Client();
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        Client actualUpdateClientResult = clientService.updateClient(client2);
        assertSame(client, actualUpdateClientResult);
        assertEquals("42 Main St", actualUpdateClientResult.getAddress());
        assertEquals("4105551212", actualUpdateClientResult.getPhone());
        assertEquals("iloveyou", actualUpdateClientResult.getPassword());
        assertEquals("Name", actualUpdateClientResult.getName());
        assertEquals("jane.doe@example.org", actualUpdateClientResult.getEmail());
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient2() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);
        when(clientRepository.save((Client) any())).thenThrow(new IllegalStateException());
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        assertThrows(IllegalStateException.class, () -> clientService.updateClient(client1));
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient3() {
        Client client = mock(Client.class);
        doNothing().when(client).setAddress((String) any());
        doNothing().when(client).setCommandList((List<Command>) any());
        doNothing().when(client).setEmail((String) any());
        doNothing().when(client).setId((Long) any());
        doNothing().when(client).setName((String) any());
        doNothing().when(client).setPassword((String) any());
        doNothing().when(client).setPhone((String) any());
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Client client2 = new Client();
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        clientService.updateClient(client2);
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
        verify(client, atLeast(1)).setAddress((String) any());
        verify(client).setCommandList((List<Command>) any());
        verify(client, atLeast(1)).setEmail((String) any());
        verify(client).setId((Long) any());
        verify(client, atLeast(1)).setName((String) any());
        verify(client, atLeast(1)).setPassword((String) any());
        verify(client, atLeast(1)).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateClient4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.youcode.visionarycrofting.service.ClientService.updateClient(ClientService.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findById((Long) any())).thenReturn(null);
        Client client1 = mock(Client.class);
        doNothing().when(client1).setAddress((String) any());
        doNothing().when(client1).setCommandList((List<Command>) any());
        doNothing().when(client1).setEmail((String) any());
        doNothing().when(client1).setId((Long) any());
        doNothing().when(client1).setName((String) any());
        doNothing().when(client1).setPassword((String) any());
        doNothing().when(client1).setPhone((String) any());
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");

        Client client2 = new Client();
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        clientService.updateClient(client2);
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient5() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findById((Long) any())).thenReturn(Optional.empty());
        Client client1 = mock(Client.class);
        doNothing().when(client1).setAddress((String) any());
        doNothing().when(client1).setCommandList((List<Command>) any());
        doNothing().when(client1).setEmail((String) any());
        doNothing().when(client1).setId((Long) any());
        doNothing().when(client1).setName((String) any());
        doNothing().when(client1).setPassword((String) any());
        doNothing().when(client1).setPhone((String) any());
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");

        Client client2 = new Client();
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        assertThrows(IllegalStateException.class, () -> clientService.updateClient(client2));
        verify(clientRepository).findById((Long) any());
        verify(client1).setAddress((String) any());
        verify(client1).setCommandList((List<Command>) any());
        verify(client1).setEmail((String) any());
        verify(client1).setId((Long) any());
        verify(client1).setName((String) any());
        verify(client1).setPassword((String) any());
        verify(client1).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient6() {
        Client client = mock(Client.class);
        doNothing().when(client).setAddress((String) any());
        doNothing().when(client).setCommandList((List<Command>) any());
        doNothing().when(client).setEmail((String) any());
        doNothing().when(client).setId((Long) any());
        doNothing().when(client).setName((String) any());
        doNothing().when(client).setPassword((String) any());
        doNothing().when(client).setPhone((String) any());
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);
        Client client2 = mock(Client.class);
        when(client2.getPhone()).thenReturn("4105551212");
        when(client2.getId()).thenReturn(123L);
        when(client2.getAddress()).thenReturn("42 Main St");
        when(client2.getEmail()).thenReturn("jane.doe@example.org");
        when(client2.getName()).thenReturn("Name");
        when(client2.getPassword()).thenReturn("iloveyou");
        doNothing().when(client2).setAddress((String) any());
        doNothing().when(client2).setCommandList((List<Command>) any());
        doNothing().when(client2).setEmail((String) any());
        doNothing().when(client2).setId((Long) any());
        doNothing().when(client2).setName((String) any());
        doNothing().when(client2).setPassword((String) any());
        doNothing().when(client2).setPhone((String) any());
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        clientService.updateClient(client2);
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
        verify(client, atLeast(1)).setAddress((String) any());
        verify(client).setCommandList((List<Command>) any());
        verify(client, atLeast(1)).setEmail((String) any());
        verify(client).setId((Long) any());
        verify(client, atLeast(1)).setName((String) any());
        verify(client, atLeast(1)).setPassword((String) any());
        verify(client, atLeast(1)).setPhone((String) any());
        verify(client2).getId();
        verify(client2, atLeast(1)).getAddress();
        verify(client2, atLeast(1)).getEmail();
        verify(client2, atLeast(1)).getName();
        verify(client2, atLeast(1)).getPassword();
        verify(client2).getPhone();
        verify(client2).setAddress((String) any());
        verify(client2).setCommandList((List<Command>) any());
        verify(client2).setEmail((String) any());
        verify(client2).setId((Long) any());
        verify(client2).setName((String) any());
        verify(client2).setPassword((String) any());
        verify(client2).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient7() {
        Client client = mock(Client.class);
        doNothing().when(client).setAddress((String) any());
        doNothing().when(client).setCommandList((List<Command>) any());
        doNothing().when(client).setEmail((String) any());
        doNothing().when(client).setId((Long) any());
        doNothing().when(client).setName((String) any());
        doNothing().when(client).setPassword((String) any());
        doNothing().when(client).setPhone((String) any());
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);
        Client client2 = mock(Client.class);
        when(client2.getPhone()).thenThrow(new IllegalStateException());
        when(client2.getId()).thenReturn(123L);
        when(client2.getAddress()).thenReturn("42 Main St");
        when(client2.getEmail()).thenReturn("jane.doe@example.org");
        when(client2.getName()).thenReturn("Name");
        when(client2.getPassword()).thenReturn("iloveyou");
        doNothing().when(client2).setAddress((String) any());
        doNothing().when(client2).setCommandList((List<Command>) any());
        doNothing().when(client2).setEmail((String) any());
        doNothing().when(client2).setId((Long) any());
        doNothing().when(client2).setName((String) any());
        doNothing().when(client2).setPassword((String) any());
        doNothing().when(client2).setPhone((String) any());
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        assertThrows(IllegalStateException.class, () -> clientService.updateClient(client2));
        verify(clientRepository).findById((Long) any());
        verify(client).setAddress((String) any());
        verify(client).setCommandList((List<Command>) any());
        verify(client, atLeast(1)).setEmail((String) any());
        verify(client).setId((Long) any());
        verify(client, atLeast(1)).setName((String) any());
        verify(client, atLeast(1)).setPassword((String) any());
        verify(client).setPhone((String) any());
        verify(client2).getId();
        verify(client2, atLeast(1)).getEmail();
        verify(client2, atLeast(1)).getName();
        verify(client2, atLeast(1)).getPassword();
        verify(client2).getPhone();
        verify(client2).setAddress((String) any());
        verify(client2).setCommandList((List<Command>) any());
        verify(client2).setEmail((String) any());
        verify(client2).setId((Long) any());
        verify(client2).setName((String) any());
        verify(client2).setPassword((String) any());
        verify(client2).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient8() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findById((Long) any())).thenReturn(Optional.empty());
        Client client1 = mock(Client.class);
        doNothing().when(client1).setAddress((String) any());
        doNothing().when(client1).setCommandList((List<Command>) any());
        doNothing().when(client1).setEmail((String) any());
        doNothing().when(client1).setId((Long) any());
        doNothing().when(client1).setName((String) any());
        doNothing().when(client1).setPassword((String) any());
        doNothing().when(client1).setPhone((String) any());
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        Client client2 = mock(Client.class);
        when(client2.getPhone()).thenReturn("4105551212");
        when(client2.getId()).thenReturn(123L);
        when(client2.getAddress()).thenReturn("42 Main St");
        when(client2.getEmail()).thenReturn("jane.doe@example.org");
        when(client2.getName()).thenReturn("Name");
        when(client2.getPassword()).thenReturn("iloveyou");
        doNothing().when(client2).setAddress((String) any());
        doNothing().when(client2).setCommandList((List<Command>) any());
        doNothing().when(client2).setEmail((String) any());
        doNothing().when(client2).setId((Long) any());
        doNothing().when(client2).setName((String) any());
        doNothing().when(client2).setPassword((String) any());
        doNothing().when(client2).setPhone((String) any());
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        assertThrows(IllegalStateException.class, () -> clientService.updateClient(client2));
        verify(clientRepository).findById((Long) any());
        verify(client1).setAddress((String) any());
        verify(client1).setCommandList((List<Command>) any());
        verify(client1).setEmail((String) any());
        verify(client1).setId((Long) any());
        verify(client1).setName((String) any());
        verify(client1).setPassword((String) any());
        verify(client1).setPhone((String) any());
        verify(client2, atLeast(1)).getId();
        verify(client2).setAddress((String) any());
        verify(client2).setCommandList((List<Command>) any());
        verify(client2).setEmail((String) any());
        verify(client2).setId((Long) any());
        verify(client2).setName((String) any());
        verify(client2).setPassword((String) any());
        verify(client2).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient9() {
        Client client = mock(Client.class);
        doNothing().when(client).setAddress((String) any());
        doNothing().when(client).setCommandList((List<Command>) any());
        doNothing().when(client).setEmail((String) any());
        doNothing().when(client).setId((Long) any());
        doNothing().when(client).setName((String) any());
        doNothing().when(client).setPassword((String) any());
        doNothing().when(client).setPhone((String) any());
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);
        Client client2 = mock(Client.class);
        when(client2.getPhone()).thenReturn("4105551212");
        when(client2.getId()).thenReturn(123L);
        when(client2.getAddress()).thenReturn(null);
        when(client2.getEmail()).thenReturn("jane.doe@example.org");
        when(client2.getName()).thenReturn("Name");
        when(client2.getPassword()).thenReturn("iloveyou");
        doNothing().when(client2).setAddress((String) any());
        doNothing().when(client2).setCommandList((List<Command>) any());
        doNothing().when(client2).setEmail((String) any());
        doNothing().when(client2).setId((Long) any());
        doNothing().when(client2).setName((String) any());
        doNothing().when(client2).setPassword((String) any());
        doNothing().when(client2).setPhone((String) any());
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        clientService.updateClient(client2);
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
        verify(client).setAddress((String) any());
        verify(client).setCommandList((List<Command>) any());
        verify(client, atLeast(1)).setEmail((String) any());
        verify(client).setId((Long) any());
        verify(client, atLeast(1)).setName((String) any());
        verify(client, atLeast(1)).setPassword((String) any());
        verify(client, atLeast(1)).setPhone((String) any());
        verify(client2).getId();
        verify(client2).getAddress();
        verify(client2, atLeast(1)).getEmail();
        verify(client2, atLeast(1)).getName();
        verify(client2, atLeast(1)).getPassword();
        verify(client2).getPhone();
        verify(client2).setAddress((String) any());
        verify(client2).setCommandList((List<Command>) any());
        verify(client2).setEmail((String) any());
        verify(client2).setId((Long) any());
        verify(client2).setName((String) any());
        verify(client2).setPassword((String) any());
        verify(client2).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(Client)}
     */
    @Test
    void testUpdateClient10() {
        Client client = mock(Client.class);
        doNothing().when(client).setAddress((String) any());
        doNothing().when(client).setCommandList((List<Command>) any());
        doNothing().when(client).setEmail((String) any());
        doNothing().when(client).setId((Long) any());
        doNothing().when(client).setName((String) any());
        doNothing().when(client).setPassword((String) any());
        doNothing().when(client).setPhone((String) any());
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);
        Client client2 = mock(Client.class);
        when(client2.getPhone()).thenReturn("4105551212");
        when(client2.getId()).thenReturn(123L);
        when(client2.getAddress()).thenReturn("42 Main St");
        when(client2.getEmail()).thenReturn(null);
        when(client2.getName()).thenReturn("Name");
        when(client2.getPassword()).thenReturn("iloveyou");
        doNothing().when(client2).setAddress((String) any());
        doNothing().when(client2).setCommandList((List<Command>) any());
        doNothing().when(client2).setEmail((String) any());
        doNothing().when(client2).setId((Long) any());
        doNothing().when(client2).setName((String) any());
        doNothing().when(client2).setPassword((String) any());
        doNothing().when(client2).setPhone((String) any());
        client2.setAddress("42 Main St");
        client2.setCommandList(new ArrayList<>());
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhone("4105551212");
        clientService.updateClient(client2);
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
        verify(client, atLeast(1)).setAddress((String) any());
        verify(client).setCommandList((List<Command>) any());
        verify(client).setEmail((String) any());
        verify(client).setId((Long) any());
        verify(client, atLeast(1)).setName((String) any());
        verify(client, atLeast(1)).setPassword((String) any());
        verify(client, atLeast(1)).setPhone((String) any());
        verify(client2).getId();
        verify(client2, atLeast(1)).getAddress();
        verify(client2).getEmail();
        verify(client2, atLeast(1)).getName();
        verify(client2, atLeast(1)).getPassword();
        verify(client2).getPhone();
        verify(client2).setAddress((String) any());
        verify(client2).setCommandList((List<Command>) any());
        verify(client2).setEmail((String) any());
        verify(client2).setId((Long) any());
        verify(client2).setName((String) any());
        verify(client2).setPassword((String) any());
        verify(client2).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#addCommand(Command, Long)}
     */
    @Test
    void testAddCommand() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Command command = new Command();
        command.setAddress("42 Main St");
        command.setDateTime("2020-03-01");
        command.setId(123L);
        command.setListItem(new ArrayList<>());
        command.setRef("Ref");
        command.setTotalPrice(1);
        assertSame(client, clientService.addCommand(command, 123L));
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#addCommand(Command, Long)}
     */
    @Test
    void testAddCommand2() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);
        when(clientRepository.save((Client) any())).thenThrow(new IllegalStateException());
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Command command = new Command();
        command.setAddress("42 Main St");
        command.setDateTime("2020-03-01");
        command.setId(123L);
        command.setListItem(new ArrayList<>());
        command.setRef("Ref");
        command.setTotalPrice(1);
        assertThrows(IllegalStateException.class, () -> clientService.addCommand(command, 123L));
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#addCommand(Command, Long)}
     */
    @Test
    void testAddCommand3() {
        Client client = mock(Client.class);
        doNothing().when(client).setAddress((String) any());
        doNothing().when(client).setCommand((Command) any());
        doNothing().when(client).setCommandList((List<Command>) any());
        doNothing().when(client).setEmail((String) any());
        doNothing().when(client).setId((Long) any());
        doNothing().when(client).setName((String) any());
        doNothing().when(client).setPassword((String) any());
        doNothing().when(client).setPhone((String) any());
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Command command = new Command();
        command.setAddress("42 Main St");
        command.setDateTime("2020-03-01");
        command.setId(123L);
        command.setListItem(new ArrayList<>());
        command.setRef("Ref");
        command.setTotalPrice(1);
        clientService.addCommand(command, 123L);
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
        verify(client).setAddress((String) any());
        verify(client).setCommand((Command) any());
        verify(client).setCommandList((List<Command>) any());
        verify(client).setEmail((String) any());
        verify(client).setId((Long) any());
        verify(client).setName((String) any());
        verify(client).setPassword((String) any());
        verify(client).setPhone((String) any());
    }

    /**
     * Method under test: {@link ClientService#addCommand(Command, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCommand4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.youcode.visionarycrofting.service.ClientService.addCommand(ClientService.java:82)
        //   See https://diff.blue/R013 to resolve this issue.

        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findById((Long) any())).thenReturn(Optional.empty());
        Client client1 = mock(Client.class);
        doNothing().when(client1).setAddress((String) any());
        doNothing().when(client1).setCommand((Command) any());
        doNothing().when(client1).setCommandList((List<Command>) any());
        doNothing().when(client1).setEmail((String) any());
        doNothing().when(client1).setId((Long) any());
        doNothing().when(client1).setName((String) any());
        doNothing().when(client1).setPassword((String) any());
        doNothing().when(client1).setPhone((String) any());
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");

        Command command = new Command();
        command.setAddress("42 Main St");
        command.setDateTime("2020-03-01");
        command.setId(123L);
        command.setListItem(new ArrayList<>());
        command.setRef("Ref");
        command.setTotalPrice(1);
        clientService.addCommand(command, 123L);
    }

    /**
     * Method under test: {@link ClientService#passerCommande(Long, Collection)}
     */
    @Test
    void testPasserCommande() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Command command = new Command();
        command.setAddress("42 Main St");
        command.setDateTime("2020-03-01");
        command.setId(123L);
        command.setListItem(new ArrayList<>());
        command.setRef("Ref");
        command.setTotalPrice(1);
        when(commandService.createCommand((Collection<PasserCommande>) any())).thenReturn(command);
        assertSame(client, clientService.passerCommande(123L, new ArrayList<>()));
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
        verify(commandService).createCommand((Collection<PasserCommande>) any());
    }

    /**
     * Method under test: {@link ClientService#passerCommande(Long, Collection)}
     */
    @Test
    void testPasserCommande2() {
        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);
        when(commandService.createCommand((Collection<PasserCommande>) any())).thenThrow(new IllegalStateException());
        assertThrows(IllegalStateException.class, () -> clientService.passerCommande(123L, new ArrayList<>()));
        verify(commandService).createCommand((Collection<PasserCommande>) any());
    }

    /**
     * Method under test: {@link ClientService#passerCommande(Long, Collection)}
     */
    @Test
    void testPasserCommande3() {
        Client client = mock(Client.class);
        doNothing().when(client).setAddress((String) any());
        doNothing().when(client).setCommand((Command) any());
        doNothing().when(client).setCommandList((List<Command>) any());
        doNothing().when(client).setEmail((String) any());
        doNothing().when(client).setId((Long) any());
        doNothing().when(client).setName((String) any());
        doNothing().when(client).setPassword((String) any());
        doNothing().when(client).setPhone((String) any());
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Command command = new Command();
        command.setAddress("42 Main St");
        command.setDateTime("2020-03-01");
        command.setId(123L);
        command.setListItem(new ArrayList<>());
        command.setRef("Ref");
        command.setTotalPrice(1);
        when(commandService.createCommand((Collection<PasserCommande>) any())).thenReturn(command);
        clientService.passerCommande(123L, new ArrayList<>());
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
        verify(client).setAddress((String) any());
        verify(client).setCommand((Command) any());
        verify(client).setCommandList((List<Command>) any());
        verify(client).setEmail((String) any());
        verify(client).setId((Long) any());
        verify(client).setName((String) any());
        verify(client).setPassword((String) any());
        verify(client).setPhone((String) any());
        verify(commandService).createCommand((Collection<PasserCommande>) any());
    }

    /**
     * Method under test: {@link ClientService#passerCommande(Long, Collection)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPasserCommande4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.youcode.visionarycrofting.service.ClientService.addCommand(ClientService.java:82)
        //       at com.youcode.visionarycrofting.service.ClientService.passerCommande(ClientService.java:90)
        //   See https://diff.blue/R013 to resolve this issue.

        Client client = new Client();
        client.setAddress("42 Main St");
        client.setCommandList(new ArrayList<>());
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhone("4105551212");
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findById((Long) any())).thenReturn(Optional.empty());
        Client client1 = mock(Client.class);
        doNothing().when(client1).setAddress((String) any());
        doNothing().when(client1).setCommand((Command) any());
        doNothing().when(client1).setCommandList((List<Command>) any());
        doNothing().when(client1).setEmail((String) any());
        doNothing().when(client1).setId((Long) any());
        doNothing().when(client1).setName((String) any());
        doNothing().when(client1).setPassword((String) any());
        doNothing().when(client1).setPhone((String) any());
        client1.setAddress("42 Main St");
        client1.setCommandList(new ArrayList<>());
        client1.setEmail("jane.doe@example.org");
        client1.setId(123L);
        client1.setName("Name");
        client1.setPassword("iloveyou");
        client1.setPhone("4105551212");

        Command command = new Command();
        command.setAddress("42 Main St");
        command.setDateTime("2020-03-01");
        command.setId(123L);
        command.setListItem(new ArrayList<>());
        command.setRef("Ref");
        command.setTotalPrice(1);
        when(commandService.createCommand((Collection<PasserCommande>) any())).thenReturn(command);
        clientService.passerCommande(123L, new ArrayList<>());
    }
}

