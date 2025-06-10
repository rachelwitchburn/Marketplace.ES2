package com.marketplace;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.marketplace.utils.InputUtil;
import com.marketplace.model.Buyer;
import com.marketplace.model.Store;
import com.marketplace.model.Admin;
import com.marketplace.model.Product;
import com.marketplace.Enum.ProductType;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarketplaceFacade marketplaceFacade = new MarketplaceFacade();

        System.out.println("\n------ BEM VINDO AO PLACEMKT ------\n");

        Object user = null;

        while (true) {
            System.out.println("1. Fazer login");
            System.out.println("2. Criar conta");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    user = handleLogin(scanner, marketplaceFacade);
                    break;
                case "2":
                    handleRegister(scanner, marketplaceFacade);
                    break;
                case "0":
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }

            if (user != null) {
                if (user instanceof Admin) {
                    adminsMenu(scanner, marketplaceFacade, (Admin) user);
                } else if (user instanceof Store) {
                    storeView storeView = new storeView();
                    storeView.showStoreMenu(scanner, marketplaceFacade, (Store) user);
                } else if (user instanceof Buyer) {
                    buyerView buyerView = new buyerView();
                    buyerView.showBuyerMenu(scanner, marketplaceFacade, (Buyer) user);
                }
            }
        }
    }

    private static Object handleLogin(Scanner scanner, MarketplaceFacade marketplaceFacade) {
        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        Object user = marketplaceFacade.login(email, password);
        if (user == null) {
            System.out.println("Login inválido. Tente novamente.");
        } else {
            System.out.println("Login realizado com sucesso!");
        }
        return user;
    }

    private static void handleRegister(Scanner scanner, MarketplaceFacade marketplaceFacade) {
        System.out.println("Escolha o tipo de conta:");
        System.out.println("1. Comprador");
        System.out.println("2. Loja");
        System.out.print("Opção: ");
        String tipo = scanner.nextLine();

        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        System.out.print("CPF/CNPJ: ");
        String cpfOrCnpj = scanner.nextLine();

        System.out.print("Endereço: ");
        String address = scanner.nextLine();

        switch (tipo) {
            case "1":
                marketplaceFacade.addBuyer(name, email, password, cpfOrCnpj, address);
                System.out.println("Conta de comprador criada com sucesso!");
                break;
            case "2":
                marketplaceFacade.addStore(name, email, password, cpfOrCnpj, address);
                System.out.println("Conta de loja criada com sucesso!");
                break;
            default:
                System.out.println("Tipo inválido.");
        }
    }
    /* 
    private static void BuyersMenu(Scanner scanner, MarketplaceFacade marketplaceFacade, Buyer user) {
        while (true) {
            System.out.println("\n=== Menu Comprador ===");
            System.out.println("1. Buscar produto");
            System.out.println("2. Adicionar produto ao carrinho");
            System.out.println("3. Excluir produto do carrinho");
            System.out.println("4. Ver carrinho");
            System.out.println("5. Comprar um produto do carrinho");
            System.out.println("6. Finalizar compra (comprar todos os produtos)");
            System.out.println("7. Ver histórico de compras");
            System.out.println("8. Avaliar Produto ou Loja");
            System.out.println("9. Sair");

            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o nome do produto: ");
                    String name = scanner.nextLine();

                    List<Product> found = marketplaceFacade.searchProduct(name);

                    if (found.isEmpty()) {
                        System.out.println("Nenhum produto encontrado.");
                    } else {
                        for (Product p : found) {
                            System.out.println("Produto encontrado: " + p.getName() + " - R$ " + p.getValue());
                        }
                    }
                    break;
                case "2":
                    System.out.print("Nome do produto a adicionar: ");
                    String productName = scanner.nextLine();
                    boolean adicionado = marketplaceFacade.addToCart(user, productName);
                    if (adicionado) {
                        System.out.println("Produto adicionado ao carrinho.");
                    } else {
                        System.out.println("Produto indisponível ou não encontrado.");
                    }
                    break;

                case "3":
                    System.out.print("Nome do produto a excluir: ");
                    String excludeProductName = scanner.nextLine();
                    boolean deletado = marketplaceFacade.deleteFromCart(user, excludeProductName);
                    if (deletado) {
                        System.out.println("Produto deletado com sucesso.");
                    } else {
                        System.out.println("Produto indisponível ou não encontrado.");
                    }
                    break;

                case "4":
                    System.out.println("\nCarrinho:");
                    for (Product p : user.getCart()) {
                        System.out.println(p.getName() + " - R$" + p.getValue());
                    }
                    break;

                case "5":
                    System.out.print("Digite o nome do produto para comprar: ");
                    String productToBuy = scanner.nextLine();
                    
                    System.out.print("Usar R$ "+user.getPoints()+",00 de desconto por pontos acumulados? (s/n) ");
                    String pointsUse = scanner.nextLine();
                    
                    int discount = 0;

                    if(pointsUse.equals("s")){
                        discount = user.getPoints();
                        user.setPoints(0);
                    }

                    boolean comprado = marketplaceFacade.buyProduct(user, productToBuy, discount);
                    if (comprado) {
                        System.out.println("Produto comprado com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado no carrinho ou sem estoque.");
                    }
                    break;

                case "6":
                    System.out.print("Usar R$ "+user.getPoints()+",00 de desconto por pontos acumulados? (s/n) ");
                    String BuyAllPointsUse = scanner.nextLine();
                    
                    int BuyAllDiscount = 0;

                    if(BuyAllPointsUse.equals("s")){
                        BuyAllDiscount = user.getPoints();
                        user.setPoints(0);
                    }
                    
                    boolean sucesso = marketplaceFacade.finalizePurchase(user, BuyAllDiscount);

                    if (sucesso) {
                        System.out.println("Compra finalizada com sucesso!");
                    } else {
                        System.out.println("Carrinho vazio ou produtos sem estoque.");
                    }
                    break;

                case "7":
                    System.out.println("\nHistórico de Compras:");
                    marketplaceFacade.getPurchaseHistory(user);
                    for (Product p : user.getPurchaseHistory()) {
                        System.out.println(p.getName() + " - R$" + p.getValue());
                    }
                    break;

                case "8":
                     Buyer buyerToRate = user;

                if (buyerToRate == null) {
                    System.out.println("Erro: Nenhum comprador logado para realizar a avaliação.");
                    break;
                }

                System.out.println("\nO que você deseja avaliar, " + buyerToRate.getName() + "?");
                System.out.println("1. Produto");
                System.out.println("2. Loja");
                System.out.print("Escolha uma opção: ");
                int ratingChoice;
                try {
                    ratingChoice = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite 1 ou 2.");
                    scanner.nextLine();
                    break;
                }

                if (ratingChoice == 1) {
                    System.out.print("Digite o nome do produto que você deseja avaliar: ");
                    String productNameToRate = scanner.nextLine();
                    System.out.print("Digite a nota (1-5): ");
                    int ratingValue;
                    try {
                        ratingValue = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Nota inválida. Por favor, digite um número entre 1 e 5.");
                        scanner.nextLine();
                        break;
                    }
                    System.out.print("Digite um comentário (opcional): ");
                    String comment = scanner.nextLine();

                    try {
                        if (marketplaceFacade.rateProduct(buyerToRate, productNameToRate, ratingValue, comment)) {
                            System.out.println("Produto avaliado com sucesso!");
                        } else {
                            System.out.println("Não foi possível avaliar o produto. Verifique se o produto foi comprado por você ou se o nome está correto.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                } else if (ratingChoice == 2) {
                    System.out.print("Digite o nome da loja que você deseja avaliar: ");
                    String storeNameToRate = scanner.nextLine();
                    System.out.print("Digite a nota (1-5): ");
                    int ratingValue;
                    try {
                        ratingValue = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Nota inválida. Por favor, digite um número entre 1 e 5.");
                        scanner.nextLine();
                        break;
                    }
                    System.out.print("Digite um comentário (opcional): ");
                    String comment = scanner.nextLine();

                    try {
                        if (marketplaceFacade.rateStore(buyerToRate, storeNameToRate, ratingValue, comment)) {
                            System.out.println("Loja avaliada com sucesso!");
                        } else {
                            System.out.println("Não foi possível avaliar a loja. Verifique se algum produto desta loja foi comprado por você ou se o nome está correto.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                } else {
                    System.out.println("Opção inválida.");
                }
                break;

                case "9":
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    */
    /* 
    private static void storesMenu(Scanner scanner, MarketplaceFacade marketplaceFacade, Store user) {
        while (true) {
            System.out.println("\n------ MENU DE LOJAS ------");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            ProductType type = null;
            ProductType updatedType = null;
            int count = 0;

            switch (choice) {
                case 1:
                // Adicionar produto
                String name = "";
                while (name.isBlank()) {
                    System.out.print("Nome do produto: ");
                    name = scanner.nextLine().trim();
                    if (name.isBlank()) {
                        System.out.println("O nome do produto não pode estar vazio.");
                    }
                }

                double productValue = -1.0;
                while (productValue < 0) {
                    System.out.print("Valor do produto: ");
                    String valueInput = scanner.nextLine().replace(",", ".");
                    try {
                        productValue = Double.parseDouble(valueInput);
                        if (productValue <= 0) {
                            System.out.println("O valor do produto não pode ser negativo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido. Digite um número válido.");
                    }
                }

                int quantity = -1;
                while (quantity < 0) {
                    System.out.print("Quantidade do produto: ");
                    String quantityInput = scanner.nextLine();
                    try {
                        quantity = Integer.parseInt(quantityInput);
                        if (quantity <= 0) {
                            System.out.println("A quantidade não pode ser negativa.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Quantidade inválida. Digite um número inteiro.");
                    }
                }

                for (ProductType t : ProductType.values()) {
                    System.out.println("- " + t);
                }
                while (type == null) {
                    System.out.print("Categoria do produto: ");
                    String input = scanner.nextLine().trim().toUpperCase();
                    input = normalizarEntrada(input);
                    try {
                        type = ProductType.valueOf(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Categoria inválida! Tente novamente.");
                    }
                }

                String brand = "";
                while (brand.isBlank()) {
                    System.out.print("Marca do produto: ");
                    brand = scanner.nextLine().trim();
                    if (brand.isBlank()) {
                        System.out.println("A marca do produto não pode estar vazia.");
                    }
                }

                String description = "";
                while (description.isBlank()) {
                    System.out.print("Descrição do produto: ");
                    description = scanner.nextLine().trim();
                    if (description.isBlank()) {
                        System.out.println("A descrição do produto não pode estar vazia.");
                    }
                }

                System.out.println("Lojas disponíveis:");

                List<Store> stores = marketplaceFacade.listStores();
                for (int i = 0; i < stores.size(); i++) {
                    System.out.printf("%d - %s (%s)%n", i + 1, stores.get(i).getName(), stores.get(i).getEmail());
                }

                Store selectedStore = null;
                while (selectedStore == null) {
                    System.out.print("Escolha o número da loja para associar ao produto: ");
                    int storeIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (storeIndex >= 1 && storeIndex <= stores.size()) {
                        selectedStore = stores.get(storeIndex - 1);
                    } else {
                        System.out.println("Índice inválido! Tente novamente.");
                    }
                }

                marketplaceFacade.addProduct(name, productValue, quantity, type, brand, description, selectedStore);
                System.out.println("Produto adicionado com sucesso!");
                break;

                case 2:
                    System.out.println("\nLista de Produtos:");
                    for (Product product : marketplaceFacade.listProducts()) {
                        System.out.println(product);
                    }
                    break;
                case 3:
                    System.out.print("Novo Nome do Produto: ");
                    String updatedName = scanner.nextLine();
                    System.out.print("Novo Valor: ");
                    Float updatedValue = scanner.nextFloat();
                    System.out.println("Nova Quantidade: ");
                    int updatedQuantity = scanner.nextInt();
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
                    System.out.print("Digite o nome do produto a ser removido: ");
                    String productName = scanner.nextLine();
                    marketplaceFacade.deleteProduct(productName);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }*/

    private static void adminsMenu(Scanner scanner, MarketplaceFacade marketplaceFacade, Admin admin) {
        while (true) {
            System.out.println("\n===== MENU DO ADMINISTRADOR =====");
            System.out.println("1. Gerenciar Lojas");
            System.out.println("2. Gerenciar Compradores");
            System.out.println("3. Gerenciar Produtos");
            System.out.println("4. Voltar ao Menu Principal");
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