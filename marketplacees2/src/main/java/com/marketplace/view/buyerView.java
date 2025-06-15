package com.marketplace.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.marketplace.facade.MarketplaceFacade;
import com.marketplace.model.Buyer;
import com.marketplace.model.Product;

public class buyerView {

    private Scanner scanner;
    private MarketplaceFacade marketplaceFacade;
    private Buyer user;

    public buyerView(Scanner scanner, MarketplaceFacade marketplaceFacade, Buyer user){
        this.scanner = scanner;
        this.marketplaceFacade = marketplaceFacade;
        this.user = user;
    }

    public void showBuyerMenu() {
        while (true) {
            System.out.println("\n=== Menu Comprador ===");
            System.out.println("1. Listar produtos");
            System.out.println("2. Buscar produto");
            System.out.println("3. Adicionar produto ao carrinho");
            System.out.println("4. Excluir produto do carrinho");
            System.out.println("5. Ver carrinho");
            System.out.println("6. Comprar um produto do carrinho");
            System.out.println("7. Finalizar compra (comprar todos os produtos)");
            System.out.println("8. Ver histórico de compras");
            System.out.println("9. Avaliar Produto ou Loja");
            System.out.println("0. Sair");

            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            System.out.flush();

            switch (opcao) {
                case "1":
                    listProducts();
                    break;
                case "2":
                    searchProduct();
                    break;
                case "3":
                    addToCart();
                    break;
                case "4":
                    deleteFromCart();
                    break;
                case "5":
                    showCart();
                    break;
                case "6":
                    buyCartProduct();
                    break;
                case "7":
                    finalizePurchase();
                    break;
                case "8":
                    purchaseHistory();
                    break;
                case "9":
                    rate();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listProducts(){
        for (Product product : marketplaceFacade.listProducts()) {
            System.out.println(product);
        }
    }

    private void searchProduct(){
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
    }

    private void addToCart(){
        System.out.print("Nome do produto a adicionar: ");
        String productName = scanner.nextLine();
        boolean adicionado = marketplaceFacade.addToCart(user, productName);
        if (adicionado) {
            System.out.println("Produto adicionado ao carrinho.");
        } else {
            System.out.println("Produto indisponível ou não encontrado.");
        }
    }

    private void deleteFromCart(){
        System.out.print("Nome do produto a excluir: ");
        String excludeProductName = scanner.nextLine();
        boolean deletado = marketplaceFacade.deleteFromCart(user, excludeProductName);
        if (deletado) {
            System.out.println("Produto deletado com sucesso.");
        } else {
            System.out.println("Produto indisponível ou não encontrado.");
        }
    }

    private void showCart(){
        System.out.println("\nCarrinho:");
        for (Product p : user.getCart()) {
            System.out.println(p.getName() + " - R$" + p.getValue());
        }
    }

    private void buyCartProduct(){
        System.out.print("Digite o nome do produto para comprar: ");
        String productToBuy = scanner.nextLine();
        
        int discount = 0;

        if(user.getPoints() != 0){
            System.out.print("Usar R$ "+user.getPoints()+",00 de desconto por pontos acumulados? (s/n) ");
            String pointsUse = scanner.nextLine();

            if(pointsUse.equals("s")){
                discount = user.getPoints();
                user.setPoints(0);
            }
        }

        boolean comprado = marketplaceFacade.buyProduct(user, productToBuy, discount);
        if (comprado) {
            System.out.println("Produto comprado com sucesso!");
        } else {
            System.out.println("Produto não encontrado no carrinho ou sem estoque.");
        }
    }

    private void finalizePurchase(){
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
    }

    private void purchaseHistory(){
        System.out.println("\nHistórico de Compras:");
        marketplaceFacade.getPurchaseHistory(user);
        for (Product p : user.getPurchaseHistory()) {
            System.out.println(p.getName() + " - R$" + p.getValue());
        }
    }

    private void rate(){
        Buyer buyerToRate = user;

        if (buyerToRate == null) {
            System.out.println("Erro: Nenhum comprador logado para realizar a avaliação.");
            return;
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
            return;
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
                return;
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
                return;
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
    }
}
