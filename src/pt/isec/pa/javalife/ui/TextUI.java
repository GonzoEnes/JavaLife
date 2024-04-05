package pt.isec.pa.javalife.ui;

import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.utils.PAInput;

public class TextUI {
    private final JavaLifeContext context;

    public TextUI(JavaLifeContext context) { // está a correr sem dar erro, falta testar as funções :))))
        this.context = context;
    }


    /*public void start() {
        int res;
        do {
            System.out.println("\n\n**** JavaLife ****\n");
            System.out.println("Estado atual: "+context.getState());
            System.out.println();;
            res = PAInput.chooseOption("Ações", "Adicionar elemento", "Remover elemento", "Editar elemento");
            switch (res){
                case 1->context.abrir(PAInput.readString("Código: ", false));
                case 2->fsm.fechar();
                case 3->fsm.desbloquear(PAInput.readString("PUK: ", false));
                case 4->fsm.depositar(PAInput.readNumber("Valor: "));
                case 5->fsm.levantar(PAInput.readNumber(String.format("Valor [max. %.2f]: ",fsm.consultar())));
                case 6->System.out.printf("Saldo: %.2f", fsm.consultar());
            }
        }while (res!=7);
    }*/
}
