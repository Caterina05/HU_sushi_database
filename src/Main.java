import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database db = null;
        try {
            db = Database.getInstance();
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database: " + e.getMessage());
            System.exit(-1);
        }

        Scanner scanner = new Scanner(System.in);
        int scelta;

        do{
            System.out.println("\nMENU");
            System.out.println("1. Inserisci un nuovo piatto");
            System.out.println("2. Visualizza tutti i piatti");
            System.out.println("3. Aggiorna un piatto");
            System.out.println("4. Elimina un piatto");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Inserisci un numero valido: ");
                scanner.next();
            }

            scelta = scanner.nextInt();
            scanner.nextLine(); // per svuotare buffer

            switch (scelta) {
                case 1:
                    System.out.println("Inserisci il nome del piatto da inserire: ");
                    String nomePiatto = scanner.nextLine();
                    System.out.println("Inserisci il prezzo del piatto da inserire: ");
                    float prezzo = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Inserisci la quantità del piatto da inserire: ");
                    int quantita = scanner.nextInt();
                    scanner.nextLine();

                    if(db.insert(nomePiatto, prezzo, quantita))
                        System.out.println("Piatto inserito con successo");
                    else
                        System.out.println("Errore durante l'inserimento");
                    break;
                case 2:
                    System.out.println("\nMENU ATTUALE");
                    System.out.println(db.selectAll());
                    break;
                case 3:
                    System.out.println("Inserisci l'ID del piatto da aggiornare: ");
                    int idAggiorna = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci il nuovo nome del piatto da aggiornare: ");
                    String nuovoNome = scanner.nextLine();
                    System.out.println("Inserisci il nuovo prezzo del piatto da aggiornare: ");
                    float nuovoPrezzo = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Inserisci la nuova quantità del piatto da aggiornare: ");
                    int nuovaQuantita = scanner.nextInt();
                    scanner.nextLine();

                    if(db.update(idAggiorna, nuovoNome, nuovoPrezzo, nuovaQuantita))
                        System.out.println("Piatto aggiornato con successo");
                    else
                        System.out.println("Errore durante l'aggiornamento");
                    break;
                case 4:
                    System.out.println("Inserisci l'ID del piatto da eliminare: ");
                    int idElimina = scanner.nextInt();
                    scanner.nextLine();
                    if(db.delete(idElimina))
                        System.out.println("Piatto eliminato successo");
                    else
                        System.out.println("Errore durante l'eliminazione");
                    break;
                case 0:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Scelta invalida, riprova");
            }
        } while (scelta != 0);
    }
}
