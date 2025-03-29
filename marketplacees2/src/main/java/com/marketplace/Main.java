package com.marketplace;

import java.util.Scanner;
import com.marketplace.model.Buyer;
import com.marketplace.model.Store;
import com.marketplace.service.AdminService;
import com.marketplace.model.Admin;
import com.marketplace.model.Product;
/*
import com.marketplace.service.BuyerService;
import com.marketplace.service.StoreService;
import com.marketplace.repository.BuyerRepository;
import com.marketplace.repository.StoreRepository;
*/
import com.marketplace.MarketplaceFacade;
import com.marketplace.Enum.ProductType;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarketplaceFacade marketplaceFacade = new MarketplaceFacade();

        // Inicialização dos repositórios e serviços
        /*
        BuyerRepository buyerRepository = new BuyerRepository();
        BuyerService buyerService = new BuyerService(buyerRepository);
        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);
        */

        while (true) {
            System.out.println("\n------ MENU PRINCIPAL ------");
            System.out.println("1. Gerenciar Compradores");
            System.out.println("2. Gerenciar Lojas");
            System.out.println("3. Gerenciar Produtos");
            System.out.println("4. Gerenciar Admins");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (choice) {
                case 1:
                    manageBuyers(scanner, marketplaceFacade);
                    break;
                case 2:
                    manageStores(scanner, marketplaceFacade);
                    break;
                case 3:
                    manageProducts(scanner, marketplaceFacade);
                case 4:
                    manageAdmins(scanner, marketplaceFacade);
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu de gerenciamento de compradores
    private static void manageBuyers(Scanner scanner, MarketplaceFacade marketplaceFacade) {
        while (true) {
            System.out.println("\n------ MENU DE COMPRADORES ------");
            System.out.println("1. Adicionar Comprador");
            System.out.println("2. Listar Compradores");
            System.out.println("3. Atualizar Comprador");
            System.out.println("4. Remover Comprador");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (choice) {
                case 1:
                    // Adicionar comprador
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String password = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String address = scanner.nextLine();

                    //Buyer newBuyer = new Buyer(name, email, password, cpf, address);
                    marketplaceFacade.addBuyer(name, email, password, cpf, address);
                    System.out.println("Comprador adicionado com sucesso!");
                    break;

                case 2:
                // PRECISA AJEITAR O RESTO DOS COMPRADORES
                    // Listar compradores
                    System.out.println("\nLista de Compradores:");
                    for (Buyer buyer : marketplaceFacade.listBuyers()) {
                        System.out.println(buyer);
                    }
                    break;

                case 3:
                    // Atualizar comprador
                    System.out.print("Digite o ID do comprador a ser atualizado: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Novo Nome: ");
                    String newName = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nova Senha: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Novo CPF: ");
                    String newCpf = scanner.nextLine();
                    System.out.print("Novo Endereço: ");
                    String newAddress = scanner.nextLine();

                    marketplaceFacade.updateBuyer(idToUpdate, newEmail, newName, newPassword, newCpf, newAddress);
                    System.out.println("Comprador atualizado com sucesso!");
                    break;

                case 4:
                    // Remover comprador
                    System.out.print("Digite o ID do comprador a ser removido: ");
                    String emailToRemove = scanner.nextLine();
                    marketplaceFacade.deleteBuyer(emailToRemove);
                    System.out.println("Comprador removido com sucesso!");
                    break;

                case 5:
                    // Voltar ao menu principal
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu de gerenciamento de lojas atualizado
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
            scanner.nextLine(); // Limpar buffer

            switch (choice) {
                case 1:
                    // Adicionar loja
                    System.out.print("Nome da Loja: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String password = scanner.nextLine();
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String address = scanner.nextLine();

                    //Store newStore = new Store(name, email, password, cnpj, address);
                    marketplaceFacade.addStore(name, email, password, cnpj, address);
                    System.out.println("Loja adicionada com sucesso!");
                    break;

                case 2:
                    // Listar lojas
                    
                    System.out.println("\nLista de Lojas:");
                    for (Store store : marketplaceFacade.listStores()) {
                        System.out.println(store);
                    }
                    break;

                case 3:
                    // Atualizar loja
                    System.out.print("Digite o ID da loja a ser atualizada: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer

                    System.out.print("Novo Nome da Loja: ");
                    String newName = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nova Senha: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Novo CNPJ: ");
                    String newCnpj = scanner.nextLine();
                    System.out.print("Novo Endereço: ");
                    String newAddress = scanner.nextLine();

                    // Store updatedStore = new Store(newName, newEmail, newPassword, newCnpj, newAddress);
                    marketplaceFacade.updateStore(idToUpdate, newName, newEmail, newPassword, newCnpj, newAddress);
                    System.out.println("Loja atualizada com sucesso!");
                    break; 
                    // Setar o ID correto
                    /*
                    if (marketplaceFacade.updateStore(updatedStore)) {
                        System.out.println("Loja atualizada com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar loja.");
                    }
                        */
                    

                case 4:
                    // Remover loja
                    System.out.print("Digite o nome da loja a ser removida: ");
                    String nameToRemove = scanner.nextLine();
                    marketplaceFacade.deleteStore(nameToRemove);
                    /*
                    if (storeService.removeStore(idToRemove)) {
                        System.out.println("Loja removida com sucesso!");
                    } else {
                        System.out.println("Erro ao remover loja.");
                    }
                        */
                    break;

                case 5:
                    // Voltar ao menu principal
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
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            ProductType type = null;
            ProductType updatedType = null;
            int count = 0;

            switch (choice) {
                 case 1:
                    // Adicionar loja
                    System.out.print("Nome do produto: ");
                    String name = scanner.nextLine();
                    System.out.print("Valor do produto: ");
                    Float value = scanner.nextFloat();
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
                    System.out.print("Marca do produto: ");
                    String brand = scanner.nextLine();
                    System.out.print("Descrição do produto: ");
                    String description = scanner.nextLine();
                    marketplaceFacade.addProduct(name, value, type, brand, description);
 
                    System.out.println("Produto adicionado com sucesso!");
                    break;
                case 2:
                    // Listar produtos
                    System.out.println("\nLista de Produtos:");
                    for (Product product : marketplaceFacade.listProducts()) {
                        System.out.println(product); // Usa o toString do Product
                    }
                    break;
                case 3:
                    // Atualizar produto
                    System.out.print("Digite o ID do pruduto a ser atualizado: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Novo Nome do Produto: ");
 
                    String newName = scanner.nextLine();
 
                    System.out.print("Novo Valor: ");
 
                    Double newValue = scanner.nextDouble();
 
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
                    String newBrand = scanner.nextLine();
                    System.out.print("Nova Descrição: ");
                    String newDescription = scanner.nextLine();

                    marketplaceFacade.updateProduct(newName, newValue, updatedType, newBrand, newDescription);
                    System.out.println("Produto atualizado com sucesso!");
                    break;
                case 4:
                    // Remover produto
                    System.out.print("Digite o nome do produto a ser removido: ");
                    String productName = scanner.nextLine();
                    marketplaceFacade.deleteProduct(productName);
                    break;
                case 5:
                    // Voltar ao menu principal
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu de gerenciamento de administradores
    private static void manageAdmins(Scanner scanner, MarketplaceFacade marketplaceFacade) {
        while (true) {
            System.out.println("\n------ MENU DE ADMINISTRADOR ------");
            System.out.println("1. Adicionar Administrador");
            System.out.println("2. Listar Administradores");
            System.out.println("3. Atualizar Administrador");
            System.out.println("4. Remover Administrador");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            switch (choice) {
                case 1:
                    // Adicionar loja
                    System.out.print("Nome do Administrador: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String password = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String address = scanner.nextLine();
                    marketplaceFacade.addAdmin(name, email, password, cpf, address);;
                    System.out.println("Administrador adicionado com sucesso!");
                    break;
                case 2:
                    // Listar administradores
                    System.out.println("\nLista de Administradores:");
                    for (Admin admin : marketplaceFacade.listAdmins()) {
                        System.out.println(admin); // Usa o toString do Admin
                    }
                    break;
                case 3:
                    // Atualizar administradores
                    System.out.print("Digite o ID do administrador a ser atualizado: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Novo Nome do Administrador: ");
                    String newName = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nova Senha: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Novo CPF: ");
                    String newCpf = scanner.nextLine();
                    System.out.print("Novo Endereço: ");
                    String newAddress = scanner.nextLine();
                    marketplaceFacade.updateAdmin(idToUpdate, newEmail, newName, newPassword, newCpf, newAddress);
                    System.out.println("Administrador atualizado com sucesso!");
                    break;
                case 4:
                    // Remover administrador
                    System.out.print("Digite o ID do administrador a ser removido: ");
                    String emailToRemove = scanner.nextLine();
                    marketplaceFacade.deleteAdmin(emailToRemove);
                    System.out.println("Administrador removido com sucesso!");
                    break;
                case 5:
                    // Voltar ao menu principal
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