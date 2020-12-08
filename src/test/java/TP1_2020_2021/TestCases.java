package TP1_2020_2021;

import base.Address;
import base.Customer;
import base.Person;
import order.base.OrderStatus;
import order.exceptions.ContainerException;
import order.exceptions.OrderException;
import order.exceptions.PositionException;
import order.packing.Color;
import order.shippingorder.ShippingOrder;
import org.junit.jupiter.api.Test;
import packing.Container;
import packing.Item;
import packing.Position;

import static org.junit.jupiter.api.Assertions.*;

class TestCases {

    /**
     * Teste da funcionalidade adicionar item, numa situação válida
     */
    @Test
    void tc1_ecp1_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10);
        Item item1 = new Item(5, 2, 2, "item1", "");

        try {
            Position pos1 = new Position(1, 1, 1);

            float volume = cont1.getOccupiedVolume();

            assertTrue(cont1.addItem(item1, pos1, Color.aqua));
            assertEquals(volume + 5*2*2, cont1.getOccupiedVolume());
            assertNotNull(cont1.getItem("item1"));

        } catch (PositionException | ContainerException e) {}

    }

    /**
     * Teste da funcionalidade adicionar item (item já existe no contentor e o contentor não está fechado)
     */
    @Test
    void tc2_ecp2_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10);
        Item item1 = new Item(5, 2, 2, "item1", "");

        try {
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.black);

            assertFalse(cont1.addItem(item1, pos1, Color.aqua));

        } catch (PositionException | ContainerException e) {}
    }

    /**
     * Teste da funcionalidade adicionar item (item não existe no contentor e o contentor está fechado)
     */
    @Test
    void tc3_ecp3_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10);
        Item item1 = new Item(5, 2, 2, "item1", "");

        try {
            Position pos1 = new Position(1, 1, 1);

            cont1.close();

            assertThrows(ContainerException.class, () -> cont1.addItem(item1, pos1, Color.aqua));

        } catch (PositionException | ContainerException e) {}
    }

    /**
     * Teste da funcionalidade adicionar item com os 3 parâmetros nulos
     */
    @Test
    void tc4_bva1_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10, 4);
        assertThrows(ContainerException.class, () -> cont1.addItem(null, null, null));
    }

    /**
     * Teste da funcionalidade adicionar item com parâmetros nulos exceto o item
     */
    @Test
    void tc5_bva2_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10, 4);
        Item item1 = new Item(2, 3, 4, "item1", "");
        assertThrows(ContainerException.class, () -> cont1.addItem(item1, null, null));
    }

    /**
     * Teste da funcionalidade adicionar item com um parâmetro null (color)
     */
    @Test
    void tc6_bva3_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10, 4);
        Item item1 = new Item(2, 3, 4, "item1", "");

        try {
            Position pos1 = new Position(1, 1, 1);
            assertThrows(ContainerException.class, () -> cont1.addItem(item1, pos1, null));
        } catch (PositionException e) {}
    }

    /**
     * Teste da funcionalidade adicionar um item numa coleção vazia.
     */
    @Test
    void tc7_bva4_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10, 4);
        Item item1 = new Item(5, 2, 2, "item1", "");

        try {
            Position pos1 = new Position(1, 1, 1);

            float volume = cont1.getOccupiedVolume();

            assertTrue(cont1.addItem(item1, pos1, Color.black));
            assertEquals(volume + 5*2*2, cont1.getOccupiedVolume());
            assertNotNull(cont1.getItem("item1"));

        } catch (PositionException | ContainerException e) {}
    }

    /**
     * Teste da funcionalidade adicionar um item numa coleção cheia.
     */
    @Test
    void tc8_bva5_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 2, 2, "item1", "");
        Item item2 = new Item(2, 2, 2, "item2", "");
        Item item3 = new Item(5, 3, 6, "item3", "");
        Item item4 = new Item(4, 1, 2, "item4", "");
        try {
            Position pos1 = new Position(1, 1, 1);
            Position pos2 = new Position(2, 2, 2);
            Position pos3 = new Position(3, 3, 3);
            Position pos4 = new Position(4, 4, 4);

            cont1.addItem(item1, pos1, Color.aqua);
            cont1.addItem(item2, pos2, Color.green);
            cont1.addItem(item3, pos3, Color.black);
            float volume = cont1.getOccupiedVolume();

            assertTrue(cont1.addItem(item4, pos4, Color.gray));
            assertEquals(volume + 4*1*2, cont1.getOccupiedVolume());
            assertNotNull(cont1.getItem("item4"));

        } catch (PositionException | ContainerException e) {}
    }

    /**
     * Teste da funcionalidade adicionar um item numa coleção semi-preenchida.
     */
    @Test
    void tc9_bva6_testeAdicionarItem() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 2, 2, "item1", "");
        Item item2 = new Item(2, 2, 2, "item2", "");
        Item item3 = new Item(5, 3, 6, "item3", "");
        try {

            Position pos1 = new Position(1, 1, 1);
            Position pos2 = new Position(2, 2, 2);
            Position pos3 = new Position(3, 3, 3);

            cont1.addItem(item1, pos1, Color.aqua);
            cont1.addItem(item2, pos2, Color.green);
            float volume = cont1.getOccupiedVolume();

            assertTrue(cont1.addItem(item3, pos3, Color.gray));
            assertEquals(volume + 5*3*6, cont1.getOccupiedVolume());
            assertNotNull(cont1.getItem("item3"));

        } catch (PositionException | ContainerException e) {}
    }

    //------------------------------------------------------------------

    /**
     * Teste da funcionalidade remover item (contentor não está fechado e o item existe no contentor)
     */
    @Test
    void tc10_ecp1_testeRemoverItem() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "A");
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.aqua);
            float volume = cont1.getOccupiedVolume();

            assertTrue(cont1.removeItem(item1));
            assertEquals(volume - 2*2*2, cont1.getOccupiedVolume());
            assertNull(cont1.getItem("item1"));

        } catch (PositionException | ContainerException e) {
        }
    }

    /**
     * Teste da funcionalidade remover item (contentor está fechado e o item existe no contentor)
     */
    @Test
    void tc11_ecp2_testeRemoverItem() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.aqua);
            cont1.close();

            assertThrows(ContainerException.class, () -> cont1.removeItem(item1));

        } catch (PositionException | ContainerException e) {
        }
    }

    /**
     * Teste da funcionalidade remover item (contentor não está fechado e o item não existe no contentor)
     */
    @Test
    void tc12_ecp3_testeRemoverItem() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");

            assertFalse(cont1.removeItem(item1));

        } catch (ContainerException e) {
        }
    }

    /**
     * Teste da funcionalidade remover item com parâmetro null
     */
    @Test
    void tc13_bva1_testeRemoverItem() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);

        assertThrows(ContainerException.class, () -> cont1.removeItem(null));
    }

    /**
     * Teste da funcionalidade remover item de uma coleção com apenas um item
     */
    @Test
    void tc14_bva2_testeRemoverItem() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.fuchsia);
            float volume = cont1.getOccupiedVolume();

            assertTrue(cont1.removeItem(item1));
            assertEquals(volume - 2*2*2, cont1.getOccupiedVolume());
            assertNull(cont1.getItem("item1"));

        } catch (PositionException | ContainerException e) {
        }
    }

    /**
     * Teste da funcionalidade remover item de uma coleção cheia
     */
    @Test
    void tc15_bva3_testeRemoverItem() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Item item2 = new Item(3, 3, 3, "item2", "");
            Item item3 = new Item(4, 4, 4, "item3", "");
            Position pos1 = new Position(1, 1, 1);
            Position pos2 = new Position(1, 2, 1);
            Position pos3 = new Position(1, 1, 3);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.addItem(item2, pos2, Color.fuchsia);
            cont1.addItem(item3, pos3, Color.fuchsia);
            float volume = cont1.getOccupiedVolume();

            assertTrue(cont1.removeItem(item1));
            assertEquals(volume - 2*2*2, cont1.getOccupiedVolume());
            assertNull(cont1.getItem("item1"));

        } catch (PositionException | ContainerException e) {
        }
    }

    /**
     * Teste da funcionalidade remover item de uma coleção semi-preenchida
     */
    @Test
    void tc16_bva4_testeRemoverItem() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Item item2 = new Item(3, 3, 3, "item2", "");
            Position pos1 = new Position(1, 1, 1);
            Position pos2 = new Position(1, 2, 1);


            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.addItem(item2, pos2, Color.fuchsia);
            float volume = cont1.getOccupiedVolume();

            assertTrue(cont1.removeItem(item1));
            assertEquals(volume - 2*2*2, cont1.getOccupiedVolume());
            assertNull(cont1.getItem("item1"));

        } catch (PositionException | ContainerException e) {
        }
    }

    //------------------------------------------------------------------

    /**
     * Teste da funcionalidade adicionar contentor numa situação válida
     */
    @Test
    void tc17_ecp1_testeAdicionarContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertTrue(ship.addContainer(cont1));
            assertTrue(ship.existsContainer(cont1));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade adicionar contentor numa encomenda com estado != IN_TREATMENT
     */
    @Test
    void tc18_ecp2_testeAdicionarContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            assertThrows(OrderException.class, () -> ship.addContainer(cont1));

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade adicionar contentor que não está fechado
     */
    @Test
    void tc19_ecp3_testeAdicionarContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.fuchsia);

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertThrows(ContainerException.class, () -> ship.addContainer(cont1));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade adicionar contentor que já existe na encomenda
     */
    @Test
    void tc20_ecp4_testeAdicionarContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);
            ship.addContainer(cont1);

            assertFalse(ship.addContainer(cont1));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade adicionar contentor com parâmetro null
     */
    @Test
    void tc21_bva1_testeAdicionarContentor() {
        try {
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertThrows(ContainerException.class, () -> ship.addContainer(null));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade adicionar contentor numa coleção vazia
     */
    @Test
    void tc22_bva2_testeAdicionarContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);
            Position pos1 = new Position(1, 1, 1);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertTrue(ship.addContainer(cont1));
            assertTrue(ship.existsContainer(cont1));

        } catch (ContainerException | PositionException | OrderException e) {
        }
    }

    //------------------------------------------------------------------

    /**
     * Teste da funcionalidade remover contentor numa situação válida
     */
    @Test
    void tc23_ecp1_testeRemoverContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);
            ship.addContainer(cont1);

            assertTrue(ship.removeContainer(cont1));
            assertFalse(ship.existsContainer(cont1));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade remover contentor numa encomenda com estado != IN_TREATMENT
     */
    @Test
    void tc24_ecp2_testeRemoverContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);
            ship.addContainer(cont1);
            ship.setStatus(OrderStatus.CLOSED);

            assertThrows(OrderException.class, () -> ship.removeContainer(cont1));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade remover contentor que não existe na encomenda
     */
    @Test
    void tc25_ecp3_testeRemoverContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertFalse(ship.removeContainer(cont1));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade remover contentor com parâmetro null
     */
    @Test
    void tc26_bva1_testeRemoverContentor() {
        try {
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertThrows(ContainerException.class, () -> ship.removeContainer(null));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade remover contentor de uma coleção com apenas um contentor
     */
    @Test
    void tc27_bva2_testeRemoverContentor() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);
            ship.addContainer(cont1);

            assertTrue(ship.removeContainer(cont1));
            assertFalse(ship.existsContainer(cont1));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    //------------------------------------------------------------------

    /**
     * Teste da funcionalidade alterar estado da encomenda para IN_TREATMENT numa situação válida
     */
    @Test
    void tc28_ecp1_testeAlterarEstadoEncomenda() {
        Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
        Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
        Customer customer = new Customer("John Doe", add1, add1);
        Person destination = new Person("Jane Doe", add2);
        ShippingOrder ship = new ShippingOrder(customer, destination);

        assertDoesNotThrow(() -> ship.setStatus(OrderStatus.IN_TREATMENT));
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda para IN_TREATMENT numa situação inválida
     */
    @Test
    void tc29_ecp2_testeAlterarEstadoEncomenda() {
        try {
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertThrows(OrderException.class, () -> ship.setStatus(OrderStatus.IN_TREATMENT));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda para CLOSED numa situação válida
     */
    @Test
    void tc30_ecp3_testeAlterarEstadoEncomenda() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);
            ship.addContainer(cont1);

            assertDoesNotThrow(() -> ship.setStatus(OrderStatus.CLOSED));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda para CLOSED numa situação inválida
     */
    @Test
    void tc31_ecp4_testeAlterarEstadoEncomenda() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);
            ship.addContainer(cont1);
            ship.setStatus(OrderStatus.CLOSED);

            assertThrows(OrderException.class, () -> ship.setStatus(OrderStatus.CLOSED));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda para CLOSED numa situação inválida (não existem contentores)
     */
    @Test
    void tc32_ecp5_testeAlterarEstadoEncomenda() {
        try {
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertThrows(OrderException.class, () -> ship.setStatus(OrderStatus.CLOSED));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda para SHIPED numa situação válida
     */
    @Test
    void tc33_ecp6_testeAlterarEstadoEncomenda() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);
            ship.addContainer(cont1);
            ship.setStatus(OrderStatus.CLOSED);

            assertDoesNotThrow(() -> ship.setStatus(OrderStatus.SHIPPED));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda para SHIPED numa situação inválida
     */
    @Test
    void tc34_ecp7_testeAlterarEstadoEncomenda() {
        try {
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertThrows(OrderException.class, () -> ship.setStatus(OrderStatus.SHIPPED));

        } catch (ContainerException | PositionException | OrderException e) {}
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda com parâmetro null
     */
    @Test
    void tc35_bva1_testeAlterarEstadoEncomenda() {
        Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
        Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
        Customer customer = new Customer("John Doe", add1, add1);
        Person destination = new Person("Jane Doe", add2);
        ShippingOrder ship = new ShippingOrder(customer, destination);

        assertThrows(OrderException.class, ()-> ship.setStatus(null));
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda para CLOSED com o nº de contentores = 0
     */
    @Test
    void tc36_bva2_testeAlterarEstadoEncomenda() {
        try {
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            ship.setStatus(OrderStatus.IN_TREATMENT);

            assertThrows(OrderException.class, () -> ship.setStatus(OrderStatus.CLOSED));

        } catch (ContainerException | PositionException | OrderException e) {
        }
    }

    /**
     * Teste da funcionalidade alterar estado da encomenda para CLOSED com o nº de contentores = 1
     */
    @Test
    void tc37_bva3_testeAlterarEstadoEncomenda() {
        try {
            Container cont1 = new Container("cont1", 20, 10, 10, 3);
            Item item1 = new Item(2, 2, 2, "item1", "");
            Position pos1 = new Position(1, 1, 1);
            Address add1 = new Address("street A1", 1, "city A1", "state A1", "country A1");
            Address add2 = new Address("street A2", 2, "city A2", "state A2", "country A2");
            Customer customer = new Customer("John Doe", add1, add1);
            Person destination = new Person("Jane Doe", add2);
            ShippingOrder ship = new ShippingOrder(customer, destination);

            cont1.addItem(item1, pos1, Color.fuchsia);
            cont1.close();

            ship.setStatus(OrderStatus.IN_TREATMENT);
            ship.addContainer(cont1);

            assertDoesNotThrow(() -> ship.setStatus(OrderStatus.CLOSED));

        } catch (ContainerException | PositionException | OrderException e) {
        }
    }

    //------------------------------------------------------------------

    /**
     * Teste da funcionalidade validar contentor numa situação válida
     */
    @Test
    void tc38_ecp1_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Item item2 = new Item(1, 1, 1, "item2", "");

        Position pos1 = null;
        Position pos2 = null;
        try {
            pos1 = new Position(0, 0, 0);
            pos2 = new Position(0, 1, 2);
        } catch (PositionException e) {
        }

        try {
            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);
        } catch (ContainerException e) {
        }

        assertDoesNotThrow(() -> cont1.validate());
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida
     */
    @Test
    void tc39_ecp2_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Item item2 = new Item(1, 1, 1, "item2", "");

        Position pos1 = null;
        Position pos2 = null;
        try {
            pos1 = new Position(0, 0, 4);
            pos2 = new Position(0, 1, 2);
        } catch (PositionException e) {
        }

        try {
            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);
        } catch (ContainerException e) {
        }

        assertDoesNotThrow(() -> cont1.validate());
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida
     */
    @Test
    void tc40_ecp3_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Item item2 = new Item(1, 1, 1, "item2", "");

        Position pos1 = null;
        Position pos2 = null;
        try {
            pos1 = new Position(0, 0, 4);
            pos2 = new Position(2, 1, 2);
        } catch (PositionException e) {
        }

        try {
            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);
        } catch (ContainerException e) {
        }

        assertDoesNotThrow(() -> cont1.validate());
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida
     */
    @Test
    void tc41_ecp4_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Item item2 = new Item(1, 1, 1, "item2", "");

        Position pos1 = null;
        Position pos2 = null;
        try {
            pos1 = new Position(5, 0, 4);
            pos2 = new Position(2, 1, 2);
        } catch (PositionException e) {
        }

        try {
            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);
        } catch (ContainerException e) {
        }

        assertDoesNotThrow(() -> cont1.validate());
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida
     */
    @Test
    void tc42_ecp5_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Item item2 = new Item(1, 1, 1, "item2", "");

        Position pos1 = null;
        Position pos2 = null;
        try {
            pos1 = new Position(0, 0, 4);
            pos2 = new Position(0, 2, 2);
        } catch (PositionException e) {
        }

        try {
            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);
        } catch (ContainerException e) {
        }

        assertDoesNotThrow(() -> cont1.validate());
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida
     */
    @Test
    void tc43_ecp6_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Item item2 = new Item(1, 1, 1, "item2", "");

        Position pos1 = null;
        Position pos2 = null;
        try {
            pos1 = new Position(0, 6, 4);
            pos2 = new Position(0, 2, 2);
        } catch (PositionException e) {
        }

        try {
            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);
        } catch (ContainerException e) {
        }

        assertDoesNotThrow(() -> cont1.validate());
    }

    /**
     * Teste da funcionalidade validar contentor numa situação (overflow volume)
     */
    @Test
    void tc44_ecp7_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(10, 10, 10, "item1", "");
        Item item2 = new Item(10, 10, 12, "item2", "");

        Position pos1 = null;
        Position pos2 = null;
        try {
            pos1 = new Position(0, 0, 0);
            pos2 = new Position(10, 0, 0);
        } catch (PositionException e) {
        }

        try {
            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);
        } catch (ContainerException e) {
        }

        assertThrows(ContainerException.class, () -> cont1.validate());
    }

    /**
     * Teste da funcionalidade validar contentor com um item fora do contentor
     */
    @Test
    void tc45_ecp8_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(12, 1, 1, "item1", "");

        Position pos1 = null;

        try {
            pos1 = new Position(10, 0, 0);

            cont1.addItem(item1, pos1, Color.black);

            assertThrows(PositionException.class, () -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor com um item fora do contentor
     */
    @Test
    void tc46_ecp9_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");

        Position pos1 = null;

        try {
            pos1 = new Position(10, 0, 10);

            cont1.addItem(item1, pos1, Color.black);

            assertThrows(PositionException.class, () -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor com um item fora do contentor
     */
    @Test
    void tc47_ecp10_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");

        Position pos1 = null;

        try {
            pos1 = new Position(10, 12, 1);

            cont1.addItem(item1, pos1, Color.black);

            assertThrows(PositionException.class, () -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor com items sobrepostos (overlapping)
     */
    @Test
    void tc48_ecp11_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");
        Item item2 = new Item(2, 1, 4, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(9, 2, 3);
            pos2 = new Position(8, 2, 1);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertThrows(PositionException.class, () -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida mas
     * com items nos limites do contentor
     */
    @Test
    void tc49_bva1_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Position pos1 = null;

        try {
            pos1 = new Position(0, 0, 0);

            cont1.addItem(item1, pos1, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida mas
     * com items nos limites do contentor
     */
    @Test
    void tc50_bva2_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Position pos1 = null;

        try {
            pos1 = new Position(19, 9, 9);

            cont1.addItem(item1, pos1, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida mas
     * com items nos limites do contentor
     */
    @Test
    void tc51_bva3_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Position pos1 = null;

        try {
            pos1 = new Position(0, 9, 0);

            cont1.addItem(item1, pos1, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida mas
     * com items nos limites do contentor
     */
    @Test
    void tc52_bva4_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Position pos1 = null;

        try {
            pos1 = new Position(1, 8, 10);

            cont1.addItem(item1, pos1, Color.black);

            assertThrows(PositionException.class,() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida mas
     * com items nos limites do contentor
     */
    @Test
    void tc53_bva5_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Position pos1 = null;

        try {
            pos1 = new Position(18, 1, 8);

            cont1.addItem(item1, pos1, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida mas
     * com items nos limites do contentor
     */
    @Test
    void tc54_bva6_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Position pos1 = null;

        try {
            pos1 = new Position(19, 10, 10);

            cont1.addItem(item1, pos1, Color.black);

            assertThrows(PositionException.class,() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida com items
     * a partilhar a mesma face (sem espaço entre eles)
     */
    @Test
    void tc55_bva7_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");
        Item item2 = new Item(2, 1, 4, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(9, 2, 3);
            pos2 = new Position(8, 2, 4);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida com items
     * a partilhar a mesma face (sem espaço entre eles)
     */
    @Test
    void tc56_bva8_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");
        Item item2 = new Item(2, 1, 4, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(9, 2, 8);
            pos2 = new Position(8, 2, 4);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida com items
     * a partilhar a mesma face (sem espaço entre eles)
     */
    @Test
    void tc57_bva9_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");
        Item item2 = new Item(2, 1, 4, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(9, 2, 7);
            pos2 = new Position(11, 2, 4);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida com items
     * a partilhar a mesma face (sem espaço entre eles)
     */
    @Test
    void tc58_bva10_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");
        Item item2 = new Item(2, 1, 4, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(9, 2, 7);
            pos2 = new Position(7, 2, 6);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida com items
     * a partilhar a mesma face (sem espaço entre eles)
     */
    @Test
    void tc59_bva11_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");
        Item item2 = new Item(2, 1, 4, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(9, 2, 7);
            pos2 = new Position(6, 3, 6);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor numa situação válida com items
     * a partilhar a mesma face (sem espaço entre eles)
     */
    @Test
    void tc60_bva12_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");
        Item item2 = new Item(2, 1, 4, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(9, 4, 7);
            pos2 = new Position(6, 3, 6);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor (volume total de itens MIN = 0)
     */
    @Test
    void tc61_bva13_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        assertDoesNotThrow(() -> cont1.validate());
    }

    /**
     * Teste da funcionalidade validar contentor (volume total de itens MIN + 1 = 1)
     */
    @Test
    void tc62_bva14_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(1, 1, 1, "item1", "");
        Position pos1 = null;

        try {
            pos1 = new Position(9, 4, 7);

            cont1.addItem(item1, pos1, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor (volume total de itens MAX - 1 = 3)
     */
    @Test
    void tc63_bva15_testeValidarContentor() {
        Container cont1 = new Container("cont1", 4, 1, 1, 3);
        Item item1 = new Item(2, 1, 1, "item1", "");
        Item item2 = new Item(1, 1, 1, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(0,0 , 0);
            pos2 = new Position(2, 0, 0);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor (volume total de itens MAX = 2000)
     */
    @Test
    void tc64_bva16_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(20, 10, 10, "item1", "");
        Position pos1 = null;

        try {
            pos1 = new Position(0, 0, 0);

            cont1.addItem(item1, pos1, Color.black);

            assertDoesNotThrow(() -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

    /**
     * Teste da funcionalidade validar contentor (volume total de itens MAX + 1 = 2001)
     */
    @Test
    void tc65_bva17_testeValidarContentor() {
        Container cont1 = new Container("cont1", 20, 10, 10, 3);
        Item item1 = new Item(20, 10, 10, "item1", "");
        Item item2 = new Item(1, 1, 1, "item2", "");
        Position pos1 = null;
        Position pos2 = null;

        try {
            pos1 = new Position(0, 0, 0);
            pos2 = new Position(19, 9, 9);

            cont1.addItem(item1, pos1, Color.black);
            cont1.addItem(item2, pos2, Color.black);

            assertThrows(ContainerException.class, () -> cont1.validate());

        } catch (ContainerException | PositionException e) {}
    }

}



