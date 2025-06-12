package com.marketplace;

import java.util.Scanner;
import com.marketplace.model.Buyer;
import com.marketplace.model.Store;
import com.marketplace.view.AdminView;
import com.marketplace.view.buyerView;
import com.marketplace.view.storeView;
import com.marketplace.facade.MarketplaceFacade;
import com.marketplace.model.Admin;

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
                    AdminView adminView = new AdminView();
                    adminView.showAdminMenu(scanner, marketplaceFacade, (Admin) user);
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
        System.out.println("0. Sair");
        System.out.print("Opção: ");
        String tipo = scanner.nextLine();

        switch (tipo){
            case "1":
                break;
            case "2":
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

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
}