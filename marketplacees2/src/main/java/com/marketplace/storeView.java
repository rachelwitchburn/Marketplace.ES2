package com.marketplace;

import java.util.List;
import java.util.Scanner;

import com.marketplace.Enum.ProductType;
import com.marketplace.model.Product;
import com.marketplace.model.Store;

public class storeView {
    public void showStoreMenu(Scanner scanner, MarketplaceFacade marketplaceFacade, Store user) {
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
