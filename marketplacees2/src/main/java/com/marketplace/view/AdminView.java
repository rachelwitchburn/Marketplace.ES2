package com.marketplace.view;

import java.util.List;
import java.util.Scanner;

import com.marketplace.Enum.ProductType;
import com.marketplace.facade.MarketplaceFacade;
import com.marketplace.model.Admin;
import com.marketplace.model.Buyer;
import com.marketplace.model.Product;
import com.marketplace.model.Store;
import com.marketplace.utils.InputUtil;

public class AdminView {
    public void showAdminMenu(Scanner scanner, MarketplaceFacade marketplaceFacade, Admin admin) {
        while (true) {
            System.out.println("\n===== MENU DO ADMINISTRADOR =====");
            System.out.println("1. Gerenciar Lojas");
            System.out.println("2. Gerenciar Compradores");
            System.out.println("3. Gerenciar Produtos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageStores(scanner, marketplaceFacade);
                    break;
                case 2:
                    manageBuyers(scanner, marketplaceFacade);
                    break;
                case 3:
                    manageProducts(scanner, marketplaceFacade);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void manageStores(Scanner scanner, MarketplaceFacade marketplaceFacade) {
        while (true) {
            System.out.println("\n------ MENU DE LOJAS ------");
            System.out.println("1. Adicionar Loja");
            System.out.println("2. Listar Lojas");
            System.out.println("3. Atualizar Loja");
            System.out.println("4. Remover Loja");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nome da Loja: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    String password = InputUtil.readPassword("Senha: ");
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String address = scanner.nextLine();

                    marketplaceFacade.addStore(name, email, password, cnpj, address);
                    System.out.println("Loja adicionada com sucesso!");
                    break;

                case 2:

                    System.out.println("\nLista de Lojas:");
                    for (Store store : marketplaceFacade.listStores()) {
                        System.out.println(store);
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID da loja a ser atualizada: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo Nome da Loja: ");
                    String newName = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String newEmail = scanner.nextLine();
                    String newPassword = InputUtil.readPassword("Senha: ");
                    System.out.print("Novo CNPJ: ");
                    String newCnpj = scanner.nextLine();
                    System.out.print("Novo Endereço: ");
                    String newAddress = scanner.nextLine();

                    marketplaceFacade.updateStore(idToUpdate, newName, newEmail, newPassword, newCnpj, newAddress);
                    System.out.println("Loja atualizada com sucesso!");
                    break;

                case 4:
                    System.out.print("Digite o nome da loja a ser removida: ");
                    String nameToRemove = scanner.nextLine();
                    marketplaceFacade.deleteStore(nameToRemove);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


    private static void manageProducts(Scanner scanner, MarketplaceFacade marketplaceFacade) {
        while (true) {
            System.out.println("\n------ MENU DE PRODUTOS ------");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("5. Voltar");

            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            ProductType type = null;
            ProductType updatedType = null;
            int count = 0;

            switch (choice) {
                case 1:
                    System.out.print("Nome do Produto: ");
                    String name = scanner.nextLine();
                    System.out.print("Valor: ");
                    Float value = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    for (ProductType t : ProductType.values()) {
                        System.out.println("\n- " + t);
                    }
                    while (type == null) {
                        System.out.print("Categoria do produto: ");
                        String input = scanner.nextLine().trim().toUpperCase();

                        input = normalizarEntrada(input);
                        try {
                            type = ProductType.valueOf(input);
                            System.out.println("Categoria selecionada: " + type);
                        } catch (IllegalArgumentException e) {
                            if (count != 0) {
                                System.out.println("Categoria inválida! Tente novamente.");
                            }
                        }
                        count++;
                    }
                    System.out.print("Marca: ");
                    String brand = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String description = scanner.nextLine();

                    List<Store> stores = marketplaceFacade.listStores();
                    if (stores.isEmpty()) {
                        System.out.println("Nenhuma loja cadastrada. Cadastre uma loja primeiro.");
                        break;
                    }

                    System.out.println("Selecione uma loja para associar ao produto:");
                    for (int i = 0; i < stores.size(); i++) {
                        System.out.printf("%d - %s (%s)%n", i + 1, stores.get(i).getName(), stores.get(i).getEmail());
                    }

                    Store selectedStore = null;
                    while (selectedStore == null) {
                        System.out.print("Número da loja: ");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        if (index >= 1 && index <= stores.size()) {
                            selectedStore = stores.get(index - 1);
                        } else {
                            System.out.println("Índice inválido. Tente novamente.");
                        }
                    }

                marketplaceFacade.addProduct(name, value, quantity, type, brand, description, selectedStore);
                System.out.println("Produto adicionado com sucesso!");
                break;

                case 2:
                    for (Product product : marketplaceFacade.listProducts()) {
                        System.out.println(product);
                    }
                    break;

                case 3:
                    System.out.print("Novo Nome: ");
                    String updatedName = scanner.nextLine();
                    System.out.print("Novo Valor: ");
                    Float updatedValue = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.print("Nova Quantidade: ");
                    int updatedQuantity = scanner.nextInt();
                    scanner.nextLine();
                    count = 0;
                    for (ProductType t : ProductType.values()) {
                        System.out.println("\n- " + t);

                    }
                    while (updatedType == null) {
                        System.out.print("Categoria do produto: ");
                        String input = scanner.nextLine().trim().toUpperCase();
                        input = normalizarEntrada(input);
                        try {
                            updatedType = ProductType.valueOf(input);
                            System.out.println("Categoria selecionada: " + updatedType);
                        } catch (IllegalArgumentException e) {
                            if (count != 0) {
                                System.out.println("Categoria inválida! Tente novamente.");
                            }
                        }
                        count++;
                    }
                    System.out.print("Nova Marca: ");
                    String updatedBrand = scanner.nextLine();
                    System.out.print("Nova Descrição: ");
                    String updatedDescription = scanner.nextLine();

                    marketplaceFacade.updateProduct(updatedName, updatedValue, updatedQuantity, updatedType, updatedBrand, updatedDescription);
                    System.out.println("Produto atualizado com sucesso!");
                    break;

                case 4:
                    System.out.print("Nome do produto a remover: ");
                    String productNameToRemove = scanner.nextLine();
                    marketplaceFacade.deleteProduct(productNameToRemove);
                    System.out.println("Produto removido com sucesso!");
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void manageBuyers(Scanner scanner, MarketplaceFacade marketplaceFacade) {
        while (true) {
            System.out.println("\n------ MENU DE COMPRADORES ------");
            System.out.println("1. Adicionar Comprador");
            System.out.println("2. Listar Compradores");
            System.out.println("3. Atualizar Comprador");
            System.out.println("4. Remover Comprador");
            System.out.println("5. Voltar");

            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    String password = InputUtil.readPassword("Senha: ");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String address = scanner.nextLine();

                    marketplaceFacade.addBuyer(name, email, password, cpf, address);
                    System.out.println("Comprador adicionado com sucesso!");
                    break;

                case 2:
                    for (Buyer buyer : marketplaceFacade.listBuyers()) {
                        System.out.println(buyer);
                    }
                    break;

                case 3:
                    System.out.print("ID do comprador a atualizar: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo Nome: ");
                    String updatedName = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String updatedEmail = scanner.nextLine();
                    String updatedPassword = InputUtil.readPassword("Senha: ");
                    System.out.print("Novo CPF: ");
                    String updatedCpf = scanner.nextLine();
                    System.out.print("Novo Endereço: ");
                    String updatedAddress = scanner.nextLine();

                    marketplaceFacade.updateBuyer(idToUpdate, updatedName, updatedEmail, updatedPassword, updatedCpf, updatedAddress);
                    System.out.println("Comprador atualizado com sucesso!");
                    break;

                case 4:
                    System.out.print("Nome do comprador a remover: ");
                    String nameToRemove = scanner.nextLine();
                    marketplaceFacade.deleteBuyer(nameToRemove);
                    System.out.println("Comprador removido com sucesso!");
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static String normalizarEntrada(String input) {
        return input

                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace("Â", "A")
                .replace("Ê", "E")
                .replace("Î", "I")
                .replace("Ô", "O")
                .replace("Û", "U")
                .replace("Ã", "A")
                .replace("Õ", "O")
                .replace("Ç", "C");
    }
}
